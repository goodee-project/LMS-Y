<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.lectureNo != null}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark small mt-n3">
		<div class="container">
			<ul class="navbar-nav m-n1">
				<li class="nav-item">
				    <a class="nav-link ml-5" href="${pageContext.request.contextPath}/student/classRegistrationDetail?lectureNo=${param.lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/student/studentAttendanceList?lectureNo=${param.lectureNo}">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/syllabusDetail?lectureNo=${param.lectureNo}">강의계획서</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/studentLectureQuestionList?lectureNo=${param.lectureNo}">질문게시판</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/reportList?lectureNo=${param.lectureNo}">과제</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/answerSheet?lectureNo=${param.lectureNo}">시험</a>
				</li>
			</ul>
		</div>
	</nav>
</c:if>

<c:if test="${param.lectureNo == null && lectureNo != null}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark small mt-n3">
		<div class="container">
			<ul class="navbar-nav m-n1">
				<li class="nav-item">
				    <a class="nav-link ml-5" href="${pageContext.request.contextPath}/student/studentLectureOne?lectureNo=${lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/student/attendanceList?lectureNo=${lectureNo}">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/syllabusDetail?lectureNo=${lectureNo}">강의계획서</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/questionList?lectureNo=${lectureNo}">질문게시판</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/reportList?lectureNo=${lectureNo}">과제</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/answerSheet?lectureNo=${lectureNo}">시험</a>
				</li>
			</ul>
		</div>
	</nav>
</c:if>