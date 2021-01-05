<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>월별 출석 상세보기</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				document.querySelector(".disableLink").removeAttribute('href');
			});
		</script>
	</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
	<div class=container>
		<div class="jumbotron">
			<h1>출석 달력</h1>
		</div>
	</div>
	<div class="container">
		<a href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${param.lectureNo}&&target=pre&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">
		이전
		</a>
			<span>${currentYear}년 ${currentMonth}월 ${currentDay}일</span>
		<a href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${param.lectureNo}&&target=next&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">
		이후
		</a>
	</div>
	<div class=container>
	<table class="table">
		<thead>
			<tr>
				<td>학생아이디</td>
				<td>학생이름</td>
				<td>학생성별</td>
				<td>학생 전화번호</td>
				<td>출석여부</td>
				<td>비고</td>
				<td>입력</td>
				<td>수정</td>

			</tr>
		</thead>
		<thead>
			<c:forEach var="al" items="${attendance}">
				<tr>
				<c:forEach var="als" items="${al.studentList}">
					<td>${als.accountId}</td>
					<td>${als.studentName}</td>
					<td>${als.studentGender}</td>
					<td>${als.studentPhone}</td>
				</c:forEach>
					<td>${al.attendanceState}</td>
					<td>${al.attendanceRemark}</td>
				<c:forEach var="als" items="${al.studentList}">
				<c:set var="state" value="${al.attendanceState}"/>
				<c:choose>
					<c:when test="${empty al.attendanceState}">
					<td><a class="btn btn-primary" id="addbtn" href="${pageContext.request.contextPath}/teacher/createAttendance?lectureNo=${param.lectureNo}&&attendanceDay=${al.attendanceDay}&&studentName=${als.studentName}&&studentGender=${als.studentGender}&&accountId=${als.accountId}&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">입력</a></td>
					<td><a class="btn btn-danger" onclick="return false;" id="modifybtn" href="${pageContext.request.contextPath}/teacher/modifyAttendanceOne?lectureNo=${param.lectureNo}&&accountId=${al.accountId}&&studentName=${als.studentName}&&studentGender=${als.studentGender}&&attendanceDay=${al.attendanceDay}&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">수정</a></td>
					</c:when>
					<c:when test="${al.attendanceState != null}">
					<td><a class="btn btn-danger" onclick="return false;" id="addbtn" href="${pageContext.request.contextPath}/teacher/createAttendance?lectureNo=${param.lectureNo}&&attendanceDay=${al.attendanceDay}&&studentName=${als.studentName}&&studentGender=${als.studentGender}&&accountId=${als.accountId}&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">입력</a></td>
					<td><a class="btn btn-primary" id="modifybtn" href="${pageContext.request.contextPath}/teacher/modifyAttendanceOne?lectureNo=${param.lectureNo}&&accountId=${al.accountId}&&studentName=${als.studentName}&&studentGender=${als.studentGender}&&attendanceDay=${al.attendanceDay}&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">수정</a></td>
					</c:when>
				</c:choose>
				</c:forEach>
				</tr>
			</c:forEach>
		</thead>
	</table>
	</div>
</body>
</html>