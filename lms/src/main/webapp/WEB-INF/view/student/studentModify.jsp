<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 정보수정</title>
		
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
			<div class="jumbotron">
				<h1>학생 정보 수정</h1>
			</div>
		</div>
		<div class=container>
			<form method="post" action="${pageContext.request.contextPath}/student/studentIndex">
				<table class="table">
					<tr>
						<td>학생 이름</td>
						<td><input type="text" name="studentName" id="studentName" value="${studentName}"></td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td><input type="text" name="studentPhone" id="studentPhone" value="${studentPhone}"></td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td><input type="radio" name="studentGender" id="studentGender" value="남">남
							<input type="radio" name="studentGender" id="studentGender" value="여">여
						</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td><input type="date" name="studentBirth" id="studentBirth" value="${student.studentBirth}"></td>
					</tr>
					
					<tr> 
						<td>주소</td>
							<td><input type="text" name="studentAddressMain" id="studentAddressMain" value="${student.studentAddressMain}"></td>
						<td>상세주소</td>
							<td><input type="text" name="studentAddressSub" id="studentAddressSub" value="${student.studentAddressMain}"></td>
					</tr>
										
					<tr>
						<td>프로필 사진</td>
						<td><input type="text" name="studentImage" id="studentImage" value="${student.studentImage}"></td>
					</tr>
					
					<tr>
						<td><button type="submit">수정</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>