<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강신청</title>
		
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
		<div class="jumbotron">
			<div class="container">
				<h1>수강 가능 강좌 목록</h1>
			</div>
		</div>
		<div class="container">
			<table class="table">
					<tr>
						<th>No.</th>
						<th>강사 이름</th>
						<th>과목 이름</th>
						<th>강좌 시작~종료일시</th>
						<th>신청 여부</th>
					</tr>
				<tbody>
					<c:forEach var="al" items="${availableLectureListMap}">
					<tr>
						<td>${al.lecture.lectureNo}</td>
						<td>${al.lecture.teacherName}</td>
						<td>${al.lecture.lectureName}</a></td>
						<td>${al.lecture.lectureStartDate}~${al.lecture.lectureEndDate}</td>
						<td>
						<c:choose>
							<c:when test="${al.isRegisterable}">
								<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/classRegistrationDetail?lectureNo=${al.lecture.lectureNo}">신청가능한 강좌입니다</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/student/classRegistration">이미 신청한 강좌입니다</a>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
		<div class="d-flex justify-content-center">
			<ul class="pagination">
				<!-- 처음으로, &lt; -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<li class="page-item">
							<a class="page-link"
							href="${pageContext.request.pathInfo}?currentPage=1">&lt;&lt;</a></li>
						<li class="page-item">
							<a class="page-link"
							href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}">&lt;</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled">
							<a class="page-link" href="#">&lt;&lt;</a>
						</li>
						<li class="page-item disabled">
							<a class="page-link" href="#">&lt;</a>
						</li>
					</c:otherwise>
				</c:choose>
				<!-- 현재페이지 네비바 -->
				<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<li class="page-item active">
									<a class="page-link" href="#">${i}</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link"
									href="${pageContext.request.pathInfo}?currentPage=${i}">${i}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<!-- &gt;, 마지막으로 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<li class="page-item">
							<a class="page-link"
							href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">&gt;</a>
						</li>
						<li class="page-item">
							<a class="page-link"
							href="${pageContext.request.pathInfo}?currentPage=${lastPage}">&gt;&gt;</a>
						</li>
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