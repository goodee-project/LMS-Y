<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
			
			<div class=container>
				<div class="jumbotron">
					<h1>공지사항 수정</h1>
				</div>
			</div>
			<div class=container>
				<form method="post" action="${pageContext.request.contextPath}/teacher/modifyLectureNotice">
						<table class="table">
							<tr>
								<td>공지번호</td>
								<td><input type="text" name="lectureNoticeNo" value="${lectureNotice.lectureNoticeNo}" readonly="readonly"></td>
							</tr>
							<tr>
								<td>제목</td>
								<td><input type="text" name="lectureNoticeTitle" value="${lectureNotice.lectureNoticeTitle}"></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea cols="70" rows="10" name="lectureNoticeContent">${lectureNotice.lectureNoticeContent}</textarea>
							</tr>
						</table>
						<button type="submit">입력</button>
				</form>
			</div>
	</body>
</html>