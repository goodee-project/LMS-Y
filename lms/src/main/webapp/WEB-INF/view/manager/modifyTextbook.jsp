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
                // 폼 유효성 검사
                // code here...
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
				<form method="post" action="${pageContext.request.contextPath}/manager/createTextbook?textbookISBN=${modifyTextbook.textbookISBN}">
					<table border="1">
						<tr>
							<td>ISBN</td>
							<td>
								<input type="text" name="textbookISBN" value="${modifyTextbook.textbookISBN}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>교재명</td>
							<td>
								<input type="text" name="textbookTitle" value="${modifyTextbook.textbookTitle}">
							</td>
						</tr>
						<tr>
							<td>가격</td>
							<td>
								<input type="text" name="textbookPrice" value="${modifyTextbook.textbookPrice}">원
							</td>
						</tr>
						<tr>
							<td>저자</td>
							<td>
								<input type="text" name="textbookWriter" value="${modifyTextbook.textbookWriter}">
							</td>
						</tr>
						<tr>
							<td>출판사</td>
							<td>
								<input type="text" name="textbookPublisher" value="${modifyTextbook.textbookPublisher}">
							</td>
						</tr>
						<tr>
							<td>출판일</td>
							<td>
								<input type="date" name="textbookPublishDate" value="${modifyTextbook.textbookPublishDate}">
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="textbookInfo" value="${modifyTextbook.textbookInfo}">
							</td>
						</tr>
					</table>
					
					<button type="button">
						수정
					</button>
				</form>
			</div>
		</div>
	</body>
</html>