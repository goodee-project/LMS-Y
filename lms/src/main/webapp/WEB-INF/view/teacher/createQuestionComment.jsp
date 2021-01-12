<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>질문게시판 댓글 등록</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		<script>
			$(document).ready(function() {
				// 첨부파일 추가버튼에 대한 이벤트 처리를 등록함
				$('#createQuestionCommentFile').click(function() {
					// 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
					$('#questionCommentFileFrame').append(`
						<div class="questionCommentFileGroup d-flex mt-2">
							<div class="flex-grow-1">
								<div class="custom-file">
									<input class="questionCommentFile custom-file-input" type="file" name="questionCommentFileList">
									<label class="custom-file-label">클릭하여 파일을 선택해주세요</label>
								</div>
							</div>
							<div class="align-self-center mx-3">
								<a class="removeQuestionCommentFile badge badge-danger rounded-circle" type="button">×</a>
							</div>
						</div>
					`);

					// (바로 위의 코드에서 추가한) 첨부파일 태그에 대한 이벤트 처리를 등록함
					$("#questionCommentFileFrame:last-child .questionCommentFile").on("change", function() {
						// 파일명을 가져오고 없을 경우 디폴트 값(파일 선택 메세지)을 가져옴
						let fileName = $(this).val().split('\\').pop();
						if (fileName == '') {
							fileName = '클릭하여 파일을 선택해주세요';
						}

						// 파일 선택 라벨의 내용을 변경함
						$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
					});
					
					// (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
					$('.removeQuestionCommentFile').last().click(function(event) {
						// 삭제 경고창을 띄움으로써 의사를 확인
						if ($(event.target).parent().parent().find('.custom-file-label').text() != '클릭하여 파일을 선택해주세요') {
							let remove = confirm('정말 등록한 파일 "'+$(event.target).parent().parent().find('.custom-file-label').text()+'" 을(를) 삭제하시겠습니까?');
							if (remove) {
								alert('삭제하였습니다');
							} else {
								return;
							}
						}
						
						// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
						$(event.target).parent().parent().remove();
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
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>질문게시판 댓글 등록</h1>
			</div>
		</div>
			
		<div class="container">
			<form id="questionCommentForm" method="POST" action="${pageContext.request.pathInfo}" enctype="multipart/form-data">
				<input type="hidden" name="questionNo" value="${question.questionNo}">
				
				<table class="table">
					<tr class="small">
						<th colspan="2">No. ${question.questionNo}</th>
					</tr>
					<tr>
						<th style="width: 20%">게시글 작성자</th>
						<td>${question.questionWriter}</td>
					</tr>
					<tr>
						<th>게시글 제목</th>
						<td>${question.questionTitle}</td>
					</tr>
					<tr>
						<th>게시글 내용</th>
						<td>${questcxzion.questionContent}</td>
					</tr>
					<tr>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="font-weight-bolder">댓글 내용</div>
							<div class="mt-2"><textarea id="questionCommentContent" name="questionCommentContent" style="width: 100%"></textarea></div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="font-weight-bolder">
								<span class="mr-1">첨부파일</span>
								<a id="createQuestionCommentFile" class="badge badge-pill badge-primary" type="button">추가</a>
							</div>
							<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
							<div id="questionCommentFileFrame"></div>
						</td>
					</tr>
					
					<tr>
						<td class="text-right" colspan="2"><button id="submitQuestionCommentForm" class="btn btn-outline-success" type="button">작성</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>