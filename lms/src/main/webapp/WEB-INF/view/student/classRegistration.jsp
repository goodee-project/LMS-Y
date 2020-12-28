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
		
		<div class="container">
			<h1>학생수강신청/취소</h1>
			
			<table class="table">
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
		
			<div>
		<!-- 처음 페이지 -->
			<c:choose>
				<c:when test="${currentPage>1}">
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
			<c:forEach var="i" begin="${navFirstPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">
									[${i}]
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/student/studentQuestionList?currentPage=${i}">
									[${i}]
								</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			
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