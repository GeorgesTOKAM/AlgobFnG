package com.LouisAdrGeo.AlgobFnG;

public class Bruteforce {
	
	String cand, ltpos = "";;
	int pos;
	
	public String brtforce (String P, String T) {
		
		for ( pos = 0; pos <= T.length() - P.length(); pos++ ){			
	 	    cand = T.substring( pos, pos + P.length() );  // Try to match this...
	 	    if ( P.equals(cand) ){ 	
	 	    	ltpos += String.valueOf(pos) + "\n";	
	 	    }
	 	}
		return ltpos;
	}
}
