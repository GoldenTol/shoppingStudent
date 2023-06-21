package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Member;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;

public class MemberDao extends SuperDao{
	// 회원 한 사람의 정보를 반환해 줍니다.
	// 현재 일시적인 데이터이고, 차후 데이터 베이스에서 직접 읽어 오도록 하겠습니다.
	
	public Member getDataByPk01(String id) {
		return new Member("hong", "홍길동", "1234", "male", "식도락회 모임,수영", "22002/06/24", 100, "비고란입니다.");
	}
	// 회원 목록 보기 기능) 회원 전체의 목록을 반환해 줍니다.
	public List<Member>getDataList(){
		List<Member> datalist = new ArrayList<Member>();
		
		datalist.add(new Member("hong", "홍길동", "1234", "male", "식도락회 모임,수영", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("park", "박혁신", "1234", "male", "식도락회 모임,수영", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("choi", "최만위", "1234", "female", "식도락회 모임,수영", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("kim", "김동섭", "1234", "male", "식도락회 모임,수영", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("lee", "이수돌", "1234", "male", "식도락회 모임,수영", "2002/06/24", 100, "비고란입니다."));
		
		return datalist;
	}
	
//	public Member SelectData(String id, String password) {
//		// 아이디와 비번을 이용하여 로그인이 가능한지 판단합니다.
//		String imsiid="hong";
//		String imsiid="admin";
//		String imsipassword="1234";
//		
//		Member bean = null;
//		
//		if(id.equals(imsiid)) {
//			if(password.equals(imsipassword)) {
//				bean = new Member(id, "홍길동", password, null, null, null, 0, null);
//			}
//		}
//		return bean;
//	}
	
	public Member SelectData(String id, String password) throws Exception{
		System.out.println(id + "/" + password);
		
		// 아이디와 비번을 이용하여 로그인이 가능한지 판단합니다.
		String sql = " select * from members";
		// ?는 placeholder이라고 하며, 치환될 대상입니다. 
		sql += " where id = ? and password = ?";
		// 1) 객체 지정 JSP 교안 p308 참고
		
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		// 2) 객체가 들어감
		
		// ?치환은 반드시 실행전에 해야 합니다.
		pstmt.setString(1, id);
		// 0베이스 아닌 1베이스
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery();
		// 3) 들어간 객체 실행해줘
		
		Member bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
		
	}
	public int InsertData(Member bean) throws Exception {
		System.out.println(bean);
		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
		
		int cnt = -1;
		
		String sql = " insert into members(id, name, password, gender, hobby, hiredate, mpoint, remark)";
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?)";
		// ?는 플레이스 홀드 반드시 실행 전에 치환하기

		Connection conn = null;
		PreparedStatement pstmt = null;
		// 결과 집합을 가져오는게 아니라 넣는 중이기 때문에 ResultSet rs = null;은 필요없다.

		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		// 객체가 구해진 다음이어야함
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getPassword());
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getHobby());
		pstmt.setString(6, bean.getHiredate());
		pstmt.setInt(7, bean.getMpoint());
		pstmt.setString(8, bean.getRemark());
		// pstmt.setString(?의 위치, 치환할 값)
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		// 실행된 다음이어야 함
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}
	public List<Member> SelectAll() throws Exception{
		// 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
		String sql = " select * from members";
		
		List<Member> lists = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		

		return lists;
	}
	private Member makeBean(ResultSet rs) throws Exception{
		
		Member bean = new Member();
		bean.setGender(rs.getString("gender"));
		bean.setHiredate(String.valueOf(rs.getDate("hiredate")));
		bean.setHobby(rs.getString("hobby"));
		bean.setId(rs.getString("id"));
		bean.setMpoint(rs.getInt("mpoint"));
		bean.setName(rs.getString("name"));
		bean.setPassword(rs.getString("password"));
		bean.setRemark(rs.getString("remark"));
		
		return bean;
	}
	
	public Member getDataByPk(String id) throws Exception{
		// id를 이용하여 회원을 조회합니다.
		System.out.println("찾고자 하는 아이디 : " + id);
		
		// 아이디와 비번을 이용하여 로그인이 가능한지 판단합니다.
		String sql = " select * from members";
		// ?는 placeholder이라고 하며, 치환될 대상입니다. 
		sql += " where id = ? ";
		// 1) 객체 지정 JSP 교안 p308 참고
		
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		// 2) 객체가 들어감
		
		// ?치환은 반드시 실행전에 해야 합니다.
		pstmt.setString(1, id);
		// 0베이스 아닌 1베이스
		
		rs = pstmt.executeQuery();
		// 3) 들어간 객체 실행해줘
		
		Member bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}
	
	public List<Member> SelectAll(Paging pageInfo) throws Exception {
		String sql = " select id, name, password, gender, hobby, hiredate, mpoint, remark";
		sql += " from(select id, name, password, gender, hobby, hiredate, mpoint, remark,";
		sql += " rank() over(order by id desc) as ranking";
		sql += " from members)";
		sql += " where ranking between ? and ?";
		
		
		List<Member> lists = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		

		return lists;
	}
	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드 : " + keyword);
		
		String sql = " select count(*) as cnt from products ";
		// 1) sql developer에서 데이터를 가져와서
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		// 3) int cnt값이 sql developer에서 가져온 값으로 바뀌어 출력
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
			// 2) cnt에 값을 담고
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}
	
	public int DeleteData(String id) throws Exception{
	      // id에 해당하는 회원이 탈퇴를 합니다.
	      System.out.println("탈퇴한 아이디 " + id);
	      
	      int cnt = -1;
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      if(conn==null) {conn=super.getConnection();}
	      conn.setAutoCommit(false);
	      
	      String sql = "";
	      
	      Member bean = this.getDataByPk(id);
	      String remark = MyUtility.getCurrentTime() + bean.getName() + "(아이디 : " + id + ")님이 탈퇴를 하였습니다.";
	      
	      // 트렌젝션 (all or nothing)
	      
	      // step01 : 게시물 테이블 remark 컬럼 업데이트
	      sql = " update boards set remark = ? where writer = ?";
	      
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, remark);
	      pstmt.setString(2, id);
	      
	      cnt = pstmt.executeUpdate();
	      
	      // step02 : 주문 테이블 remark 컬럼 업데이트
	      sql = " update orders set remark = ? where mid = ? ";
	      
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, remark);
	      pstmt.setString(2, id);
	      
	      cnt = pstmt.executeUpdate();
	      
	      // step03 : 회원 테이블 id 행 삭제
	      sql = " delete from members where id = ?";
	      
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, id);
	      
	      cnt = pstmt.executeUpdate();
	      
	      conn.commit();
	      
	      if(pstmt!=null) {pstmt.close();}
	      if(conn!=null) {conn.close();}
	      
	      return cnt;
	   }
}
