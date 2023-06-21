package com.shopping.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.shopping.controller.SuperClass;
import com.shopping.dao.ProductDao;
import com.shopping.model.Product;

public class ProductUpdateController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = 0;
		ProductDao dao = null;
		Product bean = null;
		
		try {
			dao = new ProductDao();
			num = Integer.parseInt(request.getParameter("num")); // 수정할 상품 번호
			
			bean = dao.GetDataByPk(num);
			
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("product/prUpdateForm.jsp");
		
		}catch(Exception e){
			e.printStackTrace();
			super.GotoPage("product/prList.jsp");
		}
	}
	
	public int getNumberData(String parameter) { // int 타입 숫자들이 안들어올 때 처리해주는 메소드
		boolean flag = false;
		
		/* String mystock = mr.getParameter("stock"); ← 2) 이거 필요 없음
		bean.setStock(getNumberData(mr.getParameter("stock"))); ← 1) 이거 적었기 때문에 */
		
		flag = parameter==null || parameter.equals("") || parameter.equals("null");
		// 문자열 null과 그냥 null을 다 막아둠
		
		System.out.println(this.getClass() + "getNumberData method called");
		
		return !flag ? Integer.parseInt(parameter) : 0;
		
		/* 
		if(!flag) {
			bean.setStock(Integer.parseInt(mystock));
		}else {
			bean.setStock(0);;
		} ↑ 위의 삼항 연산자로 대체 
		*/ 
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		// String writer = request.getParameter("writer"); ← 이렇게 쓰면 안됨
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr"); // set의 반대인 get 사용
		
		Product bean = new Product();
		
		// 상품 등록과는 다르게 수정은 상품번호를 반드시 챙겨야 합니다.(pk)
		bean.setNum(getNumberData(mr.getParameter("num")));

		bean.setName(mr.getParameter("name"));
		bean.setCompany(mr.getParameter("company"));
		bean.setComments(mr.getParameter("comments"));
		bean.setRemark(mr.getParameter("remark"));
		bean.setCategory(mr.getParameter("category"));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		// 객체형태로 올라가기 때문에
	
		bean.setStock(getNumberData(mr.getParameter("stock")));
		bean.setPrice(getNumberData(mr.getParameter("price")));
		bean.setPoint(getNumberData(mr.getParameter("point")));

		ProductDao dao = new ProductDao();
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) {
				super.GotoPage("product/prUpdateForm.jsp");
				
			}else {
				String gotopage = super.getUrlInfo("prList");
				gotopage += "&pageNumber=" + mr.getParameter("pageNumber");
				gotopage += "&pageSize=" + mr.getParameter("pageSize");
				gotopage += "&mode=" + mr.getParameter("mode");
				gotopage += "&keyword=" + mr.getParameter("keyword");
				
				response.sendRedirect(gotopage);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
