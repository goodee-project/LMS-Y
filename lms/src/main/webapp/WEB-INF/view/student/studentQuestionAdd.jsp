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
                // 폼 유효성 검사
                // code here...
            });

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
				<form method="post" action="${pageContext.request.contextPath}/student/studentQuestionAdd">
					<table class="table">
						
						<tr>
							<td>강좌번호</td>
							<td><input id="lecutreNo" type="text" name="lectureNo"></td>
						</tr>		

						<tr>	
							<td>작성자</td>	
							<td><input id="questionWriter" type="text" name="questionWriter"></td>
						</tr>
						
						<tr>
							<td>제목</td>
							<td><input id="questionTitle" type="text" name="questionTitle"></td>
						</tr>
						
						<tr>
							<td>내용</td>
							<td><textarea id="questionContent" cols="60" rows="10" name="questionContent"></textarea></td>
						</tr>
						
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="questionPassword"></td>
						</tr>
					</table>
					<button type="submit">[등록]</button>
				</form>
			</div>
	</body>
</html>