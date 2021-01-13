<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[여기에 이 페이지의 특징을 잘 살린 제목을 넣어주세요]</title>

<!-- jQuery 스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		<div class="jumbotron">
			<h1>${accountId}님의출석</h1>
		</div>
	</div>

	<div class=container>
		<table class="table">
			<thead>
				<tr>
					<td>날짜</td>
					<td>강좌 번호</td>
					<td>계정 id</td>
					<td>출석 상태</td>
					<td>비고</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${attendanceList}">
					<tr>
						<td>${a.attendanceCreateDate}</td>
						<td>${a.lectureNo}</td>
						<td>${a.accountId}</td>
						<td>${a.attendanceState}</td>
						<td>${a.attendanceRemark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${null eq classregistrationAllList}">
			<div>
				<!-- 처음으로, 이전 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.pathInfo}?currentPage=1">[처음으로]</a>
						<a
							href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}"><</a>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<!-- 현재페이지 네비바 -->
				<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">[${i}]</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.pathInfo}?currentPage=${i}">[${i}]</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<!-- 다음, 마지막으로 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a
							href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">></a>
						<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}">[마지막으로]</a>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>
	</div>
</body>
</html>