<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내질문 수정</title>
		
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
		
		<div class="container">
			<h1>질문 수정</h1>
		</div>
			<div class="container">
				<form id="" method="post" action="${pageContext.request.contextPath}/student/studentQuestionModify">
					<table class="table">
						<tr>
							<td>질문 번호</td>
							<td><input type="text" name="questionNo" id="questionNo" value="${question.questionNo}" readonly="readonly"></td>
						</tr>
					
						<tr>
							<td>제목</td>
							<td><input type="text" name="questionTitle" id="questionTitle" value="${question.questionTitle}"></td>
						</tr>
						
						<tr>
							<td>내용</td>
							<td><input type="text" name="questionContent" id="questionContent" value="${question.questionContent}"></td>
						</tr>
		
						<tr>
							<td><button type="submit">수정</button></td>
						</tr>
								
					</table>
				</form>
			</div>
	</body>
</html>