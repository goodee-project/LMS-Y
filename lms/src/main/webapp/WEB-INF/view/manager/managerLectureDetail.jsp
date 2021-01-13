<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 상세보기</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class="jumbotron">
 			<div class="container">
    			<h1>강좌 상세보기</h1>
 			 </div>
			</div>
		<div class=container>
				<table class="table">
				   
				   <tr hidden="hidden" >
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
						<td>교재 ISBN</td>
						<td>${lecture.textbookISBN}</td>
					</tr>
					<tr>
						<td>수강기간</td>
						<td>${lecture.lectureStartDate} ~ ${lecture.lectureEndDate}</td>
					</tr>
					<tr>
						<td>강좌 정원수</td>
						<td>${lecture.lectureTotal}명</td>
					</tr>
					<tr>
						<td>강의실 번호</td>
						<td>${lecture.classroomNo}</td>
					</tr>
				</table>
				<div>
					<a style="float: right;" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyLectureManager?lectureNo=${lecture.lectureNo}">수정</a>
				</div>
			</div>
</body>
</html>