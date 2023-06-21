<%@ page language="java" contentType="text/html; charset=UTF-8"
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
  
  <style type="text/css">
  	#backButton{margin:auto;}
  </style>
  
</head>
<body>
	<div class="container mt-3">
	<h2>${requestScope.bean.no}번 게시글 정보</h2>
		<table class="table table-striped">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center">작성자</td>
					<td>${bean.writer}</td>
				</tr>
				<tr>
					<td align="center">글제목</td>
					<td>${bean.subject}</td>
				</tr>
				<tr>
					<td align="center">글내용</td>
					<td>${bean.content}</td>
				</tr>
				<tr>
					<td align="center">작성일자</td>
					<td>${bean.regdate}</td>
				</tr>
				<tr>
					<td align="center">조회수</td>
					<td>${bean.readhit}</td>
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
    