package com.shopping.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.dao.BoardDao;
import com.shopping.model.Board;

public class BoardReplyController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		//답글의 최대 깊이 제한하기
		if(depth==3) {
			String message = "답글의 깊이는 최대 3까지 입니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			return;
			
		}
		
		int replyCount = 0; // 총 답글의 개수
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		BoardDao dao = new BoardDao();
		
		try {
			replyCount = dao.GetReplyCount(groupno);
			
			if(replyCount >= 5) {
				String message = "답글의 최대수를 초과하여 답글 작성이 불가능합니다.";
				super.setAlertMessage(message);
				new BoardListController().doGet(request, response);
				super.GotoPage("board/boList.jsp");
				
			}else {
				super.GotoPage("board/boReplyForm.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();

		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		// 답글도 신규 작성 개념이므로, no칼럼은 신경 쓰지 않아도 됩니다.
		// 기타 조회수(readhit)와 비고(remark) 컬럼도 명시하지 않습니다.
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		// String remark = request.getParameter("writer");
		
		// 다음은 답글을 위하여 반드시 챙겨야 하는 파라미터입니다.
		int groupno = Integer.parseInt(request.getParameter("groupno"))  ;
		int orderno = Integer.parseInt(request.getParameter("orderno"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		Board bean = new Board();
		
		bean.setWriter(writer);
		bean.setSubject(subject);
		bean.setContent(content);
		bean.setRegdate(regdate);
		bean.setGroupno(groupno); // 넘어온 그룸 번호는 동일하게
		bean.setOrderno(orderno + 1); // 정렬 순서는 +1
		bean.setDepth(depth + 1); // 글의 깊이도 +1
		
		int cnt = -1;
		BoardDao dao = new BoardDao();
		
		try {
			cnt = dao.ReplyData(bean, orderno);
			new BoardListController().doGet(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
