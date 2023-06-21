<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ↑ 페이지 지시어 -->

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<!-- ↑ 인클루드 지시어 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<!-- 1부터 10까지의 총합 구하기 -->
	
	<!-- 
		c:set이 사이의 값이 없다면/c:set
		c:set var="total" value="0" / 
		이렇게 쓰는 것을 허용해준다.
		다만 값이 있을 경에는 이렇게 축약할 수 없다.
	-->
	<!-- 
		int total = 0; 과 같다. 
		var은 무조건 변수명
	-->
	
	<c:set var="total" value="0"></c:set>
	<c:forEach var="i" begin="1" end="10" step="1">
	<!-- 초기, 조건, 증감식 -->
		<c:set var="total" value="${total + i}"></c:set>
	</c:forEach>
	<c:out value="${total}"></c:out>
	</br>

	<!-- 1부터 10까지 짝수/홀수 각각의 총합 구하기 -->
	<c:set var="odd" value="0"></c:set>
	<c:set var="even" value="0"></c:set>
	
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:if test="${i%2 ==1}">
			<c:set var="odd" value="${odd +i}"/>
		</c:if>
		<c:if test="${i%2 ==0}">
			<c:set var="even" value="${even +i}"/>
		</c:if>
	</c:forEach>
	
	</br>
	홀수 총합 : <c:out value="${odd}"></c:out>
	<br/>
	짝수 총합 : <c:out value="${even}"></c:out>
	</br>


	<!-- 1부터 10까지의 3의 배수 총합과 4의 배수 총합, 나머지의 합 구하기 -->
	<c:set var="result01" value="0"></c:set>
	<c:set var="result02" value="0"></c:set>
	<c:set var="result03" value="0"></c:set>
	
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:choose>
			<c:when test="${i%3 ==0}">
				<c:set var="result01" value="${result01 +i}"/>
			</c:when>
			<c:when test="${i mod 4 ==0}">
				<c:set var="result02" value="${result02 +i}"/>
			</c:when>
			<c:otherwise>
				<c:set var="result03" value="${result03 +i}"/>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	</br>
	결과01 : <c:out value="${result01}"/>
	</br>
	결과02 : <c:out value="${result02}"/>
	</br>
	결과03 : <c:out value="${result03}"/>
	</br>
	
	<c:set var="id" value="hong"/>
	
	</br>
	<c:if test="${empty id}">
		비어 있슴
		</br>
	</c:if>
	<c:if test="${not empty id}">
		비어 있지 않음
		</br>
	</c:if>
	</br>
	
	<!-- 1+2+3+...+99 -->
	<c:set var="odd" value="0"></c:set>
	<c:set var="even" value="0"></c:set>
	
	<c:forEach var="i" begin="1" end="99" step="1">
		<c:if test="${i%2 ==1}">
			<c:set var="odd" value="${odd +i}"/>
		</c:if>
		<c:if test="${i%2 ==0}">
			<c:set var="even" value="${even +i}"/>
		</c:if>
	</c:forEach>
	
	
	
	</br>
	홀수 총합 : <c:out value="${odd}"></c:out>
	<br/>
	짝수 총합 : <c:out value="${even}"></c:out>
	</br>
	
	</br>
	<!-- 학점 매기기 -->
	<c:set var="jumsu" value="82"/>
	시험 점수 : 
	<c:out value="${jumsu}"/></br>
	
	</br>if 구문을 이용한 방식</br>
	
	<c:if test="${jumsu >= 90}">
		학점 : A (수)
		</br>
	</c:if>
	<c:if test="${jumsu >= 80 and jumsu <90}">
		학점 : B (우)
		</br>
	</c:if>
	<c:if test="${jumsu >= 70 and jumsu <80}">
		학점 : C (미)
		</br>
	</c:if>
	<c:if test="${jumsu >= 60 and jumsu <70}">
		학점 : D (양)
		</br>
	</c:if>
	<c:if test="${jumsu < 60}">
		학점 : F (가)
		</br>
	</c:if>
	</br>
	
	
	</br>Choose 구문을 이용한 방식</br>
	<c:choose>
		<c:when test="${jumsu >= 90}">
			학점 : A (수)</br>
		</c:when>
		<c:when test="${jumsu >= 80 and jumsu <90}">
			학점 : B (우)</br>
		</c:when>
		<c:when test="${jumsu >= 70 and jumsu <80}">
			학점 : C (미)</br>
		</c:when>
		<c:when test="${jumsu >= 60 and jumsu <70}">
			학점 : D (양)</br>
		</c:when>
		<c:otherwise>
			학점 : F (가)</br>
		</c:otherwise>
	</c:choose>
</body>
</html>