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
			var zipCode = $('#zipCode').val();
		
			let addrList = `<div class="form-group">
					  <label for="sel">주소선택</label>
					  <select multiple class="form-control" id="sel">					  
					    	<option>1</option>
					    	<option>2</option>
					    	<option>3</option>			    
					  </select>
					  </div>`
		
			$(document).ready(function() {
				// 초기 아이디 입력칸으로 포커싱
				$('#studentId').focus();
				// 비밀번호 확인
				$('#studentPwCk').blur(function() {
					if($('#studentPw').val() != $('#studentPwCk').val()) {
						$('#pwCkMsg').text('비밀번호를 다시 확인하세요');
						$('#studentPwCk').focus();
					}else {
						$('#pwCkMsg').text('');
					}					
				});
				// 전화번호 숫자만 입력
				$("#studentPhone").on("keyup", function() {
				      $(this).val($(this).val().replace(/[^0-9]/g,""));
				});
				// 우편번호 검색시 요소 추가
				$('#zipCodeSearch').click(function() {
					$('#addAddr').empty();
					$('#addAddr').append(addrList);
				});
			});
		</script>
	</head>
	<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
	<div class=container>
		<div class="jumbotron">
			<h1>학생 회원가입 페이지</h1>
		</div>
		<form action="${pageContext.request.contextPath}/signUp" method="post" id="signUpForm">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="studentId" name="studentId" placeholder="아이디 입력">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input class="form-control col-sm-4" type="password" id="studentPw" name="studentPw" placeholder="비밀번호 입력">
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input class="form-control col-sm-4" type="password" id="studentPwCk" placeholder="비밀번호 확인">
						<div id="pwCkMsg"></div>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="studentEmail" name="studentEmail" placeholder="이메일 입력">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input class="form-control col-sm-4" type="text" id="studentName" name="studentName" placeholder="이름 입력">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input class="form-control col-sm-4" type="tel" id="studentPhone" name="studentPhone" placeholder="- 빼고 숫자만 입력">
					</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input" name="studentGender">남
						</div>
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input" name="studentGender">여
						</div>
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<input class="form-control col-sm-4" type="date" id="studentBirth" name="studentBirth">
					</td>
				</tr>
				<tr>
					<td rowspan="2">주소</td>
					<td>
						<div class="input-group">
							<input class="form-control col-sm-3" type="text" id="zipCode" name="zipCode" placeholder="우편번호 입력">
							<a href="${pageContext.request.contextPath}/address?zipCode=${zipCode}" class="btn btn-outline-primary" id="zipCodeSearch">우편번호 검색</a>
						</div>
						<div id="addAddr"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input class="form-control col-sm-7" type="text" id="subAddress" name="subAddress" placeholder="상세주소">
					</td>
				</tr>
			</table><br>
			<div align="center">
				<button class="btn btn-secondary" type="submit" id="btnSubmit">가입하기</button>
				<a href="${pageContext.request.contextPath}/studentLogin" class="btn btn-danger" id="cancel">취소하기</a>		
			</div><br>
		</form>
	</div>
	</body>
</html>