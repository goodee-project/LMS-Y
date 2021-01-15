<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>계정찾기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
				// 이메일 확인
				$('#findEmail').click(function() {
					let str = `<button type="button" class="btn btn-outline-success btn-block mt-3" id="btnKey">이메일 보내기</button>`;
					$.ajax({
						url: '${pageContext.request.contextPath}/findEmail',
						type: 'get',
						data: {email:$('#email').val()},
						success: function(data) {
							if(data == 'noPass') {
								alert('해당 이메일은 등록되지 않았습니다');
								 $('#btnKey').remove();
								return;
							}else {
								$('#btnKey').remove();
								$('#addBtn').append(str);
							}
						}
					});					
				});               
                // 유효성 검사
                $(document).on('click','#btnKey',function() {
                	console.log($('#email').val());
                	if($('#email').val() == '') {
						alert('이메일을 입력하세요');
						return;
                    }else {
                    	alert('이메일로 전송되었습니다');
                    	$('#findForm').submit();
                    }
                });
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class="jumbotron">
			<div class="container">
				<h1>ID/PW 찾기</h1>
			</div>
		</div>
			
		<div class="container">
			<div align="center">
				<p class="text-success mt-5 mb-3">해당 이메일로 ID/PW를 전송합니다</p>
				<form action="${pageContext.request.contextPath}/findAccount" method="post" id="findForm">
					<table style="width: 300px;">
						<tr>
							<td>Email&nbsp;</td>
							<td>
								<div class="input-group mb-3">
									<input type="email" class="form-control" name="email" id="email" placeholder="이메일 입력">
									<div class="input-group-append">
										<button type="button" class="btn btn-success" id="findEmail">확인</button>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="addBtn"></div>
								<a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary btn-block mt-3" id="btnSubmit">로그인으로</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>