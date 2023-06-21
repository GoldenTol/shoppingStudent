package com.shopping.model;

public class Human {
	private String id;
	private String password;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String ages;
	
	public Human() {
	}

	public Human(String id, String password, String name, String gender, int age, String email, String ages) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.ages = ages;
	}

	@Override
	public String toString() {
		return "Human [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", ages=" + ages + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAges() {
		return ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}

	
}
