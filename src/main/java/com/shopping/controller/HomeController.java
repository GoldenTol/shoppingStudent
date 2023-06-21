package com.shopping.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 홈 컨트롤러
public class HomeController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		super.doGet(request, reponse);
		
		String gotopage = "common/home.jsp";
		super.GotoPage(gotopage);
	}
}
