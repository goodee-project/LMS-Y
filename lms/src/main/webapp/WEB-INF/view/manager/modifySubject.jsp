<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 정보 수정</title>
		
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
			<h1>과목 정보 수정</h1>
			
			<!-- 과목 정보 입력 -->
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/modifySubject?subjectNo=${subject.subjectNo}">
					<table border="1">
						<tr>
							<td>고유번호</td>
							<td>
								<input type="text" name="subjectNo" value="${subject.subjectNo}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>과목명</td>
							<td>
								<input type="text" name="subjectName" value="${subject.subjectName}">
							</td>
						</tr>
						<tr>
							<td>총 이수일수</td>
							<td>
								<input type="text" name="subjectTotalDay" value="${subject.subjectTotalDay}">
							</td>
						</tr>
						<tr>
							<td>정보</td>
							<td>
								<input type="text" name="subjectInfo" value="${subject.subjectInfo}">
							</td>
						</tr>
					</table>
					<button type="button">
						수정
					</button>
				</form>
			</div>
		</div>
	</body>
</html>