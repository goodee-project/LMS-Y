<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 목록</title>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>질문게시판 목록</h1>
			
			<div>
				<table class="table table-sm">
					<thead>
						<tr>
							<th>번호</th>
							<th>계정</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="q" items="${list}">
							<tr>
								<td>${q.questionNo}</td>
								<td>${q.accountId}</td>
								<td>${q.questionWriter}</td>
								<td>
									<a href="${pageContext.request.contextPath}/teacher/questionDetail?questionNo=${q.questionNo}">
										${q.questionTitle}
									</a>
								</td>
								<td>${q.questionCreateDate}</td>
								<td>${q.questionCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="d-flex justify-content-center">
					<ul class="pagination pagination-sm">
						<%-- 이전 버튼 --%>
						<c:if test="${pageNaviBegin != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?questionNo=${q.questionNo}&currentPage=${pageNaviBegin-1}">이전</a>
							</li>
						</c:if>
						<c:if test="${pageNaviBegin == 1}">
							<li class="page-item disabled">
								<a class="page-link">이전</a>
							</li>
						</c:if>
						
						<%-- 각 페이지 이동 버튼 --%>
						<c:forEach var="p" begin="${pageNaviBegin}" end="${pageNaviEnd}" step="1">
							<c:if test="${p != currentPage}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?questionNo=${q.questionNo}&currentPage=${p}">${p}</a>
								</li>
							</c:if>
							<c:if test="${p == currentPage}">
								<li class="page-item active">
									<a class="page-link">${p}</a>
								</li>
							</c:if>
						</c:forEach>
						
						<%-- 다음 버튼 --%>
						<c:if test="${pageNaviEnd != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?questionNo=${q.questionNo}&currentPage=${pageNaviEnd+1}">다음</a>
							</li>
						</c:if>
						<c:if test="${pageNaviEnd == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">다음</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>