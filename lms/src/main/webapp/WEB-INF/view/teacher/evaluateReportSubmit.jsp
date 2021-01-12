<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과제제출 평가</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		
		<script>
			$(document).ready(function() {
				// 작성 버튼 클릭 시 유효성 검사 실시
				$('#submitReportEvaluateForm').click(function() {
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					oEditors.getById["reportSubmitFeedback"].exec("UPDATE_CONTENTS_FIELD", []);
					
					// 점수를 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if ($('#reportSubmitScore').val() == '') {
						alert('점수를 입력해주세요!');
						$('#reportSubmitScore').focus();
					} else if (parseInt($('#reportSubmitScore').val()) < 0 || parseInt($('#reportSubmitScore').val()) > 100) {
						alert('점수는 0점 이상 100점 이하로 입력해주세요!');
						$('#reportSubmitScore').focus();
					} else {
						// 유효성 검사를 만족했을 경우 submit
						$('#reportEvaluateForm').submit();
					}
				});
				
				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "reportSubmitFeedback",	// 적용할 textarea 태그의 id 속성
					sSkinURI: "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html",	
					htParams : {
						bUseToolbar : true,						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						I18N_LOCALE : "ko_KR"
					},
					fCreator: "createSEditor2"
				});
			});
		</script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>과제제출 평가</h1>
			</div>
		</div>
		
		<div class="container">
			<form id="reportEvaluateForm" method="post" action="${pageContext.request.pathInfo}">
				<input type="hidden" name="reportSubmitNo" value="${reportSubmit.reportSubmitNo}">
				
				<table class="table">
					<tr class="small">
						<th colspan="2">No. ${reportSubmit.reportSubmitNo}</th>
					</tr>
					<tr>
						<th style="width: 20%">과제제출자 ID</th>
						<td>${reportSubmit.accountId}</td>
					</tr>
					<tr>
						<th>과제제출 제목</th>
						<td>${reportSubmit.reportSubmitTitle}</td>
					</tr>
					<tr>
						<th>과제제출 내용</th>
						<td>
							<div>${reportSubmit.reportSubmitContent}</div>
							<div>
								<c:forEach var="rsf" items="${reportSubmit.reportSubmitFileList}">
									<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
									<c:if test="${rsf.reportSubmitFileSize > 0}">
										<div>
											<a href="${pageContext.request.contextPath}/teacher/downloadReportSubmitFile?reportSubmitFileUUID=${rsf.reportSubmitFileUUID}">
												${rsf.reportSubmitFileOriginal}
											</a>
											${rsf.reportSubmitFileSize}B,
											${rsf.reportSubmitFileType},
											${rsf.reportSubmitFileCount}회 다운로드,
											${rsf.reportSubmitFileCreateDate}
										</div>
									</c:if>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<td class="pb-5" colspan="2"></td>
					</tr>
					<tr>
						<th class="align-middle">점수</th>
						<td><input id="reportSubmitScore" class="form-control" type="number" name="reportSubmitScore" value="${reportSubmit.reportSubmitScore}" min="0" max="100"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="font-weight-bolder">피드백</div>
							<div class="mt-2"><textarea id="reportSubmitFeedback" name="reportSubmitFeedback" style="width: 100%">${reportSubmit.reportSubmitFeedback}</textarea></div>
						</td>
					</tr>
					<tr>
						<td class="text-right" colspan="2"><button id="submitReportEvaluateForm" class="btn btn-outline-success" type="button">평가</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>