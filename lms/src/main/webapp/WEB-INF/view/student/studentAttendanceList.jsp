<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 출석부</title>

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
	<!-- 강좌 메뉴 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>

	<div class="jumbotron">
		<div class="container">
			<h1>${lectureNo}강좌의 ${accountId}님의출석</h1>
		</div>
	</div>

	<div class=container>
		<table class="table">
			<thead>
				<tr class="text-center">
					<td>출석 날짜</td>
					<td>강좌 No(css확인후 제거)</td>
					<td>계정 id</td>
					<td>출석 상태</td>
					<td>비고</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="a" items="${attendanceList}">
					<tr class="text-center">
						<td>${a.attendanceCreateDate}</td>
						<td>${a.lectureNo}</td>
						<td>${a.accountId}</td>
						<td>${a.attendanceState}</td>
						<td>${a.attendanceRemark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${null eq attendanceCount}">
		<div style="margin-left:40%">
			<ul class="pagination">
				<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > '1'}">
							<!-- 현재 페이지가 1보다 클시 -->
							<!-- 현재 페이지가 1일시 -->
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=1&&attendanceCount=${attendanceCount}">&lt;&lt;</a></li>
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage-1}&&attendanceCount=${attendanceCount}">&lt;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link" href="#">
									&lt;&lt; </a></li>
							<li class="page-item disabled"><a class="page-link" href="#">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
					<!-- 현재페이지 네비바 -->
					<!-- 현재 페이지 표시 -->
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<li class="page-item"><a class="page-link" href="#">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${i}&&attendanceCount=${attendanceCount}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- 다음, 마지막으로 -->
					<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
					<!-- 현재 페이지가 마지막 페이지 일시 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage+1}&&attendanceCount=${attendanceCount}">&gt;</a></li>
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${lastPage}&&attendanceCount=${attendanceCount}">&gt;&gt;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link" href="#">&gt;</a>
							</li>
							<li class="page-item disabled"><a class="page-link" href="#">&gt;&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>