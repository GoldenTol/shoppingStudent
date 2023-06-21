package com.shopping.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;
import com.shopping.model.Board;

public class BoardListController03 extends SuperClass{

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// boList가 넘어오면 담당하기로 약속한 곳 / Dao로 요청만 하는 곳
		
		super.doGet(request, response);

		BoardDao dao = new BoardDao();
		
		List<Board> lists = null;
		
		try {
			lists = dao.SelectAll();
			
			request.setAttribute("datalist", lists);
			
			String gotopage = "board/boList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
