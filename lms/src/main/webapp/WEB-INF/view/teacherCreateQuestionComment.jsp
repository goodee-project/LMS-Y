<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 댓글 등록</title>
		
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
		
		<div class="container">
			<h1>질문게시판 댓글 등록</h1>
			
			<div>
				<div>
					게시글 고유번호: ${question.questionNo}
				</div>
				<div>
					강좌 고유번호: ${question.lectureNo}
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
				<form method="POST" action="${pageContext.request.contextPath}/teacher/createQuestionComment">
					<input type="hidden" name="questionNo" value="${question.questionNo}">
						
					<div>
						댓글 내용:
						<textarea name="questionCommentContent" cols="20" rows="4"></textarea>
					</div>
					<div>
						<button type="submit">작성</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>