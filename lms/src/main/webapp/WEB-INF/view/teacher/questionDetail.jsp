<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 게시글</title>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>질문게시판 게시글</h1>
			
			<div>
				<div>
					게시글 고유번호: ${question.questionNo}
				</div>
				<div>
					강좌 고유번호: ${question.lectureNo}
				</div>
				<div>
					계정 ID: ${question.accountId}
				</div>
				<div>
					게시글 작성자: ${question.questionWriter}
				</div>
				<div>
					게시글 제목: ${question.questionTitle}
				</div>
				<div>
					게시글 내용: ${question.questionContent}
				</div>
				<div>
					게시글 생성일: ${question.questionCreateDate}
				</div>
				<div>
					게시글 수정일: ${question.questionUpdateDate}
				</div>
				<div>
					게시글 조회수: ${question.questionCount}
				</div>
				<c:forEach var="qc" items="${question.questionComment}">
					<hr>
					<div>
						댓글 고유번호: ${qc.questionCommentNo}
					</div>
					<div>
						계정 ID: ${qc.accountId}
					</div>
					<div>
						댓글 작성자: ${qc.questionCommentWriter}
					</div>
					<div>
						댓글 내용: ${qc.questionCommentContent}
					</div>
					<div>
						댓글 생성일: ${qc.questionCommentCreateDate}
					</div>
					<div>
						댓글 수정일: ${qc.questionCommentUpdateDate}
					</div>
					<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
					<c:if test="${qcf.questionCommentFileSize > 0}">
						<div>
							첨부파일
						</div>
						<c:forEach var="qcf" items="${qc.questionCommentFileList}">
								<div>
									<a href="${pageContext.request.contextPath}/teacher/downloadQuestionCommentFile?questionCommentFileUUID=${qcf.questionCommentFileUUID}">
										${qcf.questionCommentFileOriginal}
									</a>
									${qcf.questionCommentFileSize}B, 
									${qcf.questionCommentFileType},
									${qcf.questionCommentFileCount}회 다운로드,
									${qcf.questionCommentFileCreateDate}
								</div>
						</c:forEach>
					</c:if>
					<%-- 세션의 accountId와 댓글 작성자 accountId가 같을때만 수정버튼 표시 --%>
					<c:if test="${accountId == qc.accountId}">
						<div>
							<a href="${pageContext.request.contextPath}/teacher/modifyQuestionComment?questionCommentNo=${qc.questionCommentNo}">수정</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</body>
</html>