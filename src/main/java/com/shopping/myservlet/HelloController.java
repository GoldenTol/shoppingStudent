package com.shopping.myservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.Person02;

// @webServlet : 나는 웹 서비스를 지원하는 서블릿입니다.
@WebServlet(urlPatterns = {"/hello"})
public class HelloController extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿이 초기화 됩니다.");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getMethod();
		System.out.println("요청 메소드 : " + method);
		
		if(method.equalsIgnoreCase("post")){
			doPost(request, response);
			
		}else {
			doGet(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + "doGet()");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass() + "doPost()");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] hobbies = request.getParameterValues("hobby");
		
		String hobby = "";
		
		if(hobbies == null) {
			hobby = "";
		}else {
			for(int i=0; i<hobbies.length; i++) {
				hobby += hobbies[i] + ", ";
			}
			hobby = hobby.substring(0, hobby.length() -2);
		}
		
		Person02 bean = new Person02(id, name, age, hobby);
		
		request.setAttribute("asdf", bean);
		
		// 리다이렉션 방식 : request 객체를 새로 만들기 때문에 데이터가 휘발됩니다.
		// response.sendRedirect("to02.jsp");
		
		// 포워딩 방식을 사용하면 문제 없음
		String gotopage = "example/to02.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(gotopage);
		dispatcher.forward(request, response);
	}
}
