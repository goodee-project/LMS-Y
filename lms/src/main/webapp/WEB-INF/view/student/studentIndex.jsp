<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 인덱스</title>
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
						            label: '강좌 출결 그래프',
						            backgroundColor: [
							            'rgba(255, 99, 132, 0.5)',
							            'rgba(54, 162, 235, 0.5)', 
							            'rgba(255, 206, 86, 0.5)', 
							            'rgba(75, 192, 192, 0.5)', 
							            'rgba(153, 102, 255, 0.5)', 
							            'rgba(255, 159, 64, 0.5)'],
						            borderColor: ['rgb(255, 99, 132,1.5)', 
							            'rgba(54, 162, 235, 1.5)', 
							            'rgba(255, 206, 86, 1.5)', 
							            'rgba(75, 192, 192, 1.5)', 
							            'rgba(153, 102, 255, 1.5)', 
							            'rgba(255, 159, 64, 1.5)'],
						            data: [data.출석, data.결석, data.외출, data.조퇴, data.지각],
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
					    if(data.length > 0) {
					        let myLabels = [];
					        let myData = [];
					        let myBackgroundColor = [];
					        let myBorderColor = [];

					        $(data).each(function(key, value) {
					            myLabels.push(value.reportTitle);
					            myData.push(value.reportScore);
					            
					            let ranDegree = Math.floor(Math.random()*360);
					            myBackgroundColor.push("hsl(" + ranDegree + ", 100%, 75%)");
					            myBorderColor.push("hsl(" + ranDegree + ", 100%, 50%)");
					        });
					        let ctx = $("#reportChart").get(0).getContext('2d');
					        let chart = new Chart(ctx, {
					            type: 'horizontalBar',
					            data: {
					                labels: myLabels,
					                datasets: [{
					                    label:'과제별 점수',
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
												suggestedMax: 100,
												beginAtZero: true
											}
										}]
									}
					            }
					        });        
					    } else {
					        $('#testChart').html('평가가 없습니다.');
					    }
					}
				});
			}
			// 처음 접근시 차트 생성
			showChart();
	
			// 강좌 선택시 차트 생성
			$('#lecture').change(function(){
				showChart();
			});
		});
	</script>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>		
		
		<div class="jumbotron">
			<div class=container>
				<h1>학생 인덱스</h1>
			</div>
		</div>
		
		<div class=container>
			<!-- 출석 차트 출력 -->
			<div>
				<h2><span class="badge badge-pill badge-info">출석 통계그래프</span></h2>
				<div class="form-group d-flex justify-content-end">
					<select class="form-control col-3" name="lecture">
						<c:forEach var="l" items="${lectureList}">
							<option value="${l.lectureNo}">${l.lectureName}</option>
						</c:forEach>
					</select>
					<div class="input-group-append">
					    <button class="btn btn-success" type="submit" id="search">검색</button>
					</div>
				</div>
				<div>
					<canvas id="attendanceChart"></canvas><br>
				</div>
			</div>			
			<!-- 출석 차트 출력 -->
			<div>
				<h2><span class="badge badge-pill badge-info mt-5">과제 통계그래프</span></h2>
				<div>
					<canvas id="reportChart"></canvas>
				</div>
			</div>
		</div>
	</body>
</html>