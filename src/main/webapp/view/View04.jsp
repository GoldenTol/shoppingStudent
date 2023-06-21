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
  	<h2>고객별 매출 총액</h2>
  	<p>회원들이 얼마나 많은 매출을 발생시켰는지 집계해 조회합니다.<br/>
각 고객들에 대한 매출 총액을 구해 봅니다.</p>
  	  		         
  	<table class="table table-striped">
		<thead>
			<tr align="center">
				<th>고객명</th>
     	  	 	<th>매출 총액</th>
     	 	</tr>
		</thead>
		
		<tbody>
			<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td align="center">${bean.id}</td>
					<td align="center">
						<fmt:formatNumber value="${bean.sumtotal}" pattern="###,###"/> 원
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>