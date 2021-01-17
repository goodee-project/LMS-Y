<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LMS 로그인</title>
		<!-- jQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			// 로그아웃 비동기처리(세션값이 기등록 되어있는 경우를 대비)
			$.ajax({
				url: '${pageContext.request.contextPath}/logout',
				method: 'get'
			});
		</script>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
			
		<div class="jumbotron">
			<div class="container">
				<h1>LMS 로그인</h1>
				<h4>Learning Management System</h4>
			</div>
		</div>

		<div class=container>
			<ul class="nav nav-tabs nav-justified">
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/studentLogin">학생</a>
				</li>
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/teacherLogin">강사</a>
				</li>
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/managerLogin">운영자</a>
				</li>
				<li class="nav-item">
					<a class="nav-link btn btn-outline-primary" href="${pageContext.request.contextPath}/adminLogin">관리자</a>
				</li>
			</ul>		
		</div>
	</body>
</html>