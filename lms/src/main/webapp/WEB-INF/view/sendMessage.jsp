<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>보낸 쪽지함</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<div class="jumbotron">
				<h1>보낸 쪽지함</h1>
			</div>
			<div>
				<div class="float-left">
					<form action="${pageContext.request.contextPath}/messageForm" method="post">
						<input type="hidden" name="accountId" value="${accountId}">
						<button class="btn btn-outline-primary">쪽지 보내기</button>
					</form>
				</div>
				<div class="float-right mb-5">
					<a href="${pageContext.request.contextPath}/sendMessage" class="btn btn-outline-success">보낸 쪽지함</a>
					<a href="${pageContext.request.contextPath}/receiveMessage" class="btn btn-outline-success">받은 쪽지함</a>
				</div>	
				<div class="input-group mb-3 justify-content-end">
					<div class="input-group-prepend">
						<select class="form-control" id="sel">
							<option value="id">아이디</option>
							<option value="name">이름</option>
							<option value="content">내용</option>
						</select>
					</div>
					<input type="text" class="form-control col-sm-2" placeholder="입력하세요">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit">검색</button>
					</div>
				</div>
			</div><hr>
			<!-- 메세지 목록 출력 -->
			<table class="table table-hover">
				<tr>
					<th>수신자 ID</th>
					<th>보낸 날짜</th>
					<th>확인여부</th>
					<th>상세보기</th>
					<th>삭제하기</th>
				</tr>
				<c:forEach var="list" items="${list}">
					<tr>
						<td>${list.toId}</td>
						<td>${list.messageDateTime}</td>
						<td>${list.isConfirm}</td>
						<td>
							<a href="${pageContext.request.contextPath}/messageDetail?messageNo=${list.messageNo}" class="btn btn-outline-info">상세보기</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/removeMessage?messageNo=${list.messageNo}" class="btn btn-outline-danger">삭제하기</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>