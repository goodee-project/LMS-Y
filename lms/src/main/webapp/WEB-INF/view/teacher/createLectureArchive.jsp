<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자료실 추가페이지</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		
		<script>
			$(document).ready(function() {
				// 첨부파일 추가버튼에 대한 이벤트 처리를 등록함
				$('#createLectureArchiveFile').click(function() {
					// 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
					$('#lectureArchiveFileFrame').append(`
						<div class="input-group mb-3">
							<input class="lectureArchiveFile" type="file" name="lectureArchiveFileList">
							<button class="removelectureArchiveFile btn btn-danger" type="button">삭제</button>
						</div>
					`);
					
					// (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
					$('#lectureArchiveFileFrame:last-child .removelectureArchiveFile').click(function(event) {
						// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
						$(event.target).parent().remove();
					});
				});

				// 작성 버튼 클릭 시 유효성 검사 실시
				$('#submitLectureArchiveForm').click(function() {
					// NAVER SmartEditor2에 적은 내용을 실제 form 태그에 적용
					oEditors.getById["lectureArchiveContent"].exec("UPDATE_CONTENTS_FIELD", []);
					
					// 댓글 내용을 입력하지 않았을 경우 입력 요구 및 포커스 이동
					if ($('#lectureArchiveContent').val() == '') {
						alert('댓글 내용을 입력해주세요!');
						$('#lectureArchiveContent').focus();

						return
					}

					// 빈 첨부파일 칸이 있을 경우 모두 삭제
					$('.lectureArchiveFile').each(function(index, element) {
						if ($(element).val() == '') {
							// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
							$(element).parent().remove();
						}
					});

					// 유효성 검사를 만족했을 경우 submit
					$('#lectureArchiveForm').submit();
				});

				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "lectureArchiveContent",	// 적용할 textarea 태그의 id 속성
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
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class="jumbotron">
			<div class=container>
				<h1>자료실 추가</h1>
			</div>
		</div>
		<div class=container>
			<form id="lectureArchiveForm"  method="post" action="${pageContext.request.contextPath}/teacher/createLectureArchive" enctype="multipart/form-data">
				<div style="text-align:left;">
					<button class="btn btn-primary" id="submitLectureArchiveForm" type="button">입력</button>
				</div>
				<p>
				<div>
						<!-- 강좌번호 -->
						<input type="hidden" name="lectureNo" value="${lectureNo}" readonly="readonly">
						<!-- 강사 아이디 -->
						<input type="hidden" name="accountId" value="${teacher.accountId}">				
						<!-- 강사 이름 -->
						<input type="hidden" name="lectureArchiveWriter" value="${teacher.teacherName}">
				</div>
			<table class="table">
						<tr>
							<td>제목</td>
							<td><input class="form-control" id="lectureArchiveTitle" type="text" name="lectureArchiveTitle"></td>
							<td></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea rows="10" cols="100" style="width:798px; height:312px; display:none;" id="lectureArchiveContent" name="lectureArchiveContent"></textarea></td>
							<td></td>
						</tr>
						<tr>
							<td>파일첨부</td>
							<td>
							<div>
								<button class="btn btn-primary" id="createLectureArchiveFile" type="button">추가</button>
								<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
								<div id="lectureArchiveFileFrame"></div>
							</div>
							</td>
							<td></td>
						</tr>
				</table>
			</form>
		</div>
	</body>
</html>