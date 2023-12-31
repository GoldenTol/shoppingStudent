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
 				
 				return ture;
 			}
 		</script>
</head>
<body>
	<div class="container mt-3 col-md-12">
	<h2>게시글 수정</h2>
	<p>사용자의 게시글을 수정하는 페이지</p>
	<form action="<%=WithFormTag%>" method="post" >
		<input type="text" name="command" value="boUpdate">
		<input type="text" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
		<input type="text" name="pageSize" value="<%=request.getParameter("pageSize")%>">
		<input type="text" name="mode" value="<%=request.getParameter("mode")%>">
		<input type="text" name="keyword" value="<%=request.getParameter("keyword")%>">

		<input type="text" name="readhit" value="${requestScope.bean.readhit}">
		<input type="text" name="groupno" value="${requestScope.bean.groupno}">
		<input type="text" name="orderno" value="${requestScope.bean.orderno}">
		<input type="text" name="depth" value="${requestScope.bean.depth}">
		
		<div class="input-group">
  		  <span class="input-group-text col-md-2">게시글 번호</span>
   		  <input id="fakeno" name="fakeno" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.no}">
   		  <input id="no" type="hidden" name="no" value="${requestScope.bean.no}">
   		  <!-- bean.setNo(Integer.parseInt(request.getParameter("no"))); 여기에 오류가 났다고 자꾸 떳는데 그건 no값이 안 들어왔다는것 그럼, jsp 파일을 먼저 열어보자 오타이거나 disabled일 때 값이 안 들어간다.-->
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">작성자</span>
   		  <input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.writer}">
   		  <input id="writer" type="hidden" name="writer">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글제목</span>
   		  <input id="subject" name="subject" type="text" class="form-control" placeholder="" value="${requestScope.bean.subject}">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글내용</span>
   		  <input id="content" name="content" type="text" class="form-control" placeholder="" value="${requestScope.bean.content}">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">등록 일자</span>
   		  <input id="regdate" name="regdate" type="datetime" class="form-control" placeholder="" value="${requestScope.bean.regdate}">
		</div>
		<div id="buttonset" class="input-group">
  		  <button type="submit" class="btn btn-primary btn-lg"
  		  onclick="return validCheck();">수정</button>
  		  &nbsp;&nbsp;
  		  <button type="reset" class="btn btn-secondary btn-lg">초기화</button>
		</div>
	</form>
</body>
</html>