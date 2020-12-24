<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의실 수정</title>
		
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
			<h1>강의실 수정</h1>
		</div>	
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/modifyClassroom?classroomNo=${classroom.classroomNo}">
					<table class=table>
						<tr>
							<td>강의실 고유번호</td>
							<td><input type="text" name="classroomNo" value="${classroom.classroomNo}" readonly="readonly"></td>
						</tr>
						<tr>
							<td>강의실 호실</td>
							<td><input type="text" name="classroomNumber" value="${classroom.classroomNumber}"></td>
						</tr>
						<tr>
							<td>강의실 면적</td>
						   <td><input type="text" name="classroomSize" value="${classroom.classroomSize}"></td>
						</tr>
						<tr>
							<td>강의실 수용 인원</td>
							<td><input type="text" name="classroomTotal" value="${classroom.classroomTotal}"></td>
						</tr>
					</table>
			  
			  		<button type="submit">입력</button>
				</form>
			</div>
		</div>
	</body>
</html>