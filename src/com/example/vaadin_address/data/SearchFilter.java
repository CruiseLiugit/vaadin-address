package com.example.vaadin_address.data;

import java.io.Serializable;

public class SearchFilter implements Serializable {
	private final String term;
	private final Object propertyId;
	private String searchName;
	
	
	public SearchFilter(String term, Object propertyId, String searchName) {
		super();
		this.term = term;
		this.propertyId = propertyId;
		this.searchName = searchName;
	}


	public String getTerm() {
		return term;
	}


	public Object getPropertyId() {
		return propertyId;
	}


	public String getSearchName() {
		return searchName;
	}
	
	
	@Override
	public String toString(){
		return getSearchName();
	}

	
	
	
	
}


