<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>html 공부</title>
	
	<!-- jquery = javascript + css + etc 등을 사용하는 라이브러리입니다. -->
	<!-- jquery.min.js 파일 내부에 여러 가지 함수들이 정의 되어 있습니다. -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script type="text/javascript">
		/* function은 동작으로 해석 */
		$(document).ready(function(){ /* 현재 이 문서가 완전히 로딩되었을 때 여기를 실행해 주세요. */
			/* alert('문서가 로딩되었습니다.') *//* alert() 함수는 일방적으로 띄워 주는 대화 상자 */
			/* $('#myform').hide(); *//* id 속성이 myform인 항목을 숨겨 주세요. */
			
			var a=5; /* 자바 스크립트에서는 변수 정의시 var를 사용 */
			var b=10;
			var c=a+b;
			/* alert(c); */
			
			/* 모든 라벨의 갯수 구하기 */
			var label_count=$('label').length;
			
			$('#label_cnt').html(label_count);
  		});
	</script>

	<style type="text/css">
	/* style 태그는 css를 적용시키기 위한 태그입니다. */
	/* test/css의 메모장으로 볼 수 있는 css형식입니다. */
	/* 선택자{속성이름:속성의값;속성이름:속성의값} */
	/* 선택자는 selector이라고 합니다. */
		label{font-size:25px;} /* 라벨 태그 모두 찾아서 글자 크기를 25픽셀로 셋팅해주세요. */
		
	/* id 속성은 sharp 기호를 사용합니다. */
	/* 문서 내에서 유일(unique)하게 지정하고자 할 때 사용합니다. */
	#age{color:red;font-size:30px;}
	#hobby{color:blue;font-size:50px;}
	
	/* class 속성은 dot(.) 기호를 사용합니다. */
	/* 여러 개를 같이 묶어서 처리하고자 할 때 사용합니다. */
	.mytarget{color:yellow;font-size:40px;background-color:black;}
	
	.yourtarget{color:red;font-size:40px;background-color:blue;}
	
	콤마를 사용하면 2개 이상의 요소에 동시 스타일 지정이 가능합니다.
	#age, #hobby{background-color:red;}
	
	/* 스페이스는 특정 요소의 하위 요소를 찾을 때 사용합니다. */
	#myform #age{background-color:yellow;color:blue;}
	
	label#hobby{background-color:green;color:black;}
	
	</style>
</head>
<body>
	<h1>폼 양식</h1>
	<!-- action 속성에 submit 할 때 이동할 곳을 명시 -->
	
	<form id="myform" name="myform" action="formExam02To.jsp">
	
		<label>이름</label> : 
		<input type="text" name="name"></input>
		<br/>  <!-- br은 엔터키 효과 -->
		<br/>
		<label id="age">나이</label> : 
		<input type="text" name="age"></input>
		<br/>
		<br/>
		
		<label class="mytarget">이미지</label> : 
		<input type="file" name="image"></input>
		<br/>
		<br/>
		
		<label>숨김 파라미터</label> : 
		<input type="hidden" name="money" value="1200000"></input>
		<br/>
		<br/>
		<label class="mytarget">성별</label> : 
		<input type="radio" name="gender" value="1" checked="checked">남자
		<input type="radio" name="gender" value="2">여자
		<br/>
		<br/>
		<label id="hobby">취미</label> : 
		<input type="checkbox" name="hobby" value="당구">당구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="checkbox" name="hobby" value="농구" checked="checked">농구
		<input type="checkbox" name="hobby" value="야구" checked="checked">야구
		<br/>
		<br/>
		<label class="yourtarget">직업</label> : 
		<select name="job">
		<option>-- 선택해주세요.
		<option>의사
		<option selected="selected">판사
		<option>검사
		<option>변호사
		</select>
		<br/>
		<br/>
		
		<label class="mytarget yourtarget">코멘트</label> :
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
	
	라벨 개수 : <span id="label_cnt"></span>
	<br/>
	<br/>
</body>
</html>