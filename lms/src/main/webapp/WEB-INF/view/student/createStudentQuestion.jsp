<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문 추가하기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- 스마트 에디터 -->
        <script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
        
        <script>
            $(document).ready(function() {
            	// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
            	$('#submitBtn').click(function(){
              
				oEditors.getById["questionContent"].exec("UPDATE_CONTENTS_FIELD", []);

				let contentText = $('#questionContent').val().replace(/<.+?>|\s+|&nbsp;/g, '');
				if ($('#lecutreNo').val() == '') {								// 과제 제목을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					alert('강좌번호를 입력해주세요!');
					$('#lecutreNo').focus();
					
				} else if ($('#questionTitle').val() == '') {						// 과제 시작일을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					alert('질문 제목을 입력하세요!');
					$('#questionTitle').focus();
					
				} else if (contentText == '') {										// 과제 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					alert('질문 내용을 입력해주세요!');
					oEditors.getById["questionContent"].exec("FOCUS");
					
				} else {
					// 유효성 검사를 만족했을 경우 submit
					$('#createQuestion').submit();
				}
			});
               
   		
    			
                // code here...
            
			// 스마트 에디터 적용
            let oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "questionContent",	// 적용할 textarea 태그의 id 속성
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
			<div class="jumbotorn">
				<h1>학생 질문 추가</h1>
			</div>
		</div>
			<div class="container">
				<form id="createQuestion" method="post" action="${pageContext.request.contextPath}/student/createStudentQuestion">
					<table class="table">
						
						<tr>
							<td>강좌번호</td>
							<td><input id="lecutreNo" type="text" name="lectureNo"></td>
						</tr>		
			
						<tr>
							<td>제목</td>
							<td><input id="questionTitle" type="text" name="questionTitle"></td>
						</tr>
						
						<tr>
							<td>내용</td>
							<td><textarea id="questionContent" name="questionContent"></textarea></td>
						</tr>
					</table>
					<button type="button" id="submitBtn">[등록]</button>
				</form>
			</div>
	</body>
</html>