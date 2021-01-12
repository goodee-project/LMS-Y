<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌별 자료실 상세보기</title>
	</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<!-- 강좌 메뉴 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
	
		<div class="jumbotron">
			<div class="container">
				<h1>자료실 상세보기</h1>
			</div>
		</div>
	<div class="container">
		<table class="table">
			<tr>
				<td>자료실 번호</td>
				<td>${lectureArchive.lectureArchiveNo}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${lectureArchive.lectureArchiveWriter}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${lectureArchive.lectureArchiveTitle}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${lectureArchive.lectureArchiveContent}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${lectureArchive.lectureArchiveCreateDate}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${lectureArchive.lectureArchiveCount}</td>
			</tr>
				<c:forEach var="laf" items="${lectureArchive.lectureArchiveFileList}">
					<tr>
						<td>첨부파일</td>
						<td>
							다운로드 : <a href="${pageContext.request.contextPath}/teacher/downloadLectureArchiveFile?lectureArchiveFileUUID=${laf.lectureArchiveFileUUID}">${laf.lectureArchiveFileUUID}</a>
							<div>원본이름 : ${laf.lectureArchiveFileOriginal}</div>
							<div>파일크기 : ${laf.lectureArchiveFileSize}B,</div>
							<div>파일유형 : ${laf.lectureArchiveFileType},</div>
							<div>파일생성일: ${laf.lectureArchiveFileCreateDate}</div>
						</td>
					</tr>
					<tr>
						<td>다운로드 횟수</td>
						<td>${laf.lectureArchiveFileCount}</td>
					</tr>
				</c:forEach>
		</table>
		<div align="right">
			<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/modifyLectureArchive?lectureArchiveNo=${param.lectureArchiveNo}">수정</a>
		</div>
	</div>
</body>
</html>