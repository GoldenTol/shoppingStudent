package com.shopping.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.ProductDao;
import com.shopping.model.Product;
import com.shopping.utility.Paging;

public class ProductListController extends SuperClass{
	
	@Override // 상품 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		
		ProductDao dao = new ProductDao();
		List<Product> lists = null;
		boolean isGrid = true;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);  
			String url = request.getContextPath() + "/ShopMall?command=prList"; 
			// 프로젝트 이름을 가져오려면 boList만으로는 부족함
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo);
			
			super.GotoPage("product/prList.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
