<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주소검색 페이지</title>
	</head>
	<body>
		<h1>테스트</h1>
		<table border="1">
			<tbody>
				<tr>
					<td>주소 번호</td>
					<td>시도</td>
					<td>시군구</td>
					<td>읍면</td>
					<td>도로명</td>
					<td>우편 번호</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td>${address.zipCode}</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>