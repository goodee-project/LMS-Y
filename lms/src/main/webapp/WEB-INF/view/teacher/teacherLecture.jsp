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
					<th class="text-center">강좌 번호</th>
					<th class="text-center">강사 이름</th>
					<th class="text-center">강좌 이름</th>
					<th class="text-center">과목 이름</th>
					<th class="text-center">상세보기</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty teacherLectureList}">
				<c:forEach var="tl" items="${teacherLectureList}">
					<tr>
						<td class="text-center">${tl.lectureNo}</td>
						<td class="text-center">${tl.teacherName}</td>
						<td class="text-center">${tl.lectureName}</td>
						<td class="text-center">${tl.subject.subjectName}</td>
						<td class="text-center"><a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/teacherLectureOne?lectureNo=${tl.lectureNo}">상세보기</a></td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty teacherLectureList}">
					<tr>			
						<td class="text-center" colspan="5">현재 강의중인 강좌가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<c:if test="${not empty teacherLectureList}">
			<div style="margin-left:35%">
				<ul class="pagination">
					<c:choose>
						<c:when test="${currentPage > '1'}">
							<!-- 현재 페이지가 1일시 -->
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=1"> &lt;&lt;</a></li>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=${currentPage-1}">&lt;</a></li>	
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
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
								<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
									<a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?currentPage=${i}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${currentPage < lastPage}">
					<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=${currentPage+1}">&gt;</a></li>
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/teacher/teacherLecture?accountId=${accountId}&&currentPage=${lastPage}">&gt;&gt;</a></li>
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
		</c:if>
	</div>
</body>
</html>