<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>강사 상세보기</h1>
		<table border="1">
			<tr>
				<td>강사 아이디</td>
				<td>${teacher.accountId}</td>
			</tr>
			<tr>
				<td>강사 이름</td>
				<td>${teacher.teacherName}</td>
			</tr>
			<tr>
				<td>강사 전화번호</td>
				<td>${teacher.teacherPhone}</td>
			</tr>
			<tr>
				<td>강사 성별</td>
				<td>${teacher.teacherGender}</td>
			</tr>
			<tr>
				<td>강사 생년월일</td>
				<td>${teacher.teacherName}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${teacher.teacherAddressMain} ${teacher.teacherAddressSub}</td>
			</tr>
			<tr>
				<td>프로필 사진</td>
				<td>${teacher.teacherImage}</td>
			</tr>
			<tr>
				<td>강사 한줄소개</td>
				<td>${teacher.teacherInfo}</td>
			</tr>
			<tr>
				<td><a href="/auth/teacher/modifyTeacher?accountId=${accountId}">수정</a></td>
			</tr>
		</table>
</body>
</html>