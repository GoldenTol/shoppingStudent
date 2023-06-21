<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#birth').datepicker();
		})
		$(document).ready(function(){
			$('[href*="naver"]').css('color', 'green').css('font-size', '50px');
			$('[href*="daum"]').css('color', 'blue').css('font-size', '50px');
			$('[href*="google"]').css('color', 'red').css('font-size', '50px');
			$('[href*="kakao"]').css('background-color', 'black').css('color', 'yellow').css('font-size', '50px');
			
			/* var list=$('[href^="http://"]');
			list.each(function(){
				alert($(this).text());
			}); */
			
			$('#check').click(function(){
				var checklist=$("input[type='checkbox']:checked");
				var result='';
				for(var i=0; i<checklist.length; i++) {
					result += checklist[i].value + ' / ';
				}
				$('#check_result').html(result);
			})
		});
			function welcome(){
				alert ('식도락회 가입을 축하합니다.');
			}
	</script>
	<style>
		.mytarget{color:red;font-size:30px;}
		#myform #hobby{font-size:30px;background-color:yellow;}
	</style>
</head>
<body>
	<h1>식도락회 가입 양식</h1>
	<form id="myform" name="myform" action="formExam01To.jsp">
		<label class="mytarget">아이디</label> :
		<input type="text" name="id"></input>
		<br/>
		<br/>
		<label class="mytarget">이름</label> :
		<input type="text" name="name"></input>
		<br/>
		<br/>
		<label class="mytarget">비밀번호</label> :
		<input type="text" name="password"></input>
		<br/>
		<br/>
		<label>성별</label> :
		<input type="radio" name="gender" value="여자">여자
		<input type="radio" name="gender" value="남자">남자
		<br/>
		<br/>
		<label class="mytarget">생일</label> :
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
		<label id="hobby">취미</label> :
		<input type="checkbox" name="hobby" value="탁구">탁구&nbsp;
		<input type="checkbox" name="hobby" value="야구">야구&nbsp;
		<input type="checkbox" name="hobby" value="축구">축구&nbsp;
		<input id="swim" type="checkbox" name="hobby" value="수영">수영&nbsp;
		<input type="checkbox" name="hobby" value="헬스">헬스&nbsp;
		<input type="checkbox" name="hobby" value="게임">게임&nbsp;
		<input type="checkbox" name="hobby" value="TV시청">TV시청&nbsp;
		<input type="checkbox" name="hobby" value="독서">독서&nbsp;
		<input type="checkbox" name="hobby" value="잠">잠&nbsp;
		<br/>
		<br/>
		<input type="checkbox" name="hobby" value="자신있게챠밍한둘리" checked="checked">자신있게&nbsp;챠밍한&nbsp;둘리와의&nbsp;춤&nbsp;
		<input type="checkbox" name="hobby" value="사오정같은깜찌기가파" checked="checked">사오정같은&nbsp;깜찍이&nbsp;가파와의&nbsp;듀엣
		<input type="checkbox" name="hobby" value="우리는 식도락회" checked="checked">우리는&nbsp;식도락회
		<hr/>
		<br/>
		<br/>
		<label class="mytarget">식도락&nbsp;여행&nbsp;국가</label> :
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
		<button onclick="welcome();">가입하기</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="초기화">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="챠밍 둘리 맘 속으로 가기">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="큐티 갓파 맘 속으로 가기">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br/>
		<br/>
		&nbsp;&nbsp;
		<button id="check" type="button">check</button>
		<br/>
 		<br/>
		체크 결과 : <span id="check_result"></span>
		<br/>
		<br/>
		<h3>식도락회 정모장소</h3>
		<ul>
		<li><a href="https://map.naver.com/v5/entry/place/1248390725?c=15,0,0,0,dh">naver : 꿈떡꿈떡 가산디지털한라타워점</a><br></li>
		
		<li><a href="https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&sq=&o=&q=%EC%82%BC%EA%B2%B9%EC%82%B4+%EB%A7%9B%EC%A7%91">Daum : 삼겹살</a><br></li>
		
		<li><a href="https://www.google.co.kr/maps/place/%ED%8F%AC%EB%A9%94%EC%9D%B8+%EA%B0%80%EC%96%91%EC%A0%90/data=!3m1!4b1!4m6!3m5!1s0x357c9c01640f6f95:0xbd3ce589944bc246!8m2!3d37.5572006!4d126.8644253!16s%2Fg%2F1v3hxxsv?hl=ko">Google : 포메인 가양점</a></li>
		
		<li><a href="https://place.map.kakao.com/m/10988813">kakao : 호남송사리민물매운탕</a><br></li>
		
		</ul>
	</form>
</body>
</html>