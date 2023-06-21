<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.shopping.model.Member"%>
<%@ page import="com.shopping.dao.MemberDao"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <style type="text/css">
  	#backButton{margin:auto;}
  </style>
  
</head>
<body>
	<div class="container mt-3">
	<h2>${requestScope.bean.name}님의 회원정보</h2>
            
		<table class="table table-striped">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center">아이디</td>
					<td>${bean.id}</td>
				</tr>
				<tr>
					<td align="center">성별</td>
					<td>${bean.gender}</td>
				</tr>
				<tr>
					<td align="center">취미</td>
					<td>${bean.hobby}</td>
				</tr>
				<tr>
					<td align="center">가입 일자</td>
					<td>${bean.hiredate}</td>
				</tr>
				<tr>
					<td align="center">적립 포인트</td>
					<td>${bean.mpoint}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-warning" onclick="history.back();">
				이전 페이지
			</button>
		</div>
		
	</div>
</body>
</html>
    