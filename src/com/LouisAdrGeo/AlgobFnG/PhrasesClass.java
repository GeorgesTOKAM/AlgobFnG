package com.LouisAdrGeo.AlgobFnG;

import java.io.Serializable;

public class PhrasesClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long      id;
    private String    phrase;
    
    public Long getId() {
        return id;
       }
       public void setId(Long id) {
        this.id = id;
       }
       public String getPhrases() {
        return phrase;
       }
       public void setPhrases(String phrase) {
        this.phrase = phrase;
       }

}