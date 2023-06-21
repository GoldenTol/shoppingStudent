package com.shopping.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;

public class BoardDeleteController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		BoardDao dao = new BoardDao(); 
		int cnt = -1;
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			cnt = dao.DeleteData(no);
			new BoardListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
