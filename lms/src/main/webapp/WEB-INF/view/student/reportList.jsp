<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과제 리스트</title>
		
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
			<h1>과제 리스트</h1>
			<div>
				<table class="table">
					<tr>
						<th>reportNo</th>
						<th>reportTitle</th>
						<th>reportUpdateDate</th>
						<th>reportStartDate</th>
						<th>reportEndDate</th>
					</tr>
					<c:forEach var="r" items="${reportList}">
						<tr>
							<td>${r.reportNo}</td>
							<td><a href="${pageContext.request.contextPath}/student/reportSubmitDetail?reportNo=${r.reportNo}&accountId=${accountId}">${r.reportTitle}</a></td>
							<td>${r.reportUpdateDate}</td>
							<td>${r.reportStartDate}</td>
							<td>${r.reportEndDate}</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<a href="${pageContext.request.pathInfo}?currentPage=1">[처음으로]</a>
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}"><</a>
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
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">></a>
							<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}">[마지막으로]</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</body>
</html>