<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>request 영역의 정보</h3>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>${requestScope.abcd.no}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${requestScope.abcd.name}</td>
		</tr>
		<tr>
			<td>좋아하는 노래</td>
			<td>${requestScope.abcd.sing}</td>
		</tr>
		<tr>
			<td>좋아하는 그룹</td>
			<td>${requestScope.abcd.group}</td>
		</tr>
		
	</table>
		<h3>session 영역의 정보</h3>
		회사명 : ${sessionScope.company}<br/>
		주소 : ${sessionScope.address}<br/>
		
		<h3>application 영역의 정보</h3>
		인사말 : ${applicationScope.hello}<br/>
</body>
</html>