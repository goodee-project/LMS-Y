<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강 취소</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                
                $('#submitBtn').click(function() {
					oEditors.getById["cancelContent"].exec("UPDATE_CONTENTS_FIELD", []);
                // code here...
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>수강 취소 사유 입력</h1>
		</div>	
			<div class="container">
				<form id="createQuestion" method="post" action="${pageContext.request.contextPath}/student/classRegistrationCancel">
					<table class="table">
						
						<tr>
							<td>강좌 번호</td>
							<td><input id="lectureNo" type="text" name="lectureNo" value="${lectureNo}"></td>
						</tr>		
			
						<tr>
							<td>과목 이름</td>
							<td><input id="subjectName" type="text" name="subjectName" value="${lectureName}"></td>
						</tr>
						
						<tr>
							<td>취소 사유</td>
							<td><textarea id="cancelContent" name="cancelContent"></textarea></td>
							
							<td><input id="classRegistrationNo" type="text" name="classRegistrationNo" value="${classRegistrationNo}" hidden></td>
						</tr>
					</table>
					<button type="submit" id="submitBtn">취소하기</button>
				</form>
			</div>
		
	</body>
</html>