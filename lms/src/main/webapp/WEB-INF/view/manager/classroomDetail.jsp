<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의실 정보</title>
<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
	<div class="jumbotron">
		<div class=container>
				<h1>강의실 정보</h1>
			</div>
		</div>
	
		

		<div class=container>
				<table class="table">
				   <tr>
						<td>강의실 고유번호</td>
						<td>${classroom.classroomNo}</td>
					</tr>
				   
					<tr>
						<td>강의실 호실</td>
						<td>${classroom.classroomNumber}호실</td>
					</tr>
					
					<tr>
						<td>강의실 면적</td>
						<td>${classroom.classroomSize}m<sup>2</sup></td>
					</tr>
					
					<tr>
						<td>강의실 수용 인원</td>
						<td>${classroom.classroomTotal}명</td>
					</tr>
					
				</table>
					<div class="d-flex justify-content-end" >
						<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyClassroom?classroomNo=${classroom.classroomNo}">수정</a>
					</div>	
			</div>
		
	</body>
</html>