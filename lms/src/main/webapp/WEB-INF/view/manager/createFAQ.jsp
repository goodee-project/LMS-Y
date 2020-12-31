<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>createFAQ</title>
		
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
            	$('#submitBtn').click(function() {
					oEditors.getById["faqContentId"].exec("UPDATE_CONTENTS_FIELD", []);


            	});
            	let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "faqContentId",	// 적용할 textarea 태그의 id 속성 
					sSkinURI: "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html",	
					htParams : {
						bUseToolbar : true,			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,	// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						I18N_LOCALE : "ko_KR"
					},
					fCreator: "createSEditor2"
				});
                // 폼 유효성 검사
                // code here...
            	
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>FAQ 작성</h1>
			
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/createFAQ">
					
				<table class="table">
					
					<tr>
						<td>FAQ 제목</td>
						<td><input type="text" name="faqTitle"> </td>
					</tr>
					
					<tr>
						<td>FAQ 카테고리</td>
						<td>
							<select name="faqCategory">
								<c:forEach items="${categoryList}" var="cl">	
									<option value="${cl.faqCategory}">${cl.faqCategory}</option>
								</c:forEach>
							</select>
						</td>
					
				</table>
				FAQ 내용
						<textarea id="faqContentId" name="faqContent"></textarea>
						
						<button id= "submitBtn" type="submit">classroomNumber</button>
				</form>
			</div>
		</div>
	</body>
</html>