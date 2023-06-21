<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.shopping.model.Board"%>
<%@ page import="com.shopping.dao.BoardDao"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript">
		$(document).ready(function(){
  		$('.rounded-pill').attr('opacity', '0.7';)
  		});
		
		function searchAll();{
		// 전체검색
			location.href = '<%=notWithFormTag%>boList';
		} 
		
		function writeForm(){
		// 글쓰기	
			location.href = '<%=notWithFormTag%>boInsert';
		
		}
	</script>
	<style type="text/css">
		.rounded-pill{opacity:0.8;}
		#noUnderLine{text-decoration-line:none;}
	</style>
</head>
<body>

	<div class="container mt-3">
		<h2>게시물 목록</h2>
		<p>사용자들이 게시한 목록을 보여 주는 페이지</p>            
	<table class="table table-striped">
    <thead>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>글제목</th>
			<th>글내용</th>
			<th>작성일자</th>
			<th>조회수</th>
			<th>수정</th>
			<th>삭제</th>
			<th>답글</th>
		</tr>
	</thead>
	<tbody>
    	<tr>
    		<td colspan="9" align="center">
    			<form class="form-inline" role="form" name="myform" action="<%=WithFormTag%>" method="get">
    				<input type="text" name="command" value="boList">
    				<div class="form-group">
    					<select class="form-control" id="mode" name="mode">
    						<option value="all" selected="selected">--- 선택해 주세요. ---
    						<option value="writer">작성자
    						<option value="subject">글제목
    						<option value="content">글내용
    					</select>
    				</div>
    				<div class="form-group">
    					<input class="form-control" type="text" name="keyword" id="keyword" placeholder="키워드를 입력해 주세요.">
    				</div>
    				<!-- paging 클래스의 private String mode/keyword와 연관성이 생겼다. -->
    				<button type="submit" class="btn btn-secondary" onclick="">검색</button>
    				<button type="button" class="btn btn-success" onclick="searchAll();">전체검색</button>
    				<button type="button" class="btn btn-info" onclick="writeForm();">글쓰기</button>
    				&nbsp;&nbsp;&nbsp;
					<p class="form-control-static">
    					${requestScope.pageInfo.pagingStatus}
    				</p>
    			</form>
    		</td>
    	</tr>
    	<c:forEach var="bean" items="${datalist}">
			<tr>
				<td align="center">${bean.no}</td>
        		<td>${bean.writer}</td>
        		<td>
        			<a id="noUnderLine" href="<%=notWithFormTag%>boDetail&no=${bean.no}">
        				<c:forEach begin="1" end="${bean.depth}"> 
        					<span class="badge rounded-pill bg-danger">re</span>
        				</c:forEach>
        				${bean.subject}
        			</a>
        		</td>
        		<td>${bean.content}</td>
        		<td>${bean.regdate}</td>
        		<td>
        			<c:if test="${bean.readhit >= 50 }">
        				<span class="badge rounded-pill bg-info">${bean.readhit}</span>
        			</c:if>
        			<c:if test="${bean.readhit < 50 }">
        				<span class="badge rounded-pill bg-warning">${bean.readhit}</span>
        			</c:if>
        		</td>
        		<td>
        			<c:if test="${sessionScope.loginfo.id==bean.writer}">
        				<a href="<%=notWithFormTag%>boUpdate&no=${bean.no}${requestScope.pageInfo.flowParameter}">
        					수정
        				</a>
        			</c:if>
        		</td>
        		<td>
        			<c:if test="${sessionScope.loginfo.id==bean.writer}">
        				<a href="<%=notWithFormTag%>boDelete&no=${bean.no}${requestScope.pageInfo.flowParameter}">
        					삭제
        				</a>
        			</c:if>		
        		</td>
        		<td>
        			<c:if test="${whologin !=0}">
        				<c:set var="replyInfo" value="&groupno=${bean.groupno}&orderno=${bean.orderno}&depth=${bean.depth}"/>
        					<a href="<%=notWithFormTag%>boReply&no=${bean.no}${requestScope.pageInfo.flowParameter}${replyInfo}">
        						답글
        					</a>
        			</c:if>
        		</td>
        	</tr>
    	</c:forEach>
    </tbody>
  </table>
	${requestScope.pageInfo.pagingHtml}
</div>
<script type="text/javascript">
	/* 필드 검색시 입력했던 콤보 박스(mode)의 내용과 키워드(keyword) 입력 상자에 있던 내용은 보존되어야 합니다. */
	$(document).ready(function(){
		var myoptions = $('#mode option');
		
		for(var i=0; i<myoptions.length; i++){
			if(myoptions[i].value == '${requestScope.pageInfo.mode}'){
				myoptions[i].selected = true;
			}
		}
		$('#keyword').val('${requestScope.pageInfo.keyword}');
	});
</script>

</body>
</html>
    