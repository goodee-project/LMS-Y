<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과제 상세정보</title>
		
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
				<h1>과제 상세정보</h1>
			</div>
		</div>
		
		<div class="container">
			<table class="table">
				<thead>
					<tr class="small">
						<th colspan="4">No. ${report.reportNo}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>과제 제목</th>
						<td colspan="3">${report.reportTitle}</td>
					</tr>
					<tr class="small">
						<th style="width: 20%">과제 시작(예정)일</th>
						<td style="width: 30%">${report.reportStartDate}</td>
						<th style="width: 20%">과제 종료일</th>
						<td style="width: 30%">${report.reportEndDate}</td>
					</tr>
					<tr class="small">
						<th>과제 작성일</th>
						<td>${report.reportCreateDate}</td>
						<th>과제 수정일</th>
						<td>${report.reportUpdateDate}</td>
					</tr>
					<tr>
						<th colspan="4">과제 내용</th>
					</tr>
					<tr>
						<td class="px-4" colspan="4">${report.reportContent}</td>
					</tr>
					<tr>
						<td class="text-right pb-5" colspan="4">
							<c:if test="${isEditable}">
								<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/modifyReport?reportNo=${report.reportNo}">과제 수정</a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<thead>
					<tr>
						<th colspan="4">
							<span>과제제출</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rs" items="${report.reportSubmitList}">
						<tr class="small" style="border-top: 2px solid #DEE2E6">
							<th>과제제출 고유번호</th>
							<td>${rs.reportSubmitNo}</td>
							<th>제출자 ID</th>
							<td>${rs.accountId}</td>
						</tr>
						<tr class="small">
							<th style="width: 20%">과제제출 점수</th>
							<td style="width: 30%">${rs.reportSubmitScore}</td>
							<th style="width: 20%">과제제출 피드백</th>
							<td style="width: 30%">${rs.reportSubmitFeedback}</td>
						</tr>
						<tr class="small">
							<th>과제제출일</th>
							<td>${rs.reportSubmitCreateDate}</td>
							<th>제출된 과제 수정일</th>
							<td>${rs.reportSubmitUpdateDate}</td>
						</tr>
						<tr>
							<th>과제제출 제목</th>
							<td colspan="3">${rs.reportSubmitTitle}</td>
						</tr>
						<tr>
							<th colspan="4">과제제출 내용</th>
						</tr>
						<tr>
							<td colspan="4">
								<div class="px-4">${rs.reportSubmitContent}</div>
								<div>
									<c:forEach var="rsf" items="${rs.reportSubmitFileList}">
										<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
										<c:if test="${rsf.reportSubmitFileSize > 0}">
											<div>
												<a href="${pageContext.request.contextPath}/teacher/downloadReportSubmitFile?reportSubmitFileUUID=${rsf.reportSubmitFileUUID}">
													${rsf.reportSubmitFileOriginal}
												</a>
												<span class="small">${rsf.reportSubmitFileSize} byte / ${rsf.reportSubmitFileCount}회 다운로드</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td class="text-right pb-5" colspan="4">
								<c:if test="${isEvaluatable == true}">
									<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/evaluateReportSubmit?reportSubmitNo=${rs.reportSubmitNo}">과제 평가</a>
								</c:if>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>