<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>loginAdmin</title>
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
	</ul><br>
	<form action="${pageContext.request.contextPath}/login" method="post">
	<div align="center">
		<h5>운영자 로그인</h5>
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input class="form-control col-sm-10" type="text" id="accountId" name="accountId" placeholder="아이디 입력">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input class="form-control col-sm-10" type="password" id="accountPw" name="accountPw" placeholder="비밀번호 입력">
				</td>
			</tr>
		</table><br>
		<button class="btn btn-primary col-sm-2" type="submit">로그인</button>
	</div>
	</form>
	<div align="center">
		<a class="btn btn-link" href="#">회원가입</a>
	</div>
</div>
</body>
</html>