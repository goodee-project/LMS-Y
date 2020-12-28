<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과제 목록</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>과제 목록</h1>
			
			<div>
				<a href="${pageContext.request.contextPath}/teacher/createReport?lectureNo=${param.lectureNo}">과제 생성</a>
				<table class="table table-sm">
					<thead>
						<tr>
							<th>번호</th>
							<th>강좌</th>
							<th>제목</th>
							<th>시작(예정)일</th>
							<th>종료일</th>
							<th>제출갯수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="info" items="${list}">
							<tr>
								<td>${info.report.reportNo}</td>
								<td>${info.report.lectureNo}</td>
								<td>
									<a href="${pageContext.request.contextPath}/teacher/reportDetail?reportNo=${info.report.reportNo}">
										${info.report.reportTitle}
									</a>
								</td>
								<td>${info.report.reportStartDate}</td>
								<td>${info.report.reportEndDate}</td>
								<td>${info.reportSubmitCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pagination pagination-sm">
					<%-- 이전 버튼 --%>
					<c:if test="${pageNaviBegin != 1}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${pageNaviBegin-1}">이전</a>
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
								<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${p}">${p}</a>
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
							<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${pageNaviEnd+1}">다음</a>
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
	</body>
</html>