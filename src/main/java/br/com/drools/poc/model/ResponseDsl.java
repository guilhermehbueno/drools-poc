package br.com.drools.poc.model;

public class ResponseDsl {
	
	private int status;
	private String message;
	private Dsl [] result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Dsl[] getResult() {
		return result;
	}
	public void setResult(Dsl[] result) {
		this.result = result;
	}
}
