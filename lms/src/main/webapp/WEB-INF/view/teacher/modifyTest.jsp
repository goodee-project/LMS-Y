<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>시험정보 수정</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		
		<script>
			$(document).ready(function() {
				$('#submitTestForm').click(function() {
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					oEditors.getById["testContent"].exec("UPDATE_CONTENTS_FIELD", []);
	
					let contentText = $('#testContent').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					if (contentText == '') {										// 시험 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
						alert('시험 내용을 입력해주세요!');
						oEditors.getById["testContent"].exec("FOCUS");
					} else if ($('#testStartDate').val() == '') {					// 시험 시작일을 입력하지 않았을 경우 입력 요구 및 포커스 이동
						alert('시험 시작일을 입력해주세요!');
						$('#testStartDate').focus();
					} else if ($('#testEndDate').val() == '') {						// 시험 종료일을 입력하지 않았을 경우 입력 요구 및 포커스 이동
						alert('시험 종료일을 입력해주세요!');
						$('#testEndDate').focus();
					} else if (new Date($('#testEndDate').val())
							- new Date($('#testStartDate').val()) <= 0) {			// 시험 시작일 및 종료일이 올바르지 않을 경우 수정 요구 및 포커스 이동
						alert('시험 시작일과 시험 종료일이 올바르지 않습니다!');
						$('#testStartDate').focus();
					} else if (new Date($('#testStartDate').val()) < new Date()) {	// 시험 시작일이 오늘 이전일 경우 수정 요구 및 포커스 이동
						alert('시험 시작일은 오늘 이후여야합니다!');
						$('#testStartDate').focus();
					} else {
						// 유효성 검사를 만족했을 경우 submit
						$('#testForm').submit();
					}
				});
				
				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "testContent",	// 적용할 textarea 태그의 id 속성
					sSkinURI: "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html",	
					htParams : {
						bUseToolbar : true,			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,	// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
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
				<h1>시험정보 수정</h1>
			</div>
		</div>
		
		<div class="container">
			<form id="testForm" method="POST" action="${pageContext.request.pathInfo}">
				<input type="hidden" name="lectureNo" value="${param.lectureNo}">
				
				<table class="table">
					<tr class="small">
						<th colspan="4">${lectureName}</th>
					</tr>
					<tr>
						<th style="width: 20%">시험 시작일</th>
						<td style="width: 30%"><input id="testStartDate" type="date" name="testStartDate" value="${test.testStartDate}"></td>
						<th style="width: 20%">시험 종료일</th>
						<td style="width: 30%"><input id="testEndDate" type="date" name="testEndDate" value="${test.testEndDate}"></td>
					</tr>
					<tr>
						<th colspan="4">
							<div class="font-weight-bolder">시험 내용</div>
							<div class="mt-2"><textarea id="testContent" name="testContent" style="width: 100%">${test.testContent}</textarea></div>
						</th>
					</tr>
					<tr>
						<td class="text-right" colspan="4"><button id="submitTestForm" class="btn btn-outline-success" type="button">수정</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>