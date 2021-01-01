<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내 정보 상세보기</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>내 정보 상세보기</h1>
			</div>
		</div>
		<div class=container>
			<a href="${pageContext.request.contextPath}/teacher/modifyTeacher">[수정]</a>
				<table class="table">
					<tr>
						<td>강사 아이디</td>
						<td>${map.accountId}</td>
					</tr>
					<tr>
						<td>강사 이메일</td>
						<td>${map.teacherEmail}</td>
					</tr>
					<tr>
						<td>강사 이름</td>
						<td>${map.teacherName}</td>
					</tr>
					<tr>
						<td>강사 전화번호</td>
						<td>${map.teacherPhone}</td>
					</tr>
					<tr>
						<td>강사 성별</td>
						<td>${map.teacherGender}</td>
					</tr>
					<tr>
						<td>강사 생년월일</td>
						<td>${map.teacherBirth}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${map.teacherAddressMain} ${map.teacherAddressSub}</td>
					</tr>
					<tr>
						<td>프로필 사진</td>
						<td><img src="${pageContext.request.contextPath}/upload/${map.teacherImage}" /></td>
					</tr>
					<tr>
						<td>강사 한줄소개</td>
						<td>${map.teacherInfo}</td>
					</tr>
				</table>
			</div>
	</body>
</html>