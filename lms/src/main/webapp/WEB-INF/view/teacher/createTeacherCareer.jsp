<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강사 경력입력</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#createCareerBtn').click(function(){

					//경력내용 유효성검사
					if ($('#careerContent').val().replace(/(\s*)/g, "") == "" ) {
						$('#careerContent').focus();
						alert('경력을 입력해주세요!');
						return;
					}

					//시작날짜 마지막날짜에 대한 유효성검사
					if ($('#careerStartDate').val().replace(/(\s*)/g, "") == "" ) {
						alert('시작일 날짜를 입력해주세요!');
						return;
					}
					if ($('#careerEndDate').val().replace(/(\s*)/g, "") == "" ) {
						alert('끝일 날짜를 입력해주세요!');
						return;
					}

					//날짜 유효성검사에 대한 함수
					 var startDate = $('#careerStartDate').val();
			         var endDate = $('#careerEndDate').val();
			         //-을 구분자로 연,월,일로 잘라내어 배열로 반환
			         var startArray = startDate.split('-');
			         var endArray = endDate.split('-');   
			         //배열에 담겨있는 연,월,일을 사용해서 Date 객체 생성
			         var start_date = new Date(startArray[0], startArray[1], startArray[2]);
			         var end_date = new Date(endArray[0], endArray[1], endArray[2]);
			              //날짜를 숫자형태의 날짜 정보로 변환하여 비교한다.
			         if(start_date.getTime() > end_date.getTime()) {//시작날짜가 종료날짜보다 클경우
			             alert("종료날짜보다 시작날짜가 작아야합니다.");
			             return false;
			         }else if (start_date.getTime() == end_date.getTime()) {//시작날짜와 종료날짜가 같을경우
			             alert("종료날짜보다 시작날짜가 작아야합니다.");
			             return false;
			         }
			         
					$('#createCareerForm').submit();
				});
			});
		</script>
	</head>
	<body>
			<!-- 부트스트랩(CSS) 인클루드 -->
			<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
			<div class="jumbotron">
				<div class="container">
					<h1>경력 추가</h1>
				</div>
			</div>
			<form id="createCareerForm" method="post" action="${pageContext.request.contextPath}/teacher/createTeacherCareer">
				<div><input type="hidden" name="accountId" value="${accountId}" readonly="readonly"></div>
				<div class="container">
					<div class="container jumbotron bg-light">
						<table class="table">
							<tr>
								<td>경력</td>
								<td><input id="careerContent" class="form-control" type="text" name="careerContent"><div id="careerContentCk"></div></td>
							</tr>
							<tr>
								<td>경력 시작일</td>
								<td><input id="careerStartDate" class="form-control" type="date" name="careerStartDate"><div id="careerCk"></div></td>
								
							</tr>
							<tr>
								<td>경력 끝일</td>
								<td><input id="careerEndDate" class="form-control" type="date" name="careerEndDate"><div id="careerCk"></div></td>
								
							</tr>
						</table>
						<div style="text-align:right;">
							<button id="createCareerBtn" class="btn btn-outline-primary" type="button">입력</button>
						</div>
					</div>
				</div>
			</form>
	</body>
</html>