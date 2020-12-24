<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>studentRegistration</title>
		
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
			<h1>학생수강신청/취소</h1>
			
			<table class="table"></table>
				<thead>
					<tr>
						<th>수강 번호</th>
						<th>강좌 번호</th>
						<th>학생 id</th>
						<th>수강 상태</th>
						<th>수강 리뷰(점수)</th>
						<th>리뷰(텍스트)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="r" items="${registraionList}">
						<td>${r.classRegistrationNo }</td>
						<td>${r.lectureNo }</td>
						<td>${r.accountId }</td>
						<td>${r.classRegistrationState}</td>
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
					</c:forEach>
				</tbody>
		</div>
	</body>
</html>