<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>대쉬보드</title>
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
						    $('#reportBody').html('');
						    $('#reportChart').append('<canvas id="chart-bar1" class="chart-canvas"></canvas>');
						    if(data.length > 0) {
						        let myLabels = [];
						        let myData = [];
						        let myBackgroundColor = [];
						        let myBorderColor = [];

						        $(data).each(function(key, value) {
						            myLabels.push(value.reportTitle + ' ( % ) ');
						            myData.push(value.submitRate);
						            $('#reportBody').append(`
										<tr>
											<td>\${value.reportTitle}</td>
											<td>\${value.submitRate}%</td>
										</tr>	
								     `);

						            
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
						        $('#reportChart').html(`
										<div class="alert alert-info text-center">
											<strong>Info!</strong> 현재 제출한 과제가 없습니다. <a href="${pageContext.request.contextPath}/teacher/reportList?lectureNo=${lectureNo}" class="alert-link"> 제출하기</a>
										</div>
								        `);
						        $('#reportTable').remove();
						    }
						}
					});
					$.ajax({
						url : '${pageContext.request.contextPath}/teacher/testAnswerRateByLecture?lectureNo='+${lectureNo},
						type : 'get',
						success : function(data){
						    $('#testChart').empty();
						    $('#testBody').html('');
						    $('#testChart').append('<canvas id="chart-bar2" class="chart-canvas"></canvas>');
						    if(data.length > 0) {
						        let myLabels = [];
						        let myData = [];
						        let myBackgroundColor = [];
						        let myBorderColor = [];

						        $(data).each(function(key, value) {
						            myLabels.push(value.multiplechoiceId + '번');
						            myData.push(value.answerRate);
						            $('#testBody').append(`
											<tr>
												<td>\${value.multiplechoiceId}번</td>
												<td>\${value.answerRate}%</td>
											</tr>	
									     `);
						            
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
						        $('#testChart').html(`
										<div class="alert alert-info text-center">
										<strong>Info!</strong> 현재 출제한 평가가 없습니다. <a href="${pageContext.request.contextPath}/teacher/testDetail?lectureNo=${lectureNo}" class="alert-link"> 제출하기</a>
									</div>
							        `);
						        $('#testTable').remove();
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
				<h1>대쉬보드</h1>
			</div>
		</div>
		<div class="container">
			<c:if test="${lectureNo eq null}">
				<div class="alert alert-info text-center">
					<strong>Info!</strong> 현재 강의하고 있는 강좌가 없습니다.
				</div>
			</c:if>
			<c:if test="${lectureNo ne null}">
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
			<div>
				<h2><span class="badge badge-pill badge-info mt-5">강좌별 과제 제출률</span></h2>
				<div id="reportChart"></div>
				<table id="reportTable" class="table text-center">
					<thead>
						<tr>
							<th>과제명</th>
							<th>제출률(%)</th>
						</tr>
					</thead>
					<tbody id="reportBody">
					</tbody>
				</table>
			</div>
			<div>
				<h2><span class="badge badge-pill badge-info mt-5">강좌별 평가 문제 정답률</span></h2>
				<div id="testChart"></div>
				<table id="testTable" class="table text-center">
					<thead>
						<tr>
							<th>문제</th>
							<th>정답률(%)</th>
						</tr>
					</thead>
					<tbody id="testBody">
					</tbody>
				</table>
			</div>
			</c:if>
		</div>
	</body>
</html>