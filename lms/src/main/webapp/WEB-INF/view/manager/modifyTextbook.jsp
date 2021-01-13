<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>교재 정보 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
            	// 유효성 검사용 정규 표현식
				// 가격 정규 표현식
				let priceCk = /^[0-9]+$/;
				// 출판일 정규 표현식
				let publishDateCk = /^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
				
				// title 생성
				let title = null;
				// price 생성
				let price = null;
				// writer 생성
				let writer = null;
				// publisher 생성
				let publisher = null;
				// publishDate 생성
				let publishDate = null;
				// info 생성
				let info = null;
				
				// publishDateNum 생성
				let publishDateNum = null;
				
				// 교재명 유효성 검사
				$('#textbookTitle').blur(function() {
					// title에 입력한 교재명 저장
					title = $('#textbookTitle').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(title == '') {
						$('#textbookTitleMsg').text('교재명을 입력하세요');
						$('#textbookTitle').focus();
						return;
					} else {
						$('#textbookTitleMsg').text('');
					}
				});
				
				// 가격 유효성 검사
				$('#textbookPrice').blur(function() {
					// price에 입력한 가격 저장
					price = $('#textbookPrice').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(price == '') {
						$('#textbookPriceMsg').text('가격을 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else if(!priceCk.test(price)) {
						$('#textbookPriceMsg').text('숫자만 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else {
						$('#textbookPriceMsg').text('');
					}
				});
            	
				// 저자 유효성 검사
				$('#textbookWriter').blur(function() {
					// writer에 입력한 저자 저장
					writer = $('#textbookWriter').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(writer == '') {
						$('#textbookWriterMsg').text('저자를 입력하세요');
						$('#textbookWriter').focus();
						return;
					} else {
						$('#textbookWriterMsg').text('');
					}
				});

				// 출판사 유효성 검사
				$('#textbookPublisher').blur(function() {
					// publisher에 입력한 출판사 저장
					publisher = $('#textbookPublisher').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(publisher == '') {
						$('#textbookPublisherMsg').text('출판사를 입력하세요');
						$('#textbookPublisher').focus();
						return;
					} else {
						$('#textbookPublisherMsg').text('');
					}
				});
				
				// 출판일 유효성 검사
				$('#textbookPublishDate').blur(function() {
					// publishDate에 입력한 출판일 저장
					publishDate = $('#textbookPublishDate').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// publishDate에서 숫자만 분리
					publishDateNum = publishDate.replace(/[^0-9]/g, "").split("");
					
					if(publishDate == '') {
						$('#textbookPublishDateMsg').text('출판일을 입력하세요');
						$('#textbookPublishDate').focus();
						return;
					} else if(publishDate.length != 10 || publishDateNum.length != 8) {
						$('#textbookPublishDateMsg').text("출판일은 숫자 8자리 입니다('-' 포함 10자리)");
						$('#textbookPublishDate').focus();
						return;
					} else if(!publishDateCk.test(publishDate)) {
						$('#textbookPublishDateMsg').text('올바른 형식으로 입력하세요');
						$('#textbookPublishDate').focus();
						return;
					} else {
						$('#textbookPublishDateMsg').text(''); 
					}
				});

				// 정보 유효성 검사
				$('#textbookInfo').blur(function() {
					// info에 입력한 정보 저장
					info = $('#textbookInfo').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					if(info == '') {
						$('#textbookInfoMsg').text('정보를 입력하세요');
						$('#textbookInfo').focus();
						return;
					} else {
						$('#textbookInfoMsg').text('');
					}
				});
				
				// 등록버튼 클릭 시 최종 유효성 검사 및 등록
				$('#submitBtn').click(function() {
					// title에 입력한 교재명 저장
					title = $('#textbookTitle').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// price에 입력한 가격 저장
					price = $('#textbookPrice').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// writer에 입력한 저자 저장
					writer = $('#textbookWriter').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// publisher에 입력한 출판사 저장
					publisher = $('#textbookPublisher').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// publishDate에 입력한 출판일 저장
					publishDate = $('#textbookPublishDate').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					// info에 입력한 정보 저장
					info = $('#textbookInfo').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					// publishDateNum에 publishDate 숫자만 저장
					publishDateNum = publishDate.replace(/[^0-9]/g, "").split("");
					
					// 교재명 유효성 검사
					if(title == '') {
						$('#textbookTitleMsg').text('교재명을 입력하세요');
						$('#textbookTitle').focus();
						return;
					} else {
						$('#textbookTitleMsg').text('');
					}				
					
					// 가격 유효성 검사
					if(price == '') {
						$('#textbookPriceMsg').text('가격을 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else if(!priceCk.test(price)) {
						$('#textbookPriceMsg').text('숫자만 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else {
						$('#textbookPriceMsg').text('');
					}
						            	
					// 저자 유효성 검사
					if(writer == '') {
						$('#textbookWriterMsg').text('저자를 입력하세요');
						$('#textbookWriter').focus();
						return;
					} else {
						$('#textbookWriterMsg').text('');
					}
					
					// 출판사 유효성 검사
					if(publisher == '') {
						$('#textbookPublisherMsg').text('출판사를 입력하세요');
						$('#textbookPublisher').focus();
						return;
					} else {
						$('#textbookPublisherMsg').text('');
					}
					
					// 출판일 유효성 검사
					if(publishDate == '') {
						$('#textbookPublishDateMsg').text('출판일을 입력하세요');
						$('#textbookPublishDate').focus();
						return;
					} else if(publishDate.length != 10 || publishDateNum.length != 8) {
						$('#textbookPublishDateMsg').text("출판일은 숫자 8자리 입니다('-' 포함 10자리)");
						$('#textbookPublishDate').focus();
						return;
					} else if(!publishDateCk.test(publishDate)) {
						$('#textbookPublishDateMsg').text('올바른 형식으로 입력하세요');
						$('#textbookPublishDate').focus();
						return;
					} else {
						$('#textbookPublishDateMsg').text(''); 
					}
					
					// 정보 유효성 검사
					if(info = '') {
						$('#textbookInfoMsg').text('정보를 입력하세요');
						$('#textbookInfo').focus();
						return;
					} else {
						$('#textbookInfoMsg').text('');
					}
					
					$('#textbookForm').submit();
					alert('교재 정보가 수정되었습니다');
				});
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>교재 정보 수정</h1>
			</div>
		</div>
		
		
		<div class="container">
			<!-- 교재 정보 입력 -->
			<div>
				<form method="post" id="textbookForm" action="${pageContext.request.contextPath}/manager/modifyTextbook?textbookISBN=${modifyTextbook.textbookISBN}">
					<table class="table">
						<tr>
							<td>ISBN</td>
							<td>
								${modifyTextbook.textbookISBN}
							</td>
						</tr>
						<tr>
							<td>교재명</td>
							<td>
								<input type="text" id="textbookTitle" name="textbookTitle" value="${modifyTextbook.textbookTitle}">
								<div id="textbookTitleMsg"></div>
							</td>
						</tr>
						<tr>
							<td>가격</td>
							<td>
								<input type="text" id="textbookPrice" name="textbookPrice" value="${modifyTextbook.textbookPrice}">원
								<div id="textbookPriceMsg"></div>
							</td>
						</tr>
						<tr>
							<td>저자</td>
							<td>
								<input type="text" id="textbookWriter" name="textbookWriter" value="${modifyTextbook.textbookWriter}">
								<div id="textbookWriterMsg"></div>
							</td>
						</tr>
						<tr>
							<td>출판사</td>
							<td>
								<input type="text" id="textbookPublisher" name="textbookPublisher" value="${modifyTextbook.textbookPublisher}">
								<div id="textbookPublisherMsg"></div>
							</td>
						</tr>
						<tr>
							<td>출판일</td>
							<td>
								<input type="text" id="textbookPublishDate" name="textbookPublishDate" value="${modifyTextbook.textbookPublishDate}">
								<div id="textbookPublishDateMsg"></div>
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" id="textbookInfo" name="textbookInfo" value="${modifyTextbook.textbookInfo}">
								<div id="textbookInfoMsg"></div>
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