<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 정보 등록</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 유효성 검사용 정규 표현식
                let numCk = /[^0-9]$/;
                
				// 과목명 입력칸에 포커싱
				$('#subjectName').focus();

				// 과목명 유효성 검사
				$('#subjectName').blur(function() {
					if($('#subjectName').val() == '') {
						$('#subjectNameMsg').text('과목명을 입력하세요');
						$('#subjectName').focus();
						return;
					} else {
						$('#subjectNameMsg').text('');
					}
				});
				
				// 총 이수일수 유효성 검사
				$('#subjectTotalDay').blur(function() {
					if($('#subjectTotalDay').val() == '') {
						$('#subjectTotalDayMsg').text('총 이수일수를 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else if(numCk.test($('#subjectTotalDay').val())) {
						$('#subjectTotalDayMsg').text('숫자만 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else {
						$('#subjectTotalDayMsg').text('');
					}
				});
            	
				// 정보 유효성 검사
				$('#subjectInfo').blur(function() {
					if($('#subjectInfo').val() == '') {
						$('#subjectInfoMsg').text('정보를 입력하세요');
						$('#subjectInfo').focus();
						return;
					} else {
						$('#subjectInfoMsg').text('');
					}
				});

				// 등록버튼 클릭 시 최종 유효성 검사 및 등록
				$('#submitBtn').click(function() {
					// 과목명 유효성 검사
					if($('#subjectName').val() == '') {
						$('#subjectNameMsg').text('과목명을 입력하세요');
						$('#subjectName').focus();
						return;
					} else {
						$('#subjectNameMsg').text('');
					}
					
					// 총 이수일수 유효성 검사
					if($('#subjectTotalDay').val() == '') {
						$('#subjectTotalDayMsg').text('총 이수일수를 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else if(numCk.test($('#subjectTotalDay').val())) {
						$('#subjectTotalDayMsg').text('숫자만 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else {
						$('#subjectTotalDayMsg').text('');
					}
					
					// 정보 유효성 검사
					if($('#subjectInfo').val() == '') {
						$('#subjectInfoMsg').text('정보를 입력하세요');
						$('#subjectInfo').focus();
						return;
					} else {
						$('#subjectInfoMsg').text('');
					}

					$('#subjectForm').submit();
				})
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>과목 정보 등록</h1>
			
			<!-- 과목 정보 입력 -->
			<div>
				<form method="post" id="subjectForm" action="${pageContext.request.contextPath}/manager/createSubject">
					<table border="1">
						<tr>
							<td>과목명</td>
							<td>
								<input type="text" name="subjectName" id="subjectName">
								<div id="subjectNameMsg"></div>
							</td>
						</tr>
						<tr>
							<td>총 이수일수</td>
							<td>
								<input type="text" name="subjectTotalDay" id="subjectTotalDay">
								<div id="subjectTotalDayMsg"></div>
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="subjectInfo" id="subjectInfo">
								<div id="subjectInfoMsg"></div>
							</td>
						</tr>
					</table>
					
					<!-- 등록버튼 -->
					<button type="button" id="submitBtn">
						등록
					</button>
				</form>
			</div>
		</div>
	</body>
</html>