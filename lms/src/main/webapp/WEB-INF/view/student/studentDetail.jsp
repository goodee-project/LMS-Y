<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentDetail</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>내 정보 상세보기</h1>
			</div>
		</div>
		<div class=container>
			<a href="${pageContext.request.contextPath}/student/studentModify?accountId=${accountId}">[수정]</a>
				<table class="table">
				
					<tr>
						<td>학생 아이디</td>
						<td>${student.accountId}</td>
					</tr>
					
					<tr>
						<td>학생 이름</td>
						<td>${student.studentName}</td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td>${student.studentPhone}</td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td>${student.studentGender}</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td>${student.studentBirth}</td>
					</tr>
					
					<tr> 
						<td>주소/상세주소</td>
						<td>${student.studentAddressMain} ${student.studentAddressSub}</td>
					</tr>
					
					<tr>
						<td>프로필 사진</td>
						<td>${student.studentImage}</td>
					</tr>
			</table>
		</div>
	</body>
</html>