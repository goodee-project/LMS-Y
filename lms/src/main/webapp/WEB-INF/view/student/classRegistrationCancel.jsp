<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강 취소</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
       <script>
            $(document).ready(function() {
				// submit시 유효성 검사
				$('#submitBtn').click(function() {
					oEditors.getById["cancelContentId"].exec("UPDATE_CONTENTS_FIELD", []);

					if ($('#cancelContentId').val() == '') {
						alert('내용을 입력해주세요.');
						$('#cancelContentId').focus();
						return
					}
					// 유효성 검사를 만족했을 경우 submit
					$('#formId').submit();
				});

				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "cancelContentId",	// 적용할 textarea 태그의 id 속성
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
				<h1>수강 취소 사유 입력</h1>
			</div>	
		</div>	
		<div class="container">
		</div>	
			<div class="container">
				<form id="formId" method="post" action="${pageContext.request.contextPath}/student/classRegistrationCancel">
					<table class="table">
						<tr>
							<td>강좌 번호</td>
							<td><input class="form-control" id="lectureNo" type="text" name="lectureNo" value="${lectureNo}" readonly="readonly"></td>
						</tr>		
			
						<tr>
							<td>과목 이름</td>
							<td><input class="form-control" id="subjectName" type="text" name="subjectName" value="${lectureName}" readonly="readonly"></td>
						</tr>
						
						<tr>
							<td>취소 사유</td>
							<td><textarea id="cancelContentId" name="cancelContent" style="width: 100%"></textarea></td>
							
							<td><input id="classRegistrationNo" type="hidden" name="classRegistrationNo" value="${classRegistrationNo}"></td>
						</tr>
					</table>
					<div class="form-group d-flex justify-content-end">
						<button class="btn btn-outline-danger" type="submit" id="submitBtn">취소하기</button>
					</div>
				</form>
			</div>
		
	</body>
</html>