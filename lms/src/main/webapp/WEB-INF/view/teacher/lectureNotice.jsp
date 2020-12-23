<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 공지사항</title>
	</head>
	<body>
			<!-- 부트스트랩(CSS) 인클루드 -->
			<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
			<div class=container>
				<div class="jumbotron">
					<h1>강좌 공지사항</h1>
				</div>
			</div>
			<div class="container">
				<a href="${pageContext.request.contextPath}/teacher/createLectureNotice?lectureNo=${lectureNo}">추가</a>
				<table class="table">
					<thead>
						<tr>
							<td>공지 번호</td>
							<td>공지 제목</td>
							<td>작성일</td>
							<td>조회 수</td>
							<td>상세보기</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ln" items="${lectureNoticeList}">
							<tr>
								<td>${ln.lectureNoticeNo}</td>
								<td>${ln.lectureNoticeTitle}</td>
								<td>${ln.lectureNoticeCreateDate}</td>
								<td colspan="1">${ln.lectureNoticeCount}</td>
								<td><a href="${pageContext.request.contextPath}/teacher/lectureNoticeOne?lectureNoticeNo=${ln.lectureNoticeNo}">상세보기</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<div style="margin-left:40%">
				<c:choose>
					<c:when test="${currentPage > '1'}">
						<!-- 현재 페이지가 1보다 클시 -->
						<!-- 현재 페이지가 1일시 -->
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=1">처음</a>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${currentPage-1}">이전</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=1">처음</a>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${currentPage-1}">이전</a>
					</c:otherwise>
					</c:choose>
						<!-- 현재 페이지 표시 -->
						<a href="">${currentPage}</a>
						<!-- 현재 페이지가 마지막 페이지 보다 작을시 -->
						<!-- 현재 페이지가 마지막 페이지 일시 -->
					<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${currentPage+1}">다음</a>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${lastPage}">끝</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${currentPage+1}">다음</a>
						<a href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${lectureNo}&currentPage=${lastPage}">끝</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>