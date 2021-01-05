<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- NAVER SmartEditor2 스크립트 -->
		<script src="${pageContext.request.contextPath}/se2/js/service/HuskyEZCreator.js"></script>
		
		<script>
		$(document).ready(function() {
				// NAVER SmartEditor2 적용 코드
				let oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "lectureNoticeContent",	// 적용할 textarea 태그의 id 속성
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
			
			<div class=container>
				<div class="jumbotron">
					<h1>공지사항 수정</h1>
				</div>
			</div>
			<div class=container>
				<form method="post" action="${pageContext.request.contextPath}/teacher/modifyLectureNotice">
						<table class="table">
							<tr>
								<td>공지번호</td>
								<td><input type="text" name="lectureNoticeNo" value="${lectureNotice.lectureNoticeNo}" readonly="readonly"></td>
							</tr>
							<tr>
								<td>제목</td>
								<td><input type="text" name="lectureNoticeTitle" value="${lectureNotice.lectureNoticeTitle}"></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea cols="70" rows="10" id="lectureNoticeContent" name="lectureNoticeContent">${lectureNotice.lectureNoticeContent}</textarea>
							</tr>
						</table>
						<button type="submit">입력</button>
				</form>
			</div>
	</body>
</html>