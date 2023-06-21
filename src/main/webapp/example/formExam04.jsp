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
	<form action="formExam04To.jsp" method="post">
		이름 : <input type="text" name="name" value="이둘리와 임갓파"><br/>
		나이 : <input type="text" name="age" value="20"><br/>
		이미지 : <input type="file" name="image"><br/>
		숨김 파라미터 : <input type="text" name="money" value="120000000"><br/>
		성별 : 
		<input type="radio" name="gender" value="남자">남자
		<input type="radio" name="gender" value="여자" checked="checked">여자<br/>
		취미 : 
		<input type="checkbox" name="hobby" value="당구">당구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="checkbox" name="hobby" value="농구" checked="checked">농구
		<input type="checkbox" name="hobby" value="야구" checked="checked">야구
		<br/>
		직업 : 
		<select name="job">
		<option>-- 선택해주세요.
		<option>의사
		<option selected="selected">판사
		<option>검사
		<option>변호사
		</select>
		<br/>
		코멘트 :
		<textarea name="comment" rows="10" cols="50"></textarea><br/> 
		<input type="submit" value="전송">
	</form>
</body>
</html>