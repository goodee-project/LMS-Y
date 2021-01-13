<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 정보</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>과목 정보</h1>
			</div>
		</div>
		
		<div class="container">		
			<!-- 생성일자, 수정일자 -->
			<div>
				<table class="table d-flex justify-content-end">
					<tr>
						<td>등록일자</td>
						<td>${subjectDetail.subjectCreateDate}</td>
					</tr>
					<tr>
						<td>수정일자</td>
						<td>${subjectDetail.subjectUpdateDate}</td>
					</tr>
				</table>
			</div>
			
			<!-- 내용 -->
			<div>
				<table class="table">
					<tr>
						<td>No.</td>
						<td>${subjectDetail.subjectNo}</td>
					</tr>
					<tr>
						<td>과목명</td>
						<td>${subjectDetail.subjectName}</td>
					</tr>
					<tr>
						<td>총 이수일수</td>
						<td>${subjectDetail.subjectTotalDay}</td>
					</tr>
					<tr>
						<td>정보</td>
						<td>${subjectDetail.subjectInfo}</td>
					</tr>
				</table>
			</div>
			
			<!-- 수정버튼 -->
			<div class="d-flex justify-content-end mb-3">
				<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifySubject?subjectNo=${subjectDetail.subjectNo}">
					수정
				</a>
			</div>
		</div>
	</body>
</html>