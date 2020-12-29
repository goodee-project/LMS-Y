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
            $(document).ready(function() {
				$('#approveBtn').click(function() {
					len cf = confirm('승인하시겠습니까?');
					if(cf) {
						$('#approveBtn').function()
					} else {
						
					}
				});
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<div class="container">
			<h1>학생 정보</h1>
			<div>
				<table class="table">
					<tr>
						<td>accountId</td>
						<td>${studentQueueDetail.accountId}</td>
					</tr>
					<tr>
						<td>studentEmail</td>
						<td>${studentQueueDetail.studentEmail}</td>
					</tr>
					<tr>
						<td>studentName</td>
						<td>${studentQueueDetail.studentName}</td>
					</tr>
					<tr>
						<td>studentPhone</td>
						<td>${studentQueueDetail.studentPhone}</td>
					</tr>
					<tr>
						<td>studentGender</td>
						<td>${studentQueueDetail.studentGender}</td>
					</tr>
					<tr>
						<td>studentBirth</td>
						<td>${studentQueueDetail.studentBirth}</td>
					</tr>
					<tr>
						<td>studentAddress</td>
						<td>${studentQueueDetail.studentAddressMain} ${studentQueueDetail.studentAddressSub}</td>
					</tr>
				</table>
				<div>
					<a id="approveBtn" href="${pageContext.request.contextPath}/manager/approveStudent?accountId=${studentQueueDetail.accountId}">승인</a>
					<a id="disapproveBtn" href="${pageContext.request.contextPath}/manager/disapproveStudent?accountId=${studentQueueDetail.accountId}">승인 거절</a>
				</div>
			</div>
		</div>
	</body>
</html>