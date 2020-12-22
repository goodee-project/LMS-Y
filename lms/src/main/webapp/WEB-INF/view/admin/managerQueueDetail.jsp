<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 승인대기 중인 운영자 상세정보</title>
		
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
			<h1>회원가입 승인대기 중인 운영자 상세정보</h1>
			
			<div>
				<table border="1">
					<tr>
						<td>아이디</td>						
						<td>${managerQueue.accountId}</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>${managerQueue.managerEmail}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${managerQueue.managerName}</td>
					</tr>
					<tr>
						<td>핸드폰 번호</td>
						<td>${managerQueue.managerPhone}</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>${managerQueue.managerGender}</td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td>${managerQueue.managerBirth}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${managerQueue.managerAddressMain}</td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td>${managerQueue.managerAddressSub}</td>
					</tr>
				</table>
			</div>
			
			<div>
				<a href="${pageContext.request.contextPath}/admin/approveManagerMembership?accountId=${managerQueue.accountId}">
					승인
				</a>
				<a href="${pageContext.request.contextPath}/admin/disapproveManagerMembership?accountId=${managerQueue.accountId}">
					거부
				</a>
			</div>
		</div>
	</body>
</html>