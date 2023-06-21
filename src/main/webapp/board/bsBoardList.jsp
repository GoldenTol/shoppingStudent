<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ↓문장은 common 밑에 bootstrap5파일을 참조하기 위한 문장 -->
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
      </tr>
    </thead>
    <tbody>
      <tr>
        <td align="center">1</td>
        <td>김철수</td>
        <td>자바 공부</td>
        <td>자바 어려워요ㅜㅜ</td>
        <td>2023/02/10</td>
        <td>11</td>
      </tr>
      <tr>
        <td align="center">2</td>
        <td>박영희</td>
        <td>파이썬 공부</td>
        <td>판다스 입문</td>
        <td>2023/03/01</td>
        <td>22</td>
      </tr>
      <tr>
        <td align="center">3</td>
        <td>이순신</td>
        <td>오라클 공부</td>
        <td>데이터 베이스 입문</td>
        <td>2023/02/25</td>
        <td>33</td>
      </tr>
    </tbody>
  </table>
  
 	 <ul class="pagination justify-content-center">
 	 <li class="page-item"><a class="page-link" href="#">Previous</a></li>
	 <li class="page-item"><a class="page-link" href="#">1</a></li>
 	 <li class="page-item active"><a class="page-link" href="#">2</a></li>
 	 <li class="page-item"><a class="page-link" href="#">3</a></li>
 	 <li class="page-item disabled"><a class="page-link" href="#">4</a></li>
 	 <li class="page-item"><a class="page-link" href="#">5</a></li>
	 <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
</div>

</body>
</html>
    