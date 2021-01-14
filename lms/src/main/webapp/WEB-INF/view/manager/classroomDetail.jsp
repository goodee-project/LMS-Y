<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의실 정보</title>
<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
    		$('#delBtnId').click(function() {
				let remove = confirm('정말 삭제하시겠습니까?');
				if(remove) {
					location.replace('${pageContext.request.contextPath}/manager/removeClassroom?classroomNo=${classroom.classroomNo}');
					alert('삭제하였습니다.');
				} else {
					alert('취소하였습니다.');
					return false;
				}
    		});
    	});
        </script>  
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			<div class="jumbotron">
				<h1>강의실 정보</h1>
				
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
						<a id="delBtnId" class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/removeClassroom?classroomNo=${classroom.classroomNo}">삭제</a>
					</div>	
			</div>
	</body>
</html>