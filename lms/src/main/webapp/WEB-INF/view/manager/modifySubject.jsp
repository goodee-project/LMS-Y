<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 정보 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
            	// 유효성 검사용 정규 표현식
                let totalDayCk = /^[0-9]+$/;
                
                // name에 기존에 입력되어 있던 과목명 저장
                let name = $('#subjectName').val().replace(/<.+?>|\s+|&nbsp;/g, '');
                // totalDay에 기존에 입력되어 있던 총 이수일수 저장
				let totalDay = $('#subjectTotalDay').val().replace(/<.+?>|\s+|&nbsp;/g, '');
				// info에 기존에 입력되어 있던 정보 저장
				let info = $('#subjectInfo').val().replace(/<.+?>|\s+|&nbsp;/g, '');
				
				// 과목명 유효성 검사
				$('#subjectName').blur(function() {
					name = $('#subjectName').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(name == '') {
						$('#subjectNameMsg').text('과목명을 입력하세요');
						$('#subjectName').focus();
						return;
					} else {
						$('#subjectNameMsg').text('');
					}
				});
				
				// 총 이수일수 유효성 검사
				$('#subjectTotalDay').blur(function() {
					totalDay = $('#subjectTotalDay').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(totalDay == '') {
						$('#subjectTotalDayMsg').text('총 이수일수를 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else if(!totalDayCk.test(totalDay)) {
						$('#subjectTotalDayMsg').text('숫자만 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else {
						$('#subjectTotalDayMsg').text('');
					}
				});
            	
				// 정보 유효성 검사
				$('#subjectInfo').blur(function() {
					info = $('#subjectInfo').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(info == '') {
						$('#subjectInfoMsg').text('정보를 입력하세요');
						$('#subjectInfo').focus();
						return;
					} else {
						$('#subjectInfoMsg').text('');
					}
				});

				// 수정 버튼 클릭 시 최종 유효성 검사 및 등록
				$('#submitBtn').click(function() {
					// 과목명 유효성 검사
					if(name == '') {
						$('#subjectNameMsg').text('과목명을 입력하세요');
						$('#subjectName').focus();
						return;
					} else {
						$('#subjectNameMsg').text('');
					}
					
					// 총 이수일수 유효성 검사
					if(totalDay == '') {
						$('#subjectTotalDayMsg').text('총 이수일수를 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else if(!totalDayCk.test(totalDay)) {
						$('#subjectTotalDayMsg').text('숫자만 입력하세요');
						$('#subjectTotalDay').focus();
						return;
					} else {
						$('#subjectTotalDayMsg').text('');
					}
					
					// 정보 유효성 검사
					if(info == '') {
						$('#subjectInfoMsg').text('정보를 입력하세요');
						$('#subjectInfo').focus();
						return;
					} else {
						$('#subjectInfoMsg').text('');
					}

					$('#subjectForm').submit();
					alert('과목 정보가 수정되었습니다');
				})
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>과목 정보 수정</h1>
			</div>
		</div>
		
		<div class="container">
			<!-- 과목 정보 입력 -->
			<div>
				<form method="post" id="subjectForm" action="${pageContext.request.contextPath}/manager/modifySubject?subjectNo=${modifySubject.subjectNo}">
					<table class="table">
						<tr>
							<td>No.</td>
							<td>
								<input type="text" name="subjectNo" value="${modifySubject.subjectNo}" readonly="readonly">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>과목명</td>
							<td>
								<input type="text" name="subjectName" id="subjectName" value="${modifySubject.subjectName}">
							</td>
							<td>
								<div id="subjectNameMsg"></div>
							</td>
						</tr>
						<tr>
							<td>총 이수일수</td>
							<td>
								<input type="text" name="subjectTotalDay" id="subjectTotalDay" value="${modifySubject.subjectTotalDay}">
							</td>
							<td>
								<div id="subjectTotalDayMsg"></div>
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="subjectInfo" id="subjectInfo" value="${modifySubject.subjectInfo}">
							</td>
							<td>
								<div id="subjectInfoMsg"></div>
							</td>
						</tr>
					</table>
					
					<!-- 수정 버튼 -->
					<div class="d-flex justify-content-end">
						<button type="button" id="submitBtn" class="justify-content-end btn btn-outline-success">
							수정
						</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>