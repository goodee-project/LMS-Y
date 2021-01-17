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
	
		<div class=jumbotron>
			<div class="container">
				<h1>내 정보</h1>
			</div>
		</div>
		<div class=container>
			<div class="imagediv"><img src="${map.imageURI}" class="rounded-circle" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""  width="200px" height="200px" /></div>
			<div class="imagediv"><a href="${pageContext.request.contextPath}/student/modifyStudent?accountId=${accountId}">[프로필 수정]</a></div>
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
			
			<div class="jumbotron">
				<div class="container">
					<h1>자격증 <a class="text-center btn btn-outline-primary" href="${pageContext.request.contextPath}/student/createStudentLicense">자격증추가</a></h1>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr class="text-center">
						<th>자격증 No.</th>
						<th>자격증명</th>
						<th>발급기관</th>
						<th>발급일</th>
						<th>자격증 삭제</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="l" items="${licenseMap.student.licenseList}">
					<tr class="text-center">
						<td>${l.licenseNumber}</td>
						<td>${l.licenseName}</td>
						<td>${l.licenseAgency}</td>
						<td>${l.licenseGetDate}</td>
						<td>
							<a id="removeBtnLicense" class="btn btn-outline-danger" href="${pageContext.request.contextPath}/student/removeLicense?licenseNo=${l.licenseNo}">삭제</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>