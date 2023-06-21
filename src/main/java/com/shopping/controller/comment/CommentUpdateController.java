package com.shopping.controller.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.board.BoardDetailController;
import com.shopping.dao.CommentDao;
import com.shopping.model.Comment;

public class CommentUpdateController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		CommentDao dao = new CommentDao();
		
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		
		System.out.println(cnum);
		System.out.println(no);
		System.out.println(content);
		
		Comment bean = new Comment();
		bean.setCnum(Integer.parseInt(request.getParameter("cnum")));
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		bean.setWriter(loginfo.getId());
		bean.setContent(request.getParameter("content"));
		
		int cnt = -1;
		
		try {
			
			cnt = dao.UpdateData(bean);
			
			new BoardDetailController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
