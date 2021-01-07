<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과제</title>
		
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
		
		<div class="container">
			<h1>과제</h1>
			
			<div>
				<table class="table">
					<tr>
						<th>reportNo</th>
						<td>${reportAndReportSubmit.reportNo}</td>
					</tr>
					<tr>
						<th>reportTitle</th>
						<td>${reportAndReportSubmit.reportTitle}</td>
					</tr>
					<tr>
						<th>reportContent</th>
						<td>${reportAndReportSubmit.reportContent}</td>
					</tr>
					<tr>
						<th>reportUpdateDate</th>
						<td>${reportAndReportSubmit.reportUpdateDate}</td>
					</tr>
					<tr>
						<th>reportDate</th>
						<td>${reportAndReportSubmit.reportStartDate}~${reportAndReportSubmit.reportEndDate}</td>
					</tr>
				</table>
				<c:forEach var="rs" items="${reportAndReportSubmit.reportSubmitList}">
					<table class="table">
						<tr>
							<th>reportSubmitNo</th>
							<td>${rs.reportSubmitNo}</td>
						</tr>
						<tr>
							<th>reportSubmitTitle</th>
							<td>${rs.reportSubmitTitle}</td>
						</tr>
						<tr>
							<th>reportSubmitContent</th>
							<td>${rs.reportSubmitContent}</td>
						</tr>
						<tr>
							<th>reportSubmitScore</th>
							<td>${rs.reportSubmitScore}</td>
						</tr>
						<tr>
							<th>reportSubmitFeedback</th>
							<td>${rs.reportSubmitFeedback}</td>
						</tr>
						<tr>
							<th>reportSubmitUpdateDate</th>
							<td>${rs.reportSubmitUpdateDate}</td>
						</tr>
					</table>
					<div>
						<c:forEach var="rsf" items="${rs.reportSubmitFileList}">
							<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
							<c:if test="${rsf.reportSubmitFileSize > 0}">
								<div>
									<a href="${pageContext.request.contextPath}/student/downloadReportSubmitFile?reportSubmitFileUUID=${rsf.reportSubmitFileUUID}">
										${rsf.reportSubmitFileOriginal}
									</a>
									${rsf.reportSubmitFileSize},
									${rsf.reportSubmitFileType},
									${rsf.reportSubmitFileCount}회 다운로드,
									${rsf.reportSubmitFileCreateDate}
								</div>
							</c:if>
						</c:forEach>
					</div>
				<div>
					<a href="${pageContext.request.contextPath}/student/modifyReportSubmit?reportSubmitNo=${rs.reportSubmitNo}">과제 수정</a>
				</div>
				</c:forEach>
				<c:if test="${empty reportAndReportSubmit.reportSubmitList}">
					<div>
						<a href="${pageContext.request.contextPath}/student/createReportSubmit?reportNo=${reportAndReportSubmit.reportNo}">과제 제출</a>
					</div>
				</c:if>
			</div>
		</div>
	</body>
</html>