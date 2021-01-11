<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 학생 메뉴바 -->
<c:if test="${accountLevel == 1}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">		
			<ul class="navbar-nav">
				<li class="nav-item">
				    <a class="navbar-brand" href="${pageContext.request.contextPath}/student/index">LMS</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/student/classRegistration">수강신청 목록</a>
				</li>
				<li class="nav-item">
				  	<a class="nav-link" href="${pageContext.request.contextPath}/student/classRegistrationAll">수강등록</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/student/lmsNoticeList">LMS 공지사항</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						<span style="color: #FFFFFF;">${accountName} 님</span>
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/student/studentDetail">내정보</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</div>
			    </li>
			</ul>
		</div>
	</nav>
</c:if>

<!-- 강사 메뉴바 -->
<c:if test="${accountLevel == 2}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">				
			<ul class="navbar-nav">
				<li class="nav-item">
				    <a class="navbar-brand" href="${pageContext.request.contextPath}/student/index">LMS</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?currentPage=1">강사강좌조회</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/teacher/lmsNoticeList">LMS 공지사항</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						<span style="color: #FFFFFF;">${accountName} 님</span>
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/teacherOne">내정보</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</div>
			    </li>
			</ul>
		</div>
	</nav>
</c:if>

<!-- 운영자 메뉴바 -->
<c:if test="${accountLevel == 3}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
			<ul class="navbar-nav">
				<li class="nav-item">
				    <a class="navbar-brand" href="${pageContext.request.contextPath}/student/index">LMS</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						승인대기 LIST
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/manager/studentQueueList">학생 승인대기</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/manager/teacherQueueList">강사 승인대기</a>
					</div>
			    </li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/textbookList">교재</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/subjectList">과목</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/managerLecture ">강좌</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/classroomList ">강의실</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/manager/lmsNoticeList">LMS 공지사항</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/manager/FAQList">자주하는질문(FAQ)</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						<span style="color: #FFFFFF;">${accountName} 님</span>
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/manager/managerDetail">내정보</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</div>
			    </li>
			</ul>
		</div>
	</nav>
</c:if>

<!-- 관리자 메뉴바 -->
<c:if test="${accountLevel == 4}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
			<ul class="navbar-nav">
				<li class="nav-item">
				    <a class="navbar-brand" href="${pageContext.request.contextPath}/student/index">LMS</a>
				</li>
				<li class="nav-item">
				    <a class="nav-link" href="${pageContext.request.contextPath}/admin/managerQueueList">운영자 승인대기</a>
				</li>
				<li class="nav-item">
			 		<a class="nav-link" href="${pageContext.request.contextPath}/receiveMessage">쪽지함</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
						<span style="color: #FFFFFF;">${accountName} 님</span>
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/adminDetail">내정보</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</div>
			    </li>
			</ul>
		</div>
	</nav>
</c:if>