package com.shopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperController {
	// 각 Controller들이 공통적으로 구현해야 할 메소드 목록을 정의해 줍니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception; 
	// 항상 두가지 (요청과 응답이)가 만들어진다.
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
