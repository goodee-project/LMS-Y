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
                let numCk = /[^0-9]$/;
				
				// 교재명 입력칸에 포커싱
				$('#textbookTitle').focus();

				// 교재명 유효성 검사
				$('#textbookTitle').blur(function() {
					if($('#textbookTitle').val() == '') {
						$('#textbookTitleMsg').text('교재명을 입력하세요');
						$('#textbookTitle').focus();
						return;
					} else {
						$('#textbookTitleMsg').text('');
					}
				});
				
				// 가격 유효성 검사
				$('#textbookPrice').blur(function() {
					if($('#textbookPrice').val() == '') {
						$('#textbookPriceMsg').text('가격을 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else if(numCk.test($('#textbookPrice').val())) {
						$('#textbookPriceMsg').text('숫자만 입력하세요');
						$('#textbookPrice').focus();
						return;
					} else {
						$('#textbookPriceMsg').text('');
					}
				});
            	
				// 저자 유효성 검사
				$('#textbookWriter').blur(function() {
					if($('#textbookWriter').val() == '') {
						$('#textbookWriterMsg').text('저자를 입력하세요');
						$('#textbookWriter').focus();
						return;
					} else {
						$('#textbookWriterMsg').text('');
					}
				});

				// 출판사 유효성 검사
				$('#textbookPublisher').blur(function() {
					if($('#textbookPublisher').val() == '') {
						$('#textbookPublisherMsg').text('출판사를 입력하세요');
						$('#textbookPublisher').focus();
						return;
					} else {
						$('#textbookPublisherMsg').text('');
					}
				});

				// 정보 유효성 검사
				$('#textbookInfo').blur(function() {
					if($('#textbookInfo').val() == '') {
						$('#textbookInfoMsg').text('정보를 입력하세요');
						$('#textbookInfo').focus();
						return;
					} else {
						$('#textbookInfoMsg').text('');
					}
				});

				// 등록버튼 클릭 시 최종 유효성 검사 및 등록
				$('#submitBtn').click(function() {
					// 교재명 유효성 검사
					$('#textbookTitle').blur(function() {
						if($('#textbookTitle').val() == '') {
							$('#textbookTitleMsg').text('교재명을 입력하세요');
							$('#textbookTitle').focus();
							return;
						} else {
							$('#textbookTitleMsg').text('');
						}
					});
					
					// 가격 유효성 검사
					$('#textbookPrice').blur(function() {
						if($('#textbookPrice').val() == '') {
							$('#textbookPriceMsg').text('가격을 입력하세요');
							$('#textbookPrice').focus();
							return;
						} else if(numCk.test($('#textbookPrice').val())) {
							$('#textbookPriceMsg').text('숫자만 입력하세요');
							$('#textbookPrice').focus();
							return;
						} else {
							$('#textbookPriceMsg').text('');
						}
					});
	            	
					// 저자 유효성 검사
					$('#textbookWriter').blur(function() {
						if($('#textbookWriter').val() == '') {
							$('#textbookWriterMsg').text('저자를 입력하세요');
							$('#textbookWriter').focus();
							return;
						} else {
							$('#textbookWriterMsg').text('');
						}
					});

					// 출판사 유효성 검사
					$('#textbookPublisher').blur(function() {
						if($('#textbookPublisher').val() == '') {
							$('#textbookPublisherMsg').text('출판사를 입력하세요');
							$('#textbookPublisher').focus();
							return;
						} else {
							$('#textbookPublisherMsg').text('');
						}
					});

					// 정보 유효성 검사
					$('#textbookInfo').blur(function() {
						if($('#textbookInfo').val() == '') {
							$('#textbookInfoMsg').text('정보를 입력하세요');
							$('#textbookInfo').focus();
							return;
						} else {
							$('#textbookInfoMsg').text('');
						}
					});

					$('#textbookForm').submit();
				});
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>교재 정보 수정</h1>
			
			<!-- 교재 정보 입력 -->
			<div>
				<form method="post" id="textbookForm" action="${pageContext.request.contextPath}/manager/modifyTextbook?textbookISBN=${modifyTextbook.textbookISBN}">
					<table border="1">
						<tr>
							<td>ISBN</td>
							<td>
								<input type="text" id="textbookISBN" value="${modifyTextbook.textbookISBN}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>교재명</td>
							<td>
								<input type="text" name="textbookTitle" id="textbookTitle" value="${modifyTextbook.textbookTitle}">
								<div id="textbookTitleMsg"></div>
							</td>
						</tr>
						<tr>
							<td>가격</td>
							<td>
								<input type="text" name="textbookPrice" id="textbookPrice" value="${modifyTextbook.textbookPrice}">원
								<div id="textbookPriceMsg"></div>
							</td>
						</tr>
						<tr>
							<td>저자</td>
							<td>
								<input type="text" name="textbookWriter" id="textbookWriter" value="${modifyTextbook.textbookWriter}">
								<div id="textbookWriterMsg"></div>
							</td>
						</tr>
						<tr>
							<td>출판사</td>
							<td>
								<input type="text" name="textbookPublisher" id="textbookPublisher" value="${modifyTextbook.textbookPublisher}">
								<div id="textbookPublisherMsg"></div>
							</td>
						</tr>
						<tr>
							<td>출판일</td>
							<td>
								<input type="date" name="textbookPublishDate" id="textbookPublishDate" value="${modifyTextbook.textbookPublishDate}">
								<div id="textbookPublishDateMsg"></div>
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="textbookInfo" id="textbookInfo" value="${modifyTextbook.textbookInfo}">
								<div id="textbookInfoMsg"></div>
							</td>
						</tr>
					</table>
					
					<button type="button" id="submitBtn">
						수정
					</button>
				</form>
			</div>
		</div>
	</body>
</html>