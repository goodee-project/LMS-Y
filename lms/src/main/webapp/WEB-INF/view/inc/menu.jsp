<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- 학생 메뉴바 -->
<c:if test="${accountLevel == 1}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/#">학생수강신청</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/#">학생수강등록</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">index</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
		<div style="color: white; margin-left: 700px">${accountId} 님 반갑습니다</div>
	</nav>
</c:if>

<!-- 강사 메뉴바 -->
<c:if test="${accountLevel == 2}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?currentPage=1">강사강좌조회</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/#">강사과제생성</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">index</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherOne">내정보 상세보기</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
	</nav>
</c:if>

<!-- 운영자 메뉴바 -->
<c:if test="${accountLevel == 3}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/#">운영자</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/#">운영자</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">index</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
	</nav>
</c:if>

<!-- 관리자 메뉴바 -->
<c:if test="${accountLevel == 4}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand">LMS</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
			    <a class="nav-link" href="${pageContext.request.contextPath}/#">관리자</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="${pageContext.request.contextPath}/#">관리자</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/#">index</a>
			</li>
			<li class="nav-item">
		 		<a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
			</li>
		</ul>
	</nav>
</c:if>