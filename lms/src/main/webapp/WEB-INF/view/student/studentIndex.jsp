<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대쉬보드</title>
	</head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<script>
		$(document).ready(function() {
			var attendanceChart;
			var reportChart;
			
			function showChart() {
				// 출석 통계 그래프
				$.ajax({
					url:'${pageContext.request.contextPath}/student/attendanceChart?lectureNo='+${lectureNo},
					type:'get',
					data:{lectureNo:$('#lecture').val()},
					success:function(data) {
						if(attendanceChart) {
							// 기존에 그래프 객체가 있으면 삭제
							attendanceChart.destroy();
						}
						var ctx = $('#attendanceChart');
						attendanceChart = new Chart(ctx, {
							type: 'bar',
						    data: {
						        labels: ['출석','결석','외출','조퇴','지각'],
						        datasets: [{
						            label: '강좌 출결 그래프(1일 단위)',
						            backgroundColor: [
						            	'rgba(54, 162, 235, 0.5)', 
							            'rgba(255, 99, 132, 0.5)',
							            'rgba(255, 206, 86, 0.5)', 
							            'rgba(75, 192, 192, 0.5)', 
							            'rgba(153, 102, 255, 0.5)', 
							            'rgba(255, 159, 64, 0.5)'],
						            borderColor: [
						            	'rgba(54, 162, 235, 1.5)',
							            'rgba(255, 99, 132,1.5)', 
							            'rgba(255, 206, 86, 1.5)', 
							            'rgba(75, 192, 192, 1.5)', 
							            'rgba(153, 102, 255, 1.5)', 
							            'rgba(255, 159, 64, 1.5)'],
						            data: [data.attendance, data.absent, data.outing, data.earlyLeave, data.late],
						            borderWidth: 1
						        }]
						    },
						    options: {
						    	scales: {
									yAxes: [{
										display: true,
										ticks: {
											suggestedMax: data.참여일수,
											beginAtZero: true
										}
									}]
								}
							}
						});
					}
				});
				// 과제 제출 통계 그래프
				$.ajax({
					url : '${pageContext.request.contextPath}/student/reportChart?lectureNo='+${lectureNo},
					type : 'get',
					success : function(data){
						// 데이터를 추가하기 위한 작업
				        let myLabels = [];	// 그래프 라벨
				        let myData = [];	// 그래프 데이터
				        let myBackgroundColor = [];	// 그래프 배경
				        let myBorderColor = [];	// 그래프 테두리
						// 데이터 항목 추가하기
				        $(data).each(function(key, value) {
				            myLabels.push(value.reportTitle);	// 라벨 추가(과제 제목)
				            myData.push(value.reportScore);		// 데이터 추가(과제 점수)
				            // 색상값 추가
				            let ranDegree = Math.floor(Math.random()*360);	// 색조 난수(0부터 360까지 색상환 각도)
				            myBackgroundColor.push("hsl(" + ranDegree + ", 100%, 75%)");	// 배경값 추가(색조, 채도, 밝기)
				            myBorderColor.push("hsl(" + ranDegree + ", 100%, 50%)");	// 테두리값 추가(색조, 채도, 밝기)
				        });
				        // 차트 생성 작업
				        let ctx = $("#reportChart").get(0).getContext('2d');
				        let chart = new Chart(ctx, {
				            type: 'horizontalBar',
				            data: {
				                labels: myLabels,
				                datasets: [{
				                    label:'과제별 점수(100점 기준)',
				                    backgroundColor: myBackgroundColor,
				                    borderColor: myBorderColor,
				                    data: myData,
				                    borderWidth: 1
				                }]
				            },
				            options: {
				            	scales: {
									xAxes: [{
										display: true,
										ticks: {
											suggestedMax: 100,	// 최대값
											beginAtZero: true	// 시작값 0
										}
									}]
								}
				            }
				        });
					}
				});
			}
			// 처음 접근시 차트 생성
			showChart();
		});
	</script>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>		
		
		<div class="jumbotron">
			<div class=container>
				<h1>대쉬보드</h1>
			</div>
		</div>
		
		<div class=container>
			<!-- 통계 차트를 위한 강좌 검색 -->
			<div>
				<form action="${pageContext.request.contextPath}/student/index" method="get">
					<div class="form-group d-flex justify-content-end">			
						<select class="form-control col-3" name="lectureNo">
							<c:forEach var="l" items="${lectureList}">
								<option value="${l.lectureNo}" ${lectureNo == l.lectureNo ? 'selected="selected"' : ''}>${l.lectureName}</option>
							</c:forEach>
						</select>
						<div class="input-group-append">
						    <button class="btn btn-success" type="submit" id="search">검색</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 출석 차트 출력 -->
			<div>
				<h2><span class="badge badge-pill badge-info">출석 통계그래프</span></h2>
				<c:if test="${lectureNo == null}">
					<div class="alert alert-info text-center">
						<strong>Info!</strong> 현재 신청한 강좌가 없습니다. <a href="${pageContext.request.contextPath}/student/availableLectureList" class="alert-link"> 신청하기</a>
					</div>
				</c:if>
				<c:if test="${lectureNo != null}">
					<canvas id="attendanceChart"></canvas><br>
					<h5>출석 진행률</h5>
					<div class="progress">
						<div class="progress-bar progress-bar-striped progress-bar-animated" style="width:${attendanceList.attendanceCount*100/attendanceList.subjectTotalday}%"></div>
					</div>
					<table class="table text-center mt-2">
						<thead class="thead-light">
							<tr>
								<th>출석</th>
								<th>결석</th>
								<th>외출</th>
								<th>조퇴</th>
								<th>지각</th>
								<th>참여일수</th>
								<th>총일수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${attendanceList.attendance}</td>
								<td>${attendanceList.absent}</td>
								<td>${attendanceList.outing}</td>
								<td>${attendanceList.earlyLeave}</td>
								<td>${attendanceList.late}</td>
								<td>${attendanceList.attendanceDay}</td>
								<td>${attendanceList.subjectTotalday}</td>
							</tr>
						</tbody>
					</table>
				</c:if>
			</div>						
			<!-- 과제 차트 출력 -->
			<div>
				<h2><span class="badge badge-pill badge-info mt-5">과제 통계그래프</span></h2>
				<div>
					<c:if test="${lectureNo == null}">
						<div class="alert alert-info text-center">
							<strong>Info!</strong> 현재 신청한 강좌가 없습니다. <a href="${pageContext.request.contextPath}/student/availableLectureList" class="alert-link"> 신청하기</a>
						</div>
					</c:if>
					<c:if test="${lectureNo != null}">
						<canvas id="reportChart"></canvas><br>
						<table class="table text-center">
							<thead class="thead-light">
								<tr>
									<c:forEach var="rl" items="${reportList}">
										<th>${rl.reportTitle}</th>
									</c:forEach>
									<th>합계</th>
									<th>총점</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="rl" items="${reportList}">
										<th>${rl.reportScore}</th>
									</c:forEach>
									<td>${sumScore}</td>
									<td>${totalScore}</td>
								</tr>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>