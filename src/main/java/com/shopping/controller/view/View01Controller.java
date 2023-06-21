package com.shopping.controller.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.CompositeDao;
import com.shopping.model.Combo01;

public class View01Controller extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		
		List<Combo01> lists = null;
		
		try {
			lists = dao.View01();
			request.setAttribute("lists", lists);
			super.GotoPage("view/View01.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
