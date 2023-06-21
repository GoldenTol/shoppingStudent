<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// 한글 깨짐 방지, 반드시 가장 상단에 명시할 것.
	request.setCharacterEncoding("UTF-8");
%>
<%
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"))+2;
	String image = request.getParameter("image");
	int money = Integer.parseInt(request.getParameter("money"));
	String gender = request.getParameter("gender");
	String[] hobbies = request.getParameterValues("hobby");
	
	String hobby = "";
	if(hobbies == null){
		hobby = "";
	}else{
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i]+", ";
		}
		hobby = hobby.substring(0, hobby.length() -2);
	}
	
	String job = request.getParameter("job");
	String comment = request.getParameter("comment");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td>이름</td>
		<td><%=name%></td>
		<br>
	</tr>
	<tr>
		<td>나이</td>
		<td><%=age%></td>
		<br/>
	</tr>
	<tr>
		<td>숨김파라미터</td>
		<td><%=money%></td>
		<br/>
	</tr>
	<tr>
		<td>이미지</td>
		<td><%=image%></td>
		<br/>
	</tr>
	<tr>
		<td>성별</td>
		<td><%=gender%></td>
		<br/>
	</tr>
	
	<tr>
		<td>취미</td>
		<td><%=hobby%></td>
		<br/>
	</tr>
	<tr>
		<td>직업</td>
		<td><%=job%></td>
		<br/>
	</tr>
	<tr>
		<td>코멘트</td>
		<td><%=comment%></td>
		<br/>
	</tr>
</table>
</body>
</html>