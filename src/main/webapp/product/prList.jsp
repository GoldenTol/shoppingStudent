<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<%@page import="com.shopping.model.Product"%>
<%@page import="com.shopping.dao.ProductDao"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 
	ProductDao dao = new ProductDao();
	List<Product> datalist = dao.getDataList();
	
	request.setAttribute("datalist", datalist);
	
	모델1 방식 : ProductDao.java에서 코딩했기 때문에 지웁니다.
-->

<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
   
  <style type="text/css">
  	/* table 셀의 수평 가운데 정렬 */
  	.card{margin-left:auto;margin-right:auto;}
  	.card-img-top{width:300px;height:300px;}
  	#buttonList{margin-top:10px;}
  	#updateAnchor, #deleteAnghor{opacity:0.8;}
  </style>	
  
  <script type="text/javascript">
	$(document).ready(function(){
      //Initialize popover
		var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
        var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl)
   		});
    });
   
   </script>
</head>

<body>

<div class="container mt-3">
  <h2>상품 목록</h2>
  <p>구매자들이 구매할 상품 목록을 보여 주는 페이지</p>            
  <table class="table table-borderless">
    <thead>
    </thead>
    <tbody>
    <c:set var="colsu" value="${applicationScope.map['product_column_size']}"/>
    <!-- from "product_column_size" in setting.txt file -->
    	컬럼 수 : ${applicationScope.map['product_column_size']}
    	<tr>
    		<td colspan="6" align="center">
    			${requestScope.pageInfo.pagingStatus}
    		</td>
    	</tr>
    	<c:forEach var="bean" items="${requestScope.datalist}" varStatus="status">
    		<c:if test="${status.index mod colsu == 0}">
    			<tr>
    		</c:if>
    		<td>
    			<div class="card" style="width: 18rem;">
        		<a href="<%=notWithFormTag%>prDetail&num=${bean.num}">
  					<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top" alt="${bean.name}">
  					<div class="card-body">
    					<h5 class="card-title">${bean.name}</h5>
    					<p class="card-text">
    						<span data-bs-toggle="popover" 
                            	title="${bean.name}" 
                            	data-bs-trigger="hover" 
                            	data-bs-content="${bean.comments}">
                                <c:if test="${fn:length(bean.comments) >= 10}">
                                	${fn:substring(bean.comments, 0, 24)}...
                                </c:if>
                            </span>
    						<c:if test="${fn:length(bean.comments) < 10}">
    							${bean.comments}
    						</c:if>
    					</p>
    					<c:if test="${whologin == 2}">
	    					<div id="buttonList">
    							<a id="updateAnchor" class="btn btn-warning" href="<%=notWithFormTag%>prUpdate&num=${bean.num}${requestScope.pageInfo.flowParameter}">수정</a>
    						
    							<a id="deleteAnchor" class="btn btn-danger" href="<%=notWithFormTag%>prDelete&num=${bean.num}${requestScope.pageInfo.flowParameter}">삭제</a>
    						</div>
    					</c:if>
  					</div>
				</a>
				</div>
    		</td>
    		<c:if test="${status.index mod colsu == (colsu-1)}">
    			<tr>
    		</c:if>
    	</c:forEach>
    </tbody>
  </table>
 	 ${requestScope.pageInfo.pagingHtml}
</div>

</body>
</html>
    