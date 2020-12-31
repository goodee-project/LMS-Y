<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>수강신청</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                // code here...
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		 
		<div class="container">
			<h1>신청 가능 목록</h1> <a href="${pageContext.request.contextPath}/student/classRegistrationAdd">수강신청 하기</a>
			<table class="table table-sm">
					<tr>
						<th>강좌 번호</th>
						<th>강사 이름</th>
						<th>수강 리뷰(점수)</th>
						<th>리뷰(텍스트)</th>
					</tr>
				<tbody>
					<c:forEach var="r" items="${registrationList}">
					<tr>
						<td><a href="${pageContext.request.contextPath}/student/classRegistartionDetail?subjectNo=${subjectNo}">${r.lectureNo}</a></td>
						<td>${r.teacherName}</td>		
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div style="margin-left:47%">
			<!-- 현재 페이지가 1일시 -->
			<a href="${pageContext.request.contextPath}/student/classRegistration?currentPage=${currentPage-1}">이전</a>	
			<!-- 현재 페이지 표시 -->
			<a href="">${currentPage}</a>
			<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
			<a href="${pageContext.request.contextPath}/student/classRegistration?currentPage=${currentPage+1}">다음</a>
			<!-- 마지막 페이지 표시 -->
		<!-- 	<a href="${pageContext.request.contextPath}/student/classRegistration?" -->
		</div>
	</body>
</html>