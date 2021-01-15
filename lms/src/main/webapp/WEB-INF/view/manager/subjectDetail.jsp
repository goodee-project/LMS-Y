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
			<!-- 내용 -->
			<div>
				<table class="table">
					<tr>
						<th>No.</th>
						<td>${subjectDetail.subjectNo}</td>
					</tr>
					<tr>
						<th>과목명</th>
						<td>${subjectDetail.subjectName}</td>
					</tr>
					<tr>
						<th>총 이수일수</th>
						<td>${subjectDetail.subjectTotalDay}</td>
					</tr>
					<tr>
						<th>정보</th>
						<td>${subjectDetail.subjectInfo}</td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td>${subjectDetail.subjectCreateDate}</td>
					</tr>
					<tr>
						<th>수정일자</th>
						<td>${subjectDetail.subjectUpdateDate}</td>
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