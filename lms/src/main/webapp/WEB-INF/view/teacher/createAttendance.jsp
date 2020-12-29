<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석 입력</title>
</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<div class=container>
		<div class="jumbotron">
			<h1>출석 입력</h1>
		</div>
	</div>
	<div class=container>
		<form method="post" action="${pageContext.request.contextPath}/teacher/modifyAttendanceStateOne">
		<table class="table">
			<tr>
			<td>
				<div>
				<input readonly="readonly" type="text" value="${accountId}" name="accountId">
				<input readonly="readonly" type="text" value="${param.lectureNo}" name="lectureNo">
				</div>
			</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>