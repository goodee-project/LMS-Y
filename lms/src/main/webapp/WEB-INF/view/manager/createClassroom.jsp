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

            	$('#submitId').click(function() {
				 
    			if($('#numberId').val()==''){
				 	alert('강의실 번호를 입력해주세요.');
				   $('#numberId').focus();
        		}else if(!isNaN($('#numberId').val())==false){
					alert('숫자만 가능합니다.');
					$('#numberId').focus();
                }else if($('#sizId').val()==''){
                	alert('강의실 면적을 입력해주세요')
                	$('#sizeId').focus();
            	}else if(!isNaN($('#sizeId').val())==false){
                      alert('숫자만 가능합니다.');
                      $('#sizeId').focus();	
                  }else if($('#totalId').val()==''){
                    alert('강의실 수용인원을 입력해주세요')
                    $('#totalId').focus();
                }else if(!isNaN($('#totalId').val())==false){
					alert('숫자만 가능합니다.');
                      $('#totalId').focus();
                  }   	
    			
            	 });
            });

            
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>강의실 정보 추가</h1>
			
			<div>
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/createClassroom/">
					
					<table class="table">
						<tr>
								<td>강의실 번호</td>
								<td><input type="text" name="classroomNumber" id="numberId"></td>
								
						</tr>
						<tr>
								<td>강의실 면적</td>
								<td><input type="text" name="classroomSize" id="sizeId"></td>
								
						</tr>
						<tr>
								<td>강의실 수용인원</td>
								<td><input type="text" name="classroomTotal" id="totalId" ></td>
						</tr>
					
					</table>
						<button id="submitId" type="submit">입력</button>
				</form>
			</div>
		</div>
	</body>
</html>