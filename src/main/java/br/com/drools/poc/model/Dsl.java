package br.com.drools.poc.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dsl {
	
	private String expression;
	private String mapping;
	private String scope;
	private String _id;
	private String __v;

	public String get__v() {
		return __v;
	}
	public void set__v(String __v) {
		this.__v = __v;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	@Override
	public String toString() {
		return "Dsl [expression=" + expression + ", mapping=" + mapping + ", scope=" + scope + ", _id=" + _id + ", __v=" + __v + "]";
	}
}
