<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의계획서 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		<script>
			$(document).ready(function() {
				// SUBMIT 버튼 클릭 시 유효성 검사 실시
				$('#submitId').click(function() {
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					// textarea가 SE2로 바뀐건 맞지만, 실제로는 가상의 에디터를 표시해둔거에 불과하기에
					// 따로 내용을 업데이트해줘야 아래의 유효성 검사가 가능하고 Form submit시 데이터가 전송됨
					oEditors.getById["textareaId"].exec("UPDATE_CONTENTS_FIELD", []);
					
					// 댓글 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if ($('#textareaId').val() == '') {
						alert('댓글 내용을 입력해주세요!');
						$('#textareaId').focus();
						return
					}
					// 유효성 검사를 만족했을 경우 submit
					$('#formId').submit();
				});
				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "textareaId",	// 적용할 textarea 태그의 id 속성
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
		
		<div class="container">
			<h1>강의계획서 수정</h1>
			
			<div>
				<form action="${pageContext.request.contextPath}/teacher/createSyllabus?syllabusNo=${syllabus.syllabusNo}">
					<div>
                		<textarea id="textareaId" name="syllabusContent" style="width: 100%">
                			${syllabusDetail.syllabusContent}
                		</textarea>
					</div>
					<div>
						<button id="submitId" type="button">수정</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>