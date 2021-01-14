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
			
               	// FAQ 제목 유효성 검사
               	$('#faqTitleId').focus();	
               	$('#faqTitleId').blur(function(){
                 // FAQ 제목 공백 검사
				 if($('#faqTitleId').val() ==""){
					alert("입력요청")
					 	$('#faqTitleId').focus();		
					return
					
				}
			});	
    			
            	$('#faqContentId').blur(function(){
                    // FAQ 제목 공백 검사
   				 if($('#faqContentId').val() ==""){
   					alert("입력요청")
   					 oEditors.getById["faqContentId"].exec("FOCUS");	
   					return
   					
   				}
   			});	
    			
            	$('#submitBtn').click(function(){
            		oEditors.getById["faqContentId"].exec("UPDATE_CONTENTS_FIELD", []);		
            		if($('#teacherId').val() == ''){
            			alert('공백이 있는지 확인해주세요');
            		} else  {
    					$('#postId').submit();
    				}
              
    				 if($('#faqContentId').val() ==""){
    					 oEditors.getById["faqContentId"].exec("FOCUS");
    						alert('FAQ 내용을 입력하여주세요');
    						return
    						
    					} 
    				
            });// 이거 지우면 스마트 에디터 사라짐

    			  
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
			<div class="jumbotron">
				<h1>자주하는 질문 작성</h1>
			</div>
			
			<div>
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/createFAQ">
					
				<table class="table">
					<tr>
						<td>
							<!-- FAQCategory -->
							<select name="faqCategory" style="width: 100%" class="btn btn-default dropdown-toggle">
								<c:forEach items="${categoryList}" var="cl">	
									<option  value="${cl.faqCategory}">${cl.faqCategory}</option>
								</c:forEach>
							</select>
							
						</td>
					</tr>
					
					<tr>
						<td><input class="form-control" placeholder="제목을 입력하여 주세요"  type="text" id="faqTitleId" name="faqTitle"> 
							<span id="faqTitleId"></span>
						</td>
					</tr>
					
				</table>
				
						<textarea id="faqContentId" name="faqContent" style="width: 100%" ></textarea>
			
					<div class="form-group d-flex justify-content-end">
						<button class="btn btn-outline-success" id= "submitBtn" type="button">입력</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>