<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>폼 양식</h1>
	<!-- action 속성에 submit 할 때 이동할 곳을 명시 -->
	<form action="formExam01To.jsp">
		<label>이름</label> : 
		<input type="text" name="name"></input>
		<br/>  <!-- br은 엔터키 효과 -->
		<br/>
		<label>나이</label> : 
		<input type="text" name="age"></input>
		<br/>
		<br/>
		
		<label>이미지</label> : 
		<input type="file" name="image"></input>
		<br/>
		<br/>
		
		<label>숨김 파라미터</label> : 
		<input type="hidden" name="money" value="1200000"></input>
		
		<label>성별</label> : 
		<input type="radio" name="gender" value="1" checked="checked">남자
		<input type="radio" name="gender" value="2">여자
		<br/>
		<br/>
		<label>취미</label> : 
		<input type="checkbox" name="hobby" value="당구">당구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="checkbox" name="hobby" value="농구" checked="checked">농구
		<input type="checkbox" name="hobby" value="야구" checked="checked">야구
		<br/>
		<br/>
		<label>직업</label> : 
		<select name="job">
		<option>-- 선택해주세요.
		<option>의사
		<option selected="selected">판사
		<option>검사
		<option>변호사
		</select>
		<br/>
		<br/>
		
		<label>코멘트</label> :
		<textarea name="comment" rows="10" cols="50"></textarea>
		<br/> 
		<br/>
		
		&nbsp;&nbsp;
		<input type="submit" value="전송">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="초기화">
		<br/>
		<br/>
	</form>
</body>
</html>