package br.com.drools.poc.model;

public class Person {
	private String name;
	private String location;
	private Integer age;
	private String message;

	public Person(String name, String location, Integer age) {
		this.name = name;
		this.location = location;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Integer getAge() {
		return age;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", location=" + location + ", age="
				+ age + ", message=" + message + "]";
	}
}
