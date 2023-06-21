package com.shopping.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;
import com.shopping.model.Board;
import com.shopping.utility.Paging;

public class BoardListController extends SuperClass{

	
	@Override // 게시물 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// boList가 넘어오면 담당하기로 약속한 곳 / Dao로 요청만 하는 곳
		
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
		

		BoardDao dao = new BoardDao();
		List<Board> lists = null;
		boolean isGrid = false;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword); //실제 데이터베이스에서 가져와야 합니다. 
			String url = super.getUrlInfo("boList");
			// String url = request.getContextPath() + "/ShopMall?command=boList"; 
			// 프로젝트 이름을 가져오려면 boList만으로는 부족함
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			// 변수들이 다 담겨 Paging이 호출 되고 메소드로 넘어감
			
			System.out.println("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter());
			
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); // 페이징 정보를 바인딩
			
			String gotopage = "board/boList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
