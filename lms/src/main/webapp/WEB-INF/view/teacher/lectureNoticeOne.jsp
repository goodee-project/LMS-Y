<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 공지사항 상세보기</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>공지사항 상세보기</h1>
			</div>
		</div>
		<div class="container">
			<div align="right">
				<a href="${pageContext.request.contextPath}/teacher/modifyLectureNotice?lectureNoticeNo=${lectureNotice.lectureNoticeNo}">수정</a>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/teacher/removeLectureNotice?lectureNoticeNo=${lectureNotice.lectureNoticeNo}">삭제</a>
			</div>
			<table class="table">
				<tr>
					<td>공지번호</td>
					<td>${lectureNotice.lectureNoticeNo}</td>
				</tr>
				<tr>
					<td>공지제목</td>
					<td>${lectureNotice.lectureNoticeTitle}</td>
				</tr>
				<tr>
					<td>공지내용</td>
					<td>${lectureNotice.lectureNoticeContent}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${lectureNotice.lectureNoticeCreateDate}</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${lectureNotice.lectureNoticeCount}</td>
				</tr>
			</table>
		</div>
	</body>
</html>