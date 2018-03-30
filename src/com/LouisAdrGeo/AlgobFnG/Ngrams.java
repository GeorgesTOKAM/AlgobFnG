package com.LouisAdrGeo.AlgobFnG;

import java.util.ArrayList;

public class Ngrams {

	public ArrayList <String> ngrams(int n, String textes){
		
		String [] sequenceMot = textes.split("\\s+");
		ArrayList <String> listeNgramme = new ArrayList <String>();
		//generation de N-gramme
		for(int k=0;k <= (sequenceMot.length-n); k++){
			String s = " ";
			int debut = k;
			int fin   = k+n;
			for(int j=debut; j<fin; j++){
				s = s+ " " + sequenceMot[j];
			}
			listeNgramme.add(s);
		}
		 return listeNgramme;
	}
}
