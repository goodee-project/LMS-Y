<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>teacherIndex</title>
</head>
<body>
	<h1>강사 인덱스</h1>
	<div></div>
	<a href="${pageContext.request.contextPath}/auth/teacher/teacherOne?accountId=${accountId}">[강사정보]</a>
	<a href="${pageContext.request.contextPath}/auth/teacher/modifyTeacher?accountId=${accountId}">[수정]</a>
	<a href="${pageContext.request.contextPath}/auth/teacher/teacherOne">[공지사항]</a>
	<a href="${pageContext.request.contextPath}/">[로그아웃]</a>
</body>
</html>