<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문 추가하기</title>
		
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
			<div class="jumbotorn">
				<h1>학생 질문 추가</h1>
			</div>
		</div>
				<form method="post" action="${pageContext.request.contextPath}/student/studentQuestionAdd">
					<table border="1">
						
						<tr>
							<td>강좌번호</td>
							<td>
								<select id="lectureId">
									<option value="0">테스트강좌1</option>
									<option value="1">테스트강좌2</option>
									<option value="2">테스트강좌3</option>
									<option value="3">테스트강좌4</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td>계정Id</td>
							<td><input type="text" name="accountId" value="${question.accountId}" readonly="readonly"></td>
							
							<td>작성자</td>	
							<td><input type="text" name="questionWriter" value="${question.questionWriter}"></td>
						</tr>
						
						<tr>
							<td>제목</td>
							<td><input type="text" name="questionTitle" value="${question.questionTitle}"></td>
						</tr>
						
						<tr>
							<td>내용</td>
							<td><textarea= cols="60" row="10" name="questionContent" value="${question.questionContent}"></textarea></td>
						</tr>
						
						<tr>
							<td>작성날짜</td>
							<td>${question.questionCreateDate}</td>
						</tr>
						
						
						<tr>
							<td>수정날짜</td>
							<td>${question.questionUpdateDate}</td>
						</tr>
						
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="questionPassword" value="${questionPassword}"></td>
						</tr>
					</table>
					<button type="submit">[등록]</button>
				</form>
	</body>
</html>