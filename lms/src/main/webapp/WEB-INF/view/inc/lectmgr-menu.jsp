<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.lectureNo != null}">
	<div class="container">
		<nav class="navbar navbar-expand-sm small mx-n3">
			<ul class="nav nav-tabs">
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherLectureOne?lectureNo=${param.lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${param.lectureNo}&target=weekDay">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${param.lectureNo}">자료실</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${param.lectureNo}">공지</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/syllabusDetail?lectureNo=${param.lectureNo}">강의계획서</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/questionList?lectureNo=${param.lectureNo}">질문게시판</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/reportList?lectureNo=${param.lectureNo}">과제</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/testDetail?lectureNo=${param.lectureNo}">시험</a>
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
				    <a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherLectureOne?lectureNo=${lectureNo}">메인</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${lectureNo}&target=weekDay">출석</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}">자료실</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}">공지</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/syllabusDetail?lectureNo=${lectureNo}">강의계획서</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/questionList?lectureNo=${lectureNo}">질문게시판</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/reportList?lectureNo=${lectureNo}">과제</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/testDetail?lectureNo=${lectureNo}">시험</a>
				</li>
			</ul>
		</nav>
	</div>
</c:if>