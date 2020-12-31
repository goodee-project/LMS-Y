<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>studentQuestionList</title>
		
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
		<a href="${pageContext.request.contextPath}/student/studentQuestionAdd?questionNo=${questionNo}">질문 추가</a>
		<table class="table table-sm">
			<tr>
				<th>질문 번호</th>
				<th>학생 Id</th>
				<th>강좌 번호</th>
				<th>작성자 이름</th>
				<th>제목</th>
				<th>생성 날짜</th>
				<th>조회수</th>
				<th>자세히 보기</th>
				<tbody>
					<c:forEach var="q" items="${questionAllList}">
						<tr>
							<td>${q.questionNo}</td>
							<td>${q.accountId}</td>
							<td>${q.lectureNo}</td>
							<td>${q.questionWriter}</td>
							<td>${q.questionTitle}</td>
							<td>${q.questionCreateDate}</td>
							<td>${q.questionCount}</td>
							<td><a href="${pageContext.request.contextPath}/student/questionOne?questionNo=${questionNo}">자세히보기</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div style="margin-left:47%">
			<!-- 현재 페이지가 1일시 -->
			<a href="${pageContext.request.contextPath}/student/studentQuestionList?currentPage=${currentPage-1}">이전</a>	
			<!-- 현재 페이지 표시 -->
			<a href="">${currentPage}</a>
			<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
			<a href="${pageContext.request.contextPath}/student/studentQuestionList?currentPage=${currentPage+1}">다음</a>
			<!-- 마지막 페이지 -->
			<a href="${pageContext.request.contextPath}/student/studentQuestionList?currentPage=${lastPage}">마지막으로</a>
		</div>
	</body>
</html>