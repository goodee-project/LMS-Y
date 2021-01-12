<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쪽지 상세보기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                // code here...
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
				
		<div class="jumbotron">
			<div class="container">
				<h1>쪽지 상세보기</h1>
			</div>
		</div>
			
		<div class="container">	
			<table class="table table-bordered">
				<tr>
					<td>수신자 ID</td>
					<td>${message.toId}</td>
				</tr>
				<tr>
					<td>발신자 ID</td>
					<td>${message.fromId}</td>
				</tr>
				<tr>
					<td>발신자 이름</td>
					<td>${message.fromName}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea class="form-control" rows="8" cols="40" readonly="readonly">${message.messageContent}</textarea>
					</td>
				</tr>
				<tr>
					<td>보낸 날짜</td>
					<td>${message.messageDateTime}</td>
				</tr>
			</table>
			<div align="center">
				<button onclick="history.back()" class="btn btn-outline-primary mb-5">뒤로가기</button>
			</div>
		</div>
	</body>
</html>