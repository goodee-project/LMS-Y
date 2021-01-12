<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>lms 공지 추가</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
       	<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
        <script>
            $(document).ready(function() {
				// submit시 유효성 검사
				$('#submitBtn').click(function() {
					oEditors.getById["lmsNoticeContentId"].exec("UPDATE_CONTENTS_FIELD", []);

					// 댓글 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if ($('#lmsNoticeTitleId').val() == '') {
						alert('제목을 입력해주세요.');
						$('#lmsNoticeTitleId').focus();
						return
					}
					if ($('#lmsNoticeContentId').val() == '') {
						alert('내용을 입력해주세요.');
						$('#lmsNoticeContentId').focus();
						return
					}
					// 유효성 검사를 만족했을 경우 submit
					$('#formId').submit();
				});

				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "lmsNoticeContentId",	// 적용할 textarea 태그의 id 속성
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
		<div class="jumbotron">
			<div class="container">
				<h1>lms 공지추가</h1>
			</div>
		</div>
		<div class="container">
			<form id="formId" action="${pageContext.request.contextPath}/manager/createLMSNotice" method="post">
				<table class="table">
					<tr>
						<td>제목</td>
						<td>
							<input class="form-control" id="lmsNoticeTitleId" type="text" name="lmsNoticeTitle">
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<div class="form-group">
							<textarea id="lmsNoticeContentId" name="lmsNoticeContent" style="width: 100%"></textarea>
						</div>
						</td>
					</tr>
				</table>
				<div class="form-group">
					<button class="btn btn-success" id="submitBtn" type="button">추가</button>
				</div>
			</form>
		</div>
	</body>
</html>