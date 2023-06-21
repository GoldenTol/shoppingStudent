package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Board;
import com.shopping.model.Member;
import com.shopping.utility.Paging;

public class BoardDao extends SuperDao{
	// 오라클과 연동하여 Board의 데이터를 가져옴
	
	public Board getDataByPk01(int no) {
		return new Board(10, "hong", "자바 프로그램", "핵노잼", "2002/08/15", 1234, "비고란.", 111, 222, 333);
	}
	
	// 게시글 1건
	public List<Board>getDataList(){
		List<Board> datalist = new ArrayList<Board>();
		
		datalist.add(new Board(10, "hong", "자바 프로그램", "핵노잼", "2002/08/15", 30, "비고란.", 111, 222, 1));
		datalist.add(new Board(11, "park", "파이썬 프로그램", "잼없어요", "2002/08/15", 40, "비고란.", 111, 222, 1));
		datalist.add(new Board(12, "choi", "database 프로그램", "조금잼있어요", "2002/08/15",50, "비고란.", 111, 222, 1));
		datalist.add(new Board(13, "kim", "html 프로그램", "잼있어요", "2002/08/15", 60, "비고란.", 111, 222, 2));
		datalist.add(new Board(14, "lee", "자바 프로그램", "대유잼", "2002/08/15", 70, "비고란.", 111, 222, 2));
		
		return datalist;
	}
	
	public int InsertData(Board bean) throws Exception{
		System.out.println(bean);
			
		int cnt = -1;
			
		String sql = " insert into boards(no, writer, subject, content, regdate, readhit, remark, groupno, orderno, depth)";
		sql += " values(myboard.nextval, ?, ?, ?, ?, default, ?, myboard.currval, default, default)";

		Connection conn = null; // 접속 객체
		PreparedStatement pstmt = null; // 문장(" select * from boards") 처리자 
		// ResultSet rs = null; // 결과집합 몇 행 몇 열이 메모리에 올라가는지 
			
		// 접속객체 구하기 ex) 네이버에 로그인 안 했으면 로그인 해와라
		if(conn==null) {conn=super.getConnection();} 
		// connection이 의미가 생겼으니 PreparedStatement가 의미를 구하고
			
		conn.setAutoCommit(false);
			
		pstmt = conn.prepareStatement(sql);
		// PreparedStatement가 의미가 생겼으니 ResultSet가 의미를 구해와라
		
		pstmt.setString(1, bean.getWriter());
		pstmt.setString(2, bean.getSubject());
		pstmt.setString(3, bean.getContent());
		pstmt.setString(4, bean.getRegdate());
		pstmt.setString(5, bean.getRemark());
			
		cnt = pstmt.executeUpdate();
			
		conn.commit();
			
		// 닫을 때는 열었던 순서 역순으로 닫기
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}
	
	public List<Board> SelectAll(Paging pageInfo) throws Exception{ 
		// TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
		// 아래 public List<Board> SelectAll() throws Exception{ 메소드를 오버로딩

		String sql = " select no, writer, subject, content, regdate, readhit, remark, groupno, orderno, depth";
		sql += " from(select no, writer, subject, content, regdate, readhit, remark, groupno, orderno, depth,";
		// sql += " rank() over(order by no desc) as ranking"; ← 답글 기능 구현하기 이전 코딩
		sql += " rank() over(order by groupno desc, orderno asc) as ranking";
		sql += " from boards";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		
		if(mode.equals("all") == false) { 
			sql += " where " + mode + " like '%" + keyword + "%'";
			
		}
		sql += " )";
		sql += " where ranking between ? and ?";
		
		
		
		List<Board> lists = new ArrayList<Board>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		// (↓) 실행하기 전 (↑) 치환하기
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}
	
	public List<Board> SelectAll() throws Exception{
		// JSP 교안 308page 참고/ 전체 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.

		String sql = " select * from boards order by no desc";
		
		List<Board> lists = new ArrayList<Board>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}

	private Board makeBean(ResultSet rs) throws Exception{
		Board bean = new Board();
		
		bean.setNo(rs.getInt("no"));
		bean.setWriter(rs.getString("writer"));
		bean.setSubject(rs.getString("subject"));
		bean.setContent(rs.getString("content"));
		bean.setRegdate(rs.getString("regdate"));
		bean.setRemark(rs.getString("remark"));
		bean.setGroupno(rs.getInt("groupno"));
		bean.setOrderno(rs.getInt("orderno"));
		bean.setDepth(rs.getInt("depth"));
		
		return bean;
	}
	
	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드 : " + keyword + "\n");
		
