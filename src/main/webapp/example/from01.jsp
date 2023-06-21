<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="to01.jsp" method="post">
		아이디 : <input type="text" name="id" value="hong"><br/>
		이름 : <input type="text" name="name" value="홍길동"><br/>
		나이 : <input type="text" name="age" value="20"><br/>
		취미 : 
		<input type="checkbox" name="hobby" value="식도락회 모임" checked="checked">식도락회 모임
		<input type="checkbox" name="hobby" value="수영">수영
		<input type="checkbox" name="hobby" value="자전거 라이딩">자전거 라이딩
		<input type="checkbox" name="hobby" value="게임">게임
		<input type="checkbox" name="hobby" value="TV 시청">TV 시청
		<br/>
		<input type="submit" value="전송">
		<!-- p67 참고 문자열 결합 공부하기
		id=hong 
		name=홍길동
		hobby=수영
		왼쪽은 파라미터 이름 / 오른쪽은 파라미터 값 
		파라미터는 3개 넘어간다.
		-->
	</form>
	
	<br/>
	앵커 태그
	<!-- 
	파라미터를 get방식으로 보낼 때는 문자열 결합을 해야 한다. 
	우리는 이걸 '하이퍼링크'라고 부른다.
	-->
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