<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>시험 정보</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>시험 정보</h1>
			
			<div>
				<%-- 시험 정보가 생성되지 않았을 경우 --%>
				<c:if test="${test == null}">
					<p>아직 만들어진 시험 정보가 없습니다!</p>
					<p><a href="${pageContext.request.contextPath}/teacher/createTest?lectureNo=${param.lectureNo}">시험정보 생성</a></p>
				</c:if>
				
				<%-- 시험 정보가 생성되었을 경우 --%>
				<c:if test="${test != null}">
					<c:if test="${isEditable}">
						<p><a href="${pageContext.request.contextPath}/teacher/modifyTest?lectureNo=${param.lectureNo}">시험정보 수정</a></p>
					</c:if>
					<div>
						시험 시작일: ${test.testStartDate}
					</div>
					<div>
						시험 종료일: ${test.testEndDate}
					</div>
					<div>
						시험 내용: ${test.testContent}
					</div>
					
					<c:if test="${isEditable}">
						<p><a href="${pageContext.request.contextPath}/teacher/createMultipleChoice?lectureNo=${param.lectureNo}">시험문제 추가</a></p>
					</c:if>
					<c:if test="${multipleChoiceListSize == 0}">
						<span>
							시험 문제가 없습니다! 위의 시험문제 추가 버튼을 눌러 시험문제를 추가해주세요!
						</span>
					</c:if>
					<c:forEach var="mc" items="${multipleChoiceList}">
						<hr>
						<p>${mc.multipleChoiceId}. ${mc.multipleChoiceQuestion} (정답: ${mc.multipleChoiceAnswer})</p>
						
						<c:forEach var="mce" items="${mc.multipleChoiceExampleList}">
							<div>${mce.multipleChoiceExampleId}| ${mce.multipleChoiceExampleContent}</div>
						</c:forEach>
						<c:if test="${isEditable}">
							<p>
								<a href="${pageContext.request.contextPath}/teacher/modifyMultipleChoice?multipleChoiceNo=${mc.multipleChoiceNo}">시험문제 수정</a>
								<a href="${pageContext.request.contextPath}/teacher/removeMultipleChoice?multipleChoiceNo=${mc.multipleChoiceNo}">시험문제 삭제</a>
							</p>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</body>
</html>