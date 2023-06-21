package com.shopping.model;

public class Member {
	private String id;
	private String name;
	private String password;
	private String gender;
	private String hobby;
	private String hiredate; 
	/* 날짜이지만 문자열로 처리하면 코딩이 좀 간결해집니다. */
	private int mpoint;
	private String remark;
	
	/* getter(), setter(), toString(), 생성자(), 생성자(모든_변수_리스트) */
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String name, String password, String gender, String hobby, String hiredate, int mpoint,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.hobby = hobby;
		this.hiredate = hiredate;
		this.mpoint = mpoint;
		this.remark = remark;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", hobby="
				+ hobby + ", hiredate=" + hiredate + ", mpoint=" + mpoint + ", remark=" + remark + "]";
	}
}
