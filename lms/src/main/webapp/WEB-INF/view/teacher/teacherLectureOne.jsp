<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강사강좌조회상세보기</title>
	</head>
	<style>
		hr{
			background-color: black;
		}
	</style>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
	
		
		<div class="jumbotron">
			<div class=container>
				<h1>${lecture.lectureNo}강좌조회</h1>
			</div>
		</div>
		
		<div class=container>
			<h3>강의실 정보</h3>
			<table class="table">
				<thead>
					<tr>
						<th>강좌 번호</th>
						<th>강사 이름</th>
						<th>강좌 이름</th>
						<th>과목 이름</th>
						<th>강좌 개강일</th>
						<th>강좌 종강일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${lecture.lectureNo}</td>
						<td>${lecture.teacherName}</td>
						<td>${lecture.lectureName}</td>
						<td>${lecture.subject.subjectName}</td>
						<td>${lecture.lectureStartDate}</td>
						<td>${lecture.lectureEndDate}</td>
					</tr>
				</tbody>
			</table>
			<br>
			<h3>강의실 기능</h3>
			<table class="table table-border">
				<tr>
					<th>기능</th>
					<th>이동링크</th>
					<th>설명</th>
				</tr>
				<tr>
					<td>출석</td>
					<td><a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/attendanceList?lectureNo=${param.lectureNo}&target=weekDay">출석</a></td>
					<td>
						학생들의 출석관리를 위한 기능페이지
					</td>
				</tr>
				<tr>
					<td>자료실</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/lectureArchive?lectureNo=${param.lectureNo}">자료실</a></td>
					<td>
						강좌의 자료실 입력,수정,읽기가 가능한 기능페이지
					</td>
				</tr>
				<tr>
					<td>공지</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/lectureNotice?lectureNo=${param.lectureNo}">공지</a></td>
					<td>
						강좌의 공지사항 입력,수정,읽기,삭제가 가능한 기능페이지
					</td>
				</tr>
				<tr>
					<td>강의계획서</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/syllabusDetail?lectureNo=${param.lectureNo}">강의계획서</a></td>
					<td>
						강좌의 대한 강의계획서 입력 기능페이지
					</td>
				</tr>
				<tr>
					<td>질문게시판</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/questionList?lectureNo=${param.lectureNo}">질문게시판</a></td>
					<td>
						학생들의 질문에 대한 답변을 할 수 있는 기능페이지
					</td>
				</tr>
				<tr>
					<td>과제</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/reportList?lectureNo=${param.lectureNo}">과제</a></td>
					<td>
						과제 생성 및 수정할 수 있는 기능페이지
					</td>
				</tr>
				<tr>
					<td>시험</td>
					<td><a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/testDetail?lectureNo=${param.lectureNo}">시험</a></td>
					<td>
						시험 및 문제를 추가할 수 있는 기능페이지
					</td>
				</tr>
			</table>
		</div>
		<div class=container>
		<hr>
		</div>
	</body>
</html>