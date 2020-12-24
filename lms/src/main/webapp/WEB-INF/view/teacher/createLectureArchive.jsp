<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자료실 추가페이지</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>자료실 추가</h1>
			</div>
		</div>
		<div class=container>
			<form method="post" action="${pageContext.request.contextPath}/teacher/createLectureArchive" enctype="multipart/form-data">
				<div>
						<!-- 강좌번호 -->
						<div>강좌번호</div>
						<input type="text" name="lectureNo" value="${lectureNotice.lectureNo}" readonly="readonly">
						<!-- 강사 아이디 -->
						<div>강사 아이디</div>
						<input type="text" name="accountId" value="${teacher.accountId}">				
						<!-- 강사 이름 -->
						<div>강사이름</div>
						<input type="text" name="lectureArchiveWriter" value="${teacher.teacherName}">
				</div>
			<table class="table">
						<tr>
							<td>제목</td>
							<td><input type="text" name="lectureArchiveTitle"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea cols="70" rows="10" name="lectureArchiveContent"></textarea></td>
						</tr>
						<tr>
							<td>파일첨부</td>
							<td></td>
						</tr>
				</table>
				<button type="submit">입력</button>
			</form>
		</div>
	</body>
</html>