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
				let approve = confirm('정말 회원가입을 승인하시겠습니까?');
				
				if(approve) {
					location.replace('${pageContext.request.contextPath}/manager/approveStudent?accountId=${studentQueueDetail.accountId}');
					alert('회원가입이 승인되었습니다.');
				} else {
					alert('취소하였습니다.');
					return;
				}
    		});
    		
    		$('#disapproveBtn').click(function() {
				let disapprove = confirm('정말 회원가입을 거부하시겠습니까?');
				
				if(disapprove) {
					location.replace('${pageContext.request.contextPath}/manager/disapproveStudent?accountId=${studentQueueDetail.accountId}');
					alert('회원가입이 거부되었습니다.');
				} else {
					alert('취소하였습니다.');
					return;
				}
    		});
    	});
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<div class="jumbotron">
			<div class="container">
				<h1>학생 정보</h1>
			</div>
		</div>
		<div class="container">
			<div>
				<table class="table">
					<tr>
						<td>ID</td>
						<td>${studentQueueDetail.accountId}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${studentQueueDetail.studentName}</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>${studentQueueDetail.studentPhone}</td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td>${studentQueueDetail.studentEmail}</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>${studentQueueDetail.studentGender}</td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td>${studentQueueDetail.studentBirth}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${studentQueueDetail.studentAddressMain} ${studentQueueDetail.studentAddressSub}</td>
					</tr>
				</table>
				<div>
					<button class="btn btn-success" type="button" id="approveBtn">승인</button>
					<button class="btn btn-danger" type="button" id="disapproveBtn">승인 거절</button>
				</div>
			</div>
		</div>
	</body>
</html>