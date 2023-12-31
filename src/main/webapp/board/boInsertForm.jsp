<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	/* box model 공부할 것 */
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	#buttonset{margin-top:15px;}
	
	#boardno{display : none; visibility : hidden;}
	</style>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  		<link rel="stylesheet" href="/resources/demos/style.css">
 		
 		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 		<script type="text/javascript">
 			$(document).ready(function(){
 				/* $('#regdate').datepicker(); */
 				$('#regdate').datepicker({dateFormat:"yy/mm/dd"});
 			});
 			
 			function validCheck(){ /* 폼 유효성 검사 */
 				var subject = $('#subject').val();
 				if(subject.length < 3 || subject.length > 20){
 					alert('글 제목은 3글자 이상 20글자 이하여야 합니다.');
 					$('#subject').focus();
 					return false;
 				}
 				
 				var content = $('#content').val();
 				if(content.length < 5 || content.length > 50){
 					alert('글 내용은 5글자 이상 50글자 이하여야 합니다.');
 					$('#content').focus();
 					return false;
 				}
 				var regdate = $('#regdate').val();
 				
 				var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/;
 				var result = regex.test(regdate);
 				
 				if(result == false){
 					alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
 					$('#regdate').focus();
 					return false;
 				}
 				
 				return true;
 			}
 		</script>
</head>
<body>
	<div class="container mt-3 col-md-12">
	<h2>게시판</h2>
	<p>사용자들의 게시글을 등록하는 페이지</p>
	<form action="<%=WithFormTag%>" method="post">
		<input type="hidden" name="command" value="boInsert">
		
		<div class="input-group" id="boardno">
  		  <span class="input-group-text col-md-2" >게시글 번호</span>
   		  <input id="num" name="num" type="number" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">작성자</span>
   		  <input id="writer" name="fakewriter" disabled="disabled" type="text" class="form-control" value="${sessionScope.loginfo.name} (${sessionScope.loginfo.id})" placeholder="">
   		  <input type="hidden" name="writer" value="${sessionScope.loginfo.id}">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글제목</span>
   		  <input id="subject" name="subject" type="text" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글내용</span>
   		  <textarea id="content" name="content" type="text" class="form-control" placeholder=""rows="10" cols="50"></textarea>
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">등록 일자</span>
   		  <input id="regdate" name="regdate" type="datetime" class="form-control" placeholder="">
		</div>
		<div id="buttonset" class="input-group">
  		  <button type="submit" class="btn btn-success btn-lg"
  		  onclick="return validCheck();">등록</button>
  		  &nbsp;&nbsp;
  		  <button type="reset" class="btn btn-danger btn-lg">초기화</button>
		</div>
	</form>
</body>
</html>