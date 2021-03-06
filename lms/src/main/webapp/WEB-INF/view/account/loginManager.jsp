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
			$(document).ready(function() {
				// 아이디로 포커스
				$('#accountId').focus();
				// 로그인 버튼 클릭시(아이디, 비밀번호 공백 처리)
				$('#loginBtn').click(function() {
					if($('#accountId').val() == '') {
						alert('아이디를 입력하세요')
						$('#accountId').focus();
					}else if($('#accountPw').val() == '') {
						alert('비밀번호를 입력하세요');
						$('#accountPw').focus();
						return;
					}else {
						$('#loginForm').submit();
					}
				});
			});
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
			</ul><br>
			<form action="${pageContext.request.contextPath}/login?pageLevel=3" method="post" id="loginForm">
			<div align="center">
				<h5>운영자 로그인</h5>
				<table style="margin-top: 30px">
					<tr>
						<td>ID&nbsp;</td>
						<td>
							<input class="form-control" type="text" id="accountId" name="accountId" value="manager" placeholder="아이디 입력">
						</td>
					</tr>
					<tr>
						<td>PW&nbsp;</td>
						<td>
							<input class="form-control" type="password" id="accountPw" name="accountPw" value="1234" placeholder="비밀번호 입력">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-top: 20px">
							<button class="btn btn-primary btn-block" type="button" id="loginBtn">로그인</button>
						</td>
					</tr>
				</table><br>
			</div>
			</form>
			<div align="center">
				<a class="btn btn-link" href="${pageContext.request.contextPath}/signUpManager">회원가입</a>
			</div>
			<div align="center">
				<a class="btn btn-link text-success" href="${pageContext.request.contextPath}/findAccount">계정을 잊으셨나요?</a>
			</div>
		</div>
	</body>
</html>