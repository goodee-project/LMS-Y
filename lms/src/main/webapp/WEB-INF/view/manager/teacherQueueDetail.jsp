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
					location.replace('${pageContext.request.contextPath}/manager/approveTeacher?accountId=${teacherQueueDetail.accountId}');
					alert('회원가입이 승인되었습니다.');
				} else {
					alert('취소하였습니다.');
					return;
				}
    		});
    		
    		$('#disapproveBtn').click(function() {
				let disapprove = confirm('정말 회원가입을 거부하시겠습니까?');
				
				if(disapprove) {
					location.replace('${pageContext.request.contextPath}/manager/disapproveTeacher?accountId=${teacherQueueDetail.accountId}');
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
				<h1>강사 정보</h1>
			</div>
		</div>
		<div class="container">
			<div>
				<table class="table">
					<tr>
						<th>ID</th>
						<td>${teacherQueueDetail.accountId}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${teacherQueueDetail.teacherName}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${teacherQueueDetail.teacherPhone}</td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td>${teacherQueueDetail.teacherEmail}</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>${teacherQueueDetail.teacherGender}</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>${teacherQueueDetail.teacherBirth}</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>${teacherQueueDetail.teacherAddressMain} ${teacherQueueDetail.teacherAddressSub}</td>
					</tr>
				</table>
				<div class="d-flex justify-content-end">
					<button class="btn btn-outline-success" type="button" id="approveBtn">승인</button>
					<button class="btn btn-outline-danger mx-2" type="button" id="disapproveBtn">거절</button>
				</div>
			</div>
		</div>
	</body>
</html>