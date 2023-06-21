package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shopping.model.CartItem;
import com.shopping.model.Member;
import com.shopping.model.Order;
import com.shopping.model.ShoppingBasket;

public class MallDao extends SuperDao {

	public int InsertShoppingBaskets(String id, Map<Integer, Integer> cartList) throws Exception {
		// 로그인 한 사람의 카트 품목(wishList)을 임시 테이블에 저장합니다.
		System.out.println("카트 주인 : " + id);

		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
		int cnt = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;

		if (conn == null) {
			conn = super.getConnection();
		}

		// step01 : 혹시 남아 있을지 모르는 나의 이전 내역을 삭제합니다.
		String sql = "";
		sql += " delete from ShoppingBaskets where mid = ?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		cnt = pstmt.executeUpdate();

		if (pstmt != null) {
			pstmt.close();
		}

		// step02 : 반복문을 사용하여 테이블에 인서트 합니다.
		Set<Integer> keylist = cartList.keySet();
		System.out.println("상품 개수 : " + keylist.size());

		sql = " insert into ShoppingBaskets(mid, pnum, qty)";
		sql += " values(?, ?, ?)";

		for (Integer pnum : keylist) {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, pnum);
			pstmt.setInt(3, cartList.get(pnum));

			cnt = pstmt.executeUpdate();
			if (pstmt != null) {
				pstmt.close();
			}
		}

		conn.commit();

		if (conn != null) {
			conn.close();
		}

		return cnt;
	}

	public List<ShoppingBasket> GetShoppingBasket(String id) throws Exception {
		// 나의 wishList 목록을 읽어 옵니다.
		System.out.println("카트 주인 : " + id);

		String sql = " select * from ShoppingBaskets where mid = ? ";

		List<ShoppingBasket> lists = new ArrayList<ShoppingBasket>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			lists.add(this.makeBean(rs));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	private ShoppingBasket makeBean(ResultSet rs) throws Exception {
		// ShoppingBasket 전용 빈 만들기
		ShoppingBasket bean = new ShoppingBasket();

		bean.setMid(rs.getString("mid"));
		bean.setPnum(rs.getInt("pnum"));
		bean.setQty(rs.getInt("qty"));

		return bean;
	}

	public void Calculate(Member payer, Map<Integer, Integer> CartList, int totalPoint) throws Exception {
		// payer : 계산을 하는 사람
		// CartList : 카트에 담겨있는 상품번호와 구매수량
		// totalPoint : payer에게 적립해 줄 포인트

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;

		if (conn == null) {
			conn = super.getConnection();
		}
		conn.setAutoCommit(false);

		String sql = "";

		// step01 : 주문 테이블(orders)에 매출 1건 입력
		sql = " insert into orders (oid, mid, orderdate) ";
		sql += " values(seqoid.nextval, ?, sysdate)";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, payer.getId()); // 수정함

		// sql인설트 문장을 사용하려면 반드시 하기 명령어 기재필수
		cnt = pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
		}

		// step02 : 1번에 들어간 송장 번호 읽기 (why : 참조 무결정 제약조건)
		sql = " select max(oid) as invoice from orders ";

		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		int invoice = 0; // 송장번호
		if (rs.next()) {
			invoice = rs.getInt("invoice");
		}
		if (pstmt != null) {
			pstmt.close();
		}

		// 반복문을 사용하여 (상품이 여러개니까)
		Set<Integer> keylist = CartList.keySet();

		for (Integer pnum : keylist) {
			// step03 : 주문 상세 테이블(OrderDetails)에 데이터 추가
			sql = " insert into orderdetails (odid, oid, pnum, qty) ";
			sql += " values(seqodid.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, invoice); // 송장 번호
			pstmt.setInt(2, pnum); // 상품 번호
			pstmt.setInt(3, CartList.get(pnum)); // 구매 수량

			cnt = pstmt.executeUpdate();
			if (pstmt != null) {
				pstmt.close();
			}

			// step04 : 상품들의 재고 수량을 뺄샘하기
			sql = " update products set stock = stock - ? ";
			sql += " where num = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, CartList.get(pnum)); // 구매 수량
			pstmt.setInt(2, pnum); // 상품 번호

			cnt = pstmt.executeUpdate();
			if (pstmt != null) {
				pstmt.close();
			}

		}

		// step05 : 회원에 대하여 적립 포인트를 업데이트 하기
		sql = " update members set mpoint = mpoint + ? ";
		sql += " where id = ? ";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, totalPoint); // 적립 포인트
		pstmt.setString(2, payer.getId()); // 회원 아이디

		cnt = pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
		}

		conn.commit();

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public List<Order> getHistory(String id) throws Exception {
		// 나의 과거 쇼핑내역을 최신순으로 정렬하여 반환해 줍니다.
		String sql = " select * from orders";
		sql += " where mid = ? order by oid desc ";

		List<Order> lists = new ArrayList<Order>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			lists.add(this.makeOrderBean(rs));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	private Order makeOrderBean(ResultSet rs) throws Exception {
		// Order 전용 빈만들기 메소드
		Order bean = new Order();

		bean.setMid(rs.getString("mid"));
		bean.setOid(rs.getInt("oid"));
		bean.setOrderdate(String.valueOf(rs.getString("orderdate")));
		bean.setRemark(rs.getString("remark"));

		return bean;
	}

	public Order getDetailHistory(int oid) throws Exception {
		// 해당 송장 번호에 대한 주문 정보를 반환해 줍니다.
		System.out.println("송장번호 : " +oid);
		String sql = " select * from orders where oid = ?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, oid);
		rs = pstmt.executeQuery();
		
		Order bean = null;
		if(rs.next()) {
			bean = this.makeOrderBean(rs);
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return bean;
	}

	public List<CartItem> ShowDetail(int oid) throws Exception {
		// 해당 송장 번호에 대한 세부적인 상세 내역을 컬렉션 형태로 반환해 줍니다.
		String sql = " select p.num pnum, p.name pname, od.qty, p.price, p.point, p.image01";
		sql += " from (orders o inner join orderdetails od";
		sql += " on o.oid = od.oid) inner join products p";
		sql += " on od.pnum = p.num and o.oid = ?";
		sql += " order by od.oid desc";

		List<CartItem> lists = new ArrayList<CartItem>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, oid);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			lists.add(this.makeCartItemBean(rs));
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	private CartItem makeCartItemBean(ResultSet rs) throws Exception {
		CartItem bean = new CartItem();
		
		bean.setImage01(rs.getString("image01"));
		// bean.setMid(rs.getString("mid"));
		bean.setPname(rs.getString("pname"));
		bean.setPnum(rs.getInt("pnum"));
		bean.setPoint(rs.getInt("point"));
		bean.setPrice(rs.getInt("price"));
		bean.setQty(rs.getInt("qty"));
		
		return bean;
	}

}