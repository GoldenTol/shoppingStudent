package com.shopping.controller;

import java.io.IOException;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.mall.CartManager;
import com.shopping.model.Board;
import com.shopping.model.Member;

// 하위 컨트롤러들이 공통적으로 사용하는 기능을 여기에 작성하도록 합니다.
// common.jsp와 비슷한 역할을 한다.
public abstract class SuperClass implements SuperController{
	private HttpServletRequest request = null;
	private HttpServletResponse reponse = null;
	
	protected HttpSession session = null;
	protected Member loginfo = null; // 로그인 여부를 파악하는 변수
	protected CartManager mycart = null;
	
	private String contextPath = null;
	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.reponse = response;
		this.session = request.getSession();
		this.loginfo = (Member)this.session.getAttribute("loginfo");
		
		// 세션 영역의 카트 정보를 읽어 오고, 만약 카트가 없으면 새 카트를 준비합니다.
		this.mycart = (CartManager)this.session.getAttribute("mycart");
		if(this.mycart==null) {this.mycart=new CartManager();}
		this.contextPath = request.getContextPath();
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.reponse = response;
		this.session = request.getSession();
		this.loginfo = (Member)this.session.getAttribute("loginfo");
		this.mycart = (CartManager)this.session.getAttribute("mycart");
		if(this.mycart==null) {this.mycart=new CartManager();}
		this.contextPath = request.getContextPath();
	}

	public void GotoPage(String gotopage) {
		// 요청한 페이지로 이동시킵니다.
		
		RequestDispatcher dispatcher = null;
		try {
			dispatcher = request.getRequestDispatcher(gotopage);
			dispatcher.forward(request, reponse);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAlertMessage(String message) {
		// session 영역에 "alertMsg"라는 이름으로 사용자에게 경고/오류/주의 문구를 띄워줍니다.
		// in common.jsp 파일 하단 참조
		this.session.setAttribute("alertMsg", message);
	}

	public String getUrlInfo(String todoCommand) {
		String urlPrefix = contextPath + "/ShopMall?command=";
		return urlPrefix + todoCommand;
	}

	public void youNeededLogin() {
		// 로그인을 하지 않았으면 로그인 페이지로 이동시킵니다.
		String message = "로그인이 필요한 서비스입니다.";
		this.setAlertMessage(message);
		this.GotoPage("member/meLoginForm.jsp");
		
	}

	

}
