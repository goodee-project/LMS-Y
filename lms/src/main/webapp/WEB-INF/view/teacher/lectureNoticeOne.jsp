<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 공지사항 상세보기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
    		$('#removeBtn').click(function() {
				let remove = confirm('정말 삭제하시겠습니까?');
				
				if(remove) {
					location.replace('${pageContext.request.contextPath}/teacher/removeLectureNotice?lectureNoticeNo=${lectureNotice.lectureNoticeNo}');
					alert('삭제하였습니다.');
				} else {
					alert('취소하였습니다.');
					return;
				}
    		});
    	});
        </script>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class=container>
				<h1>공지사항 상세보기</h1>
			</div>
		</div>
		<div class="container">
			<table class="table">
				<tr>
					<td>공지번호</td>
					<td>${lectureNotice.lectureNoticeNo}</td>
				</tr>
				<tr>
					<td>공지제목</td>
					<td>${lectureNotice.lectureNoticeTitle}</td>
				</tr>
				<tr>
					<td>공지내용</td>
					<td>${lectureNotice.lectureNoticeContent}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${lectureNotice.lectureNoticeCreateDate}</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${lectureNotice.lectureNoticeCount}</td>
				</tr>
			</table>
			<div style="text-align:right;">
				<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/modifyLectureNotice?lectureNoticeNo=${lectureNotice.lectureNoticeNo}">수정</a>&nbsp;&nbsp;
				<button class="btn btn-outline-danger" id="removeBtn">삭제</button>
			</div>
		</div>
	</body>
</html>