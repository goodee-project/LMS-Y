<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- 학생 메뉴바 -->
<c:if test="${accountLevel == 1}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand ml-3">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/student/classRegistration">학생 수강신청 목록</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/student/classRegistrationAll">학생수강등록</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lmsNoticeList">LMS 공지사항</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">내정보 상세보기</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
		<div style="color: white; position: absolute; right: 30px;">${accountName} 님</div>
	</nav>
</c:if>

<!-- 강사 메뉴바 -->
<c:if test="${accountLevel == 2}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand ml-3">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?currentPage=1">강사강좌조회</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lmsNoticeList">LMS 공지사항</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherOne">내정보 상세보기</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
		<div style="color: white; position: absolute; right: 30px;">${accountName} 님</div>
	</nav>
</c:if>

<!-- 운영자 메뉴바 -->
<c:if test="${accountLevel == 3}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand ml-3">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/manager/lmsNoticeList">LMS 공지사항</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/manager/studentQueueList">학생 승인대기리스트</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/teacherQueueList">강사 승인대기리스트</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/textbookList">교재목록</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/subjectList">과목목록</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">내정보 상세보기</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
		<div style="color: white; position: absolute; right: 30px;">${accountName} 님</div>
	</nav>
</c:if>

<!-- 관리자 메뉴바 -->
<c:if test="${accountLevel == 4}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand ml-3">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/admin/managerQueueList">운영자 승인대기리스트</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/admin/adminDetail">내정보 상세보기</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
		<div style="color: white; position: absolute; right: 30px;">${accountName} 님</div>
	</nav>
</c:if>