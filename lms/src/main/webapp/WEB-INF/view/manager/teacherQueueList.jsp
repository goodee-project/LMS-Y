<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>승인대기목록</title>
		
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
			<h1>강사 승인대기목록</h1>
			
			<div>
				<table class="table">
					<tr>
						<th>accountId</th>
						<th>teacherName</th>
						<th>teacherEmail</th>
						<th>teacherPhone</th>
					</tr>
					<c:forEach var="tq" items="${teacherQueueList}">
						<tr>
							<td>${tq.accountId}</td>
							<td><a href="${pageContext.request.contextPath}/manager/teacherQueueDetail?accountId=${tq.accountId}">${tq.teacherName}</a></td>
							<td>${tq.teacherEmail}</td>
							<td>${tq.teacherPhone}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 페이징 -->
				<div>
					<c:if test="${null eq teacherName}">
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
					</c:if>
					<!-- 검색 페이징 -->
					<c:if test="${null ne teacherName}">				
						<div>
							<!-- 처음으로, 이전 -->
							<c:choose>
								<c:when test="${currentPage > 1}">
									<a href="${pageContext.request.pathInfo}?currentPage=1&teacherName=${teacherName}">[처음으로]</a>
									<a href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}&teacherName=${teacherName}"><</a>
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
											<a href="${pageContext.request.pathInfo}?currentPage=${i}&teacherName=${teacherName}">[${i}]</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
							<!-- 다음, 마지막으로 -->
							<c:choose>
								<c:when test="${currentPage < lastPage}">
									<a href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&teacherName=${teacherName}">></a>
									<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}&teacherName=${teacherName}">[마지막으로]</a>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</div>
				<!-- 검색 -->
				<div>
					<form action="${pageContext.request.pathInfo}" method="get">
						<input type="hidden" name="currentPage" value="1">
						<input type="text" name="teacherName" value="${teacherName}">
						<button type="submit">버튼</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>