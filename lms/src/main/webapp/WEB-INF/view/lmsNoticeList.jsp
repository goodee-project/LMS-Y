<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS 공지사항</title>

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
	<div class="jumbotron">
		<div class="container">
			<h1>LMS 공지사항</h1>
		</div>
	</div>
	<div class="container">
		<!-- 매니저일 경우 공지 추가버튼 -->
		<c:if test="${accountLevel eq managerLevel}">
			<div class="d-flex justify-content-start mb-3">
				<a class="btn btn-outline-primary"
					href="${pageContext.request.contextPath}/manager/createLMSNotice">공지 추가</a>
			</div>
		</c:if>
		<!-- 검색 -->
		<div class="d-flex justify-content-end">
			<form action="${pageContext.request.pathInfo}" method="get">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="Search"
						name="lmsNoticeSearch" value="${lmsNoticeSearch}">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit">검색</button>
					</div>
				</div>
			</form>
		</div>
		<table class="table">
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>작성자</th>
				<th>수정 날짜</th>
			</tr>
			<c:forEach var="n" items="${lmsNoticeList}">
				<tr>
					<td>${n.lmsNoticeNo}</td>
					<td>
						<a href="${pageContext.request.contextPath}/notice/lmsNoticeDetail?lmsNoticeNo=${n.lmsNoticeNo}">${n.lmsNoticeTitle}</a>
					</td>
					<td>${n.lmsNoticeWriter}</td>
					<td>${n.lmsNoticeUpdateDate}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 페이징 -->
		<c:if test="${null eq lmsNoticeSearch}">
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<!-- 처음으로, &lt; -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=1">&lt;&lt;</a></li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}">&lt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">&lt;&lt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
					<!-- 현재페이지 네비바 -->
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<li class="page-item active">
										<a class="page-link" href="#">${i}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link"
										href="${pageContext.request.pathInfo}?currentPage=${i}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- &gt;, 마지막으로 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">&gt;</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${lastPage}">&gt;&gt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">&gt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">&gt;&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</c:if>
		<!-- 검색 페이징 -->
		<c:if test="${null ne lmsNoticeSearch}">
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<!-- 처음으로, &lt; -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=1&lmsNoticeSearch=${lmsNoticeSearch}">&lt;&lt;</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}&lmsNoticeSearch=${lmsNoticeSearch}">&lt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">&lt;&lt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
					<!-- 현재페이지 네비바 -->
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<li class="page-item active">
										<a class="page-link" href="#">${i}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link"
										href="${pageContext.request.pathInfo}?currentPage=${i}&lmsNoticeSearch=${lmsNoticeSearch}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- &gt;, 마지막으로 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&lmsNoticeSearch=${lmsNoticeSearch}">&gt;</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${lastPage}&lmsNoticeSearch=${lmsNoticeSearch}">&gt;&gt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">&gt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">&gt;&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>