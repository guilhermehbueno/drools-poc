package com.walmart.search.rules.model;

public class OperationResponse {
	
	public enum OperationResponseStatus{
		SUCCESS, ERROR;
	}
	
	private String message;
	private OperationResponseStatus status;
	
	public OperationResponse(String message, OperationResponseStatus status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public OperationResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public OperationResponseStatus getStatus() {
		return status;
	}
	public void setStatus(OperationResponseStatus status) {
		this.status = status;
	}
}
