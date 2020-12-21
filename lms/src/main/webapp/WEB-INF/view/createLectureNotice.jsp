<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 추가</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
			<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
			<div class=container>
				<div class="jumbotron">
					<h1>공지사항 추가</h1>
				</div>
			</div>
			<div class=container>
				<form method="post" action="${pageContext.request.contextPath}/teacher/createLectureNotice">
					<input type="hidden" name="lectureNo" value="${lectureNo}">
					<table class="table">
						<tr>
							<td>제목</td>
							<td><input type="text" name="lectureNoticeTitle"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea cols="70" rows="10" name="lectureNoticeContent"></textarea>
						</tr>
					</table>
					<button type="submit">입력</button>
				</form>
			</div>
	</body>
</html>