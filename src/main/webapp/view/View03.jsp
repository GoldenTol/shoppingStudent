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
  	<h2>주문 정보</h2>
  	<p>회원별 주문 정보를 조회합니다.<br/>
주문한 고객의 이름, 상품명, 주문일자, 구매수량, 금액을 구해 봅니다.</p>
  	  		         
  	<table class="table table-striped">
		<thead>
			<tr align="center">
				<th>주문자</th>
     	  	 	<th>상품명</th>
     	  	 	<th>주문 일자</th>
     	  	 	<th>구매 수량</th>
     	  	 	<th>가격</th>
     	  	 	<th>금액</th>
     	 	</tr>
		</thead>
		
		<tbody>
			<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td align="center">${bean.mname}</td>
					<td align="center">${bean.pname}</td>
					<td align="center">${bean.orderdate}</td>
					<td align="center">
						<fmt:formatNumber value="${bean.qty}" pattern="###,###"/> 원
					</td>
					<td align="center">
						<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원
					</td>
					<td align="center">
						<fmt:formatNumber value="${bean.amount}" pattern="###,###"/> 원
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>