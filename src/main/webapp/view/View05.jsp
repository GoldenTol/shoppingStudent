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
  	<h2>회원별 주문 건수</h2>
  	<p>회원별 주문 건수를 집계해 조회합니다. <br/>
회원별 주문 건수를 조회하되, 주문이 없는 고객도 같이 조회합니다.</p>
  	  		         
  	<table class="table table-striped">
		<thead>
			<tr align="center">
				<th>주문자</th>
     	  	 	<th>주문 건수</th>
     	 	</tr>
		</thead>
		
		<tbody>
			<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td align="center">${bean.id}</td>
					<td align="center">
						<fmt:formatNumber value="${bean.cnt}"/> 개
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>