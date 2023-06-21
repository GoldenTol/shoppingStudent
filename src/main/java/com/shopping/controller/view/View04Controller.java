package com.shopping.controller.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.CompositeDao;
import com.shopping.model.Combo04;

public class View04Controller extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		
		List<Combo04> lists = null;
		
		try {
			lists = dao.View04();
			request.setAttribute("lists", lists);
			super.GotoPage("view/View04.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
