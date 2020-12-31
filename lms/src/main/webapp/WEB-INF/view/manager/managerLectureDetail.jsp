<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 상세보기 (운영자)</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			
				<h1>강좌 상세보기 (운영자)</h1>
		</div>
		<div class=container>
				<table class="table">
				   
				   <tr>
						<td>강좌 번호</td>
						<td>${lecture.lectureNo}</td>
					</tr>
				   
					<tr>
						<td>강사 계정Id</td>
						<td>${lecture.accountId}</td>
					</tr>
					
					<tr>
						<td>강좌 과목번호</td>
						<td>${lecture.subjectNo}</td>
					</tr>
					
					<tr>
						<td>강사 이름</td>
						<td>${lecture.teacherName}</td>
					</tr>
					
					<tr>
						<td>강좌 이름</td>
						<td>${lecture.lectureName}</td>
					</tr>
					
					<tr>
						<td>교재 일련번호</td>
						<td>${lecture.textbookISBN}</td>
					</tr>
					<tr>
						<td>강좌 시작날짜</td>
						<td>${lecture.lectureStartDate}</td>
					</tr>
					<tr>
						<td>강좌 종료날짜</td>
						<td>${lecture.lectureEndDate}</td>
					</tr>
					<tr>
						<td>강좌 정원수</td>
						<td>${lecture.lectureTotal}</td>
					</tr>
					<tr>
						<td>강의계획서</td>
						<td>${lecture.syllabusNo}</td>
					</tr>
					<tr>
						<td>강의실 번호</td>
						<td>${lecture.classroomNo}</td>
					</tr>
			
				</table>
			</div>
</body>
</html>