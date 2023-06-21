package com.shopping.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;
import com.shopping.model.Board;

public class BoardDetailController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		this.doGet(request, response);
	}
	
	@Override // 회원의 아이디를 이용하여 회원 상세 정보를 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		if(loginfo==null) {
			super.setAlertMessage("로그인이 필요한 정보입니다.");
			super.GotoPage("member/meLoginForm.jsp"); 
			return ;
		}
		
		int no = Integer.parseInt(request.getParameter("no")) ;		
		BoardDao dao = new BoardDao() ;		
		Board bean = null ;
		int cnt = -1 ;
		
		try {
			bean = dao.GetDataByPk(no);	
			
			if(loginfo.getId().equals(bean.getWriter()) == false) {
				// 조회수 업데이트 하기
				cnt = dao.UpdateReadhit(no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean==null){
			super.setAlertMessage("잘못된 게시물 정보입니다.");
			super.GotoPage("common/home.jsp");
		}else {
			request.setAttribute("bean", bean) ;
			super.GotoPage("board/boDetail.jsp"); 
		}
	}
}
