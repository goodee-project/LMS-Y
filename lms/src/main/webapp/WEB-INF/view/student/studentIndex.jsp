<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 인덱스</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class=container>
			<div class="jumbotron">
				<h1>학생 인덱스</h1>
			</div>
		</div>
		<div class=container>
		<a href="${pageContext.request.contextPath}/student/studentDetail?accountId=${accountId}">내정보</a>
		<a href="${pageContext.request.contextPath}/student/studentQuestionList">질문게시판</a>
		<a href="${pageContext.request.contextPath}/student/studentMultipleChoiceList?lectureNo=${lectureNo}">시험 문제리스트</a>
		</div>
	</body>
</html>