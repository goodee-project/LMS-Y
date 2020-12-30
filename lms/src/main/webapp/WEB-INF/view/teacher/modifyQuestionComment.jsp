<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 댓글 수정</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		
		<script>
			$(document).ready(function() {
				// 첨부파일 삭제버튼에 대한 이벤트 처리를 등록함
				$('.removeQuestionCommentFile').click(function(event) {
					$.ajax({
						url: $(event.target).prop('href'),
						method: 'post',
						success: function(removed) {
							if (removed) {
								$(event.target).parent().remove();
							}
						}
					});
					
					return false;
				});
				
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
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					oEditors.getById["questionCommentContent"].exec("UPDATE_CONTENTS_FIELD", []);
					
					// 댓글 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					let contentText = $('#questionCommentContent').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					if (contentText == '') {
						alert('댓글 내용을 입력해주세요!');
						oEditors.getById["questionCommentContent"].exec("FOCUS");

						return;
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

				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "questionCommentContent",	// 적용할 textarea 태그의 id 속성
					sSkinURI: "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html",	
					htParams : {
						bUseToolbar : true,						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						I18N_LOCALE : "ko_KR"
					},
					fCreator: "createSEditor2"
				});
			});
		</script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>질문게시판 댓글 수정</h1>
			
			<div>
				<div>
					게시글 고유번호: ${map.question.questionNo}
				</div>
				<div>
					강좌 고유번호: ${map.question.lectureNo}
				</div>
				<div>
					게시글 작성자: ${map.question.questionWriter}
				</div>
				<div>
					게시글 제목: ${map.question.questionTitle}
				</div>
				<div>
					게시글 내용: ${map.question.questionContent}
				</div>
				<form id="questionCommentForm" method="POST" action="${pageContext.request.pathInfo}">
					<input type="hidden" name="questionCommentNo" value="${map.questionComment.questionCommentNo}">
						
					<div>
						댓글 내용:
						<textarea id="questionCommentContent" name="questionCommentContent" style="width: 100%">${map.questionComment.questionCommentContent}</textarea>
					</div>
					<div>
						첨부파일:
						<c:forEach var="qcf" items="${map.questionComment.questionCommentFileList}">
							<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
							<c:if test="${qcf.questionCommentFileSize > 0}">
								<div>
									<a href="${pageContext.request.contextPath}/teacher/downloadQuestionCommentFile?questionCommentFileUUID=${qcf.questionCommentFileUUID}">
										${qcf.questionCommentFileOriginal}
									</a>
									${qcf.questionCommentFileSize}B, 
									${qcf.questionCommentFileType},
									${qcf.questionCommentFileCount}회 다운로드,
									${qcf.questionCommentFileCreateDate}
									<a class="removeQuestionCommentFile" href="${pageContext.request.contextPath}/teacher/removeQuestionCommentFile?questionCommentFileUUID=${qcf.questionCommentFileUUID}">삭제</a>
								</div>
							</c:if>
						</c:forEach>
						<div>
							<button id="createQuestionCommentFile" type="button">추가</button>
						</div>
						<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
						<div id="questionCommentFileFrame"></div>
					</div>
					<div>
						<button id="submitQuestionCommentForm" type="button">수정</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>