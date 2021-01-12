<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<!-- 강좌 메뉴 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>

	
	<div class="jumbotron">
		<div class=container>
				<h1>출석 입력</h1>
		</div>
	</div>
		
	<div class=container>
		<form method="post" action="${pageContext.request.contextPath}/teacher/createAttendance">
		<div>
			<input readonly="readonly" type="hidden" value="${param.accountId}" name="accountId">
			<input readonly="readonly" type="hidden" value="${param.lectureNo}" name="lectureNo">
		</div>
		<table class="table">
				<tr>
					<td>학생이름</td>
					<td><input class="form-control bg-light" readonly="readonly" type="text" value="${param.studentName}"></td>
				</tr>
				<tr>
					<td>학생성별</td>
					<td><input class="form-control bg-light" readonly="readonly" type="text" value="${param.studentGender}"></td>
				</tr>
				<tr>
					<td>출석날짜</td>
					<td><input class="form-control bg-light" name="attendanceDay" type="text" readonly="readonly" value="${currentYear}-${currentMonth}-${currentDay}"></td>
				</tr>
				<tr>
					<td>출석여부</td>
					<td>
						<select class="form-control" name="attendanceState">
							<option value="">선택</option>
							<option value="출석">출석</option>
							<option value="결석">결석</option>
							<option value="외출">외출</option>
							<option value="조퇴">조퇴</option>
							<option value="지각">지각</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>비고</td>
					<td><textarea rows="5" class="form-control" name="attendanceRemark"></textarea></td>
				</tr>
		</table>
			<div style="text-align:right;">
				<button type="submit" class="btn btn-primary">입력</button>
			</div>
		</form>
	</div>
</body>
</html>