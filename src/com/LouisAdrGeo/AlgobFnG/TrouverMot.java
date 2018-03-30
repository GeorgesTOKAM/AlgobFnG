package com.LouisAdrGeo.AlgobFnG;

import java.util.*;
import java.util.Map.Entry;


public class TrouverMot {
	
	public static List<String> RechercherMot(String texte, String wordtofind){
		
		Map<String, Integer> carte = new HashMap<>();
		//compter les mots 	
		Ngrams pa = new Ngrams();
		ArrayList<String> alt= new ArrayList<String>();
		alt =  pa.ngrams(1, texte);
		Iterator<String> i = alt.iterator();
		while (i.hasNext()){
			String mot= i.next().replaceAll("\\W", "");
			Integer compteurCouran = carte.get(mot);
			if(compteurCouran == null){ 
				compteurCouran = 0;			   
			}
			compteurCouran =compteurCouran+1;
			carte.put(mot, compteurCouran);
		}
		Set <Entry<String, Integer>> ensemble = carte.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(ensemble);
		
		Collections.sort(list, Collections.reverseOrder(new Comparator<Entry<String, Integer>>(){
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {	
					return (o1.getValue()).compareTo(o2.getValue());	
			}
				
		}));
		List<String> triL = new ArrayList<>();
		for (Entry<String, Integer> entry :list){
			if (entry.getKey().startsWith(wordtofind)) {
				// do stuff with entry
				triL.add( entry.getKey()+": " +entry.getValue());   
			}			
		}
		return triL;
		//System.out.print(triL);
	}

}
