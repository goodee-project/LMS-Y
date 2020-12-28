<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LMS 공지사항</title>
		
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
			<h1>LMS 공지사항</h1>
			<div>
				<table class="table">
					<tr>
						<th>lmsNoticeNo</th>
						<th>lmsNoticeTitle</th>
						<th>lmsNoticeWriter</th>
						<th>lmsNoticeUpdateDate</th>
					</tr>
					<c:forEach var="n" items="${lmsNoticeList}">
						<tr>
							<td>${n.lmsNoticeNo}</td>
							<td><a href="${pageContext.request.contextPath}/notice/lmsNoticeDetail?lmsNoticeNo=${n.lmsNoticeNo}">${n.lmsNoticeTitle}</a></td>
							<td>${n.lmsNoticeWriter}</td>
							<td>${n.lmsNoticeUpdateDate}</td>
						</tr>				
					</c:forEach>
				</table>
			</div>
			<!-- 그냥 페이징 -->
			<c:if test="${null eq lmsNoticeSearch}">
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
			<c:if test="${null ne lmsNoticeSearch}">				
				<div>
					<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<a href="${pageContext.request.pathInfo}?currentPage=1&lmsNoticeSearch=${lmsNoticeSearch}">[처음으로]</a>
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}&lmsNoticeSearch=${lmsNoticeSearch}"><</a>
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
									<a href="${pageContext.request.pathInfo}?currentPage=${i}&lmsNoticeSearch=${lmsNoticeSearch}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- 다음, 마지막으로 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&lmsNoticeSearch=${lmsNoticeSearch}">></a>
							<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}&lmsNoticeSearch=${lmsNoticeSearch}">[마지막으로]</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<!-- 매니저일 경우 공지 생성버튼 -->
			<c:if test="${accountLevel eq managerLevel}">
				<div>
					<a href="${pageContext.request.contextPath}/manager/createLMSNotice">공지 생성</a>
				</div>
			</c:if>
			<!-- 검색 -->
			<div>
				<form action="${pageContext.request.pathInfo}" method="get">
					<input type="hidden" name="currentPage" value="1">
					<input type="text" name="lmsNoticeSearch" value="${lmsNoticeSearch}">
					<button type="submit">버튼</button>
				</form>
			</div>
		</div>	
	</body>
</html>