<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>시험 정보</title>
		
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
				<h1>시험 정보</h1>
			</div>
		</div>
		
		<div class="container">
			<div>
				<%-- 시험 정보가 생성되지 않았을 경우 --%>
				<c:if test="${test == null}">
					<table class="table">
						<thead>
							<tr>
								<th colspan="4">
									<span>시험 정보</span>
									<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/teacher/createTest?lectureNo=${param.lectureNo}">생성</a>
								</th>
							</tr>
						</thead>
					</table>
				</c:if>
				
				<%-- 시험 정보가 생성되었을 경우 --%>
				<c:if test="${test != null}">
					<table class="table">
						<thead>
							<tr>
								<th colspan="4">
									<span>시험 정보</span>
									<c:if test="${isEditable}">
										<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/teacher/modifyTest?lectureNo=${param.lectureNo}">수정</a>
									</c:if>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr class="small">
								<th style="width: 20%">시험 시작일</th>
								<td style="width: 30%">${test.testStartDate}</td>
								<th style="width: 20%">시험 종료일</th>
								<td style="width: 30%">${test.testEndDate}</td>
							</tr>
							<tr>
								<th colspan="4">시험 내용</th>
							</tr>
							<tr>
								<td class="px-4" colspan="4">${test.testContent}</td>
							</tr>
						</tbody>
					</table>
					<table class="table mt-5">
						<thead>
							<tr>
								<th colspan="3">
									<span>객관식 문제 정보</span>
									<c:if test="${isEditable}">
										<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/teacher/createMultipleChoice?lectureNo=${param.lectureNo}">추가</a>
									</c:if>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${multipleChoiceListSize == 0}">
								<tr>
									<td>시험 문제가 없습니다! 위의 시험문제 추가 버튼을 눌러 시험문제를 추가해주세요!</td>
								</tr>
							</c:if>
							<c:forEach var="mc" items="${multipleChoiceList}">
								<tr style="border-top: 2px solid #DEE2E6">
									<th class="align-middle" style="width: 20%">
										<div class="d-flex flex-column">
											<div>${mc.multipleChoiceId}번 문제</div>
											<div class="small">정답 ${mc.multipleChoiceAnswer}번</div>
										</div>
									</th>
									<td class="align-middle">
										<span>${mc.multipleChoiceQuestion}</span>
									</td>
								</tr>
								<c:forEach var="mce" items="${mc.multipleChoiceExampleList}">
									<tr class="small">
										<th>보기 ${mce.multipleChoiceExampleId}</th>
										<td>${mce.multipleChoiceExampleContent}</td>
									</tr>
								</c:forEach>
								<tr>
									<td class="text-right pb-5" colspan="2">
										<c:if test="${isEditable}">
											<a class="btn btn-outline-primary mx-2" href="${pageContext.request.contextPath}/teacher/modifyMultipleChoice?multipleChoiceNo=${mc.multipleChoiceNo}">수정</a>
											<a class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/teacher/removeMultipleChoice?multipleChoiceNo=${mc.multipleChoiceNo}">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</body>
</html>