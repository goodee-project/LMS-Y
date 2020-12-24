<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 정보 수정</title>
		
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
		<div class="jumbotron">
			<h1>운영자 정보수정</h1>
		</div>	
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/managerModify?accountId=${accountId}">
					<table class=table>
						<tr>
							<td>운영자 아이디</td>
							<td><input type="text" name="accountId" value="${accountId}" readonly="readonly"></td>
						</tr>
						<tr>
							<td>운영자 이메일</td>
							<td><input type="text" name="managerEmail" value="${manager.managerEmail}"></td>
						</tr>
						<tr>
							<td>운영자 전화번호</td>
						   <td><input type="text" name="managerPhone" value="${manager.managerPhone}"></td>
						</tr>
						<tr>
							<td>운영자 이름</td>
							<td><input type="text" name="managerName" value="${manager.managerName}"></td>
						</tr>
						<tr>
							<td>운영자 성별</td>
							<td><input type="text" name="managerGender" value="${manager.managerName}"></td>
						</tr>
						<tr>
							<td>운영자 생년월일</td>
							<td><input type="text" name="managerBirth" value="${manager.managerBirth}"></td>
						</tr>
						<tr>
							<td>운영자 직책</td>
							<td><input type="text" name="managerPosition" value="${manager.managerPosition}"></td>
						</tr>
						<tr>
							<td>운영자 주소</td>
							<td><input type="text" name="managerAddressMain" value="${manager.managerAddressMain}"></td>
						</tr>
						<tr>
							<td>운영자 상세주소</td>
							<td><input type="text" name="managerAddressSub" value="${manager.managerAddressSub}"></td>
						</tr>
						<tr>
							<td>운영자 사진</td>
							<td><input type="text" name="managerImage" value="${manager.managerImage}"></td>
						</tr>
					</table>
			  
			  		<button type="submit">입력</button>
				</form>
			</div>
		</div>
	</body>
</html>