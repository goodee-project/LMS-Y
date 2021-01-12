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
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>과제 목록</h1>
			</div>
		</div>
		
		<div class="container">
			<div>
				<a class="btn btn-outline-primary mb-5" href="${pageContext.request.contextPath}/teacher/createReport?lectureNo=${param.lectureNo}">과제 생성</a>
				<table class="table">
					<thead>
						<tr>
							<th>No.</th>
							<th style="width: 40%">제목</th>
							<th>시작(예정)일</th>
							<th>종료일</th>
							<th>제출갯수</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${pageNaviBegin == 0 || pageNaviEnd == 0}">
							<tr>
								<td class="text-center" colspan="5">
									<span>생성된 과제가 없습니다</span>
								</td>
							</tr>
						</c:if>
						<c:forEach var="info" items="${list}">
							<tr>
								<td>${info.report.reportNo}</td>
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
						<tr>
							<td colspan="5"></td>
						</tr>
					</tbody>
				</table>
				
				<c:if test="${pageNaviBegin > 0 && pageNaviEnd > 0}">
					<div class="d-flex justify-content-center mt-4">
						<ul class="pagination">
							<%-- 이전 버튼 --%>
							<c:if test="${pageNaviBegin != 1}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}">&lt;&lt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${pageNaviBegin-1}">&lt;</a>
								</li>
							</c:if>
							<c:if test="${pageNaviBegin == 1}">
								<li class="page-item disabled">
									<a class="page-link">&lt;&lt;</a>
								</li>
								<li class="page-item disabled">
									<a class="page-link">&lt;</a>
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
									<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${pageNaviEnd+1}">&gt;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?lectureNo=${param.lectureNo}&currentPage=${lastPage}">&gt;&gt;</a>
								</li>
							</c:if>
							<c:if test="${pageNaviEnd == lastPage}">
								<li class="page-item disabled">
									<a class="page-link">&gt;</a>
								</li>
								<li class="page-item disabled">
									<a class="page-link">&gt;&gt;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</body>
</html>