<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사강좌조회</title>
</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<div class="jumbotron">
		<div class=container>
			<h1>강좌조회 목록</h1>
		</div>
	</div>

	<div class=container>
		<table class="table">
			<thead>
				<tr>
					<th>강좌 번호</th>
					<th>강사 이름</th>
					<th>강좌 이름</th>
					<th>과목 이름</th>
					<th>상세보기</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty teacherLectureList}">
				<c:forEach var="tl" items="${teacherLectureList}">
					<tr>
						<td>${tl.lectureNo}</td>
						<td>${tl.teacherName}</td>
						<td>${tl.lectureName}</td>
						<td>${tl.subject.subjectName}</td>
						<td><a href="${pageContext.request.contextPath}/teacher/teacherLectureOne?lectureNo=${tl.lectureNo}">상세보기</a></td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty teacherLectureList}">
					<tr>			
						<td colspan="7">현재 강의중인 강좌가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div style="margin-left:35%">
			<ul class="pagination">
				<c:choose>
					<c:when test="${currentPage > '1'}">
						<!-- 현재 페이지가 1일시 -->
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=${currentPage-1}">이전</a></li>	
					</c:when>
				<c:otherwise>
					<li class="page-item disabled">
						<a class="page-link" href="#"> &lt;&lt; </a>
					</li>
					<li class="page-item disabled">
						<a class="page-link" href="#">&lt;</a>
					</li>
				</c:otherwise>
				</c:choose>
				<!-- 현재 페이지 표시 -->
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?currentPage=${currentPage}">${currentPage}</a></li>
				<c:choose>
					<c:when test="${currentPage < lastPage}">
				<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=${currentPage+1}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled">
								<a class="page-link" href="#">&gt;</a>
							</li>
							<li class="page-item disabled">
								<a class="page-link" href="#">&gt;&gt;</a>
							</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</body>
</html>