<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>lms 공지 입력</title>
		
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
			<h1>lms 공지추가</h1>
			
			<div>
				<form action="${pageContext.request.contextPath}/manager/createLMSNotice" method="post">
					<input type="text" name="lmsNoticeTitle">
					<textarea name="lmsNoticeContent"></textarea>
					<div>
						<button type="submit">추가</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>