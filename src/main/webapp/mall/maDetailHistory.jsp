<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		span {font-size:1.2rem;}
	</style>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
    	<div class="col-sm-1"></div>
    	<div class="col-sm-10">
    	<h2>
			<span>${sessionScope.loginfo.name}(${sessionScope.loginfo.id})</span>
        		님의 주문 상세 내역

		</h2>
		<p>
			${sessionScope.loginfo.name}고객님이 ${order.orderdate}에
			구매하신 상품에 대한 세부 결제 내역입니다.
		</p>
		<br/>
		<h3>주문 내역</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>제품명</th>
					<th>이미지</th>
					<th>단가</th>
					<th>수량</th>
					<th>금액</th>
				</tr>
			</thead>
			<tbody>
				<!-- 변수 : totalAmount(총금액) -->
				<c:set var = "totalAmount" value="0" />
				
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td valign="middle">${bean.pname}</td>
					<td valign="middle">
						<img alt="${bean.image01}" width="45" height="45" 
                    		src="<%=contextPath%>/image/${bean.image01}">
					</td>
					<td valign="middle">
						<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원
					</td>
					<td valign="middle">
						<fmt:formatNumber value="${bean.qty}" pattern="###,###"/> 개
					</td>
					<td valign="middle">
						<c:set var="amount" value="${bean.price*bean.qty}"/> 
						<c:set var="totalAmount" value="${totalAmount + amount}"/> 
						<fmt:formatNumber value="${amount}" pattern="###,###"/> 원
					</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
						합계 : <fmt:formatNumber value="${totalAmount}" pattern="###,###"/> 원&nbsp;&nbsp;
					</td>
				</tr>
				
				<!-- 배송비(shipExpense) 구하기 -->
				<c:set var="shipExpense" value="0"/>
				<c:choose>
					<c:when test="${totalAmount >= 100000}">
						<c:set var="shipExpense" value="0"/>
					</c:when>
					<c:when test="${totalAmount >= 50000}">
						<c:set var="shipExpense" value="2000"/>
					</c:when>
					<c:otherwise>
						<c:set  var="shipExpense" value="4000"/>
					</c:otherwise>
				</c:choose>
				<tr>
					<td colspan="5" align="right">
						배송비 : <fmt:formatNumber value="${shipExpense}" pattern="###,###"/> 원&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="5" align="right">
						<c:set var="finalAmount" value="${totalAmount + shipExpense}"/> 
						최종금액 : <fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원&nbsp;
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<h3>결제 정보</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>				
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center">주문 번호</td>				
					<td align="left">${requestScope.order.oid}</td>				
					<td align="center">주문 일자</td>				
					<td align="left">${requestScope.order.orderdate}</td>				
				</tr>
				<tr>
					<td align="center">주문 총액</td>				
					<td align="left">
						<fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원
					</td>				
					<td align="center">할인 금액</td>				
					<td align="left">0 원</td>				
				</tr>
				<tr>
					<td align="center">결제 금액</td>				
					<td align="left">
						<fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원
					</td>				
					<td align="center">결제 상태</td>				
					<td align="left">결제 완료</td>				
				</tr>
			</tbody>
		</table>
		<br/>
		<h3>배송 정보</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>				
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center" width="20%">받으시는 분</td>				
					<td align="left" width="80%">
						${sessionScope.loginfo.name}(${sessionScope.loginfo.id}) 님
					</td>			
				</tr>
				<tr>
					<td align="center" width="20%">적립 포인트</td>				
					<td align="left" width="80%">
						<fmt:formatNumber value="${sessionScope.loginfo.mpoint}" pattern="###,###"/> 원
					</td>			
				</tr>
				<tr>
					<td align="center" width="20%">배송 방법</td>				
					<td align="left" width="80%">택배</td>			
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-warning" onclick="history.back();">
				이전 페이지
			</button>
		</div>
		</div>
    	<div class="col-sm-1"></div>
 	 	</div>
	</div>
	<br/>
</body>
</html>