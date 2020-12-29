<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>월별 출석 상세보기</title>
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
	<div> 
		<a href="${pageContext.request.contextPath}/teacher/calendarAttendanceListOne?lectureNo=${param.lectureNo}&&target=pre&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">
		이전
		</a>
			<span>${currentYear}년 ${currentMonth}월 ${currentDay}일</span>
		<a href="${pageContext.request.contextPath}/teacher/calendarAttendanceListOne?lectureNo=${param.lectureNo}&&target=next&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">
		이후
		</a>
	</div>
	<div>
		<a href="">추가</a>
		<a href="">수정</a>
	</div>
	</div>
	<div class=container>
	<table class="table">
		<thead>
			<tr>
				<td>강좌번호</td>
				<td>학생이름</td>
				<td>학생성별</td>
				<td>학생번호</td>
				<td>출석여부</td>
				<td>비고</td>
			</tr>
		</thead>
		<thead>
			<c:forEach var="al" items="${attendance}">
				<tr>
				<c:forEach var="als" items="${al.student}">
					<td>${param.lectureNo}</td>
					<td>${als.studentName}</td>
					<td>${als.studentGender}</td>
					<td>${als.studentPhone}</td>
				</c:forEach>
					<td>${al.attendanceState}</td>
					<td>${al.attendanceRemark}</td>
				</tr>
				
			</c:forEach>
		</thead>
	</table>
	</div>
</body>
</html>