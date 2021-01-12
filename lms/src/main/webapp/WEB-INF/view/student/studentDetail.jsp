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
			<div class="imagediv"><img src="${map.imageURI}" class="rounded-circle" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""  width="200px" height="200px" /></div>
			<div class="imagediv"><a href="${pageContext.request.contextPath}/student/studentmodify">[프로필 사진 수정]</a></div>
			<a href="${pageContext.request.contextPath}/student/studentModify?accountId=${accountId}">[수정]</a>
				<table class="table">
				
					<tr>
						<td>학생 아이디</td>
						<td>${student.student.accountId}</td>
					</tr>
					
					<tr>
						<td>학생 이메일</td>
						<td>${student.student.studentEmail}</td>
					</tr>
					<tr>
						<td>학생 이름</td>
						<td>${student.student.studentName}</td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td>${student.student.studentPhone}</td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td>${student.student.studentGender}</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td>${student.student.studentBirth}</td>
					</tr>
					
					<tr> 
						<td>주소/상세주소</td>
						<td>${student.student.studentAddressMain} ${student.student.studentAddressSub}</td>
					</tr>
					
			</table>
		</div>
	</body>
</html>