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
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>LMS 공지사항</h1>
			<div>
				<table class="table">
					<tr>
						<th>lmsNoticeNo</th>
						<th>lmsNoticeTitle</th>
						<th>lmsNoticeWriter</th>
						<th>lmsNoticeUpdateDate</th>
					</tr>
					<c:forEach var="n" items="${lmsNoticeList}">
						<tr>
							<td>${n.lmsNoticeNo}</td>
							<td><a href="${pageContext.request.contextPath}/notice/lmsNoticeDetail?lmsNoticeNo=${n.lmsNoticeNo}">${n.lmsNoticeTitle}</a></td>
							<td>${n.lmsNoticeWriter}</td>
							<td>${n.lmsNoticeUpdateDate}</td>
						</tr>				
					</c:forEach>
				</table>
			</div>
			<!-- 4개로 분할 -->
			<div>
				<a href="${pageContext.request.contextPath}/manager/lmsNoticeList?currentPage=${currentPage-1}">이전</a>
				<a href="${pageContext.request.contextPath}/manager/lmsNoticeList?currentPage=${currentPage+1}">다음</a>
			</div>
			<!-- 조건문으로 manager로 들어왔을 경우만 보이게끔 변경해야함 -->
			<div><a href="${pageContext.request.contextPath}/manager/createLMSNotice">공지 생성</a></div>
		</div>
		
		<!-- 검색 -->
		<div>
			<form action="" method="post">
			
			</form>
		</div>
	</body>
</html>