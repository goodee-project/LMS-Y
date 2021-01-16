<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내질문 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
        <script>
        $(document).ready(function() {
			// 작성 버튼 클릭 시 유효성 검사 실시
			$('#submitBtn').click(function() {
				// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
				oEditors.getById["questionContent"].exec("UPDATE_CONTENTS_FIELD", []);
				
				if($('#questionTitle').val()==''|| $('#questionContent').val()==''){
					alert('제목과 내용을 입력하세요');
					return;
				}else{
				// 유효성 검사를 만족했을 경우 submit
				$('#modifyQuestion').submit();
				}
			});
			// NAVER SmartEditor2 적용 코드
			let oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "questionContent",	// 적용할 textarea 태그의 id 속성
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
		<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
		<div class="jumbotron">
			<div class="container">
				<h1>질문 수정</h1>
			</div>
		</div>
			<div class="container">
				<form id="modifyQuestion" method="post" action="${pageContext.request.contextPath}/student/modifyStudentQuestion">
				<input name="lectureNo" value="${question.lectureNo}" hidden="hidden">
					<table class="table">
						<tr>
							<td>질문 번호</td>
							<td><input class="form-control" type="text" name="questionNo" id="questionNo" value="${question.questionNo}" readonly="readonly"></td>
						</tr>
					
						<tr>
							<td>제목</td>
							<td><input class="form-control" type="text" name="questionTitle" id="questionTitle" value="${question.questionTitle}"></td>
						</tr>
						
						<tr>
							<td>내용</td>
							<td><textarea rows="10" cols="100" style="width:800px; height:312px; display:none;" class="form-control" name="questionContent" id="questionContent">${question.questionContent}</textarea></td>
						</tr>		
						</table>
					<div style="text-align:right">
						<button class="btn btn-outline-success" type="button" id="submitBtn">수정</button>
					</div>
				</form>
			</div>
	</body>
</html>