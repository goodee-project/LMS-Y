<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의계획서 정보</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>강의계획서 정보</h1>
			
			<!-- 서명 여부 -->
			<div>
				<c:if test="${accountLevel != 1}">
					<table class="table">
						<tr>
							<td></td>
							<td>서명</td>
							<td>서명일자</td>
							<td>서명하기</td>
						</tr>
						<tr>
							<td>강사</td>
							<td>${syllabusDetail.syllabusTeacherSign}</td>
							<td>${syllabusDetail.syllabusTeacherSignDate}</td>
							<td>
								<c:if test="${accountLevel == 2 && syllabusDetail.syllabusTeacherSign == null}">
									<a href="${pageContext.request.contextPath}/teacher/signSyllabusByTeacher?syllabusNo=${syllabusDetail.syllabusNo}">
										[서명]
									</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>운영자</td>
							<td>${syllabusDetail.syllabusManagerSign}</td>
							<td>${syllabusDetail.syllabusManagerSignDate}</td>
							<td>
								<c:if test="${accountLevel == 3 && syllabusDetail.syllabusTeacherSign != null && syllabusDetail.syllabusManagerSign == null}">
									<a href="${pageContext.request.contextPath}/manager/signSyllabusByManager?syllabusNo=${syllabusDetail.syllabusNo}">
										[서명]
									</a>
								</c:if>
							</td>
						</tr>
					</table>
				</c:if>
			</div>
			
			<!-- 수정, 서명 버튼 -->
			<div>
				<c:if test="${accountLevel == 2 && syllabusDetail.accountId == accountId}">
					<a href="${pageContext.request.contextPath}/teacher/modifySyllabus?syllabusNo=${syllabusDetail.syllabusNo}">
						[수정]
					</a>
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
		</div>
	</body>
</html>