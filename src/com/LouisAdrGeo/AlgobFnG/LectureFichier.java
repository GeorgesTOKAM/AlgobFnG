package com.LouisAdrGeo.AlgobFnG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LectureFichier {
	
	public static String LectureFichier(String f) throws FileNotFoundException {
		
		Scanner sc = null;		
		StringBuilder sb = new StringBuilder();
		
		try {
			sc = new Scanner(new BufferedReader( new FileReader(new File (f))));
			while (sc.hasNext()){
				sb.append(" "+sc.next());
			}		
		} 
		finally{
			if (sc!= null){	
				sc.close();
			}		
		}
		return sb.toString();		
	}

}
