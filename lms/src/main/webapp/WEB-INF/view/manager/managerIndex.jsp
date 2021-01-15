<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 인덱스</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- 차트 스크립트 -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script>
			$(document).ready(function() {
				var teacherAndStudentCountChart;
				
				function showChart() {
					// 인원수 그래프
					$.ajax({
						url: '${pageContext.request.contextPath}/manager/teacherAndStudentCountChart',
						type: 'get',
						success: function(data){
					        // 차트 생성 작업
					        let ctx = $("#teacherAndStudentCountChart");
					        let chart = new Chart(ctx, {
					            type: 'doughnut',
					            data: {
					                labels: ['강사 인원수','학생 인원수'],
					                datasets: [{
					                    label:'인원수',
					                    backgroundColor: [
											'rgba(54, 162, 235, 0.5)',
											'rgba(255, 99, 132, 0.5)'],
					                    borderColor: [
						                    'rgba(54, 162, 235, 0.5)',
											'rgba(255, 99, 132, 0.5)'],
					                    data: [data.teacherCount, data.studentCount],
					                    borderWidth: 1
					                }]
					            },
					            options: {}
					        });
					        
					        $('#teacherCount').text(data.teacherCount+'명');
					        $('#studentCount').text(data.studentCount+'명');
					        $('#totalCount').text((data.teacherCount + data.studentCount) + '명');
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
					<canvas id="teacherAndStudentCountChart"></canvas><br>
					<table class="table text-center">
						<thead class="thead-light">
							<tr>
								<th>강사 인원수</th>
								<th>학생 인원수</th>
								<th>총 인원수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="teacherCount"></td>
								<td id="studentCount"></td>
								<td id="totalCount"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>