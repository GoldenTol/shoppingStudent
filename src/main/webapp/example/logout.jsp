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
<%
	session.invalidate();	
	response.sendRedirect("bsMemberLogin2.jsp");
%>
</body>
</html>