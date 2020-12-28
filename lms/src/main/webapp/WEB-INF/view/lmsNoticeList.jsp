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
			<c:if test="${null eq lmsNoticeSearch}">
				<!-- student -->
				<c:if test="${accountLevel eq studentLevel}">
					<div>
						<a href="${pageContext.request.contextPath}/student/lmsNoticeList?currentPage=${currentPage-1}">이전</a>
						<a href="${pageContext.request.contextPath}/student/lmsNoticeList?currentPage=${currentPage+1}">다음</a>
					</div>
				</c:if>
				<!-- teacher -->
				<c:if test="${accountLevel eq teacherLevel}">
					<div>
						<a href="${pageContext.request.contextPath}/teacher/lmsNoticeList?currentPage=${currentPage-1}">이전</a>
						<a href="${pageContext.request.contextPath}/teacher/lmsNoticeList?currentPage=${currentPage+1}">다음</a>
					</div>
				</c:if>
				<!-- manager -->
				<c:if test="${accountLevel eq managerLevel}">
					<div>
						<a href="${pageContext.request.contextPath}/manager/lmsNoticeList?currentPage=${currentPage-1}">이전</a>
						<a href="${pageContext.request.contextPath}/manager/lmsNoticeList?currentPage=${currentPage+1}">다음</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/manager/createLMSNotice">공지 생성</a>
					</div>
				</c:if>
			</c:if>
			<c:if test="${null ne lmsNoticeSearch}">
			<!-- 검색 페이징 -->
			</c:if>
		</div>
		
		<!-- 검색 -->
		<div>
			<form action="${pageContext.request.contextPath}/manager/lmsNoticeList" method="get">
				<input type="hidden" name="currentPage" value="${currentPage}">
				<input type="text" name="lmsNoticeSearch" value="${lmsNoticeSearch}">
				<button type="submit">버튼</button>
			</form>
		</div>
	</body>
</html>