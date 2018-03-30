package com.LouisAdrGeo.AlgobFnG;

import java.io.Serializable;

public class MotsClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long      id;
    private String    mots;
    
    public Long getId() {
        return id;
       }
       public void setId(Long id) {
        this.id = id;
       }
       public String getMots() {
        return mots;
       }
       public void setMots(String mots) {
        this.mots = mots;
       }

}