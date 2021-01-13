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
			<!-- 생성일자, 수정일자 -->
			<div>
				<table class="table d-flex justify-content-end">
					<tr>
						<td>등록일자</td>
						<td>${textbookDetail.textbookCreateDate}</td>
					</tr>
					<tr>
						<td>수정일자</td>
						<td>${textbookDetail.textbookUpdateDate}</td>
					</tr>
				</table>
			</div>
			
			<!-- 내용 -->
			<div>
				<table class="table">
					<tr>
						<td>ISBN</td>
						<td>${textbookDetail.textbookISBN}</td>
					</tr>
					<tr>
						<td>교재명</td>
						<td>${textbookDetail.textbookTitle}</td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${textbookDetail.textbookPrice}원</td>
					</tr>
					<tr>
						<td>저자</td>
						<td>${textbookDetail.textbookWriter}</td>
					</tr>
					<tr>
						<td>출판사</td>
						<td>${textbookDetail.textbookPublisher}</td>
					</tr>
					<tr>
						<td>출판일</td>
						<td>${textbookDetail.textbookPublishDate}</td>
					</tr>
					<tr>
						<td>정보</td>
						<td>${textbookDetail.textbookInfo}</td>
					</tr>
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