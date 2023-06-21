package com.shopping.controller;

import java.io.IOException;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.shopping.transport.VehicleController;
import com.shopping.utility.MyUtility;

// 서블릿 ex) boList가 들어오면 BoardListController로 가라

@WebServlet(urlPatterns= {"/ShopMall"}, initParams = { // 초기화 파라미터
		@WebInitParam(name = "txtSetting", value = "/WEB-INF/Settings.txt"),
		@WebInitParam(name = "TodoList", value = "/WEB-INF/TodoList.txt")
})
public class FrontController extends HttpServlet{
	private String txtSetting = null;
	private String TodoList = null;
	
	private String realPath = null;
	
	private ServletContext application = null;
	
	// map for setting.txt file
	private Map<String, String> SettingMap = null;
	
	// map for TodoList.txt file
	private Map<String, SuperController> TodoListMap = null;
		
		@Override
		public void init(ServletConfig config) throws ServletException {
			System.out.println(this.getClass() + " init() 메소드 호출됨");
			
			this.txtSetting = config.getInitParameter("txtSetting");
			System.out.println("Setting file name : "+this.txtSetting);
			
			this.TodoList = config.getInitParameter("TodoList");
			System.out.println("controller file name : "+this.TodoList);
			
			this.application = config.getServletContext();
			
			
			System.out.println();
			
			String txtSettingFile = this.application.getRealPath(txtSetting);
			System.out.println("Setting fullPath name : "+txtSettingFile);		
			
			String TodoListFile = this.application.getRealPath(TodoList);
			System.out.println("controller fullpath name : "+TodoListFile);
			
			this.SettingMap = MyUtility.getSettingMap(txtSettingFile);
			
			System.out.println("setting file element size : "+this.SettingMap.size());
			
			this.application.setAttribute("map", this.SettingMap);
			
			this.TodoListMap = MyUtility.getTodoListMap(TodoListFile);
			
			System.out.println("controller file element size : "+this.TodoListMap.size());
			
			String imsiPath = this.SettingMap.get("uploadPath");
			
			this.realPath = this.application.getRealPath(imsiPath);
			
			System.out.println("image upload realPath : " + realPath);
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.doProcess(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.doProcess(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		
		String command = request.getParameter("command");
		System.out.println("command is ["+command+"]");
		/* 업로드를 하면 command 값이 null값이다.
		command가 null이 들어오면 업로드 하는구나 라고 이해 */
		
		if(command==null) {
			System.out.println("file upload event invoked");
			MultipartRequest mr = MyUtility.getMultipartRequest(request, realPath);
			// JSP 교안 213page 참고 cos라이브러리의 링크 들어가서 공부하기
			
			if(mr!=null) { 
				// command가 null이 아닐 때 비로서 잘 들어왔음을 확인 prInsert가 작동
				command = mr.getParameter("command");
				
				// file upload object binding in application scope.
				// this.application.setAttribute("mr", mr); ← 1) 이렇게 하면 코딩이 너무 길어질 거 같아서
				MyUtility.deleteOldImageFile(realPath, mr);
				
				request.setAttribute("mr", mr); // 2) requset에 올림
				
			}else {
				System.out.println("MultipartRequest object is null");
			}
			
		}
		
		
		SuperController controller = this.TodoListMap.get(command);
		if(controller != null) {
			String method = request.getMethod().toLowerCase();
			
			if(method.equals("get")) {
				System.out.println(controller.toString() + " get method called");
				controller.doGet(request, response);
			}else {
				System.out.println(controller.toString() + " post method called");
				controller.doPost(request, response);
			}

		}else {
			System.out.println("request command is not found");
		}
	}
}
