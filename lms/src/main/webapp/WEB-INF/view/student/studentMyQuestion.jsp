<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 질문리스트</title>


<body>
	<!-- 메뉴+CSS 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
	<div class="container">
		<h1>${accountId}님의보기</h1>
		<!-- studentMyQuestionSearch -->
		<form action="${pageContext.request.pathInfo}" method="get">
			<input type="hidden" name="accountId" value="${accountId}">
			<input type="hidden" name="currentPage" value="1">
			<div class="justify-content-end mb-3 input-group">
				<input class="form-control col-sm-2" type="text" name="studentMyQuestionSearch" value="${studentMyQuestionSearch}" placeholder="Search">
				<div class="input-group-append">
						<button class="btn btn-success" type="submit">버튼</button>
				</div>
			</div>
		</form>
		<table class="table">
			<tr>
				<th>질문 No.</th>
				<th>제목</th>
				<th>생성날짜</th>
				<th>수정날짜</th>
				<th>조회수</th>
			<tbody>
				<c:forEach var="myq" items="${questionList}">
					<tr>
						<td>${myq.questionNo}</td>
						<td><a href="${pageContext.request.contextPath}/student/studentQuestionDetail?questionNo=${myq.questionNo}&&accountId=${accountId}">${myq.questionTitle}</a></td>
						<td>${myq.questionCreateDate}</td>
						<td>${myq.questionUpdateDate}</td>
						<td>${myq.questionCount}</td>
					</tr>
				</c:forEach>
				<tr>
					<td><a href="${pageContext.request.contextPath}/student/studentQuestionList">전체 질문보기</a></td>
					<td><a href="${pageContext.request.contextPath}/student/createStudentQuestion?questionNo=${questionNo}">질문 추가</a></td>
				</tr>
			</tbody>
		</table>
		<c:if test="${null eq question}">
			<div>
				<!-- 처음으로, 이전 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.pathInfo}?currentPage=1">[처음으로]</a>
						<a
							href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}"><</a>
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