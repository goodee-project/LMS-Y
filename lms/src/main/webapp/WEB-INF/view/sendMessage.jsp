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
					<a href="${pageContext.request.contextPath}/messageForm" class="btn btn-outline-primary">쪽지 보내기</a>
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
		</div>
	</body>
</html>