<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>교재 정보</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>교재 정보</h1>
			</div>
		</div>
		
		<div class="container">
			<!-- 내용 -->
			<div>
				<table class="table">
					<tr>
						<th>ISBN</th>
						<td>${textbookDetail.textbookISBN}</td>
					</tr>
					<tr>
						<th>교재명</th>
						<td>${textbookDetail.textbookTitle}</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${textbookDetail.textbookPrice}원</td>
					</tr>
					<tr>
						<th>저자</th>
						<td>${textbookDetail.textbookWriter}</td>
					</tr>
					<tr>
						<th>출판사</th>
						<td>${textbookDetail.textbookPublisher}</td>
					</tr>
					<tr>
						<th>출판일</th>
						<td>${textbookDetail.textbookPublishDate}</td>
					</tr>
					<tr>
						<th>정보</th>
						<td>${textbookDetail.textbookInfo}</td>
					</tr>
					<c:if test="${accountLevel != 1}">
						<tr>
							<th>등록일자</th>
							<td>${textbookDetail.textbookCreateDate}</td>
						</tr>
						<tr>
							<th>수정일자</th>
							<td>${textbookDetail.textbookUpdateDate}</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<!-- 수정버튼 -->
			<div class="d-flex justify-content-end mb-3">
				<c:if test="${accountLevel == 3}">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyTextbook?textbookISBN=${textbookDetail.textbookISBN}">
						수정
					</a>
				</c:if>
			</div>
		</div>
	</body>
</html>