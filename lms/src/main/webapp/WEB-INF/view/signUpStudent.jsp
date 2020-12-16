<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signUpStudent</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class=container>
	<div class="jumbotron">
		<h1>학생 회원가입 페이지</h1>
	</div>
	<form action="${pageContext.request.contextPath}/signUp" method="post">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td>
					<input class="form-control col-sm-4" type="text" id="studentId" name="studentId" placeholder="아이디 입력">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input class="form-control col-sm-4" type="password" id="studentPw" name="studentPw" placeholder="비밀번호 입력">
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input class="form-control col-sm-4" type="password" id="studentPw2" placeholder="비밀번호 확인">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input class="form-control col-sm-4" type="text" id="studentEmail" name="studentEmail" placeholder="이메일 입력">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input class="form-control col-sm-4" type="text" id="studentName" name="studentName" placeholder="이름 입력">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input class="form-control col-sm-4" type="number" id="studentPhone" name="studentPhone" placeholder="- 빼고 숫자만 입력">
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<div class="form-check-inline">
					    <input type="radio" class="form-check-input" name="studentGender">남
					</div>
					<div class="form-check-inline">
					    <input type="radio" class="form-check-input" name="studentGender">여
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>	
</.body>
</html>