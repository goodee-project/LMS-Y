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
            
		    // 버튼을 클릭했을때 공백여부 확인
			$('#btnId').click(function() {
				// 강의실 번호 유효성 검사 
				if($('#numberId').val() ==''){
					$('#numberId').focus();
					$('#numberMsg').text("강의실 호실을 입력해주세요");
					return
			
				}else{
						$('#numberMsg').text('');	
					}	
				// 강의실 면적 유효성 검사
				if($('#sizeId').val() ==''){
					$('#sizeId').focus();
					$('#sizeMsg').text("강의실 면적을 입력해주세요");
					return
			
				}else{
						$('#sizeMsg').text('');	
					}	
				// 강의실 수용인원 유효성 검사	
				if($('#totalId').val() ==''){
					$('#totalId').focus();
					$('#totalMsg').text("강의실 수용인원을 입력해주세요");
					return
			
				}else{
						$('#totalMsg').text('');	
						
				} 
					$('#postId').submit();
			});
			console.log();  				 	
     });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">	
					<h1>강의실</h1>
				</div>
			</div>
			<div class="container">
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/createClassroom/">
					
					<table class="table">
						<tr>
								<td>강의실 호실</td>
								<td><input class ="form-control col-sm-4 "  type="number" name="classroomNumber" id="numberId">
									<span id="numberMsg"></span>
								</td>
						</tr>
						<tr>
								<td>강의실 면적(단위:m<sup>2</sup>)</td>
								<td><input class ="form-control col-sm-4 "  type="number" name="classroomSize" id="sizeId">
									<span id="sizeMsg"></span>
								</td>
								
						</tr>
						<tr>
								<td>강의실 수용인원</td>
								<td><input class ="form-control col-sm-4 " type="number" name="classroomTotal" id="totalId" >
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