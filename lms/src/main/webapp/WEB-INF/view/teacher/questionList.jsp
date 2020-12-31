<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 목록</title>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>질문게시판 목록</h1>
			
			<div>
				<table class="table table-sm">
					<thead>
						<tr>
							<th>번호</th>
							<th>계정</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="q" items="${list}">
							<tr>
								<td>${q.questionNo}</td>
								<td>${q.accountId}</td>
								<td>${q.questionWriter}</td>
								<td>
									<a href="${pageContext.request.contextPath}/teacher/questionDetail?questionNo=${q.questionNo}">
										${q.questionTitle}
									</a>
								</td>
								<td>${q.questionCreateDate}</td>
								<td>${q.questionCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>