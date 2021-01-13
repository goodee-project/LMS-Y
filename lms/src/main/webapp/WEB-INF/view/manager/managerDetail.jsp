<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보 상세보기</title>
	
	<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				function sendError(url) {
					$.ajax({
						url : 'error.jsp',
						data : 'url=' + encodeURIComponent(url)
					});
				}
			});
		</script>


	<style type="text/css">
			.imagediv{
				width: 100%;
				text-align: center;
			}
			 img { display : block;
                  margin : auto;}
		</style>

</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class="jumbotron">
				<div class="container">
					<h1>내 정보 상세보기</h1>
				</div>
			</div>
		<div class=container>
				<div class="imagediv"><img src="${map.imageURI}" class="rounded-circle" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""  width="200px" height="200px" /></div>
				<div class="imagediv"><a href="${pageContext.request.contextPath}/manager/modifyManager">[프로필 수정]</a></div>
				<br>
				
				<table class="table">
				   
				   <tr>
						<td>운영자 아이디</td>
						<td>${map.manager.accountId}</td>
					</tr>
				   	<tr>
				   		<td>비밀번호</td>
				   		<td>
				   			<a href="${pageContext.request.contextPath}/manager/modifyManagerPasswd">비밀번호 변경</a>
						</td>
				   	</tr>
					<tr>
						<td>운영자 이메일</td>
						<td>${map.manager.managerEmail}</td>
					</tr>
					
					<tr>
						<td>운영자 전화번호</td>
						<td>${map.manager.managerPhone}</td>
					</tr>
					
					<tr>
						<td>운영자 이름</td>
						<td>${map.manager.managerName}</td>
					</tr>
					
					<tr>
						<td>운영자 성별</td>
						<td>${map.manager.managerGender}</td>
					</tr>
					
					<tr>
						<td>운영자 생년월일</td>
						<td>${map.manager.managerBirth}</td>
					</tr>
					<tr>
						<td>운영자 직책</td>
						<td>${map.manager.managerPosition}</td>
					</tr>
					<tr>
						<td>운영자 주소</td>
						<td>${map.manager.managerAddressMain}</td>
						<td>${map.manager.managerAddressSub}</td> 
					</tr>
					
				</table>
					<div class="d-flex justify-content-end">
						<a class="btn btn-outline-primary"  href="${pageContext.request.contextPath}/manager/modifyManager">내 정보 수정</a>
					</div>
			</div>
</body>
</html>