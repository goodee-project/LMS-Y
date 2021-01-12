<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강사강좌조회상세보기</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
	
		
		<div class="jumbotron">
			<div class=container>
				<h1>${lecture.lectureNo}강좌조회</h1>
			</div>
		</div>
		
		<div class=container>
			<table class="table">
				<thead>
					<tr>
						<td>강좌 번호</td>
						<td>강사 이름</td>
						<td>강좌 이름</td>
						<td>과목 이름</td>
						<td>강좌 개강일</td>
						<td>강좌 종강일</td>
						<td>출석확인</td>
						<td>자료실</td>
						<td>공지사항</td>
						<td>강의계획서</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${lecture.lectureNo}</td>
						<td>${lecture.teacherName}</td>
						<td>${lecture.lectureName}</td>
						<td>${lecture.subject.subjectName}</td>
						<td>${lecture.lectureStartDate}</td>
						<td>${lecture.lectureEndDate}</td>
						<td><a href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${param.lectureNo}&&target=weekDay&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${currentDay}">출석확인</a></td>
						<td><a href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lecture.lectureNo}&&currentPage=1">자료실</a></td>
						<td><a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lecture.lectureNo}&&currentPage=1">공지사항</a></td>
						<td><a href="${pageContext.request.contextPath}/teacher/syllabusDetail">강의계획서</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>