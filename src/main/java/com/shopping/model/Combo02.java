package com.shopping.model;

public class Combo02 {
	// 회원과 게시물 조인을 위한 bean 클래스
	private String name;
	private int cnt;
	
	public Combo02() {
	}

	public Combo02(String name, int cnt) {
		super();
		this.name = name;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Combo02 [name=" + name + ", cnt=" + cnt + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
