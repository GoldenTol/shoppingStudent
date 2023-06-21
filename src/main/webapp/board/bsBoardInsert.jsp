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
	</style>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  		<link rel="stylesheet" href="/resources/demos/style.css">
 		
 		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 		<script type="text/javascript">
 			$(document).ready(function(){
 				/* $('#regdate').datepicker(); */
 				$('#regdate').datepicker({dateFormat:"yy/mm/dd"})
 			})
 		</script>
</head>
<body>
	<div class="container mt-3 col-md-12">
	<h2>게시판</h2>
	<p>사용자들의 게시글을 등록하는 페이지</p>
	<form>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">게시글 번호</span>
   		  <input id="num" name="num" type="number" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">작성자</span>
   		  <input id="writer" name="writer" type="text" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글제목</span>
   		  <input id="subject" name="subject" type="text" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글내용</span>
   		  <input id="content" name="content" type="text" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">등록 일자</span>
   		  <input id="regdate" name="regdate" type="datetime" class="form-control" placeholder="">
		</div>
		<div id="buttonset" class="input-group">
  		  <button type="submit" class="btn btn-primary btn-lg">등록</button>
  		  &nbsp;&nbsp;
  		  <button type="reset" class="btn btn-secondary btn-lg">초기화</button>
		</div>
	</form>
</body>
</html>