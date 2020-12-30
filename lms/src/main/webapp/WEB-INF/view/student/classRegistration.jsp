<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>studentRegistration</title>
		
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
		<a href="${pageContext.request.contextPath}/student/classRegistration">수강신청 하러가기</a>
		<div class="container">
			<h1>학생수강목록</h1>
			<table class="table table-sm">
					<tr>
						<th>수강 번호</th>
						<th>강좌 번호</th>
						<th>학생 id</th>
						<th>수강 상태</th>
						<th>수강 리뷰(점수)</th>
						<th>리뷰(텍스트)</th>
					</tr>
				<tbody>
					<c:forEach var="r" items="${registraionList}">
						<td>${r.classRegistrationNo }</td>
						<td><a href="${pageContext.request.contextPath}/student/classRegistartionDetail?subjectNo=${subjectNo}">${r.lectureNo}</a></td>
						<td>${r.accountId }</td>
						<td>${r.classRegistrationState}</td>
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div style="margin-left:47%">
			<!-- 현재 페이지가 1일시 -->
			<a href="${pageContext.request.contextPath}/student/studentQuestionList?accountId=${accountId}&&currentPage=${currentPage-1}">이전</a>	
			<!-- 현재 페이지 표시 -->
			<a href="">${currentPage}</a>
			<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
			<a href="${pageContext.request.contextPath}/student/studentQuestionList?accountId=${accountId}&&currentPage=${currentPage+1}">다음</a>
		</div>
		
	</body>
</html>