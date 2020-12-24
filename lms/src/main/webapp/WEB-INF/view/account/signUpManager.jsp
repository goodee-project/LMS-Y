<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<!-- jQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				// 초기 아이디 입력칸으로 포커싱
				$('#managerId').focus();
				// 아이디 중복체크
				$('#managerId').blur(function() {
					if($('#managerId').val() == '' || $('#managerId').val().length > 50) {
						$('#managerId').focus();
						$('#idMsg').text('아이디를 확인하세요');
						return;
					}else {
						$('#idMsg').text('');
					}
					$.ajax({
						url: '${pageContext.request.contextPath}/accountIdCheck',
						type: 'post',
						data: {accountId:$('#managerId').val()},
						success: function(data) {
							if(data == 'noPass') {
								$('#idMsg').text('아이디가 중복됩니다');
								$('#managerId').focus();
								return;
							}else {
								$('#idMsg').text('');
							}
						}
					});
				});
				// 비밀번호 확인
				$('#managerPwCk').blur(function() {
					if($('#managerPw').val() != $('#managerPwCk').val()) {
						$('#pwCkMsg').text('비밀번호를 다시 확인하세요');
						$('#managerPwCk').focus();
						return;
					}else {
						$('#pwCkMsg').text('');
					}
				});
				// 이메일 입력 유효성 검사
				$('#managerEmail').blur(function() {
					if($('#managerEmail').val() == '' || $('#managerEmail').val().length > 200) {
						$('#emailCkMsg').text('이메일을 확인하세요');
						$('#managerEmail').focus();
						return;
					}else {
						$('#emailCkMsg').text('');
					}
					$.ajax({
						url: '${pageContext.request.contextPath}/accountEmailCheck',
						type: 'post',
						data: {accountEmail:$('#managerEmail').val()},
						success: function(data) {
							if(data == 'noPass') {
								$('#emailCkMsg').text('이메일이 중복됩니다');
								$('#managerEmail').focus();
								return;
							}else {
								$('#emailCkMsg').text('');
							}
						}
					});
				});
				// 전화번호 숫자만 입력
				$("#managerPhone").on("keyup", function() {
				      $(this).val($(this).val().replace(/[^0-9]/g,""));
				});
				// 우편번호 검색시 요소 추가
				$('#zipCodeSearch').click(function() {
					if($('#zipCode').val() == '') {
						alert('우편번호를 입력하시오');
						return;
					}
					$.ajax({			             
			            url : '${pageContext.request.contextPath}/address',
			            type : 'get',
			            data : {zipCode:$('#zipCode').val()},
			            error : function(){
			                alert('데이터에 오류가 있습니다');
			            },
			            success : function(data){
				            let str = `<div class="form-group">
				            		   <select multiple class="form-control" name="accountAddressMain">`;
				            for(let i=0; i<data.length; i++) {
				            	str += '<option>' + data[i] + '</option>';
					        }
							str += '</select> </div>';
			                $('#addAddr').empty();
							$('#addAddr').append(str);
			            }
			        });
				});
				// 가입하기 버튼 클릭시 유효성 검사
				$('#btnSubmit').click(function() {
					if($('#managerPw').val() == '' || $('#managerPwCk').val() == '' || $('#managerName').val() == '' || $('#managerPhone').val() == '' || $("input[name='accountGender']:checked").val() == '' || $('#managerBirth').val() == '' || $('select[name=accountAddressMain]').val() == null || $('#zipCode').val() == '' || $('#subAddress').val() == '') {
						alert('입력부분을 다시 확인하세요');
						return;
					} else {
						$('#signUpForm').submit();
					}
				});
				console.log();
			});
		</script>
	</head>
	<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
	<div class=container>
		<div class="jumbotron">
			<h1>운영자 회원가입 페이지</h1>
		</div>
		<form action="${pageContext.request.contextPath}/signUpManager" method="post" id="signUpForm">
			<input class="form-control col-sm-4" type="hidden" name="accountLevel" value="3">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="managerId" name="accountId" placeholder="아이디 입력">
						<div id="idMsg"></div>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input class="form-control col-sm-4" type="password" id="managerPw" name="accountPw" placeholder="비밀번호 입력">
						<div id="pwMsg"></div>
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input class="form-control col-sm-4" type="password" id="managerPwCk" placeholder="비밀번호 확인">
						<div id="pwCkMsg"></div>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="managerEmail" name="accountEmail" placeholder="이메일 입력">
						<div id="emailCkMsg"></div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="managerName" name="accountName" placeholder="이름 입력">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input class="form-control col-sm-4" type="tel" id="managerPhone" name="accountPhone" placeholder="- 빼고 숫자만 입력">
					</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input" value="남" name="accountGender">남
						</div>
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input" value="여" name="accountGender">여
						</div>
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<input class="form-control col-sm-4" type="date" id="managerBirth" name="accountBirth">
					</td>
				</tr>
				<tr>
					<td rowspan="2">주소</td>
					<td>
						<div class="input-group">
							<input class="form-control col-sm-3" type="text" id="zipCode" placeholder="우편번호 입력">
							<button class="btn btn-outline-primary" type="button" id="zipCodeSearch">우편번호 검색</button>
						</div>
						<div id="addAddr"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input class="form-control col-sm-7" type="text" id="subAddress" name="accountAddressSub" placeholder="상세주소">
					</td>
				</tr>
			</table><br>
			<div align="center">
				<button class="btn btn-secondary" type="button" id="btnSubmit">가입하기</button>
				<a href="${pageContext.request.contextPath}/managerLogin" class="btn btn-danger" id="cancel">취소하기</a>		
			</div><br>
		</form>
	</div>
	</body>
</html>