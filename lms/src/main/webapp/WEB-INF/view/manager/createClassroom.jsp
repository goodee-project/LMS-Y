<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>createClassroom</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        
            $(document).ready(function() {
                
            	// 강의실 번호 유효성 검사 
    			$('#numberId').focus();	
    			// 강의실 번호 공백 체크 
                $('#numberId').blur(function(){
                   	// 공백 
					if($('#numberId').val() ==""){
						$('#numberId').focus();
						$('#numberMsg').text("강의실 번호를 입력해주세요");
						return
						
					}else{
						
						$('#numberMsg').text("");
						
						}
					});
                		
				// 강의실 면적 유효성 검사	
				$('#sizeId').blur(function(){
					if($('#sizeId').val() ==''){
						$('#sizeMsg').text("강의실 면적을 입력해주세요");
						$('#sizeId').focus();
						return
						
					}else{
						
						$('#sizeMsg').text("");
						
						}

				});		

				// 강의실 수용인원 유효성 검사
				$('#totalId').blur(function(){
					if($('#totalId').val() == ''){
						$('#totalMsg').text("강의실 수용인원을 입력해주세요");
						$('#totalId').focus();
						return
						
					}else{
						
						$('#totalMsg').text("");
						
						}
 			
			    });	
			    // 버튼을 클릭했을때 공백여부 확인
				$('#btnId').click(function() {
					if($('#numberId').val() == '' || $('#sizeId').val() == '' || $('#totalId').val() == '') {
						alert('다시 확인하세요');
						return;
					} else {
						$('#postId').submit();
					}
				});
				console.log();  				 	
         });
         
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<div class="jumbotron">
				<h1>강의실</h1>
				
			</div>
			
			<div>
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/createClassroom/">
					
					<table class="table">
						<tr>
								<td>강의실 번호</td>
								<td><input type="number" name="classroomNumber" id="numberId">
									<span id="numberMsg"></span>
								</td>
						</tr>
						<tr>
								<td>강의실 면적(단위:m<sup>2</sup>)</td>
								<td><input type="number" name="classroomSize" id="sizeId">
									<span id="sizeMsg"></span>
								</td>
								
						</tr>
						<tr>
								<td>강의실 수용인원</td>
								<td><input type="number" name="classroomTotal" id="totalId" >
									<span id="totalMsg"></span>
								</td>
						</tr>
					</table>
						<div class="d-flex justify-content-end">
							<button class="btn btn-outline-primary" id="btnId" type="button">입력</button>
							<a type="button" class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/classroomList">취소</a>
						</div>
				</form>
			</div>
		</div>
	</body>
</html>