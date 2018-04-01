package com.LouisAdrGeo.AlgobFnG;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
//import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Enumeration;
import java.util.List;


public class Main extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("Ajouter mot");
	static Database db = new Database();
	static String  lstmot = "" ;
	String  allworddb = "" ;
	String data = "";
	List<String> allword = new ArrayList<>();
	
    public Main() {
    	
        initComponents();
        
    }
    
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.add(new FormPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);              
        btnclear.addActionListener(this);                
        btnsend.addActionListener(this); 
        btnsend.addActionListener(new sendListener());
        brutf.addActionListener(new brutforcListener());
        ngrams.addActionListener(new ngramsListener());
        tfmsg.addActionListener(new MyTextActionListener());
        tfmsg.getDocument().addDocumentListener(new MyDocumentListener());
        tfmsg.getDocument().putProperty("name", "Text Field");
        tfmsg.requestFocusInWindow();
        tavword.getDocument().addDocumentListener(new MyDocumentListener());
        tavword.getDocument().putProperty("name", "Text Area");
        tadisplay.setEditable(false);
        brutf.setSelected(true);
        try {
			data = new String(LectureFichier.LectureFichier("test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.connect();
        allworddb = db.AllMots();
        
        
    }
    
    public class brutforcListener implements ActionListener{
        // TODO Desactivatin ngrams ratio
        @Override
        public void actionPerformed ( ActionEvent event ){
        	ngrams.setSelected(false);
        	brutf.setSelected(true);
        }
    }
    public class ngramsListener implements ActionListener{
        // TODO Desactivatin brute force ratio
        @Override
        public void actionPerformed ( ActionEvent event ){
        	ngrams.setSelected(true);
        	brutf.setSelected(false);
        }
    }
    public class sendListener implements ActionListener{
        // TODO Ajout da la db
        @Override
        public void actionPerformed ( ActionEvent event ){
        	if(tfmsg.getText().isEmpty()) {
        		// TODO gere insertion vide
        		System.out.println("Erreur champ vide");
        	}
        	else {
        		//PhrasesClass ph = new PhrasesClass();
        		MotsClass mt = new MotsClass();
        		MotsClass mtdb = new MotsClass();
        		
        		mt.setMots(tfmsg.getText());
        		String listMots[]  = mt.getMots().split(",");
        		
        		
        		for(int i = 0 ; i < listMots.length ; i++)
        		{
        			mtdb.setMots(listMots[i]);
        			db.insertMots(mtdb);       		   
        		}
        		tfmsg.setText("");
        		
        	}
        }
    }
    
    

    /*
     * Realization of the action on JTextField tfmsg relative in document
     */
    class MyDocumentListener implements DocumentListener {
    	// TODO implement the method MyDocumentListener
        final String newline = "\n";

        public void insertUpdate(DocumentEvent e) {
            updateLog(e);
        }
        public void removeUpdate(DocumentEvent e) {
            updateLog(e);
        }
        public void changedUpdate(DocumentEvent e) {
            //Plain text components don't fire these events.
        }
        public void updateLog(DocumentEvent e) {
            // TODO call brute force
        	String intf = tfmsg.getText();
        	tadisplay.setText("");
        	if(intf.isEmpty()) {
        		tadisplay.setText("");
        	}
        	else {
        		if(brutf.isSelected()) {
        			// TODO implement Bruteforce
        			String listpos = new Bruteforce().brtforce(intf, data) ;
                	tadisplay.setText(listpos);
        		}
        		else {
        			// TODO implement ngrams
        			long start = System.currentTimeMillis();
        			allword = TrouverMot.RechercherMot(data,intf);
        			long end = System.currentTimeMillis();
					for(Object o:allword) {
						tadisplay.append(o + "\n");
					}
					System.out.println("Taille mot : " + intf.length() + " - " + "Taille Dico : " 
							+ data.length() + " - Temps d'execution = " + (end-start) + " ms"
							+ " - Taille memoire : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        		}
        		
        	}
        }
    }
    
    /*
     * Realization of the action on JTextField tfmsg
     */
    class MyTextActionListener implements ActionListener {
        /** Handle the text field Return. */
    	// TODO implement the method MyTextActionListener
        public void actionPerformed(ActionEvent e) {
        	
            int selStart = tavword.getSelectionStart();
            int selEnd = tavword.getSelectionEnd();
            tavword.replaceRange(tfmsg.getText(),selStart, selEnd);
            tfmsg.selectAll();
        }
    }
    
    /** Handle button click. */
    public void actionPerformed(ActionEvent e) {
        tadisplay.setText("");
        tfmsg.requestFocus();        
    }
    

    public class FormPane extends JPanel {

		private static final long serialVersionUID = 1L;

		public FormPane() {
            setBorder(new EmptyBorder(8, 8, 8, 8));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 2, 2, 2); // marge entre composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;

            DescPane descPane = new DescPane();
            descPane.setBorder(new CompoundBorder(new TitledBorder("Algorithme Brute force et n-grams"), new EmptyBorder(4, 4, 4, 4)));
            add(descPane, gbc);
      
        }

    }

    

    protected class DescPane    extends JPanel {
    	
		private static final long serialVersionUID = 1L;

		public DescPane() {
            JPanel detailsPane = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 2, 2, 2);
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            detailsPane.add(tadisplay, gbc);
            
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            detailsPane.add(tavword, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            detailsPane.add(tfmsg, gbc);

            
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.BASELINE_LEADING;
            gbc.fill = GridBagConstraints.NONE;
            detailsPane.add(brutf, gbc);
            
            gbc.gridx = 1;
            gbc.weightx = 1; 
            detailsPane.add(ngrams, gbc);
            
            
            gbc.gridx = 2; 
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            gbc.weightx = 1.;
            gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
            gbc.insets = new Insets(0, 0, 0, 0);
            detailsPane.add(btnsend, gbc);            
            
            gbc.gridx = 3;
            gbc.weightx = 0.;
            detailsPane.add(btnclear, gbc);

            setLayout(new BorderLayout());
            add(detailsPane);
            add(detailsPane, BorderLayout.LINE_END);
                    
        }            
    } 
           
     
 // TODO Main Program
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException ex) {} 
        catch (IllegalAccessException ex) {} 
        catch (UnsupportedLookAndFeelException ex) {}

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	@SuppressWarnings("unused")
				Main fom = new Main();
            }
        });
    }
    

    private JTextField tfmsg= new JTextField();
	private JTextArea tavword= new JTextArea(5,40);
	private TextArea tadisplay = new TextArea(15,40);
	private JButton btnclear = new JButton("Clear");
	private JButton btnsend = new JButton("Send");
	private JRadioButton brutf = new JRadioButton(" Brute Force");
	private JRadioButton ngrams = new JRadioButton(" N-grams");

}
