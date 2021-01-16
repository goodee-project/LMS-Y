<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내 정보 상세보기</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				//학력 삭제 유효성검사
				$('#removeBtnEducation').click(function() {
					let remove = confirm('정말 삭제하시겠습니까?');
					
					if(remove) {
						<c:forEach var="m" items="${paramMap.teacher.educationList}">
						<c:if test="${not empty m.educationNo}">
						location.replace('${pageContext.request.contextPath}/teacher/removeTeacherEducation?educationNo=${m.educationNo}');
						</c:if>
						</c:forEach>
						alert('삭제하였습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
	    		});
				
				//경력 삭제 유효성검사
				$('#removeBtnCareer').click(function() {
					let remove = confirm('정말 삭제하시겠습니까?');
					
					if(remove) {
						<c:forEach var="m" items="${paramMap.teacher.careerList}">
						<c:if test="${not empty m.careerContent}">
						location.replace('${pageContext.request.contextPath}/teacher/removeTeacherCareer?careerNo=${m.careerNo}');
						</c:if>
						</c:forEach>
						alert('삭제하였습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
	    		});

				//자격증 삭제 유효성검사
				$('#removeBtnLicense').click(function() {
					let remove = confirm('정말 삭제하시겠습니까?');
					
					if(remove) {
						<c:forEach var="m" items="${paramMap.teacher.licenseList}">
						<c:if test="${not empty m.licenseNumber}">
						location.replace('${pageContext.request.contextPath}/teacher/removeTeacherLicense?licenseNo=${m.licenseNo}');
						</c:if>
						</c:forEach>
						alert('삭제하였습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
	    		});
	    		
				/*function sendError(url) {
					$.ajax({
						url : 'error.jsp',
						data : 'url=' + encodeURIComponent(url)
					});
				}*/	
			});
		</script>
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
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
			<div class="jumbotron">
				<div class="container">
					<h1>내 정보 상세보기</h1>
				</div>
			</div>
		<div class=container>
				<div class="imagediv"><img src="${map.imageURI}" class="rounded-circle" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""  width="200px" height="200px" /></div>
				<div class="imagediv"><a href="${pageContext.request.contextPath}/teacher/modifyTeacher">[프로필 수정]</a></div>
				<br>
				
				<table class="table">
					<tr>
						<td>강사 아이디</td>
						<td>${map.teacher.accountId}</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호변경</a></td>
					</tr>
					<tr>
						<td>강사 이메일</td>
						<td>${map.teacher.teacherEmail}</td>
					</tr>
					<tr>
						<td>강사 이름</td>
						<td>${map.teacher.teacherName}</td>
					</tr>
					<tr>
						<td>강사 전화번호</td>
						<td>${map.teacher.teacherPhone}</td>
					</tr>
					<tr>
						<td>강사 성별</td>
						<td>${map.teacher.teacherGender}</td>
					</tr>
					<tr>
						<td>강사 생년월일</td>
						<td>${map.teacher.teacherBirth}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${map.teacher.teacherAddressMain} ${map.teacher.teacherAddressSub}</td>
					</tr>
					<tr>
						<td>강사 한줄소개</td>
						<td>${map.teacher.teacherInfo}</td>
					</tr>
				</table>
				<!-- 학력 테이블 -->
				<p>
				<div class="jumbotron">
					<div class="container">
						<h1>학력&nbsp;<a class="text-center btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/createTeacherEducation">학력추가</a></h1>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr class="text-center">
							<th>출신학교</th>
							<th>전공</th>
							<th>입학일</th>
							<th>졸업일</th>
							<th>삭제</th>
						<tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${paramMap.teacher.educationList}">
						<c:if test="${empty m.educationNo}">
							<tr>
								<td class="text-center" colspan="5">
									<span>학력에 데이터가 없습니다</span>
								</td>
							</tr>
						</c:if>
						</c:forEach>
						<c:forEach var="m" items="${paramMap.teacher.educationList}">
							<tr class="text-center">
								<td>${m.educationSchool}</td>
								<td>${m.educationMajor}</td>
								<td>${m.educationStartDate}</td>
								<td>${m.educationEndDate}</td>
								<c:if test="${not empty m.educationNo}">
								<td><button id="removeBtnEducation" class="btn btn-outline-danger">경력삭제</button></td>
								</c:if>
							</tr>	
						</c:forEach>
					</tbody>
				</table>
				
				<!-- 경력 테이블 -->
				<p>
				<div class="jumbotron">
					<div class="container">
						<h1>경력 사항&nbsp;<a class="text-center btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/createTeacherCareer">경력추가</a></h1>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr class="text-center">
							<th>경력</th>
							<th>경력 시작일</th>
							<th>경력 종료일</th>
							<th>삭제</th>
						<tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${paramMap.teacher.careerList}">
						<c:if test="${empty m.careerContent}">
							<tr>
								<td class="text-center" colspan="5">
									<span>경력에 데이터가 없습니다</span>
								</td>
							</tr>
						</c:if>
						</c:forEach>
						<c:forEach var="m" items="${paramMap.teacher.careerList}">
							<tr class="text-center">
								<td>${m.careerContent}</td>
								<td>${m.careerStartDate}</td>
								<td>${m.careerEndDate}</td>
								<c:if test="${not empty m.careerContent}">
								<td><button id="removeBtnCareer" class="btn btn-outline-danger">경력삭제</button></td>
								</c:if>
							</tr>	
						</c:forEach>
					</tbody>
				</table>

				<!-- 자격증 테이블 -->
				<p>
				<div class="jumbotron">
					<div class="container">
						<h1>자격증 &nbsp;<a class="text-center btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/createTeacherLicense">자격증추가</a></h1>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr class="text-center">
							<th>자격증 일련번호</th>
							<th>자격증명</th>
							<th>발급기관</th>
							<th>발급일</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${paramMap.teacher.licenseList}">
						<c:if test="${empty m.licenseNumber}">
							<tr>
								<td class="text-center" colspan="5">
									<span>자격증에 데이터가 없습니다</span>
								</td>
							</tr>
						</c:if>
						</c:forEach>
						<c:forEach var="m" items="${paramMap.teacher.licenseList}">
							<tr class="text-center">
								<td>${m.licenseNumber}</td>
								<td>${m.licenseName}</td>
								<td>${m.licenseAgency}</td>
								<td>${m.licenseGetDate}</td>
								<c:if test="${not empty m.licenseNumber}">
								<td><button id="removeBtnLicense" class="btn btn-outline-danger">경력삭제</button></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</body>
</html>