<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signUpStudent</title>
</head>
<body>
<div class=container>
	<div class="jumbotron">
		<h1>학생 회원가입 페이지</h1>
	</div>
	<table>
		<tr>
			<td>아이디</td>
			<td>
				<input class="form-control col-sm-10" type="text" id="studentId" placeholder="아이디 입력">
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input class="form-control col-sm-10" type="text" id="studentPw" placeholder="비밀번호 입력">
			</td>
		</tr>
	</table>
</div>	
</body>
</html>