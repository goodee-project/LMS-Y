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
					<table border="1">
						<tr>
							<td></td>
							<td>서명</td>
							<td>서명일자</td>
						</tr>
						<tr>
							<td>강사</td>
							<td>${syllabusDetail.syllabusTeacherSign}</td>
							<td>${syllabusDetail.syllabusTeacherSignDate}</td>
						</tr>
						<tr>
							<td>운영자</td>
							<td>${syllabusDetail.syllabusManagerSign}</td>
							<td>${syllabusDetail.syllabusManagerSignDate}</td>
						</tr>
					</table>
				</c:if>
			</div>
			
			<!-- 수정, 서명 버튼 -->
			<div>
				<c:if test="${syllabusDetail.syllabusManagerSign == NULL}">
					<!-- 강사에게 표시되는 항목 -->
					<c:if test="${accountLevel == 2}">
						<a href="${pageContext.request.contextPath}/teacher/modifySyllabus?syllabusNo=${syllabusDetail.syllabusNo}">
							[수정]
						</a>
						<a href="${pageContext.request.contextPath}/teacher/signSyllabusByTeacher?syllabusNo=${syllabusDetail.syllabusNo}">
							[서명]
						</a>
					</c:if>
					
					<!-- 운영자에게 표시되는 항목 -->
					<c:if test="${accountLevel == 3 && syllabusDetail.syllabusTeacherSign != NULL}">
						<a href="${pageContext.request.contextPath}/manager/signSyllabusByManager?syllabusNo=${syllabusDetail.syllabusNo}">
							[서명]
						</a>
					</c:if>
				</c:if>
			</div>
			
			<!-- 작성일자, 수정일자 -->
			<div>
				<table border="1">
					<tr>
						<td>작성일자</td>
						<td>${syllabusDetail.syllabusCreateDate}</td>
					</tr>
					<tr>
						<td>수정일자</td>
						<td>${syllabusDetail.syllabusUpdateDate}</td>
					</tr>
				</table>
			</div>
			
			<!-- 내용 -->
			<div>
				<table border="1">
					<tr>
						<td>
							${syllabusDetail.syllabusContent}
						</td>
					</tr>				
				</table>
			</div>
		</div>
	</body>
</html>