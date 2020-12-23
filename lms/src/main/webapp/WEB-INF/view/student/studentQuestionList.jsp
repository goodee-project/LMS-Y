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
			
			<table class="table">
				<tr>
					<th>학생 id</th>
					<th>강좌 번호</th>
					<th>작성자 이름</th>
					<th>제목</th>
					<th>생성 날짜</th>
					<th>수정 날짜</th>
					<th>조회수</th>
				<tbody>
					<c:forEach var="q" items="${questionList}">
						<tr>
							<td>${q.accountId}</td>
							<td>${q.lectureNo}</td>
							<td>${q.questionWriter}</td>
							<td>${q.questionTitle}</td>
							<td>${q.questionCreateDate}</td>
							<td>${q.questionUpdateDate}</td>
							<td>${q.questionCount}</td>
							<td><a href="${pageContext.request.contextPath}/student/questionOne?accountId=${accountId}&currentPage=${currentPage}">자세히보기</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
			<a href="${pageContext.request.contextPath}/student/studentQuestionAdd">입력</a>
			
		<div>
		<!-- 처음 페이지 -->
			<c:choose>
				<c:when test="${currentPage=1}">
					<a href="${pageContext.request.contextPath}/student/studentQuestionList?questionNo=${questionNo}&currentPage=1">처음</a>
				</c:when>
			<c:otherwise>
				<a href="a">처음</a>
			</c:otherwise>
			</c:choose>
			
		<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${currentPage>1 }">
					<a href="${pageContext.request.contextPath}/student/studentQuestionList?questionNo=${questionNo}&currentPage=${currentPage-1}">이전</a>
				</c:when>
			<c:otherwise>
				<a href="a">이전</a>
			</c:otherwise>
			</c:choose>
			
		<!-- 현재 페이지 -->
			<c:choose>
				<c:when test="${currentPage}">
				<a href="">${currentPage}</a>
				</c:when>
			</c:choose>
			
		<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${currentPage<lastPage}">
					<a href="${pageContext.request.contextPath}/student/studentQuestionList?questionNo=${questionNo}&currentPage=${currentPage+1}">다음</a>
				</c:when>
				<c:otherwise>
					<a href="a">다음</a>
				</c:otherwise>
			</c:choose>
			
		<!-- 마지막 페이지 -->
			<c:choose>
				<c:when test="${currentPage<lastPage}">
					<a href="${pageContext.request.contextPath}/student/studentQuestionList?questionNo=${questionNo}&currentPage=${lastPage}">마지막</a>
				</c:when>
				<c:otherwise>
					<a href="a">마지막</a>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>