<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LMS 공지사항</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
               // 폼 유효성 검사
               // code here...
               //   
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>공지사항</h1>
			<div>
				<table class="table">
					<tr>
						<td>${lmsNotice.lmsNoticeNo}</td>
						<td>${lmsNotice.lmsNoticeTitle}</td>
						<td>${lmsNotice.lmsNoticeWriter}</td>
						<td>${lmsNotice.lmsNoticeUpdateDate}</td>
						<td>${lmsNotice.lmsNoticeCount}</td>
					</tr>
					<tr>
						<td>${lmsNotice.lmsNoticeContent}</td>
					</tr>
					<c:if test="${accountLevel eq managerLevel}">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/manager/modifyLMSNotice?lmsNoticeNo=${lmsNotice.lmsNoticeNo}">수정</a>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/manager/removeLMSNotice?lmsNoticeNo=${lmsNotice.lmsNoticeNo}">삭제</a>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</body>
</html>