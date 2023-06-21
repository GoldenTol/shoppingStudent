<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=contextPath%>/world" method="post">
		이름 : <input type="text" name="name" value="공실"><br/>
		성별 : 
		<input type="radio" name="gender" value="여성" checked="checked">여성
		<input type="radio" name="gender" value="남성">남성<br/>
		직업 : 
		<input type="checkbox" name="job" value="취준생" checked="checked">취준생
		<input type="checkbox" name="job" value="직장인">직장인
		<input type="checkbox" name="job" value="프리랜서">프리랜서
		<input type="checkbox" name="job" value="사업가">사업가
		<input type="checkbox" name="job" value="공무원">공무원
		<br/>
		<input type="submit" value="전송">
		
	</form>
	
	<br/>
	앵커 태그
	<br/>
	<a href="to01.jsp?id=kim&age=50">get방식 하드코딩</a>
	<br/>
	<% 
		String id = "park";
		int age = 30;
	%>
	<a href="to01.jsp?id=<%=id%>&age=<%=age%>">get방식 변수사용</a>
	<br/>
	<%
		request.setAttribute("id", "choi");
		request.setAttribute("age", 20);
	%>
	<a href="to01.jsp?id=${requestScope.id}&age=${requestScope.age}">EL방식 변수사용</a>
	
</body>
</html>