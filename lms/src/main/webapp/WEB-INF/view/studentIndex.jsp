<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>studentIndex</title>
</head>
<body>
	<h1>학생 인덱스</h1>
	<h1></h1>
	<!-- 학생 더보기 -->
	<div>학생<a href="${pageContext.request.contextPath}/studentIndex">더보기</a></div>
	<div>
		<table border="1">
		
		</table>
	</div>
</body>
</html>