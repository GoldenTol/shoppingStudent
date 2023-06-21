package com.shopping.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.controller.SuperClass;
import com.shopping.dao.CategoryDao;
import com.shopping.dao.ProductDao;
import com.shopping.model.Category;
import com.shopping.model.Product;

public class ProductInsertController extends SuperClass{
	
	@Override // 관리자가 common의 상품등록을 누르면 이리로 온다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 상품 등록 화면이 보이기 전에 category 목록 테이블을 읽어서 콤보 박스에 채워 넣기
		CategoryDao dao = new CategoryDao();
		List<Category> categories = null;
		
		try {
			categories = dao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		request.setAttribute("categories", categories);
		
		
		String gotopage = "product/prInsertForm.jsp";
		super.GotoPage(gotopage);
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		// String writer = request.getParameter("writer"); ← 이렇게 쓰면 안됨
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr"); // set의 반대인 get 사용
		
		Product bean = new Product();
		
		// 상품 등록 시 상품번호는 시퀀스가 알아서 처리해주므로 신경 쓸 필요가 없습니다.(pk)
		// bean.setNum(mr.getParameter("num"));
		
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
			cnt = dao.InsertData(bean);
			
			if(cnt==-1) {
				super.GotoPage("product/prInsertForm.jsp");
				
			}else {
				new ProductListController().doGet(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
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
}
