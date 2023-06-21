package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Board;
import com.shopping.model.CartItem;
import com.shopping.model.Member;
import com.shopping.model.Product;
import com.shopping.utility.MyUtility;
import com.shopping.utility.Paging;


public class ProductDao extends SuperDao{
	
	// 상품 1개
	public Product getDataByPk01(int num) {
		return new Product(10, "유아용", "키즈패션", "넘 이쁘요", "child1.jpg", "child10.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25");
	}
	
	public Product getDataByPk02(int num) {
		if(num == 10) {
			return new Product(10, "유아용", "키즈패션", "넘 이쁘요", "child1.jpg", "child10.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25");
		}else {
			return new Product(11, "유아용", "키즈패션", "좋아요", "child2.jpg", "child3.jpg", null, 10, 20000, 4, "비고란", "child", "2000/12/25");
		}
	}
	
	public List<Product>getDataList(){
		List<Product> datalist = new ArrayList<Product>();
		
		datalist.add(new Product(10, "유아용", "키즈패션", "넘 이쁘요", "child2.jpg", "child5.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
		datalist.add(new Product(11, "유아용", "키즈패션", "좋아요", "hild6.jpg", "child7.jpg", null, 10, 20000, 4, "비고란", "child", "2000/12/25"));
		datalist.add(new Product(12, "유아용", "키즈패션", "비행기를 타고 가던 너 따라가고싶어 울었죠 철없을적 내 기억 속에 비행기 타고 가요.", "child1.jpg", "child10.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
		datalist.add(new Product(13, "유아용", "키즈패션", "맘에 들어요", "child_shoes5.jpg", "child_shoes6.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
		datalist.add(new Product(14, "여성 패션 01", "이신우패션", "별로예요", "woman_frulldress1.jpg", "woman_pants2.jpg", null, 10, 10000, 4, "비고란", "woman", "2000/12/25"));
		datalist.add(new Product(15, "여성 패션 02", "앙드레김", "고와요", "woman_shoes3.jpg", "woman_shoes1.jpg", null, 10, 10000, 4, "비고란", "woman", "2000/12/25"));
		
		return datalist;
	}

	public List<Product> SelectAll() throws Exception{
		// 상품 목록을 조회하여 반환해 줍니다.
		// rownum은 한시적, 차후에 수정 예정임
		String sql = " select * from products order by num desc";
		/* 
		  String sql = " select * from products where rownum <= 6;";
		  자바의 세미콜론이기 때문에 데이터베이스 세미콜론 가져오면 안됩니다.
		 */
		
		List<Product> lists = new ArrayList<Product>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 오라클의 테이블 데이터(~행~열의 데이터)를 ResultSet라고 한다.
		
		if(conn==null) {conn=super.getConnection();} 
		pstmt = conn.prepareStatement(sql);
		/* 만약 오류가 뜬다면 pstmt = conn.prepareStatement(); 문장 자체는 문제가 없고
		 sql 문장 즉 select * from products 문장에 오류가 있는 것 */
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		
		return lists;
	}
	
	private Product makeBean(ResultSet rs) throws Exception{
		Product bean = new Product();
		
		bean.setCategory(rs.getString("category"));
		bean.setComments(rs.getString("comments"));
		bean.setCompany(rs.getString("company"));
		bean.setImage01(rs.getString("image01"));
		bean.setImage02(rs.getString("image02"));
		bean.setImage03(rs.getString("image03"));
		bean.setInputdate(rs.getString("inputdate"));
		bean.setName(rs.getString("name"));
		bean.setNum(rs.getInt("num"));
		bean.setPoint(rs.getInt("point"));
		bean.setPrice(rs.getInt("price"));
		bean.setRemark(rs.getString("remark"));
		bean.setStock(rs.getInt("stock"));
		
		return bean;
	}

	public int InsertData(Product bean) throws Exception{
		// bean 객체 1개를 등록합니다.
		System.out.println(bean);
		
		int cnt = -1;
		
		String sql = " insert into products(num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate)";
		sql += " values(myproduct.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, '', ?, ?)";
		// ?는 플레이스 홀드 반드시 실행 전에 치환하기

		Connection conn = null;
		PreparedStatement pstmt = null;
		// 결과 집합을 가져오는게 아니라 넣는 중이기 때문에 ResultSet rs = null;은 필요없다.

		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		// 객체가 구해진 다음이어야함
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getCompany());
		pstmt.setString(3, bean.getComments());
		pstmt.setString(4, bean.getImage01());
		pstmt.setString(5, bean.getImage02());
		pstmt.setString(6, bean.getImage03());
		pstmt.setInt(7, bean.getStock());
		pstmt.setInt(8, bean.getPrice());
		pstmt.setInt(9, bean.getPoint());
		pstmt.setString(10, bean.getCategory());
		pstmt.setString(11, bean.getInputdate());
		// pstmt.setString(?의 위치, 치환할 값)
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		// 실행된 다음이어야 함
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public List<Product> SelectAll(Paging pageInfo) throws Exception {
		
		String sql = " select num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate";
		sql += " from(select num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate,";
		sql += " rank() over(order by num desc) as ranking";
		sql += " from products)";
		sql += " where ranking between ? and ?";
		
		
		List<Product> lists = new ArrayList<Product>();
		
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
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public Product GetDataByPk(int num) throws Exception{
		// 해당 상품 번호를 이용하여 상품 bean 객체를 반환해 줍니다.
		System.out.println("찾고자 하는 글번호 : " + num);
		
		String sql = " select * from products";
		sql += " where num = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		Product bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}

	public int UpdateData(Product bean) throws Exception{
		// 관리자가 상품 정보를 수정합니다.
		System.out.println(bean);
		
		int cnt = -1;
		// remark칼럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
		String sql = " update products set";
		sql += " name = ?, company = ?, comments = ?, image01 = ?, image02 = ?, image03 = ?, stock = ?, price = ?, point = ?, category = ?, inputdate = ?";
		sql += " where num = ?";
		// ?는 플레이스 홀드 반드시 실행 전에 치환하기

		Connection conn = null;
		PreparedStatement pstmt = null;

		if(conn==null) {conn=super.getConnection();}
		conn.setAutoCommit(false);
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getCompany());
		pstmt.setString(3, bean.getComments());
		pstmt.setString(4, bean.getImage01());
		pstmt.setString(5, bean.getImage02());
		pstmt.setString(6, bean.getImage03());
		pstmt.setInt(7, bean.getStock());
		pstmt.setInt(8, bean.getPrice());
		pstmt.setInt(9, bean.getPoint());
		pstmt.setString(10, bean.getCategory());
		pstmt.setString(11, bean.getInputdate());
		pstmt.setInt(12, bean.getNum());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public CartItem getCartItem(Integer pnum, Integer qty)throws Exception {
		// 해당 상품 번호에 대한 Product 정보를 CartItem 객체에 대입하고 반환해줍니다.
		Product bean = this.GetDataByPk(pnum);
		
		CartItem item = new CartItem();
		
		item.setImage01(bean.getImage01());
		item.setMid(null); // 임시 저장용 테이블과 관련 있음.
		item.setPname(bean.getName());
		item.setPnum(pnum);
		item.setPoint(bean.getPoint());
		item.setPrice(bean.getPrice());
		item.setQty(qty);
		
		return item;
	}

	public int DeleteData(int num) throws Exception{
		// 관리자가 해당 상품 번호 num을 삭제하려고 시도합니다.
		// 참조 무결성 제약 조건을 확인하고, 주문 상세 테이블의 비고(remark) 컬럼에 변경 사항을 기록합니다.
		System.out.println("삭제할 상품 번호 : " + num);
		
		int cnt = -1;
		String sql = " ";
		sql += " ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		if(conn==null) {conn=super.getConnection();}
		conn.setAutoCommit(false);
		
		// step01 : 주문 상세 테이블의 비고(remark)칼럼에 데이터를 수정하기
		Product bean = this.GetDataByPk(num);
		String remark = MyUtility.getCurrentTime() + bean.getName() + "(상품번호 : "+num+"번) 상품이 삭제 되었습니다.";
		sql = " update orderdetails set remark = ? where pnum = ?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, remark);
		pstmt.setInt(2, num);
		cnt = pstmt.executeUpdate();
		if(pstmt != null) {pstmt.close();}
		
		// step02 : 상품 테이블에서 해당 상품 번호 삭제하기
		sql = " delete form products where num = ?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}
}
