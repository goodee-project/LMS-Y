<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 변경</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script>
		$(document).ready(function(){ 
				$('#accountPw').focus();
				// 기존비밀번호 맞는지 체크
				$('#accountPw').blur(function() {
					if($('#accountPw').val() == '') {
						$('#pwMsg').text('비밀번호를 입력해주세요');
						$('#accountPw').focus();
						return;
					}
					$.ajax({
						url: '${pageContext.request.contextPath}/manager/managerPwCheck',
						type: 'post',
						data: {accountId:$('#accountId').val(),accountPw:$('#accountPw').val()},
						success: function(data) {
							if(data == 0) {
								$('#pwMsg').text('비밀번호를 다시확인해주세요');
								$('#accountPw').focus();
								return;
							}else {
								$('#pwMsg').text('');
							}	
						}
					});
				});
				// 버튼을 클릭했을때 유효성 검사
				$('#btnId').click(function(){
				 // 새 비밀번호 공백확인	
				 if($('#newPw').val() == ''){
					alert('새 비밀번호를 입력하세요!');
					return
				// 새 비밀번호 확인의 공백확인	
				} else if($('#PwCheck').val() == ''){
					alert('새 비밀번호 확인을 입력하세요!');
					return
				// 새 비밀번호 일치 여부 확인		
				} else if($('#newPw').val() != $('#PwCheck').val()){
					alert('새 비밀번호가 일치하지 않습니다.');
					return
				// 비밀번호 확인		
				}  else  {
					$('#fromId').submit();
				}
				 console.log();  

		    });	 
	});	     
		</script>
	</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	<div class="jumbotron">
  		<div class="container">
   			 <h1>비밀번호 변경</h1>
  		</div>
	</div>
	<div class=container>
	<form id="fromId" method="post" action="${pageContext.request.contextPath}/manager/modifyManagerPasswd">
			<input hidden="hidden" type="text" id="accountId" name="accountId" value="${accountId}">
			
			<!-- 사용중인 비밀번호 -->
			<input style="width:500px; height:50px;" type="password" id="accountPw" class="form-control form-control-alternative" placeholder="현재 비밀번호">
			<div id="pwMsg"></div>
			
			<!-- 새 비밀번호 -->
			<input  style="width:500px; height:50px;" type="password" id="newPw" name="accountPw" class="form-control form-control-alternative" placeholder="새 비밀번호" >
			
			<!-- 새 비밀번호 확인 -->
			<input  style="width:500px; height:50px;" type="password" id="PwCheck" class="form-control form-control-alternative" placeholder="새 비밀번호 확인">
		<div>
			<button id="btnId" type="button" class="btn btn-danger">확인</button>
			<a class="btn btn-danger" type="button" href="${pageContext.request.contextPath}/manager/managerDetail">취소</a>
		</div>
	</form>
	</div>
</body>
</html>