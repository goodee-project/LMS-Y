<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>승인 대기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
 
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<div class="container">
			<h1>강사 정보</h1>
			<div>
				<table class="table">
					<tr>
						<td>accountId</td>
						<td>${teacherQueueDetail.accountId}</td>
					</tr>
					<tr>
						<td>teacherEmail</td>
						<td>${teacherQueueDetail.teacherEmail}</td>
					</tr>
					<tr>
						<td>teacherName</td>
						<td>${teacherQueueDetail.teacherName}</td>
					</tr>
					<tr>
						<td>teacherPhone</td>
						<td>${teacherQueueDetail.teacherPhone}</td>
					</tr>
					<tr>
						<td>teacherGender</td>
						<td>${teacherQueueDetail.teacherGender}</td>
					</tr>
					<tr>
						<td>teacherBirth</td>
						<td>${teacherQueueDetail.teacherBirth}</td>
					</tr>
					<tr>
						<td>teacherAddress</td>
						<td>${teacherQueueDetail.teacherAddressMain} ${teacherQueueDetail.teacherAddressSub}</td>
					</tr>
				</table>
				<div>
					<a id="approveBtn" href="${pageContext.request.contextPath}/manager/approveTeacher?accountId=${teacherQueueDetail.accountId}">승인</a>
					<a id="disapproveBtn" href="${pageContext.request.contextPath}/manager/disapproveTeacher?accountId=${teacherQueueDetail.accountId}">승인 거절</a>
				</div>
			</div>
		</div>
	</body>
</html>