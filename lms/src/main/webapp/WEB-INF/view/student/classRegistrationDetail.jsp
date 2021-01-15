<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강사강좌조회상세보기</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<c:choose>
			<c:when test="${classRegistrationNoCount==1}">
				<!-- 강좌 메뉴 인클루드 -->
				<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<div class=jumbotron>
			<div class="container">
				<h1>강좌조회</h1>
			</div>
		</div>
		
		<div class=container>
			<table class="table">
				<thead>
					<tr class="text-center">
						<th>No.</th>
						<th>강사 이름</th>
						<th>강좌 이름</th>
						<th>과목 이름</th>
						<th>강좌 개강일</th>
						<th>강좌 종강일</th>
						<c:choose>
							<c:when test="${classRegistrationNoCount==1}">
							</c:when>
							<c:otherwise>
								<th class="text-center">
									강의계획서
								</th>
							</c:otherwise>
						</c:choose>
						<th class="text-center">
							<c:choose>
								<c:when test="${classRegistrationNoCount==1}">
									출석 확인
								</c:when>
								<c:otherwise>
									수강 신청
								</c:otherwise>
							</c:choose>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td class="align-middle">${classRegistration.lectureInfo.lectureNo}</td>
							<td class="align-middle">${classRegistration.lectureInfo.teacherName}</td>
							<td class="align-middle">${classRegistration.lectureInfo.lectureName}</td>
							<td class="align-middle">${classRegistration.subject.subjectName}</td>
							<td class="align-middle">${classRegistration.lectureInfo.lectureStartDate}</td>
							<td class="align-middle">${classRegistration.lectureInfo.lectureEndDate}</td>
							<c:choose>
							<c:when test="${classRegistrationNoCount==1}">
							</c:when>
							<c:otherwise>
								<td>
									<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/syllabusDetail?lectureNo=${classRegistration.lectureInfo.lectureNo}">강의계획서</a>
								</td>
							</c:otherwise>
						</c:choose>
							<td>
							<c:choose>
								<c:when test="${classRegistrationNoCount==1}">
									<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/studentAttendanceList?accountId=${accountId}&lectureNo=${classRegistration.lectureInfo.lectureNo}">출석확인</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/student/classRegistrationChoose?lectureNo=${classRegistration.lectureInfo.lectureNo}&accountId=${accountId}">신청하기</a>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>