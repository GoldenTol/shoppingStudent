<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 가입 양식</h1>
	<form action="formExam01To.jsp">
		<label>아이디</label> :
		<input type="text" name="id"></input>
		<br/>
		<br/>
		<label>이름</label> :
		<input type="text" name="name"></input>
		<br/>
		<br/>
		<label>비밀번호</label> :
		<input type="text" name="password"></input>
		<br/>
		<br/>
		<label>성별</label> :
		<input type="radio" name="gender" value="여자">여자
		<input type="radio" name="gender" value="남자">남자
		<br/>
		<br/>
		<label>생일</label> :
		<input type="text" name="birth"></input>
		<br/>
		<br/>
		<label>결혼&nbsp;여부</label>  :
		<input type="radio" name="marriage" value="결혼">결혼
		<input type="radio" name="marriage" value="미혼">미혼
		<input type="radio" name="marriage" value="이혼">이혼
		<br/>
		<br/>
		<label>급여</label> :
		<input type="text" name="salary"></input>
		<br/>
		<br/>
		<label>주소</label> :
		<input type="text" name="address"></input>
		<br/>
		<br/>
		<label>매니저</label> :
		<input type="text" name="manager"></input>
		<br/>
		<br/>
		<label>이미지</label> :
		<input type="file" name="image"></input>
		<br/>
		<br/>
		<label>취미</label> :
		<input type="checkbox" name="hobby" value="탁구">탁구&nbsp;
		<input type="checkbox" name="hobby" value="야구">야구&nbsp;
		<input type="checkbox" name="hobby" value="축구">축구&nbsp;
		<input type="checkbox" name="hobby" value="자신있게챠밍한둘리" checked="checked">자신있게&nbsp;챠밍한&nbsp;둘리와의&nbsp;춤&nbsp;
		<input type="checkbox" name="hobby" value="사오정같은깜찌기가파" checked="checked">사오정같은&nbsp;깜찍이&nbsp;가파와의&nbsp;듀엣
		<br/>
		<br/>
		<label>가고&nbsp;싶은&nbsp;국가</label> :
		<select name="travel">
		<option>--선택해주세요.
		<option>스위스
		<option>영국
		<option>배트남
		<option>미국
		<option>튀르키예
		<option>이탈리아
		<option>자신있게&nbsp;챠밍한&nbsp;소피마르소&nbsp;둘리&nbsp;맘&nbsp;속
		<option>사오정같은&nbsp;큐티&nbsp;가파&nbsp;맘&nbsp;속
		</select>
		<br/>
		<label>코멘트</label> :
		<textarea name="comment" rows="10" cols="50"></textarea>
		<br/>
		<br/>
		&nbsp;&nbsp;
		<input type="submit" value="가입하기">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="초기화">
	</form>
</body>
</html>