<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>표 만들기</h3>
	<table border="1">
		<tr>
			<th align="center">이름</th>
			<th align="center">국어</th>
			<th align="center">영어</th>
			<th align="center">수학</th>
		</tr>
		<tr>
			<td align="center">김유신</td>
			<td align="center">40</td>
			<td align="center">50</td>
			<td align="center">60</td>
		</tr>
		<tr>
			<td align="center">유관순</td>
			<td align="center">45</td>
			<td align="center">55</td>
			<td align="center">65</td>
		</tr>
	</table>
	
	<hr/>
	<h3>표 만들기</h3>
	<table border="1">
		<tr>
			<th align="center">이름</th>
			<th align="center">국어</th>
			<th align="center">영어</th>
			<th align="center">수학</th>
		</tr>
		<tr>
        	<td rowspan='2'>김유신</td>
      	    <td align="center">40</td>
        	<td align="center">50</td>
        	<td align="center">60</td>
       	</tr>
       	<tr>
       		<td colspan='3' align="center">150</td>
       	</tr>
    </table>
</body>
</html>