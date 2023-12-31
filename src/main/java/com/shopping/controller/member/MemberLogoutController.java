package com.shopping.controller.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.MallDao;
import com.shopping.mall.CartManager;

public class MemberLogoutController extends SuperClass{
	
	@Override // 회원이 로그아웃을 사도합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 카트 품목이 저장된 데이터가 있다면, ShoppingBaskets 테이블에 저장하고 로그아웃하도록 합니다. 
		Map<Integer, Integer> cartList =  mycart.GetAllCartList();
		
		MallDao madao = new MallDao();
		
		try {
			
			if(super.loginfo != null) {
				if(cartList.size() > 0) {
					madao.InsertShoppingBaskets(super.loginfo.getId(), cartList);
					
				}
			}
			
			// 로그인시 session에 저장했던 로그인 정보, 장바구니 정보 등등을 삭제(휘발)하고 로그인 페이지로 이동합니다.
			super.session.invalidate(); // invalidate : 세션에 들어 있는걸 모두 무용지물로 만듬.
			super.GotoPage("member/meLoginForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
