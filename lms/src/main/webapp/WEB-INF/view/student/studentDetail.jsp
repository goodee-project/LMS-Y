<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studentDetail</title>

<style type="text/css">
			.attendanceTableMenu{
				width: 100%;
				text-align: center;
			}
		</style>
		
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
	
		<div class=container>
			<div class="jumbotron">
				<h1>내 정보</h1>
			</div>
		</div>
		<div class=container>
			<div class="imagediv"><img src="${map.imageURI}" class="rounded-circle" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""  width="200px" height="200px" /></div>
			<div class="imagediv"></div><a href="${pageContext.request.contextPath}/student/modifyStudent?accountId=${accountId}">[프로필 수정]</a>
				<table class="table">
				
					<tr>
						<td>학생 아이디</td>
						<td>${map.student.accountId}</td>
					</tr>
					
					<tr>
						<td>비밀번호</td>
						<td><a href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호변경</a></td>
					</tr>
			
					<tr>
						<td>학생 이메일</td>
						<td>${map.student.studentEmail}</td>
					</tr>
					<tr>
						<td>학생 이름</td>
						<td>${map.student.studentName}</td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td>${map.student.studentPhone}</td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td>${map.student.studentGender}</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td>${map.student.studentBirth}</td>
					</tr>
					
					<tr> 
						<td>주소</td>
						<td>${map.student.studentAddressMain}</td> 
					</tr>
					
					<tr>
						<td>상세주소</td>
						<td>${map.student.studentAddressSub}</td>
					</tr>
					
			</table>
		</div>
	</body>
</html>