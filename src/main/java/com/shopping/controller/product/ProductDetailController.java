package com.shopping.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.ProductDao;
import com.shopping.model.Product;

public class ProductDetailController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ProductDao dao = new ProductDao();
		
		try {
			Product bean = dao.GetDataByPk(num);
			request.setAttribute("bean", bean);
			super.GotoPage("product/prDetail.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
