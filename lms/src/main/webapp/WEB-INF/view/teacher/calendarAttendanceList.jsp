<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>출석에 필요한 강좌별 출석 캘린더 목록</title>
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class=container>
			<div class="jumbotron">
				<h1>출석 달력</h1>
			</div>
		</div>
		
		<div class=container>
			<table class="attendanceTableMenu">
				<tr>
					<td width="15%" style="text-align: right;">	
						<a class="btn btn-dark" href="${pageContext.request.contextPath}/teacher/calendarAttendanceList?lectureNo=${param.lectureNo}&&currentYear=${currentYear}&&currentMonth=${currentMonth-1}">←</a>
					</td>
					<td width="30%">
						<h3>${currentYear}년 ${currentMonth}월</h3>
					</td>
					<td width="15%" style="text-align: left;">
						<a class="btn btn-dark" href="${pageContext.request.contextPath}/teacher/calendarAttendanceList?lectureNo=${param.lectureNo}&&currentYear=${currentYear}&&currentMonth=${currentMonth+1}">→</a>
					</td>
				</tr>
			</table>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="sunday">일</th>		
						<th>월</th>		
						<th>화</th>		
						<th>수</th>		
						<th>목</th>		
						<th>금</th>		
						<th>토</th>		
					</tr>
				</thead>
				<tbody>
					<tr>
						<!--for문으로 1~31까지 -->
						<c:forEach var="i" begin="1" end="${lastDay+(firstDayOfWeek-1) }" step="1">
							<c:if test="${i-(firstDayOfWeek-1) < 1 }">
								<td>&nbsp;</td>
							</c:if>
							<c:if test="${i-(firstDayOfWeek-1) > 0 }">
								<td>
									<!-- 일요일 -->
									<c:if test="${i % 7 == 1}">
										<div class="sunday">
											<a href="${pageContext.request.contextPath}/teacher/calendarAttendanceListOne?lectureNo=${param.lectureNo}&&target=sunday&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${i-(firstDayOfWeek-1)}">${i-(firstDayOfWeek-1)}</a>
										</div>
									</c:if>
									
									<!-- 토요일 -->
									<c:if test="${i % 7 == 0}">
										<div class="saturday">
											<a href="${pageContext.request.contextPath}/teacher/calendarAttendanceListOne?lectureNo=${param.lectureNo}&&target=saturday&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${i-(firstDayOfWeek-1)}">${i - (firstDayOfWeek - 1)}</a>
										</div>
									</c:if>
									
									<!-- 평일 (월요일 ~ 금요일) -->
									<c:if test="${i % 7 != 1 && i % 7 != 0}">
										<div class="weekday">
											<a href="${pageContext.request.contextPath}/teacher/calendarAttendanceListOne?lectureNo=${param.lectureNo}&&target=weekday&&currentYear=${currentYear}&&currentMonth=${currentMonth}&&currentDay=${i-(firstDayOfWeek-1)}">${i - (firstDayOfWeek - 1)}</a>
										</div>
									</c:if>
								</td>
								</c:if>
							<c:if test="${i%7 == 0}">
								<tr></tr>
							</c:if>
						</c:forEach>
						
						<c:if test="${(lastDay+(firstDayOfWeek-1)) % 7 !=0}">
							<c:forEach begin="1" end="${7- ((lastDay+(firstDayOfWeek-1)) % 7)}" step="1">
								<td>&nbsp;</td>
							</c:forEach>
						</c:if>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>