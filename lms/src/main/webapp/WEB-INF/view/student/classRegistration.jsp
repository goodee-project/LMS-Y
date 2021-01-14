<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentRegistration</title>

<!-- jQuery 스크립트 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		<h1>학생수강목록</h1>
		<table class="table">
			<tr>
				<th>강좌 No.</th>
				<th>강사 이름</th>
				<th>과목 이름</th>
				<th>수강 상태</th>
				<th>수강신청일</th>
				<th>수강 리뷰(점수)</th>
				<th>리뷰(텍스트)</th>
				<th>강좌 상세보기</th>
				<th>수강 취소</th>
			</tr>
			<tbody>
				<c:forEach var="r" items="${classRegistrationList}">
					<tr>
						<td>${r.lectureNo}</td>
						<td>${r.lectureInfo.teacherName}</td>
						<td>${r.lectureInfo.lectureName}</td>
						<td>${r.classRegistrationState}</td>
						<td>${r.classRegistrationCreateDate}</td>
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
						<td><a href="${pageContext.request.contextPath}/student/classRegistrationMyDetail?lectureNo=${r.lectureNo}&accountId=${accountId}">상세보기</a></td>
						<td>
							<c:choose> 
								<c:when test ="${r.classRegistrationState=='취소'}"/>
								<c:when test="${r.classRegistrationState=='수료'}"/>
								<c:otherwise>
								<a href="${pageContext.request.contextPath}/student/classRegistrationCancel?classRegistrationNo=${r.classRegistrationNo}&&lectureName=${r.lectureInfo.lectureName}&&lectureNo=${r.lectureNo}">수강 취소하기</a>
								</c:otherwise>					
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${null eq classregistrationList}">
			<div>
				<!-- 처음으로, 이전 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.pathInfo}?currentPage=1">[처음으로]</a>
						<a href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}"><</a>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<!-- 현재페이지 네비바 -->
				<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">[${i}]</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.pathInfo}?currentPage=${i}">[${i}]</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<!-- 다음, 마지막으로 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a
							href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">></a>
						<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}">[마지막으로]</a>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>
	</div>
</body>
</html>