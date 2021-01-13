<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 비밀번호 변경</title>
		
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
		<div class="jumbotorn">
			<div class="container">
				<h1>비밀번호 변경</h1>
			</div>
		</div>
		<form id="studentPwModify" method="post" action="${pageContext.request.contextPath}/student/studentPwModify">
		<input type="text" id="accountId" name="accountId" value="${accountId}" hidden="hidden">
		<!-- 현재 비밀번호 -->
		<input type="password" id="accountPw" class="form-control form-control-alternative" placeholder="현재 비밀번호를 입력하세요.">
		
		<!-- 변경할 비밀번호 -->
		<input type="password" id="studentPw" name="accountPw" class="form-control form-control-alternative" placeholder="새로운 비밀번호를 입력하세요." >
		</form>	
		
		<!-- 변경할 비밀번호 재확인 -->
		<input type="password" id="teacherPwCk" class="form-control form-control-alternative" placeholder="새로운 비밀번호를 다시 입력하세요">
		
		<div>
			<button id="btnPw" type="button" class="btn btn-danger">입력</button>
		</div>
	</body>
</html>