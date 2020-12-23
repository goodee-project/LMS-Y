<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ClassroomList</title>
		
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
			<h1>classroomList</h1>
			
			<div>
				<table class="table">
					<tr>
						<th>강의실 고유번호</th>
						<th>강의실 번호</th>
					</tr>
				<c:forEach items="${classroomList}" var="c">
					<tr>
						<td>${c.classroomNo}</td>
						<td>${c.classroomNumber}</td>
					</tr>
					
				
				</c:forEach>	
				
				
				</table>
				
			</div>
		</div>
	</body>
</html>