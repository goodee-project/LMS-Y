<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${lectureDetail.lectureName} 강의계획서</title>
		
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
		
		<!-- 강사 강좌 메뉴 인클루드 -->
		<c:if test="${accountLevel == 2}">		
			<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		</c:if>
		
		<!-- 학생 강좌 메뉴 인클루드 -->
		<c:if test="${accountLevel == 1}">	
			<jsp:include page="/WEB-INF/view/inc/stmgr-menu.jsp"></jsp:include>
		</c:if>
		
		<div class="jumbotron">
			<div class="container">
				<h1>${lectureDetail.lectureName} 강의계획서</h1>
			</div>
		</div>
		
		<div class="container">	
			<c:if test="${syllabusDetail == null}">
				<c:if test="${accountLevel == 2 && lectureDetail.accountId == accountId}">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/teacher/createSyllabus?lectureNo=${lectureDetail.lectureNo}">
						작성
					</a>
				</c:if>
				
				<div class="d-flex justify-content-center">
					작성된 강의계획서가 없습니다.
				</div>
			</c:if>
			
			<c:if test="${syllabusDetail != null}">
				<!-- 서명 여부 -->
				<div>
					<c:if test="${accountLevel != 1}">
						<table class="table d-flex justify-content-end">
							<tr class="text-center">
								<td></td>
								<th>서명</th>
								<th>서명일자</th>
							</tr>
							<tr>
								<th>강사</th>
								<td class="text-center" width="150">
									<c:if test="${accountLevel == 2 && syllabusDetail.syllabusTeacherSign == null && syllabusDetail.accountId == accountId}">
										<button type="button" id="teacherSignBtn" class="btn btn-outline-success btn-sm">
											서명하기
										</button>
									</c:if>
									<c:if test="${syllabusDetail.syllabusTeacherSign != null}">
										${syllabusDetail.syllabusTeacherSign}
									</c:if>
								</td>
								<td width="150">${syllabusDetail.syllabusTeacherSignDate}</td>
							</tr>
							<tr>
								<th>운영자</th>
								<td class="text-center" width="150">
									<c:if test="${accountLevel == 3 && syllabusDetail.syllabusTeacherSign != null && syllabusDetail.syllabusManagerSign == null}">
										<button type="button" id="managerSignBtn" class="btn btn-outline-success btn-sm">
											서명하기
										</button>
									</c:if>
									<c:if test="${syllabusDetail.syllabusManagerSign != null}">
										${syllabusDetail.syllabusManagerSign}
									</c:if>
								</td>
								<td width="200">${syllabusDetail.syllabusManagerSignDate}</td>
							</tr>
						</table>
					</c:if>
				</div>
				
				<!-- 작성일자, 수정일자 -->
				<div>
					<table class="table">
						<tr>
							<th>작성자</th>
							<td>${syllabusDetail.syllabusWriter}</td>
						</tr>
						<tr>
							<th>작성일자</th>
							<td>${syllabusDetail.syllabusCreateDate}</td>
						</tr>
						<tr>
							<th>수정일자</th>
							<td>${syllabusDetail.syllabusUpdateDate}</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<c:forEach var="syllabusFile" items="${syllabusDetail.syllabusFileList}">
									<%-- 파일 사이즈가 0 이상일 때만 보여줌 --%>
									<c:if test="${syllabusFile.syllabusFileSize > 0}">
										<a href="${pageContext.request.contextPath}/teacher/downloadSyllabusFile?syllabusFileUUID=${syllabusFile.syllabusFileUUID}">
											${syllabusFile.syllabusFileOriginal}
										</a>
										<span class="small">${syllabusFile.syllabusFileSize} byte / ${syllabusFile.syllabusFileCount}회 다운로드</span>
										<br>
									</c:if>
									<c:if test="${syllabusFile.syllabusFileUUID == null}">
										첨부된 파일이 없습니다
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
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
			</c:if>
		</div>
	</body>
</html>