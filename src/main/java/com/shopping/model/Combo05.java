package com.shopping.model;

public class Combo05 {
	// 회원과 게시물 조인을 위한 bean 클래스
	private String id; // 주문자
	private int cnt; // 주문건수
	
	public Combo05() {
	}

	public Combo05(String id, int cnt) {
		super();
		this.id = id;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Combo05 [id=" + id + ", cnt=" + cnt + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
