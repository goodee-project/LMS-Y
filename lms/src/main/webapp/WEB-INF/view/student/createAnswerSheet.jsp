<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>시험</title>
		
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
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>시험</h1>
			</div>
		</div>
		
		<div class="container">
			<c:if test="${!isTestable}">
				<table class="table">
					<thead>
						<tr class="small">
							<th>${lectureName}</th>
						</tr>
					</thead>
					<tbody>
						<tr class="text-center">
							<td>시험 제출 기간이 아닙니다</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<c:if test="${isTestable}">
				<form method="post" action="${pageContext.request.pathInfo}">
					<input type="hidden" name="lectureNo" value="${param.lectureNo}">
					
					<table class="table">
						<thead>
							<tr class="small">
								<th colspan="2">${lectureName}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="m" items="${list}">
								<tr style="border-top: 2px solid #DEE2E6">
									<th width="20%">${m.multipleChoiceId}번 문제</th>
									<td>${m.multipleChoiceQuestion}</td>
								</tr>
								<c:forEach var="me" items="${m.multipleChoiceExampleList}">
									<tr class="small">
										<th>
											<input type="radio" name="multipleChoiceNo-${m.multipleChoiceNo}" value="${me.multipleChoiceExampleId}">
											<span>${me.multipleChoiceExampleId}</span>
										</th>
										<td>${me.multipleChoiceExampleContent}</td>
									</tr>
								</c:forEach>
								<tr>
									<td class="pb-5" colspan="2"></td>
								</tr>
							</c:forEach>
							<tr>
								<td class="text-right pb-5" colspan="2" style="border-top: 2px solid #DEE2E6">
									<button class="btn btn-outline-success">답안 제출</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</c:if>
		</div>
	</body>
</html>