package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Combo01;
import com.shopping.model.Combo02;
import com.shopping.model.Combo03;
import com.shopping.model.Combo04;
import com.shopping.model.Combo05;

// CompositeDao : 여러 개의 테이블을 조인하고자 할 때 사용하는 Dao
public class CompositeDao extends SuperDao{
	
	public List<Combo01> View01() throws Exception{
		String sql = " select m.name, b.subject, b.content, b.regdate";
		sql += " from members m inner join boards b";
		sql += " on m.id = b.writer";
		sql += " order by m.name";
		
		List<Combo01> lists = new ArrayList<Combo01>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Combo01 bean = new Combo01();
			
			bean.setContent(rs.getString("content"));
			bean.setName(rs.getString("name"));
			bean.setRegdate(String.valueOf(rs.getDate("regdate")));
			bean.setSubject(rs.getString("subject"));
			
			lists.add(bean);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}

	public List<Combo02> View02() throws Exception {
		String sql = " select m.name, count(*) as cnt";
		sql += " from members m inner join boards b";
		sql += " on m.id = b.writer";
		sql += " group by m.name";
		sql += " order by m.name";
		
		List<Combo02> lists = new ArrayList<Combo02>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Combo02 bean = new Combo02();
			
			bean.setName(rs.getString("name"));
			bean.setCnt(rs.getInt("cnt"));
			
			
			lists.add(bean);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}

	public List<Combo03> View03() throws Exception{
		String sql = " select m.name mname, p.name pname, o.orderdate, od.qty, p.price, od.qty*p.price as amount";
		sql += " from ((members m inner join orders o";
		sql += " on m.id = o.mid) inner join orderdetails od";
		sql += " on o.oid = od.oid) inner join products p";
		sql += " on od.pnum = p.num";
		sql += " order by p.name desc, m.name asc";
		
		List<Combo03> lists = new ArrayList<Combo03>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Combo03 bean = new Combo03();
			
			bean.setAmount(rs.getInt("amount"));
			bean.setMname(rs.getString("mname"));
			bean.setOrderdate(String.valueOf(rs.getDate("orderdate")));
			bean.setPname(rs.getString("pname"));
			bean.setPrice(rs.getInt("price"));
			bean.setQty(rs.getInt("qty"));
			
			
			lists.add(bean);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}
	
	public List<Combo04> View04() throws Exception{
		String sql = " select m.id, sum(od.qty*p.price) as sumtotal";
		sql += " from ((members m inner join orders o";
		sql += " on m.id = o.mid) inner join orderdetails od";
		sql += " on o.oid = od.oid) inner join products p";
		sql += " on od.pnum = p.num";
		sql += " group by m.id";
		
		List<Combo04> lists = new ArrayList<Combo04>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Combo04 bean = new Combo04();
			
			bean.setId(rs.getString("id"));
			bean.setSumtotal(rs.getInt("sumtotal"));
			
			lists.add(bean);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return lists;
	}
	
	public List<Combo05> View05() throws Exception{
		String sql = " select m.id, count(mid) as cnt";
		sql += " from members m left outer join orders o";
		sql += " on m.id=o.mid";
		sql += " group by m.id";
		sql += " order by cnt desc, m.id asc";
		
		List<Combo05> lists = new ArrayList<Combo05>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Combo05 bean = new Combo05();
			
			bean.setId(rs.getString("id"));
			bean.setCnt(rs.getInt("cnt"));
			
			lists.add(bean);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return lists;
	}
	
}
