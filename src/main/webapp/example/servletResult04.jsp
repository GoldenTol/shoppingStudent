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
			<td>아이디</td>
			<td>${requestScope.abcd.id}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${requestScope.abcd.password}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${requestScope.abcd.name}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${requestScope.abcd.gender}</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>${requestScope.abcd.age}</td>
		</tr>
		<tr>
			<td>이메일 주소</td>
			<td>${requestScope.abcd.email}</td>
		</tr>
		<tr>
			<td>연령대</td>
			<td>${requestScope.abcd.ages}</td>
		</tr>
		
	</table>
	
</body>
</html>