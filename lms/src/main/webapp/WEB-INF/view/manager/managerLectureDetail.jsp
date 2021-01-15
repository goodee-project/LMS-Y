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
						<td>과목명</td>
						<td><a href="${pageContext.request.contextPath}/manager/subjectDetail?subjectNo=${lecture.subjectNo}">${lecture.subject.subjectName}</a> </td>
					</tr>
					
					<tr>
						<td>강사명</td>
						<td>${lecture.teacherName}</td>
					</tr>
					
					<tr>
						<td>강좌명</td>
						<td>${lecture.lectureName}</td>
					</tr>
					<tr>
						<td>교재명</td>
						<td><a href="${pageContext.request.contextPath}/manager/textbookDetail?textbookISBN=${lecture.textbook.textbookISBN}">${lecture.textbook.textbookTitle}</a></td>
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
						<td>강의실</td>
						<td><a href="${pageContext.request.contextPath}/manager/classroomDetail?classroomNo=${lecture.classroom.classroomNo}">${lecture.classroom.classroomNumber}호실</a></td>
					</tr>
					<tr>
						<td>강의계획서</td>
						<td><a href="${pageContext.request.contextPath}/manager/syllabusDetail?lectureNo=${param.lectureNo}">강의계획서</a></td>
					</tr>
				</table>
				<div>
					<a style="float: right;" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyLectureManager?lectureNo=${lecture.lectureNo}">수정</a>
				</div>
			</div>
</body>
</html>