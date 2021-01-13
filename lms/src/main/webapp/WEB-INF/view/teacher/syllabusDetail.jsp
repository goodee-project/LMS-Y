<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의계획서 정보</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#teacherSignBtn').click(function() {
					let signSyllabusByTeacher = confirm('정말 서명하시겠습니까?');
					
					if(signSyllabusByTeacher) {
						location.replace('${pageContext.request.contextPath}/teacher/signSyllabusByTeacher?lectureNo=${syllabusDetail.lectureNo}');
						alert('서명되었습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
        		});
        		
        		$('#managerSignBtn').click(function() {
					let signSyllabusByManager = confirm('정말 서명하시겠습니까?');
					
					if(signSyllabusByManager) {
						location.replace('${pageContext.request.contextPath}/manager/signSyllabusByManager?lectureNo=${syllabusDetail.lectureNo}');
						alert('서명되었습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
        		});
        	});
		</script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>${lectureDetail.lectureName} 강의계획서 정보</h1>
			</div>
		</div>
		
		<div class="container">			
			<!-- 서명 여부 -->
			<div>
				<c:if test="${accountLevel != 1}">
					<table class="table d-flex justify-content-end">
						<tr>
							<td></td>
							<td>서명</td>
							<td>서명일자</td>
						</tr>
						<tr>
							<td>강사</td>
							<td>
								<c:if test="${accountLevel == 2 && syllabusDetail.syllabusTeacherSign == null && syllabusDetail.accountId == accountId}">
									<button type="button" id="teacherSignBtn" class="btn btn-outline-success">
										서명하기
									</button>
								</c:if>
								<c:if test="${syllabusDetail.syllabusTeacherSign != null}">
									${syllabusDetail.syllabusTeacherSign}
								</c:if>
							</td>
							<td>${syllabusDetail.syllabusTeacherSignDate}</td>
						</tr>
						<tr>
							<td>운영자</td>
							<td>
								<c:if test="${accountLevel == 3 && syllabusDetail.syllabusTeacherSign != null && syllabusDetail.syllabusManagerSign == null}">
									<button type="button" id="managerSignBtn" class="btn btn-outline-success">
										서명하기
									</button>
								</c:if>
								<c:if test="${syllabusDetail.syllabusManagerSign != null}">
									${syllabusDetail.syllabusManagerSign}
								</c:if>
							</td>
							<td>${syllabusDetail.syllabusManagerSignDate}</td>
						</tr>
					</table>
				</c:if>
			</div>
			
			<!-- 작성일자, 수정일자 -->
			<div>
				<table class="table">
					<c:if test="${accountLevel != 1}">
						<tr>
							<td>작성자</td>
							<td>${syllabusDetail.syllabusWriter}</td>
						</tr>
						<tr>
							<td>작성일자</td>
							<td>${syllabusDetail.syllabusCreateDate}</td>
						</tr>
						<tr>
							<td>수정일자</td>
							<td>${syllabusDetail.syllabusUpdateDate}</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="2">
							${syllabusDetail.syllabusContent}
						</td>
					</tr>
				</table>
			</div>
			
			<!-- 수정 버튼 -->
			<div class="d-flex justify-content-end mb-3">
				<c:if test="${accountLevel == 2 && syllabusDetail.accountId == accountId}">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/modifySyllabus?lectureNo=${syllabusDetail.lectureNo}">
						수정
					</a>
				</c:if>
			</div>
		</div>
	</body>
</html>