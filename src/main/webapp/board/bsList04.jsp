shopping<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.shopping.model.Board"%>
<%@ page import="com.shopping.dao.BoardDao"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
  	$(document).ready(function(){
  		$('.rounded-pill').attr('opacity', '0.7';)
  	});
  </script>
  <style type="text/css">
  	.rounded-pill{opacity:0.8;}
  </style>
</head>
<body>

<div class="container mt-3">
  <h2>게시물 목록</h2>
  <p>사용자들이 게시한 목록을 보여 주는 페이지</p>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>글번호</th>
        <th>작성자</th>
        <th>글제목</th>
        <th>글내용</th>
        <th>작성일자</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach var="bean" items="${datalist}">
    		<tr>
    			<td align="center">${bean.no}</td>
        		<td>${bean.writer}</td>
        		<td>
        			<a href="<%=notWithFormTag%>boDetail&no=${bean.no}">
        				<c:forEach begin="1" end="${bean.depth}"> 
        					<span class="badge rounded-pill bg-danger">re</span>
        				</c:forEach>
        				${bean.subject}
        			</a>
        		</td>
        		<td>${bean.content}</td>
        		<td>${bean.regdate}</td>
        		<td>
        			<c:if test="${bean.readhit >= 50 }">
        				<span class="badge rounded-pill bg-info">${bean.readhit}</span>
        			</c:if>
        			<c:if test="${bean.readhit < 50 }">
        				<span class="badge rounded-pill bg-warning">${bean.readhit}</span>
        			</c:if>
        		</td>
        	</tr>
    	</c:forEach>
    </tbody>
  </table>
  
 	 <ul class="pagination justify-content-center">
 		 <li class="page-item"><a class="page-link" href="#">Previous</a></li>
		 <li class="page-item"><a class="page-link" href="#">1</a></li>
 		 <li class="page-item active"><a class="page-link" href="#">2</a></li>
 		 <li class="page-item"><a class="page-link" href="#">3</a></li>
 		 <li class="page-item disabled"><a class="page-link" href="#">4</a></li>
 		 <li class="page-item"><a class="page-link" href="#">5</a></li>
		 <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
</div>

</body>
</html>
    