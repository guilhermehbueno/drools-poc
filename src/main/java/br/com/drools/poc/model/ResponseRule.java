package br.com.drools.poc.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRule {
	
	private DslRule [] result;

	public DslRule[] getResult() {
		return result;
	}

	public void setResult(DslRule[] result) {
		this.result = result;
	}
}
