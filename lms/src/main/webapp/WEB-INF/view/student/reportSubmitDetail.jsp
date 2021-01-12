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
		<div class="jumbotron">
			<div class="container">
							<h1>과제</h1>
			</div>
		</div>
		<div class="container">		
			<div>
				<table class="table mb-3">
					<tr>
						<th>No.</th>
						<td>${reportAndReportSubmit.reportNo}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${reportAndReportSubmit.reportTitle}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${reportAndReportSubmit.reportContent}</td>
					</tr>
					<tr>
						<th>기한</th>
						<td>${reportAndReportSubmit.reportStartDate}~${reportAndReportSubmit.reportEndDate}</td>
					</tr>
					<tr>
						<th>수정 날짜</th>
						<td>${reportAndReportSubmit.reportUpdateDate}</td>
					</tr>
				</table>
				<c:forEach var="rs" items="${reportAndReportSubmit.reportSubmitList}">
					<table class="table">
						<tr>
							<th>SubmitNo.</th>
							<td>${rs.reportSubmitNo}</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${rs.reportSubmitTitle}</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>${rs.reportSubmitContent}</td>
						</tr>
						<tr>
							<th>점수</th>
							<td>${rs.reportSubmitScore}</td>
						</tr>
						<tr>
							<th>피드백</th>
							<td>${rs.reportSubmitFeedback}</td>
						</tr>
						<tr>
							<th>수정 날짜</th>
							<td>${rs.reportSubmitUpdateDate}</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
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
							</td>
						</tr>
					</table>
				<div class="d-flex justify-content-end">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/modifyReportSubmit?reportSubmitNo=${rs.reportSubmitNo}">과제 수정</a>
				</div>
				</c:forEach>
				<c:if test="${empty reportAndReportSubmit.reportSubmitList}">
					<div class="d-flex justify-content-end">
						<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/createReportSubmit?reportNo=${reportAndReportSubmit.reportNo}">과제 제출</a>
					</div>
				</c:if>
			</div>
		</div>
	</body>
</html>