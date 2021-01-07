<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 리스트</title>
		
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
			<h1>강좌 리스트</h1>
			
			
				<!-- 강좌 테이블 -->
				<table class="table">
					<tr>
						<th>강좌 번호</th>
						<th>담당 강사</th>
						<th style="width:40%">강좌 제목</th>
						<th>강좌 시작날짜</th>
						<th>강좌 종료날짜</th>
						<th>강좌 정원</th>
						
					</tr>
				<c:forEach items="${ManagerLectureList}" var="ml">
					<tr>
						<td>${ml.lectureNo}</td>
						<td>${ml.teacherName}</td>
						<td><a href="${pageContext.request.contextPath}/manager/managerLectureDetail?lectureNo=${ml.lectureNo}">${ml.lectureName}</a></td>
						<td>${ml.lectureStartDate}</td>
						<td>${ml.lectureEndDate}</td>
						<td>${ml.lectureTotal}</td>
					</tr>
				</c:forEach>	
			</table>
			<!-- 강좌 작성 -->
			<div>
				<a href="${pageContext.request.contextPath}/manager/createLectureManager">강좌 작성</a>
			</div>
			<!-- 페이징 -->
			<div>
				<!-- 처음 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/managerLecture?currentPage=1">[처음]</a>
					</c:when>
				</c:choose>
				
				<!-- 이전 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/managerLecture?currentPage=${currentPage-1}">[이전]</a>
					</c:when>
				</c:choose>
				
				<!-- 현재 페이지 표시 -->
				<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">
									[${i}]
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/manager/managerLecture?currentPage=${i}">[${i}]</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				
				<!-- 다음 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/managerLecture?currentPage=${currentPage+1}">[다음]</a>
					</c:when>
				</c:choose>
				
				<!-- 마지막 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/managerLecture?currentPage=${lastPage}">[마지막]</a>
					</c:when>
				</c:choose>
			</div>	
		</div>
	</body>
</html>