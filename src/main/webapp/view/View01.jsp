<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
  	<h2>회원과 게시물</h2>
  	<p>회원들이 게시물을 얼마나 많이 작성했는지를 확인합니다.</p>
  		         
  	<table class="table table-striped">
		<thead>
			<tr align="center">
				<th>이름</th>
     	  	 	<th>글제목</th>
      	  		<th>글내용</th>
      	  		<th>작성 일자</th>
     	 	</tr>
		</thead>
		
		<tbody>
			<c:forEach var="bean" items="${requestScope.lists}">
			<!-- View01Controller의 request.setAttribute("lists", lists);의 lists가 들어온다. -->
				<tr>
					<td align="center">${bean.name}</td>
              	 	<td align="center">${bean.subject}</td>
             	  	<td align="center">${bean.content}</td>
              	 	<td align="center">${bean.regdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>