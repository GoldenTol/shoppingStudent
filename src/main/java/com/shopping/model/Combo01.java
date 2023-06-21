package com.shopping.model;

public class Combo01 {
	// 회원과 게시물 조인을 위한 bean 클래스
	private String name;
	private String subject;
	private String content;
	private String regdate;
	
	public Combo01() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Combo01 [name=" + name + ", subject=" + subject + ", content=" + content + ", regdate=" + regdate + "]";
	}

	public Combo01(String name, String subject, String content, String regdate) {
		super();
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
