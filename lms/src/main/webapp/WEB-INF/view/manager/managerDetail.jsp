<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>managerDetail</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			
				<h1>내 정보 (운영자)</h1>
		
		</div>
		<div class=container >
			<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/manager/modifyManager">내 정보 수정</a>
			<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/manager/modifyManagerPasswd">비밀번호</a>
		</div>
		<div class=container>
				<table class="table">
				   
				   <tr>
						<td>운영자 아이디</td>
						<td>${manager.accountId}</td>
					</tr>
				   
					<tr>
						<td>운영자 이메일</td>
						<td>${manager.managerEmail}</td>
					</tr>
					
					<tr>
						<td>운영자 전화번호</td>
						<td>${manager.managerPhone}</td>
					</tr>
					
					<tr>
						<td>운영자 이름</td>
						<td>${manager.managerName}</td>
					</tr>
					
					<tr>
						<td>운영자 성별</td>
						<td>${manager.managerGender}</td>
					</tr>
					
					<tr>
						<td>운영자 생년월일</td>
						<td>${manager.managerBirth}</td>
					</tr>
					<tr>
						<td>운영자 직책</td>
						<td>${manager.managerPosition}</td>
					</tr>
					<tr>
						<td>운영자 주소</td>
						<td>${manager.managerAddressMain}</td>
						<td>${manager.managerAddressSub}</td> 
					</tr>
					
					<tr>
						<td>운영자 사진</td>
						<td>${manager.managerImage}</td>
					</tr>
					
			
				</table>
			</div>
</body>
</html>