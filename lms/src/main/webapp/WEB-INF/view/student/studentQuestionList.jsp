<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>[여기에 이 페이지의 특징을 잘 살린 제목을 넣어주세요]</title>
		
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
			<h1>질문 게시판</h1>
			
			<table class="table">
				[헤으응...]
				<tr>
					<th>학생 id</th>
					<th>강좌 번호</th>
					<th>작성자 이름</th>
					<th>제목</th>
					<th>생성 날짜</th>
					<th>수정 날짜</th>
					<th>조회수</th>

					
				<tbody>
					<tr>
						<td>${q.accountId}</td>
						<td>${q.lectureNo}</td>
						<td>${q.questionWriter}</td>
						<td>${q.questionTitle}</td>
						<td>${q.questionCreateDate}</td>
						<td>${q.questionUpdateDate}</td>
						<td>${q.questionCount}</td>
						<td><a href="${pageContext.request.contextPath}/question/questionOne?accountId=${accountId}&currentPage=${currentPage}">자세히보기</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>