package com.shopping.model;

public class Combo04 {
	// 회원과 게시물 조인을 위한 bean 클래스
	private String id; // 고객명
	private int sumtotal; // 매출 총액
	
	public Combo04() {
	}

	@Override
	public String toString() {
		return "Combo04 [id=" + id + ", aumtotal=" + sumtotal + "]";
	}

	public Combo04(String id, int sumtotal) {
		super();
		this.id = id;
		this.sumtotal = sumtotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSumtotal() {
		return sumtotal;
	}

	public void setSumtotal(int sumtotal) {
		this.sumtotal = sumtotal;
	}
}
