package com.shopping.controller.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.CompositeDao;
import com.shopping.model.Combo05;

public class View05Controller extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		
		List<Combo05> lists = null;
		
		try {
			lists = dao.View05();
			request.setAttribute("lists", lists);
			super.GotoPage("view/View05.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
