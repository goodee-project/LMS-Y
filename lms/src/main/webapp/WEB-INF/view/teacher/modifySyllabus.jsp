<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${lectureDetail.lectureName} 강의계획서 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		<script>
			$(document).ready(function() {
				// 첨부파일 삭제 버튼에 대한 이벤트 처리를 등록함
				$('.removeSyllabusFile').click(function(event) {
					let filename = $.trim($(event.target).siblings('.syllabusFileOriginal').text());
					let remove = confirm('정말 등록한 파일 "'+filename+'" 을(를) 삭제하시겠습니까?\n\n삭제한 파일은 복구할 수 없습니다!');
					if (remove) {
						alert('삭제하였습니다');
					} else {
						return false;
					}
					
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
				$('#createSyllabusFile').click(function() {
					// 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
					$('#syllabusFileFrame').append(`
						<div class="syllabusFileGroup d-flex mt-2 mb-3">
							<div class="flex-grow-1">
								<div class="custom-file">
									<input class="syllabusFile custom-file-input" type="file" name="syllabusFileList">
									<label class="custom-file-label">클릭하여 파일을 선택해주세요</label>
								</div>
							</div>
							<div class="align-self-center mx-3">
								<button class="removeSyllabusFile btn btn-outline-danger" type="button">×</button>
							</div>
						</div>
					`);

					// (바로 위의 코드에서 추가한) 첨부파일 태그에 대한 이벤트 처리를 등록함
					$('.syllabusFile').last().on("change", function() {
						// 파일명을 가져오고 없을 경우 디폴트 값(파일 선택 메세지)을 가져옴
						let fileName = $(this).val().split("\\").pop();
						if (fileName == "") {
							fileName = "클릭하여 파일을 선택해주세요";
						}

						// 파일 선택 라벨의 내용을 변경함
						$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
					});
					
					// (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
					let label = $('.custom-file-label').last();
					$('.removeSyllabusFile').last().click(function(event) {
						// 삭제 경고창을 띄움으로써 의사를 확인
						if (label.text() != '클릭하여 파일을 선택해주세요') {
							let remove = confirm('정말 등록한 파일 "'+label.text()+'" 을(를) 삭제하시겠습니까?');
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
				
				// 수정 버튼 클릭 시 유효성 검사 실시
				$('#submitBtn').click(function() {
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					// textarea가 SE2로 바뀐건 맞지만, 실제로는 가상의 에디터를 표시해둔거에 불과하기에
					// 따로 내용을 업데이트 해줘야 아래의 유효성 검사가 가능하고 Form submit시 데이터가 전송됨
					oEditors.getById["syllabusContent"].exec("UPDATE_CONTENTS_FIELD", []);

					let syllabusContent = $('#syllabusContent').val().replace(/<.+?>|\s+|&nbsp;/g, '');
					
					// 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if (syllabusContent == '') {
						alert('내용을 입력해주세요');
						oEditors.getById["syllabusContent"].exec("FOCUS");
						return;
					}
					
					// 빈 첨부파일 칸이 있을 경우 모두 삭제
					$('.syllabusFile').each(function(index, element) {
						if ($(element).val() == '') {
							// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
							$(element).parent().remove();
						}
					});
					
					// 유효성 검사를 만족했을 경우 submit
					$('#syllabusForm').submit();
				});
				
				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "syllabusContent",	// 적용할 textarea 태그의 id 속성
					sSkinURI: "${pageContext.request.contextPath}/se2/SmartEditor2Skin.html",	
					htParams : {
						bUseToolbar : true,			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,	// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
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
		<c:if test="${accountLevel == 2}">		
			<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		</c:if>
		
		<div class="jumbotron">
			<div class="container">
				<h1>${lectureDetail.lectureName} 강의계획서 수정</h1>
			</div>
		</div>
		
		<div class="container">
			<!-- 강의계획서 입력 -->
			<div>
				<form method="post" id="syllabusForm" action="${pageContext.request.contextPath}/teacher/modifySyllabus?lectureNo=${syllabusDetail.lectureNo}" enctype="multipart/form-data">
					<input type="text" id="lectureNo" name="lectureNo" value="${lectureDetail.lectureNo}" hidden="hidden">
					
					<table class="table">
	                	<tr>
	                		<th>내용</th>
	                		<td>
	                			<textarea id="syllabusContent" name="syllabusContent" rows="10" cols="100" style="width:798px; height:312px; display:none;">${syllabusDetail.syllabusContent}</textarea>
	                		</td>
	                	</tr>
	                	<tr>
	                		<th>첨부파일</th>
	                		<td>
	                			<c:forEach var="syllabusFile" items="${syllabusDetail.syllabusFileList}">
									<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
									<c:if test="${syllabusFile.syllabusFileSize > 0}">
										<div>
											<a class="syllabusFileOriginal" href="${pageContext.request.contextPath}/teacher/downloadSyllabusFile?syllabusFileUUID=${syllabusFile.syllabusFileUUID}">
												${syllabusFile.syllabusFileOriginal}
											</a>
											<span class="small">${syllabusFile.syllabusFileSize} byte / ${syllabusFile.syllabusFileCount}회 다운로드</span>
											<a class="removeSyllabusFile btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/teacher/removeSyllabusFile?syllabusFileUUID=${syllabusFile.syllabusFileUUID}">
												삭제
											</a>
										</div>
									</c:if>
									<c:if test="${syllabusFile.syllabusFileUUID == null}">
										<div class="mb-3">첨부된 파일이 없습니다</div>
									</c:if>
								</c:forEach>
						
								<div class="mb-3">
									<button type="button" id="createSyllabusFile" class="justify-content-end btn btn-outline-primary">
										추가
									</button>
								</div>
								<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
								<div id="syllabusFileFrame"></div>
	                		</td>
	                	</tr>
					</table>
					
					<!-- 수정 버튼 -->
					<div class="d-flex justify-content-end">
						<button type="button" id="submitBtn" class="justify-content-end btn btn-outline-success">
							수정
						</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>