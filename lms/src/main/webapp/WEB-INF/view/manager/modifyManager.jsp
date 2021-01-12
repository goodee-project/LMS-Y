<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 정보 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
     $(document).ready(function() {
		    // 버튼을 클릭했을때 유효성 검사
			$('#btnId').click(function() {
				//이메일 공백 확인
				if($('#emailId').val() == ''){
					alert('이메일을 입력하여 주세요!');
					return;
				// 전화번호 공백확인	
				} else if($('#phoneId').val() == ''){
					alert('번호를 입력하여 주세요!');
					return
				// 전화번호 공백확인		
				} else if($('#nameId').val() == ''){
					alert('이름을 입력하여 주세요!');
					return
				// 생일 공백확인		
				} else if($('#birthId').val() == ''){
					alert('생일을 입력하여 주세요!');
					return
				// 직책 공백확인		
				} else if($('#positionId').val() == ''){
					alert('직책을 입력하여 주세요!');
					return
				// 상세주소 공백확인	
				} else if($('#addressSubId').val() == ''){
					alert('상세주소를 입력하여 주세요!');
					return
				} else  {
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
			<h1>운영자 정보수정</h1>
		</div>	
			<div>
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/modifyManager">
					<table class=table>
						<tr>
							<td>운영자 아이디</td>
							<td><input type="text" name="accountId" value="${manager.accountId}" readonly="readonly"></td>
						</tr>
						<tr>
							<td>운영자 이메일</td>
							<td><input id="emailId" type="text" name="managerEmail" value="${manager.managerEmail}"></td>
						</tr>
						<tr>
							<td>운영자 전화번호('-'없이 번호만 입력해주세요)</td>
						   <td><input id="phoneId" type="text" name="managerPhone" value="${manager.managerPhone}"></td>
						</tr>
						<tr>
							<td>운영자 이름</td>
							<td><input id="nameId" type="text" name="managerName" value="${manager.managerName}"></td>
						</tr>
						<tr>
							<td>운영자 성별</td>
							<td>
								<input type="radio" name="managerGender" value="남" id="female"checked="checked">남자
								<input type="radio" name="managerGender" value="여" id="male">여자
							</td>
						</tr>
						<tr>
							<td>운영자 생년월일</td>
							<td><input id="birthId" type="date" name="managerBirth" value="${manager.managerBirth}"></td>
						</tr>
						<tr>
							<td>운영자 직책</td>
							<td><input id="positionId" type="text" name="managerPosition" value="${manager.managerPosition}"></td>
						</tr>
						<tr>
							<td>운영자 주소</td>
							<td><input type="text" name="managerAddressMain" value="${manager.managerAddressMain}"></td>
						</tr>
						<tr>
							<td>운영자 상세주소</td>
							<td><input id="addressSubId"type="text" name="managerAddressSub" value="${manager.managerAddressSub}">
								<span id="numberMsg"></span>
							</td>
						</tr>
						<tr>
							<td>운영자 사진</td>
							<td><input type="text" name="managerImage" value="${manager.managerImage}"></td>
						</tr>
					</table>
			  
			  		<button id="btnId" type="button" class="btn btn-outline-primary">수정</button>
			  		<a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/managerDetail">취소</a>
				</form>
			</div>
		</div>
	</body>
</html>