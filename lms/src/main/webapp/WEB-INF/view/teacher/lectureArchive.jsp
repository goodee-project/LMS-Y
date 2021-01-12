<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌별 자료실</title>
	</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
	<!-- 강좌 메뉴 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
	
		
			<div class="jumbotron">
				<div class=container>
					<h1>${lectureNo}강의 자료실</h1>
				</div>
			</div>
		<!-- 검색 -->
		<div class="container">
			<div style="text-align:left;">
				<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/createLectureArchive?lectureNo=${lectureNo}">글쓰기</a>
			</div>
			<form action="${pageContext.request.pathInfo}" method="get">
					<input type="hidden" name="lectureNo" value="${lectureNo}">
					<input type="hidden" name="currentPage" value="1">
						<div class="justify-content-end mb-3 input-group">
						<input class="form-control col-sm-2" type="text" name="lectureArchiveSearch" value="${lectureArchiveSearch}" placeholder="Search">
						<div class="input-group-append">
							<button class="btn btn-success" type="submit">버튼</button>
						</div>
					</div>
			</form>
		</div>
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="la" items="${lectureArchiveList}">
						<tr>
							<td>${la.lectureArchiveNo}</td>
							<td><a href="${pageContext.request.contextPath}/teacher/lectureArchiveOne?lectureArchiveNo=${la.lectureArchiveNo}">${la.lectureArchiveTitle}</a></td>
							<td>${la.lectureArchiveWriter}</td>
							<td>${la.lectureArchiveCreateDate}</td>
							<td>${la.lectureArchiveCount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 강좌 자료실 목록 페이징 -->
			<div style="margin-left:35%">
				<ul class="pagination">
					<c:if test="${null == lectureArchiveSearch}">
						<c:choose>
							<c:when test="${currentPage > '1'}">
								<!-- 현재 페이지가 1보다 클시 -->
								<!-- 현재 페이지가 1일시 -->
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=1"> &lt;&lt;</a></li>
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${currentPage-1}"> &lt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled">
									<a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=1"> &lt;&lt;</a>
								</li>
								<li class="page-item disabled">	
									<a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${currentPage-1}">&lt;</a>
								</li>
							</c:otherwise>
							</c:choose>
								<!-- 현재 페이지 표시 -->
								<!-- 현재 페이지 표시 -->
								<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
									<c:if test="${i <= lastPage}">
										<c:choose>
											<c:when test="${i == currentPage}">
											<li class="page-item"><a class="page-link" href="#">${i}</a></li>
											</c:when>
											<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${i}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
								<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
								<!-- 현재 페이지가 마지막 페이지 일시 -->
							<c:choose>
							<c:when test="${currentPage < lastPage}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${currentPage+1}">&gt;</a></li>
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${lastPage}">&gt;&gt;</a></li>
							</c:when>
							<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${currentPage+1}">&gt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${lectureNo}&currentPage=${lastPage}">&gt;&gt;</a>
							</li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</ul>
			</div>
			<!-- 검색 목록 페이징 -->
			<div style="margin-left:35%">
				<ul class="pagination">
					<c:if test="${null != lectureArchiveSearch}">
								<c:choose>
									<c:when test="${currentPage > '1'}">
										<!-- 현재 페이지가 1보다 클시 -->
										<!-- 현재 페이지가 1일시 -->
										<li class="page-item"><a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=1&&lectureArchiveSearch=${lectureArchiveSearch}">&lt;&lt;</a></li>
										<li class="page-item"><a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage-1}&&lectureArchiveSearch=${lectureArchiveSearch}">&lt;</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item disabled">
											<a class="page-link" href="#"> &lt;&lt; </a>
										</li>
										<li class="page-item disabled">
											<a class="page-link" href="#">&lt;</a>
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
												<li class="page-item"><a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${i}&&lectureArchiveSearch=${lectureArchiveSearch}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
								<!-- 다음, 마지막으로 -->
								<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
								<!-- 현재 페이지가 마지막 페이지 일시 -->
							<c:choose>
									<c:when test="${currentPage < lastPage}">
										<li class="page-item"><a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage+1}&&lectureArchiveSearch=${lectureArchiveSearch}">&gt;</a></li>
										<li class="page-item"><a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${lastPage}&&lectureArchiveSearch=${lectureArchiveSearch}">&gt;&gt;</a></li>
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
					</c:if>
				</ul>
			</div>
		</div>
</body>
</html>