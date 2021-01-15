<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>성적 확인</title>
		
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
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>성적 확인</h1>
			</div>
		</div>
		
		<div class="container">
			<table class="table">
				<thead>
					<tr class="small">
						<th colspan="5">
							<div>${lectureName}</div>
							<div>총점: ${userTotal} / ${multipleChoiceTotal}</div>
						</th>
					</tr>
					<tr>
						<th style="width: 20%">문제번호</th>
						<th>문제</th>
						<th>제출안</th>
						<th>정답</th>
						<th>점수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="map" items="${mapList}">
						<tr>
							<th rowspan="2">${map.multipleChoice.multipleChoiceId}번 문제</th>
							<td>${map.multipleChoice.multipleChoiceQuestion}</td>
							<td>${map.answerSheet.answerSelect}</td>
							<td>${map.multipleChoice.multipleChoiceAnswer}</td>
							<td>${map.answerSheet.answerScore}</td>
						</tr>
						<tr class="small">
							<td colspan="4">
								<div>제출안: ${map.submitExample}</div>
								<div>정답안: ${map.answerExample}</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>