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
		
		<div class="container">
			<h1>과제 상세정보</h1>
			
			<div>
				<div>
					과제 고유번호: ${report.reportNo}
				</div>
				<div>
					강좌 고유번호: ${report.lectureNo}
				</div>
				<div>
					과제 제목: ${report.reportTitle}
				</div>
				<div>
					과제 내용: ${report.reportContent}
				</div>
				<div>
					과제 작성일: ${report.reportCreateDate}
				</div>
				<div>
					과제 수정일: ${report.reportUpdateDate}
				</div>
				<div>
					과제 시작(예정)일: ${report.reportStartDate}
				</div>
				<div>
					과제 종료일: ${report.reportEndDate}
				</div>
				<c:forEach var="rs" items="${report.reportSubmitList}">
					<div>
						과제제출 고유번호: ${rs.reportSubmitNo}
					</div>
					<div>
						제출자 ID: ${rs.accountId}
					</div>
					<div>
						과제제출일: ${rs.reportSubmitCreateDate}
					</div>
					<div>
						제출된 과제 수정일: ${rs.reportSubmitUpdateDate}
					</div>
					<div>
						과제제출 제목: ${rs.reportSubmitTitle}
					</div>
					<div>
						과제제출 내용: ${rs.reportSubmitContent}
					</div>
					<c:forEach var="rsf" items="${rs.reportSubmitFileList}">
						<div>
							<a href="${pageContext.request.contextPath}/teacher/downloadReportSubmitFile?reportSubmitFileUUID=${rsf.reportSubmitFileUUID}">
								${rsf.reportSubmitFileOriginal}
							</a>
							${rsf.reportSubmitFileSize}B,
							${rsf.reportSubmitFileType},
							${rsf.reportSubmitFileCount}회 다운로드,
							${rsf.reportSubmitFileCreateDate}
						</div>
					</c:forEach>
					<div>
						과제제출 점수: ${rs.reportSubmitScore}
					</div>
					<div>
						과제제출 피드백: ${rs.reportSubmitFeedback}
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>