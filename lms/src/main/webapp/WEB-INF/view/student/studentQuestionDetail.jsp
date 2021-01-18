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
	<!-- 강좌 메뉴 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
	
	<div class="jumbotron">
		<div class="container">
			<h1>질문 상세보기</h1>
		</div>
	</div>

	<div class="container">
		<table class="table">
			<thead>
				<tr class="small">
					<th colspan="4">강좌 No. ${question.lectureNo}</th>
				</tr>
				<tr class="small">
					<th>No. ${question.questionNo}</th>
					<th>학생ID ${question.accountId}</th>
				</tr>
			</thead>
			<tbody>

			<tr>
				<th>작성자 이름</th>
				<td>${question.questionWriter}</td>
			</tr>

			<tr>
				<th>게시글 제목</th>
				<td>${question.questionTitle}</td>
			</tr>

			<tr>
				<th>게시글 내용</th>			
				<td>${question.questionContent}</td>
			</tr> 

			<tr class="small">
				<th>생성 날짜</th>
				<td>${question.questionCreateDate}</td>
			</tr>
				<c:forEach var="qc" items="${question.questionComment}">
					<!-- 댓글이 없을때는 보이지 않음 -->
					<c:if test="${qc.questionCommentNo != 0}">
					
					<!-- 작성자 작성일자 넣기 -->
					<tr class="small">
						<th colspan="5">댓글</th>
					</tr>
					<tr>
						<td>${qc.questionCommentContent}</td>
					</tr>

					
					<c:forEach var="qcf" items="${qc.questionCommentFileList}">
					<!-- 파일이 크기 0 일때는 보이지 않음 -->
					<c:if test="${qcf.questionCommentFileSize>0 }">
						<tr>
							<td>첨부파일
							<a href="${pageContext.request.contextPath}/student/downloadStudentQuestionCommentFile?questionCommentFileUUID=${qcf.questionCommentFileUUID}">${qcf.questionCommentFileOriginal}</a>
							</td>
							<td>${question.questionCommentFile}</td>
						</tr>
					</c:if>
					</c:forEach>
					
					</c:if>
				</c:forEach>
				</tbody>
			</table>
			<c:if test="${accountId == question.accountId}">
				<div style="text-align:right;">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/modifyStudentQuestion?questionNo=${question.questionNo}">수정</a>
					<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/student/removeQuestion?questionNo=${question.questionNo}">삭제</a>
				</div>
			</c:if>
		</div>
  	</body>
 </html>
 