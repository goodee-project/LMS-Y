<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>[여기에 이 페이지의 특징을 잘 살린 제목을 넣어주세요]</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 차트
            	$.ajax({
        			url:'${pageContext.request.contextPath}/#',
        			type:'GET or POST',
        			data:{'매개 data'},
        			success:function(data) {
        				var ctx = $('#chart');
        				myChart = new Chart(ctx, {
        					type: '차트 종류',
        				    data: {
        				        labels: [],
        				        datasets: [{
        				            label: '성적 통계',
        				            backgroundColor: [],
        				            borderColor: [],
        				            data: []
        				        }]
        				    },
        				    options: {}
        			        }
        				});
        			}
        		});
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>[여기에 이 페이지의 특징을 잘 살린 제목을 넣어주세요]</h1>
			</div>
		</div>
		
		<div class="container">	
			<!-- chart 출력-->
			<div>
				<canvas id="chart"></canvas>
			</div>
		</div>
	</body>
</html>