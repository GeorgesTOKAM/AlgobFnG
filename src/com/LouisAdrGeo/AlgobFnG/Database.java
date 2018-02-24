package com.LouisAdrGeo.AlgobFnG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class Database {
	
	private String DBPath = "DBFile.db";
    private Connection con = null;
    private Statement  stm = null;
    private String stmots ="";
    private String stphrases ="";
    
    public void connect() {
    	try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            stm = con.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }
    
    public void close() {
        try {
            con.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Permet de faire une requête SQL
     * @param requete La requête SQL (avec un ";" à la fin)
     * @return Un ResultSet contenant le résultat de la requête
     */
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = stm.executeQuery(requet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + requet);
        }
        return resultat;
  
    }
    
    public String AllMots() {
    	stmots ="";
    	ResultSet r = query("SELECT Mots FROM MOTS");
    	try {
			ResultSetMetaData rsmd = r.getMetaData();			
			int columnsNumber = rsmd.getColumnCount();
			while (r.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					stmots += r.getString(i) + ";" ;
					}
				}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stmots;
    }
    
    public String AllPhrase() {
    	stphrases = "";
    	ResultSet r = query("SELECT Phrases FROM PHRASES");
    	try {
			ResultSetMetaData rsmd = r.getMetaData();			
			int columnsNumber = rsmd.getColumnCount();
			while (r.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					stphrases += r.getString(i) + ";" ;
					}
				}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stphrases;
    }
    
    public void insertMots (MotsClass mot){
        try {
            PreparedStatement p = con.prepareStatement("INSERT INTO MOTS (MOTS) VALUES(?)");
            p.setString(1, mot.getMots());
            p.execute();
            System.out.println("Le mot est ajouté ");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insertPhrases (PhrasesClass phrase){
        try{
            PreparedStatement p = con.prepareStatement("INSERT INTO PHRASES (PHRASES) VALUES(?)");
            p.setString(1, phrase.getPhrases());
            p.execute();
            System.out.println("La phrase est ajouté ");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
	
}
