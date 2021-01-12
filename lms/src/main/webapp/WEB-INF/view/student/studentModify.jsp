<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 정보수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                // code here...
                
                //이름 50글자 제한
                $('').focus(); //name 포커스
				$('').blur(function(){
					//공백 사용 불가능
					if($('').val().length>50){
						$('').focus();
						$('').text('이름을 50글자까지 적을수만 있습니다);
						return;
						}else if($.isNumeric($('').val())){
							$('').text('숫자는 입력이 불가능 합니다');
							$('').focus();
							}else{
								$('').text('');
							}
					});
				//전화 번호 검사
				$('').focus(); //name 포커스
				$('').blur(function(){
					if($('').val.length=11){
						$('').text('전화번호 11자리를 적으세요');
						$('')focus();
						return;
						}else if(!$.isNumeric($('').val())){
							$('').text('숫자만 입력이 가능 합니다');
							$('').focus();
							}else{
								$('').text('');
								}
					});
				//성별 검사
				$('').focus();
				$('').click(function(){
					if($('').length == 0) {
						$('').text('성별을 선택해주세요');
					}else{
						$('').text('');
						}
				});
				//주소 검사
				$('').blur(function(){
					//공백 사용 불가능
					if($('').val().length==''){
						$('').text('주소를 입력해 주세요);
						$('').focus();
						return;
					}else{
						$('').text('');
						}
				});
				//주소 검사
				$('').blur(function(){
					//공백 사용 불가능
					if($('').val().length==''){
						$('').text('상세주소를 입력해 주세요);
						$('').focus();
						return;
					}else{
						$('').text('');
						}
				});
				
		});
        </script>
	</head>
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<div class="jumbotron">
				<h1>학생 정보 수정</h1>
			</div>
		</div>
		<div class=container>
			<form id="" method="post" action="${pageContext.request.contextPath}/student/studentModify">
				<table class="table">
					<tr>
						<td>학생 아이디</td>
						<td><input type="text" name="accountId" id="accountId" value="${map.student.accountId}"></td>
						
					</tr>
					
					<tr>
						<td>학생 이름</td>
						<td><input type="text" name="studentName" id="studentName" value="${map.student.studentName}" placeholder="이름 입력"></td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td><input type="text" name="studentPhone" id="studentPhone" value="${map.student.studentPhone}" placeholder="전화번호 입력"></td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td><input type="radio" name="studentGender" id="studentGender" value="남" checked="checked">남
							<input type="radio" name="studentGender" id="studentGender" value="여">여
						</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td><input type="date" name="studentBirth" id="studentBirth" value="${map.student.studentBirth}"></td>	
					</tr>
					
					<tr> 
						<td>주소</td>
							<td><input type="text" name="studentAddressMain" id="studentAddressMain" value="${map.student.studentAddressMain}" placeholder="주소 입력"></td>
						<td>상세주소</td>
							<td><input type="text" name="studentAddressSub" id="studentAddressSub" value="${map.student.studentAddressSub}" placeholder="상세주소 입력"></td>
					</tr>
										
					<tr>
						<td>프로필 사진</td>
						</td><img src="${map.imageURI}" id="preview" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""
						style="width: 170px; height: 200px;" />
						<c:if test="${not empty myImage.imageFileUUID}">
							<a href="${pageContext.request.contextPath}/student/removeStudentFile?accountId=${accountId}">삭제</a>
						</c:if>
						<td><input type="text" name="studentImage" id="studentImage" value="${map.student.studentImage}">
					</tr>
					
					<tr>
						<td><button type="submit">수정</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>