package com.shopping.model;

public class Person02 {
	private String id;
	private String name;
	private int age;
	private String hobby;
	
	@Override
	public String toString() {
		return "Person02 [id=" + id + ", name=" + name + ", age=" + age + ", hobby=" + hobby + "]";
	}
	
	public Person02(String id, String name, int age, String hobby) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}

