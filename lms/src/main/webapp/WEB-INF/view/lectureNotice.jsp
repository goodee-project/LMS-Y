<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
			<!-- 부트스트랩(CSS) 인클루드 -->
			<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
			<div class=container>
				<div class="jumbotron">
					<h1>강좌 공지사항</h1>
				</div>
			</div>
			<div class="container">
				<table class="table">
					<thead>
						<tr>
							<td>강좌</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lnl" items="${lectureNoticeList}">
							<tr>
								<td>${lnl.lectureNo}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</body>
</html>