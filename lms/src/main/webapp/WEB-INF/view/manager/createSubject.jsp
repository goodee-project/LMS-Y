<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 정보 등록</title>
		
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
			<h1>과목 정보 등록</h1>
			
			<!-- 과목 정보 입력 -->
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/createSubject">
					<table border="1">
						<tr>
							<td>과목명</td>
							<td>
								<input type="text" name="subjectName">
							</td>
						</tr>
						<tr>
							<td>총 이수일수</td>
							<td>
								<input type="text" name="subjectTotalDay">
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="subjectInfo">
							</td>
						</tr>
					</table>
					<button type="button">
						등록
					</button>
				</form>
			</div>
		</div>
	</body>
</html>