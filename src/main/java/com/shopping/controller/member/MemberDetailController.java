package com.shopping.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.MemberDao;
import com.shopping.model.Member;

public class MemberDetailController extends SuperClass{
	
	@Override // 회원의 아이디를 이용하여 회원 상세정보를 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		
		Member bean = null;
		
		try {
			bean = dao.getDataByPk(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if(bean==null) {
		// getDataByPk(id);를 try...catch를 했을 때 if(bean==null)의 bean에 빨간줄이 들어오면 위 Member bean;을 Member bean = null;로 초기화해야 한다.
			super.setAlertMessage("잘못된 회원 정보입니다.");
			super.GotoPage("common/home.jsp");
		}else { // 이리로 왔다는건 그 사람이 존재한다는 것
			request.setAttribute("bean", bean);
			super.GotoPage("member/meDetail.jsp");
		}
	}
}
