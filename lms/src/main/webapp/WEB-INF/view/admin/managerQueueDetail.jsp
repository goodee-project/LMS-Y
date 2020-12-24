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
						<td>${managerQueueDetail.accountId}</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>${managerQueueDetail.managerEmail}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${managerQueueDetail.managerName}</td>
					</tr>
					<tr>
						<td>핸드폰 번호</td>
						<td>${managerQueueDetail.managerPhone}</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>${managerQueueDetail.managerGender}</td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td>${managerQueueDetail.managerBirth}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${managerQueueDetail.managerAddressMain}</td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td>${managerQueueDetail.managerAddressSub}</td>
					</tr>
				</table>
			</div>
			
			<div>
				<a href="${pageContext.request.contextPath}/admin/approveManagerMembership?accountId=${managerQueueDetail.accountId}">
					[승인]
				</a>
				<a href="${pageContext.request.contextPath}/admin/disapproveManagerMembership?accountId=${managerQueueDetail.accountId}">
					[거부]
				</a>
			</div>
		</div>
	</body>
</html>