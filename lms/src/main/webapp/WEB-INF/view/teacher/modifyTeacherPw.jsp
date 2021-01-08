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
			$(document).ready(function() {
				// 초기 기존비밀번호 입력칸으로 포커싱
				$('#accountPw').focus();
				// 기존비밀번호 맞는지 체크
				$('#accountPw').blur(function() {
					if($('#accountPw').val() == '' || $('#accountPw').val().length > 50) {
						$('#accountPw').focus();
						$('#teacherPasswordMsg').text('비밀번호를 확인하세요');
						return;
					}else {
						$('#teacherPasswordMsg').text('');
					}
					$.ajax({
						url: '${pageContext.request.contextPath}/teacher/teacherPwCk',
						type: 'post',
						data: {accountId:$('#accountId').val(),accountPw:$('#accountPw').val()},
						success: function(data) {
							if(data == 0) {
								$('#teacherPasswordMsg').text('비밀번호가 틀렸습니다!');
								$('#accountPw').focus();
								return;
							}else {
								$('#teacherPasswordMsg').text('');
							}
						}
					});
				});

			//비밀번호 확인
			$('#teacherPwCk').blur(function() {
				if($('#teacherPw').val() != $('#teacherPwCk').val()) {
					$('#pwCkMsg').text('비밀번호를 다시 확인하세요!');
					$('#teacherPwCk').focus();
					return;
				}else {
					$('#pwCkMsg').text('');
				}
			});

			//입력 버튼 클릭시 유효성 검사
			$('#btnPw').click(function(){
				if($('#accountPw').val() ==''||$('#teacherPw').val() != $('#teacherPwCk').val()|| $('#teacherPw').val() ==''|| $('#teacherPwCk').val()==''){
					alert('입력부분을 다시 확인하세요');
					return;
				}else{
					alert('비밀번호 변경완료!');
					alert('다시 로그인 해주세요!');
					$('#modifyTeacherPw').submit();
				}
			});
		});
		</script>
	</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	<div class=container>
		<div class="jumbotron">
			<h1>비밀번호 변경</h1>
		</div>
	</div>
	<div class=container>
	<form id="modifyTeacherPw" method="post" action="${pageContext.request.contextPath}/teacher/modifyTeacherPw">
			<input hidden="hidden" type="text" id="accountId" name="accountId" value="${accountId}">
			
			<!-- 기존비밀번호 -->
			<input type="password" id="accountPw" class="form-control form-control-alternative" placeholder="현재 비밀번호를 입력하세요.">
			<div id="teacherPasswordMsg"></div>
			
			<!-- 변경할 비밀번호 -->
			<input type="password" id="teacherPw" name="accountPw" class="form-control form-control-alternative" placeholder="새로운 비밀번호를 입력하세요." >
			<div id="pwMsg"></div>
			<input type="password" id="teacherPwCk" class="form-control form-control-alternative" placeholder="새로운 비밀번호를 다시 입력하세요">
			<div id="pwCkMsg"></div>
		<div>
			<button id="btnPw" type="button" class="btn btn-danger">입력</button>
		</div>
	</form>
	</div>
</body>
</html>