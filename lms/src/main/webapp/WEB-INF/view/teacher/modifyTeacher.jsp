<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사정보 수정</title>

<!-- jQuery 스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$$(document).ready(function() {
		// 폼 유효성 검사
		// code here...
	});
</script>
</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<div class=container>
		<div class="jumbotron">
			<h1>내 정보수정</h1>
		</div>
	</div>
	<div class=container>
		<form id="" method="post" action="${pageContext.request.contextPath}/teacher/modifyTeacher">
			<table class="table">
				<tr>
					<td>강사 아이디</td>
					<td><input type="text" name="accountId" id="accountId"
						value="${accountId}"></td>
				</tr>
				<tr>
					<td>강사 이름</td>
					<td><input type="text" name="teacherName" id="teacherName"
						value="${teacher.teacherName}"></td>
				</tr>
				<tr>
					<td>강사 전화번호</td>
					<td><input type="text" name="teacherPhone" id="teacherPhone"
						value="${teacher.teacherPhone}"></td>
				</tr>
				<tr>
					<td>강사 성별</td>
					<td><input type="radio" name="teacherGender"
						id="teacherGender" value="남">남 <input type="radio"
						name="teacherGender" id="teacherGender" value="여">여</td>
				</tr>
				<tr>
					<td>강사 생년월일</td>
					<td><input type="date" name="teacherBirth" id="teacherBirth"
						value="${teacher.teacherBirth}"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>메인주소: <input type="text" name="teacherAddressMain"
						id="teacherAddressMain" value="${teacher.teacherAddressMain}">
						서브주소: <input type="text" name="teacherAddressSub"
						id="teacherAddressSub" value="${teacher.teacherAddressSub}">
						<input type="button" name="주소찾기" value="주소찾기"
						onClick="window.open('http://localhost/lms/teacher/addressOne?currentPage=1','width=70px','height=70px')">
					</td>

				</tr>
				<tr>
					<td>프로필 사진</td>
					<td><input type="text" name="teacherImage" id="teacherImage"
						value="${teacher.teacherImage}"></td>
				</tr>
				<tr>
					<td>강사 한줄소개</td>
					<td><input type="text" name="teacherInfo" id="teacherInfo"
						value="${teacher.teacherInfo}"></td>
				</tr>
				<tr>
					<td>
						<button type="submit">수정</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>