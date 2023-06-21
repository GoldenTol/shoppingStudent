<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// 한글 깨짐 방지, 반드시 가장 상단에 명시할 것.
	request.setCharacterEncoding("UTF-8");
%>
<%
	/* 
	1. String xx에서 xx는 변수, 
	2. getParameter("id")의 id는 파라미터, 
	3. from01파일의 name="id"의 id는 파라미터 
	4. <body>의 <%=xx%꺽쇠의 xx는 변수
	따라서 1번과 4번의 값(여기서 xx)이 같아야 하고
	2번과 3번의 값(여기서 id)은 같아야 한다.
	*/
	String xx = request.getParameter("id");
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"))+5;
	
	String[] hobbies = request.getParameterValues("hobby");
	/* 문자열배열의 기본자료형이 참조 자료형이기 때문에 ([Ljava.lang.String;@7f80719f) ← tostring()의 @가 등장 */
	
	/* 폼 유효성 검사에서 최소 1개이상 선택하게 만들던가 널 값 체크하기 */
	String hobby = "";
	if(hobbies == null){
		hobby = "";
	}else{
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i]+", ";
		}
		hobby = hobby.substring(0, hobby.length() -2);
	}
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	아이디 : <%=xx %><br/>
	아이디 길이 : <%=xx.length()%><br/>
	이름 : <%=name%><br/>
	나이 : <%=age%><br/>
	취미 : <%=hobby%><br/>
</body>
</html>