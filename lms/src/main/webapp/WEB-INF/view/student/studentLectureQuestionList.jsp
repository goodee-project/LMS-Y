<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentLectureQuestionList</title>

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
		<h1>강좌별 질문 게시판</h1>
		<!-- studentlectureSearch-->
		<form action="${pageContext.request.pathInfo}" method="get">
			<input type="hidden" name="lectureNo" value="${lectureNo}"> <input
				type="hidden" name="currentPage" value="1">
			<div class="justify-content-end mb-3 input-group">
				<input class="form-control col-sm-2" type="text"
					name="studentLectureSearch" value="${studentlectureSearch}"
					placeholder="Search">
				<div class="input-group-append">
					<button class="btn btn-success" type="submit">버튼</button>
				</div>
			</div>
		</form>
		<table class="table ">
			<tr>
				<th>질문 No.</th>
				<th>학생 Id</th>
				<th>강좌 번호</th>
				<th>작성자 이름</th>
				<th>제목</th>
				<th>생성 날짜</th>
				<th>조회수</th>
			<tbody>
				<c:forEach var="q" items="${questionList}">
					<tr>
						<td>${q.questionNo}</td>
						<td>${q.accountId}</td>
						<td>${q.lectureNo}</td>
						<td>${q.questionWriter}</td>
						<td><a
							href="${pageContext.request.contextPath}/student/studentQuestionDetail?questionNo=${q.questionNo}">${q.questionTitle}</a></td>
						<td>${q.questionCreateDate}</td>
						<td>${q.questionCount}</td>
					</tr>
				</c:forEach>
				<tr>
					
					<td><a
						href="${pageContext.request.contextPath}/student/createStudentQuestion?questionNo=${questionNo}">질문
							추가</a></td>
				</tr>
			</tbody>
		</table>

		<c:if test="${null eq studentlectureSearch}">
			
				<ul class="pagination">
				<!-- 처음으로, 이전 -->
					<c:choose>
						<c:when test="${currentPage > '1'}">
							<!-- 현재 페이지가 1보다 클시 -->
							<!-- 현재 페이지가 1일시 -->
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=1&&studentlectureSearch=${studentlectureSearch}">&lt;&lt;</a></li>
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage-1}&&studentlectureSearch=${studentlectureSearch}">&lt;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link" href="#">
									&lt;&lt; </a></li>
							<li class="page-item disabled"><a class="page-link" href="#">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
					<!-- 현재페이지 네비바 -->
					<!-- 현재 페이지 표시 -->
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<li class="page-item"><a class="page-link" href="#">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${i}&&studentlectureSearch=${studentlectureSearch}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- 다음, 마지막으로 -->
					<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
					<!-- 현재 페이지가 마지막 페이지 일시 -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${currentPage+1}&&studentlectureSearch=${studentlectureSearch}">&gt;</a></li>
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.pathInfo}?lectureNo=${lectureNo}&&currentPage=${lastPage}&&studentlectureSearch=${studentlectureSearch}">&gt;&gt;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link" href="#">&gt;</a>
							</li>
							<li class="page-item disabled"><a class="page-link" href="#">&gt;&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
			</ul>
		</c:if>

	</div>
</body>
</html>