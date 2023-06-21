<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	<style type="text/css">
		.card{margin-left:auto;margin-right:auto;}
		.leftside{margin-left:0px;}
		.card_borderless{border:0px;}
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
		#totalprice{color:red;font-size:20px;font-weight:bolder;}
		.cart{background-color:black;border:1px solid black;} /* 속성표기법 */
		.rightnow{background-color:red;border:1px solid red;}
		#qty{margin:-10px;border:0px;font-size:0.7rem;}
		.plus, .minus{font-size:1.1rem;}
		
   </style>

	<script type="text/javascript">
		/* var price = 10000; */ /* 단가 */
		
		/* const 키워드는 읽기 전용(자바의 final과 동일 개념) */
		var maxPurchaseSize = 5; /* 최대 구매 가능 개수 */
	
		$(document).ready(function(){
			/* alert('단가 : ${bean.price}원') */
			var price = ${bean.price};
			
			$('#qty').innerWidth($('.minus').innerWidth() -3);
			/* $('#qty').innerHeight($('.minus').innerHeight()); */
			
			$('#totalprice').text('0'); /* 최초 시작시 금액을 0으로 설정 */
			
			/* atte() 함수는 속성(attribute)을 읽거나 쓰기 위한 함수 */
			$('.small_image').click(function(){ 
				/*작은 이미지를 클릭했을 때 아래를 동작하세요*/
				$('.active_image').attr('src', $(this).attr('src')); 
			});
			
			$('.plus').click(function(){
	            var qty = $('#qty').val();
	            if(qty == maxPurchaseSize){
	               swal('최대 ' + maxPurchaseSize + '개까지만 구매 가능합니다.');
	               return; /* return 구문으로 함수가 더 이상 실행하지 않도록 합니다. */
	            }
	            /* Number는 Integer.parseInt()와 동일한 효과 */
	            var newQty = Number(qty) + 1;
	            if(qty == ''){
	               $('#qty').val('1');
	            }else{
	               $('#qty').val(newQty);
	            }
	            
	            var amount = newQty*price ;
	            $('#totalprice').text(amount.toLocaleString());
	         });
	         
	         $('.minus').click(function(){
	            var qty = $('#qty').val();
	            if(qty == '0'){
	               swal('최소 1개 이상 구매 가능 합니다.');
	               return; /* return 구문으로 함수가 더 이상 실행하지 않도록 합니다. */
	            }
	            var newQty = Number(qty) - 1;
	            if(qty == ''){
	               $('#qty').val('0');
	               $('#totalprice').text('0');
	            }else{
	               $('#qty').val(newQty);
	               
	               var amount = newQty*price ;
	               $('#totalprice').text(amount.toLocaleString());
	            }
	         });

			
			/* blur 이벤트는 포커스를 잃어 버릴 때 반응합니다. */
			$('#qty').blur(function(){ 
				var qty = $('#qty').val();
				
				/* isNaN() 함수는 숫자가 아니면 true를 반환해 줍니다. */
				if(qty == ''||isNaN(qty) == true){
					swal('0이상 '+maxPurchaseSize+'이하의 숫자만 가능합니다.');
					$('#qty').val('0');
					$('#qty').focus();
					return;
				}
				
				if(isNaN(qty) == false){
					var newQty = Number(qty);
					if(newQty<0 || newQty>5){
						swal('0이상 ' +maxPurchaseSize+'이하의 숫자만 가능합니다.');
						$('#qty').val('0');
						$('#qty').focus();
						return;
					}
				}
			});	
			
			$('.cart').click(function(){ 
				var qty = $('#qty').val();
				if(qty<1 || qty>5){
					swal('최소 1개 이상 카트에 담을 수 있습니다.');
					return;
				}
			});
			
			$('.rightnow').click(function(){ 
				var qty = $('#qty').val();
				if(qty<1 || qty>5){
				swal('구매는 최소 1개 이상 가능합니다.');
				return;
				}
			});
			
			$('.zoom').zoom();
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
				<tr>
					<td class="col-lg-5">
						<div class="card" style="width: 30rem;">
							<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top active_image" alt="${bean.name}">
						</div>
					</td>
					<td class="col-lg-1">
						<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top small_image" alt="${bean.name}">
						
						<c:if test="${not empty bean.image02}">
							<img src="<%=contextPath%>/image/${bean.image02}" class="card-img-top small_image" alt="${bean.name}">
						</c:if>
						
						<c:if test="${not empty bean.image03}">
							<img src="<%=contextPath%>/image/${bean.image03}" class="card-img-top small_image" alt="${bean.name}">
						</c:if>
						
					</td>
					<td class="col-lg-6">
						<div class="card leftside card_borderless" style="width: 18rem;">
							<h5 class="card-title">${bean.name}</h5>
							
							<p class="card-text">상품번호 : ${bean.num}</p>
							<p class="card-text">
								단가 : <fmt:formatNumber value="${bean.price}" pattern="###,###"/>원
							</p>
							<p class="card-text">재고 : ${bean.stock}</p>
							<p class="card-text">${bean.comments}</p>
							
							<p class="card-text">합계 : <span id="totalprice">0</span>원</p>

							<form action="<%=WithFormTag%>" method="post">
							<ul class="pagination">
								<li class="page-item"><a class="page-link minus" href="#">-</a></li>
								<li class="page-item"><a class="page-link" href="#">
									<input type="text" name="command" value="maInsert">
									<input type="text" name="num" value="${bean.num}">
									<input type="text" name="stock" value="${bean.stock}">
									
									<input type="text" name="qty" id="qty" value="0">
								</a></li>
								<li class="page-item"><a class="page-link plus" href="#">+</a></li>
							</ul>
							
							<div class="btn-group">
								<button type="submit" class="btn btn-primary cart">장바구니</button>
								<button type="button" class="btn btn-primary rightnow">바로 구매</button>
							</div>
							</form>
						</div>
					</td>
			</tbody>
		</table>
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>
</body>
</html>