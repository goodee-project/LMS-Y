<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 인덱스</title>
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script>
			$(document).ready(function() {
				var attendanceChart;
				var reportChart;
				
				function showChart() {
					// 인원수 그래프
					$.ajax({
						url: '${pageContext.request.contextPath}/manager/teacherAndStudentCountChart',
						type: 'get',
						success: function(data){
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
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class=container>
				<h1>운영자 인덱스</h1>
			</div>
		</div>
		
		<div class=container>
			<!-- 인원수 차트 -->
			<div>
				<h2><span class="badge badge-pill badge-info mt-5">강사, 학생 인원수</span></h2>
				<div>
					<canvas id="reportChart"></canvas><br>
					<table class="table text-center">
						<thead class="thead-light">
							<tr>
								<c:forEach var="rl" items="${reportList}">
									<th>${rl.reportTitle}</th>
								</c:forEach>
								<th>강사 인원수</th>
								<th>학생 인원수</th>
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
				</div>
			</div>
		</div>
	</body>
</html>