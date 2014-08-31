package br.com.drools.poc.example.main;

import org.kie.api.io.ResourceType;

public class RuleResource {
	private String resourceName;
	private ResourceType type;
	private String content;

	public RuleResource() {
		super();
	}
	public String getResourceName() {
		return resourceName;
	}
	public ResourceType getType() {
		return type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
}
