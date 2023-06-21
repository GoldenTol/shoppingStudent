package com.shopping.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;
import com.shopping.model.Board;

public class BoardUpdateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = 0;
		BoardDao dao = null;
		Board bean = null;
		
		try {
			dao = new BoardDao();
			no = Integer.parseInt(request.getParameter("no"));
			
			bean = dao.GetDataByPk(no);
			
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("board/boUpdateForm.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			super.GotoPage("board/boList.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		Board bean = new Board();
		
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setContent(request.getParameter("content"));
		bean.setRegdate(request.getParameter("regdate")); // 작성일자
		bean.setReadhit(Integer.parseInt(request.getParameter("readhit"))); // 조회수
		bean.setRemark(request.getParameter("remark"));
		bean.setGroupno(Integer.parseInt(request.getParameter("groupno"))); // 게시글 그룹 번호
		bean.setOrderno(Integer.parseInt(request.getParameter("orderno"))); // 게시할 순번
		bean.setDepth(Integer.parseInt(request.getParameter("depth"))); // 글의 깊이
		
		BoardDao dao = new BoardDao();
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) {
				super.GotoPage("board/boUpdateForm.jsp");
				
			}else {
				String gotopage = super.getUrlInfo("boList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");
				
				response.sendRedirect(gotopage);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
