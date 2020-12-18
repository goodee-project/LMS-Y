<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주소찾기 페이지</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
			$(document).ready(function(){
					
				});
        </script>
	</head>
	<body>
		<h1>주소 찾기</h1>
		<form method="get" action="${pageContext.request.contextPath}/auth/teacher/addressOne">
			<div>
				<input type="text" placeholder="우편번호 입력">
				<button>검색</button>
			</div>
		</form>
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
	
					</tr>
					<c:forEach var="a" items="${addressList}">
						<tr>
							<td>${a.id}</td>
							<td>${a.province}</td>
							<td>${a.city}</td>
							<td>${a.town}</td>
							<td>${a.street}</td>
							<td>${a.zipCode}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<!-- 현재 페이지가 1보다 클시 -->
		<!-- 현재 페이지가 1일시 -->
				<a href="${pageContext.request.contextPath}/auth/teacher/addressOne?currentPage=1">처음</a>
				<a href="${pageContext.request.contextPath}/auth/teacher/addressOne?currentPage=${currentPage-1}">이전</a>	
		<!-- 현재 페이지 표시 -->
		<a href="">${currentPage} / ${lastPage}</a>
		<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
		<!-- 현재 페이지가 마지막 페이지 일시 -->
		<a href="${pageContext.request.contextPath}/auth/teacher/addressOne?currentPage=${currentPage+1}">다음</a>
		<a href="${pageContext.request.contextPath}/auth/teacher/addressOne?currentPage=${lastPage}">끝</a>
	</body>
</html>