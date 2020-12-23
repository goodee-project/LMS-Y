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
				// 첨부파일 추가버튼에 대한 이벤트 처리를 등록함
				$('#createQuestionCommentFile').click(function() {
					// 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
					$('#questionCommentFileFrame').append(`
						<div>
							<input class="questionCommentFile" type="file" name="questionCommentFileList">
							<button class="removeQuestionCommentFile" type="button">삭제</button>
						</div>
					`);
					
					// (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
					$('#questionCommentFileFrame:last-child .removeQuestionCommentFile').click(function(event) {
						// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
						$(event.target).parent().remove();
					});
				});

				// 작성 버튼 클릭 시 유효성 검사 실시
				$('#submitQuestionCommentForm').click(function() {
					// 댓글 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if ($('#questionCommentContent').val() == '') {
						alert('댓글 내용을 입력해주세요!');
						$('#questionCommentContent').focus();

						return
					}

					// 빈 첨부파일 칸이 있을 경우 모두 삭제
					$('.questionCommentFile').each(function(index, element) {
						if ($(element).val() == '') {
							// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
							$(element).parent().remove();
						}
					});

					// 유효성 검사를 만족했을 경우 submit
					$('#questionCommentForm').submit();
				});
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
				<hr>
				<form id="questionCommentForm" method="POST" action="${pageContext.request.contextPath}/teacher/createQuestionComment" enctype="multipart/form-data">
					<input type="hidden" name="questionNo" value="${question.questionNo}">
						
					<div>
						댓글 내용:
						<textarea id="questionCommentContent" name="questionCommentContent" cols="20" rows="4"></textarea>
					</div>
					<div>
						첨부파일:
						<div>
							<button id="createQuestionCommentFile" type="button">추가</button>
						</div>
						<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
						<div id="questionCommentFileFrame"></div>
					</div>
					<hr>
					<div>
						<button id="submitQuestionCommentForm" type="button">작성</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>