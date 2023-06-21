package com.shopping.model;

public class Combo03 {
	// 회원과 게시물 조인을 위한 bean 클래스
	private String mname; // 사람이름
	private String pname; // 상품이름
	private String orderdate;// 주문일자
	private int qty; // 구매수량
	private int price; // 가격
	private int amount; // 금액
	
	
	public Combo03() {
	}


	@Override
	public String toString() {
		return "Combo03 [mname=" + mname + ", pname=" + pname + ", orderdate=" + orderdate + ", qty=" + qty + ", price="
				+ price + ", amount=" + amount + "]";
	}
	
	public Combo03(String mname, String pname, String orderdate, int qty, int price, int amount) {
		super();
		this.mname = mname;
		this.pname = pname;
		this.orderdate = orderdate;
		this.qty = qty;
		this.price = price;
		this.amount = amount;
	}
	
	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
}
