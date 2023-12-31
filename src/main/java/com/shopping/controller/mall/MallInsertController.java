package com.shopping.controller.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductListController;

public class MallInsertController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String message = null;
		
		if(super.loginfo==null) {
			super.youNeededLogin();
			return;
		}
			
		// 누군가 로그인 되어 있음
		int stock = Integer.parseInt(request.getParameter("stock")); // 재고 수량
		int qty = Integer.parseInt(request.getParameter("qty")); // 구매할 수량
			
		if(stock < qty) { // 재고 수량 초과
			message = "재고 수량이 부족합니다.";
			super.setAlertMessage(message);
			new ProductListController().doGet(request, response);
			
		}else { // 재고 수량이 충분하면 카트에 담기
			int num = Integer.parseInt(request.getParameter("num")); // 상품 번호
			super.mycart.AddCart(num, qty);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
				
		}
	}
}
