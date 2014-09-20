package br.com.drools.poc.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DslRule {

	private String name;
	private String when;
	private String then;
	private String _id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	public String getThen() {
		return then;
	}
	public void setThen(String then) {
		this.then = then;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	@Override
	public String toString() {
		return "DslRule [name=" + name + ", when=" + when + ", then=" + then + ", _id=" + _id + "]";
	}
}
