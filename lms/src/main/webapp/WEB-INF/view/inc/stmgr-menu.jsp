<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.lectureNo != null}">
	<div class="container">
		<nav class="navbar navbar-expand-sm small mx-n3">
			<ul class="nav nav-tabs">
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/student/classRegistrationDetail?lectureNo=${param.lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/student/attendanceList?lectureNo=${param.lectureNo}&target=weekDay">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lectureArchive?lectureNo=${param.lectureNo}">자료실</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lectureNotice?lectureNo=${param.lectureNo}">공지</a>
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
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/testDetail?lectureNo=${param.lectureNo}">시험</a>
				</li>
			</ul>
		</nav>
	</div>
</c:if>

<c:if test="${param.lectureNo == null && lectureNo != null}">
	<div class="container">
		<nav class="navbar navbar-expand-sm small mx-n3">
			<ul class="nav nav-tabs">
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/student/studentLectureOne?lectureNo=${lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/student/attendanceList?lectureNo=${lectureNo}&target=weekDay">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lectureArchive?lectureNo=${lectureNo}">자료실</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lectureNotice?lectureNo=${lectureNo}">공지</a>
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
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/testDetail?lectureNo=${lectureNo}">시험</a>
				</li>
			</ul>
		</nav>
	</div>
</c:if>