<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>승인대기목록</title>

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
			<h1>학생 승인대기목록</h1>
		</div>
	</div>
	<div class="container">
		<!-- 검색 -->
		<div class="d-flex justify-content-end">
			<form action="${pageContext.request.pathInfo}" method="get">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="Search"
						name="studentName" value="${studentName}">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit">검색</button>
					</div>
				</div>
			</form>
		</div>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>E-mail</th>
				<th>전화번호</th>
			</tr>
			<c:forEach var="sq" items="${studentQueueList}">
				<tr>
					<td>${sq.accountId}</td>
					<td>
						<a href="${pageContext.request.contextPath}/manager/studentQueueDetail?accountId=${sq.accountId}">${sq.studentName}</a>
					</td>
					<td>${sq.studentEmail}</td>
					<td>${sq.studentPhone}</td>
				</tr>
			</c:forEach>
		</table>
				<!-- 페이징 -->
		<c:if test="${null eq studentName}">
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=1">First</a></li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}">이전</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">First</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">이전</a>
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
					<!-- 다음, 마지막으로 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">다음</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${lastPage}">Last</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">다음</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">Last</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</c:if>
		<!-- 검색 페이징 -->
		<c:if test="${null ne studentName}">
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=1&studentName=${studentName}">First</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}&studentName=${studentName}">이전</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">First</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">이전</a>
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
										href="${pageContext.request.pathInfo}?currentPage=${i}&studentName=${studentName}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- 다음, 마지막으로 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&studentName=${studentName}">다음</a>
							</li>
							<li class="page-item">
								<a class="page-link"
								href="${pageContext.request.pathInfo}?currentPage=${lastPage}&studentName=${studentName}">Last</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="#">다음</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">Last</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>