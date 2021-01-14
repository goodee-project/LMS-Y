<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강사</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script>
			$(document).ready(function() {
				function showChart() {
					$.ajax({
						url : '${pageContext.request.contextPath}/teacher/reportSubmitRateByLecture?lectureNo='+${lectureNo},
						type : 'get',
						success : function(data){
						    $('#reportChart').empty();
						    $('#reportChart').append('<canvas id="chart-bar1" class="chart-canvas"></canvas>');
						    if(data.length > 0) {
						        let myLabels = [];
						        let myData = [];
						        let myBackgroundColor = [];
						        let myBorderColor = [];

						        $(data).each(function(key, value) {
						            myLabels.push(value.reportTitle + ' ( % ) ');
						            myData.push(value.submitRate);
						            
						            let ranDegree = Math.floor(Math.random()*360);
						            myBackgroundColor.push("hsl(" + ranDegree + ", 100%, 75%)");
						            myBorderColor.push("hsl(" + ranDegree + ", 100%, 50%)");
						        });
						        let ctx = $("#chart-bar1").get(0).getContext('2d');
						        let chart = new Chart(ctx, {
						            type: 'bar',
						            data: {
						                labels: myLabels,
						                datasets: [{
						                    label:'과제 평균 점수',
						                    backgroundColor: myBackgroundColor,
						                    borderColor: myBorderColor,
						                    data: myData,
						                    borderWidth: 1
						                }]
						            },
						            options: {
						                scales: {
						                    yAxes: [{
						                        ticks: {
						                            beginAtZero: true
						                        }
						                    }]
						                }
						            }
						        });        
						    } else {
						        $('#reportChart').html('과제가 없습니다.');
						    }
						}
					});
					$.ajax({
						url : '${pageContext.request.contextPath}/teacher/testAnswerRateByLecture?lectureNo='+${lectureNo},
						type : 'get',
						success : function(data){
						    $('#testChart').empty();
						    $('#testChart').append('<canvas id="chart-bar2" class="chart-canvas"></canvas>');
						    if(data.length > 0) {
						        let myLabels = [];
						        let myData = [];
						        let myBackgroundColor = [];
						        let myBorderColor = [];

						        $(data).each(function(key, value) {
						            myLabels.push(value.multiplechoiceId + '번');
						            myData.push(value.answerRate);
						            
						            let ranDegree = Math.floor(Math.random()*360);
						            myBackgroundColor.push("hsl(" + ranDegree + ", 100%, 75%)");
						            myBorderColor.push("hsl(" + ranDegree + ", 100%, 50%)");
						        });
						        let ctx = $("#chart-bar2").get(0).getContext('2d');
						        let chart = new Chart(ctx, {
						            type: 'bar',
						            data: {
						                labels: myLabels,
						                datasets: [{
						                    label:'문제별 정답률',
						                    backgroundColor: myBackgroundColor,
						                    borderColor: myBorderColor,
						                    data: myData,
						                    borderWidth: 1
						                }]
						            },
						            options: {
						                scales: {
						                    yAxes: [{
						                        ticks: {
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
	</head>
	<body>
		<!-- 부트스트랩(CSS) 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>강사 인덱스</h1>
			</div>
		</div>
		<div class="container">
			<form action="${pageContext.request.pathInfo}" method="get">
				<select name="lectureNo">
					<c:forEach var="l" items="${lectureList}">
						<option value="${l.lectureNo}" 
							<c:if test="${param.lectureNo==l.lectureNo}">
								selected="selected"
							</c:if>
						>${l.lectureName}</option>
					</c:forEach>
				</select>
				<button class="btn btn-primary" type="submit">변경</button>
			</form>
			<div class="row">
				<div class="col">
					<div id="reportChart"></div>
				</div>
				<div class="col">
					<div id="testChart"></div>
				</div>
			</div>
		</div>
	</body>
</html>