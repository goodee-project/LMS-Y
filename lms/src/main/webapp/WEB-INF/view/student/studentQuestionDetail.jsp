<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생의 질문 상세보기</title>

<body>
	<!-- 메뉴+CSS 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<div class="container">
		<h1>질문 상세보기</h1>
	</div>
	<div class="container">
		<c:if test="${accountId == question.accountId}">
			<a href="${pageContext.request.contextPath}/student/modifyStudentQuestion?questionNo=${question.questionNo}">[수정]</a>
			<a href="${pageContext.request.contextPath}/student/removeQuestion?questionNo=${question.questionNo}">[삭제]</a>
		</c:if>
		
		<table class="table">
			<tr>
				<td>게시글 고유번호 :</td>
				<td>${question.questionNo}</td>
			</tr>
			
			<tr>
				<td>강좌 고유번호 :</td>
				<td>${question.lectureNo}</td>
			</tr>
			
			<tr>
				<td>학생 id</td>
				<td>${question.accountId}</td>
			</tr>

			<tr>
				<td>작성자 이름 :</td>
				<td>${question.questionWriter}</td>
			</tr>

			<tr>
				<td>게시글 제목 :</td>
				<td>${question.questionTitle}</td>
			</tr>

			<tr>
				<td>게시글 내용 :</td>
				<td>${question.questionContent}</td>
			</tr> 

			<tr>
				<td>생성 날짜 :</td>
				<td>${question.questionCreateDate}</td>
			</tr>
				<c:forEach var="qc" items="${question.questionComment}">
					<!-- 댓글이 없을때는 보이지 않음 -->
					<c:if test="${qc.questionCommentNo != 0}">
					
					<!-- 작성자 작성일자 넣기 -->
					<tr>
						<td>댓글 내용 :</td>
						<td>${qc.questionCommentContent}</td>
					</tr>
					
					<c:forEach var="qcf" items="${qc.questionCommentFileList}">
					<!-- 파일이 크기 0 일때는 보이지 않음 -->
					<c:if test="${qcf.questionCommentFileSize>0 }">
					<tr>
						<td>첨부파일
						<a href="${pageContext.request.contextPath}/student/downloadStudentQuestionCommentFile?questionCommentFileUUID=${qcf.questionCommentFileUUID}">
						${qcf.questionCommentFileOriginal}</a>
						<td>${question.questionCommentFile}</td>
						</td>
					</tr>
					</c:if>
					</c:forEach>
					
					</c:if>
				</c:forEach>
		</table>
	</div>
  