		String sql = " select count(*) as cnt from boards ";
		// 1) sql developer에서 데이터를 가져와서
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'";
			// 여기서 mode에 boList에 있는 value값이 들어간다.
		}
		System.out.println(sql);
		
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

	public Board getDataByPk(int no) throws Exception {
		// no(글번호)를 이용하여 회원을 조회합니다.
		System.out.println("찾고자 하는 글번호 : " + no);
		
		String sql = " select * from boards";
		sql += " where no = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		// 2) 객체가 들어감
		
		// ?치환은 반드시 실행전에 해야 합니다.
		pstmt.setInt(1, no);
		// 0베이스 아닌 1베이스
		
		rs = pstmt.executeQuery();
		// 3) 들어간 객체 실행해줘
		
		Board bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}

	

	public Board GetDataByPk(int no) throws Exception {
		System.out.println("찾고자 하는 글번호 : " + no);
		
		String sql = " select * from boards";
		sql += " where no = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		
		Board bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}

	public int UpdateData(Board bean) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
			
		String sql = " update boards set";
		sql += " writer = ?, subject = ?, content = ?, regdate = ?, readhit = ?, groupno = ?, orderno = ?, depth = ?";
		sql += " where no = ?";

		Connection conn = null; // 접속 객체
		PreparedStatement pstmt = null; // 문장(" select * from boards") 처리자 
		
		if(conn==null) {conn=super.getConnection();} 
			
		conn.setAutoCommit(false);
			
		pstmt = conn.prepareStatement(sql);
		// PreparedStatement가 의미가 생겼으니 ResultSet가 의미를 구해와라
		
		pstmt.setString(1, bean.getWriter());
		pstmt.setString(2, bean.getSubject());
		pstmt.setString(3, bean.getContent());
		pstmt.setString(4, bean.getRegdate());
		pstmt.setInt(5, bean.getReadhit());
		pstmt.setInt(6, bean.getGroupno());
		pstmt.setInt(7, bean.getOrderno());
		pstmt.setInt(8, bean.getDepth());
		pstmt.setInt(9, bean.getNo());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int GetReplyCount(int groupno) throws Exception{
		// 해당 원글에 대한 답글 개수를 반환해 줍니다.
		System.out.println("검색할 groupno : " + groupno);
		
		String sql = " select count(*) as cnt from boards where groupno = ?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, groupno);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public int ReplyData(Board bean, int orderno) throws Exception{
		System.out.println(bean);
		
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		
		// update : 동일한 그룹 번호에 대하여 orderNo 컬럼의 숫자를 1씩 증가시켜야 합니다.
		String sql1 = " update boards set orderno = orderno +1";
		sql1 += " where groupno = ? and orderno > ?";
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getGroupno());
		pstmt.setInt(2, orderno);
		
		cnt = pstmt.executeUpdate();
		

		
			
		// insert : 해당 bean 객체를 이용하여 답글을 작성합니다.
		pstmt=null; // 업데이트 후 참조관계를 끊기 위에 초기화
		
		String sql2 = " insert into boards(no, writer, subject, content, regdate, groupno, orderno, depth)";
		sql2 += " values(myboard.nextval, ?, ?, ?, ?, ?, ?, ?)";
			
		pstmt = conn.prepareStatement(sql2);
		
		pstmt.setString(1, bean.getWriter());
		pstmt.setString(2, bean.getSubject());
		pstmt.setString(3, bean.getContent());
		pstmt.setString(4, bean.getRegdate());
		pstmt.setInt(5, bean.getGroupno());
		pstmt.setInt(6, bean.getOrderno());
		pstmt.setInt(7, bean.getDepth());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int UpdateReadhit(int no) throws Exception{
		// 게시물 작성자가 게시글을 수정합니다.
		int cnt = -1 ;
		
		// remark 컬럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " update boards set readhit = readhit + 1 where no = ?" ; 

		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, no) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		return cnt ;
		
	}

	public int DeleteData(int no) throws Exception{
		System.out.println("삭제할 게시글 : " + no);
		
		int cnt = -1;
			
		String sql = " delete from boards where no = ?";

		Connection conn = null;
		PreparedStatement pstmt = null; 
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		return cnt;
	}
}