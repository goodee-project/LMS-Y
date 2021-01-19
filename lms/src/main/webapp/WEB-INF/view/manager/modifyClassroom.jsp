<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의실 수정</title>
		
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
					<h1>강의실 수정</h1>
				</div>
			</div>
			<div class="container">
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/modifyClassroom?classroomNo=${classroom.classroomNo}">
					<table class=table>
						<tr>
							<td>강의실 고유번호</td>
							<td><input class ="form-control col-sm-4 " type="text" name="classroomNo"  value="${classroom.classroomNo}" readonly="readonly"></td>
						</tr>
						<tr>
							<td>강의실 호실</td>
							<td><input class ="form-control col-sm-4 " type="number" name="classroomNumber" id="numberId" value="${classroom.classroomNumber}">
								<span id="numberMsg"></span>
							</td>
						</tr>
						<tr>
							<td>강의실 면적 (단위:m<sup>2</sup>)</td>
						<td><input class ="form-control col-sm-4 " type="number" name="classroomSize" id="sizeId" value="${classroom.classroomSize}">
							<span id="sizeMsg"></span>
						</td>
						</tr>
						<tr>
							<td>강의실 수용 인원</td>
							<td><input class ="form-control col-sm-4 " type="number" name="classroomTotal" id="totalId" value="${classroom.classroomTotal}">
								<span id="totalMsg"></span>
							</td>	
						</tr>
						
					</table>
						<div class="text-right">
							<button class="btn btn-outline-success" id="btnId" type="button">수정</button>
						</div>
				</form>
			</div>
	</body>
</html>