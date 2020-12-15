<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>login</title>
</head>
<body>
<div class=container>
	<div class="jumbotron">
		<h1>LMS 로그인</h1>
		<h4>Learning Management System</h4>
	</div>
	<ul class="nav nav-tabs justify-content-center">
		<li class="nav-item">
			<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/studentLogin">학생</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/teacherLogin">강사</a>
		</li>
		<li class="nav-item">
		  	<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/managerLogin">운영자</a>
		</li>
		<li class="nav-item">
		  	<a class="nav-link" data-toggle="tab" href="${pageContext.request.contextPath}/adminLogin">관리자</a>
		</li>
	</ul>
</div>
</body>
</html>