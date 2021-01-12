<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의실 상세보기</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>강의실 상세정보</h1>
				
			</div>
			<div>
				 <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/classroomList">목록</a>
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
						<td>${classroom.classroomNumber}</td>
					</tr>
					
					<tr>
						<td>강의실 면적</td>
						<td>${classroom.classroomSize}</td>
					</tr>
					
					<tr>
						<td>강의실 수용 인원</td>
						<td>${classroom.classroomTotal}</td>
					</tr>
				</table>
				<div>
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyClassroom?classroomNo=${classroom.classroomNo}">수정</a>
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/removeClassroom?classroomNo=${classroom.classroomNo}">삭제</a>
				</div>
			</div>
	</body>
</html>