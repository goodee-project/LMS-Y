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
				<a href="${pageContext.request.contextPath}/teacher/createReport">과제 생성</a>
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
						<c:forEach var="map" items="${list}">
							<tr>
								<td>${map.report.reportNo}</td>
								<td>${map.report.lectureNo}</td>
								<td>
									<a href="${pageContext.request.contextPath}/teacher/reportDetail?reportNo=${map.report.reportNo}">
										${map.report.reportTitle}
									</a>
								</td>
								<td>${map.report.reportStartDate}</td>
								<td>${map.report.reportEndDate}</td>
								<td>${map.reportSubmitCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:forEach var="p" begin="${pageNaviBegin}" end="${pageNaviEnd}" step="1">
					<c:if test="${p == param.currentPage}">
						<a href="${pageContext.request.pathInfo}?currentPage=${p}">${p}</a>
					</c:if>
					<c:if test="${p != param.currentPage}">
						<a href="${pageContext.request.pathInfo}?currentPage=${p}">${p}</a>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</body>
</html>