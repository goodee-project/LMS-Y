<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내정보</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>내정보</h1>
			</div>
		</div>
		
		<div class="container">
			<!-- 관리자 정보 -->
			<div>
				<table class="table">
					<tr>
						<th>아이디</th>
						<td>${adminDetail.accountId}</td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td>${adminDetail.adminEmail}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${adminDetail.adminPhone}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${adminDetail.adminName}</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>