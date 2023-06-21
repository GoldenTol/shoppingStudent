package com.shopping.myservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.Human;

@WebServlet(urlPatterns = {"/hihihi"})
public class Information extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿이 초기화됩니다.");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
	
	String method = request.getMethod();
		System.out.println("요청 메소드 : "+method);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String ages = request.getParameter("ages");
		
		ages = "";
	      if(age>=0 && age<=35) {
	         ages = "청년";
	      }else if(age>=36 && age<=60) {
	         ages = "중년";
	      }else if(age>=61) {
	         ages = "노년";
	      }

		Human bean = new Human();
		bean.setId(id);
		bean.setPassword(password);
		bean.setName(name);
		bean.setGender(gender);
		bean.setAge(age);
		bean.setEmail(email);
		bean.setAges(ages);

		request.setAttribute("abcd", bean);
			
		String gotopage = "example/servletResult04.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
		
		
		
	}
	
}
