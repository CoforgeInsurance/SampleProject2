package com.aviva.ca.esb.api.apollo.exception;

public class ApolloServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String status;
	private String description;

	
	public ApolloServiceException(String status, String description) {
		super();
		this.status = status;
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	


}
