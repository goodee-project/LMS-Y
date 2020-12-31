<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>객관식 문제 생성</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#submitMultipleChoiceForm').click(function() {
					if ($('#multipleChoiceQuestion').val() == '') {
						alert('문제 내용을 입력해주세요');
						$('#multipleChoiceQuestion').focus();
					} else if ($('.multipleChoiceAnswer:checked').length == 0) {
						alert('문제의 정답을 기입해주세요');
					} else if ($('#multipleChoiceExample1').val() == '') {
						alert('문제 보기를 빈칸없이 입력해주세요');
						$('#multipleChoiceExample1').focus();
					} else if ($('#multipleChoiceExample2').val() == '') {
						alert('문제 보기를 빈칸없이 입력해주세요');
						$('#multipleChoiceExample2').focus();
					} else if ($('#multipleChoiceExample3').val() == '') {
						alert('문제 보기를 빈칸없이 입력해주세요');
						$('#multipleChoiceExample3').focus();
					} else if ($('#multipleChoiceExample4').val() == '') {
						alert('문제 보기를 빈칸없이 입력해주세요');
						$('#multipleChoiceExample4').focus();
					} else if ($('#multipleChoiceExample5').val() == '') {
						alert('문제 보기를 빈칸없이 입력해주세요');
						$('#multipleChoiceExample5').focus();
					} else {
						$('#multipleChoiceForm').submit();
					}
				});
			});
		</script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>객관식 문제 생성</h1>
			
			<div>
				<form id="multipleChoiceForm" method="post" action="${pageContext.request.pathInfo}">
					<div>
						문제 내용: <input id="multipleChoiceQuestion" class="form-control" type="text" name="multipleChoiceQuestion">
					</div>
					<div>
						정답:
						<div class="row">
							<div class="col">
								<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="1"> 1번
							</div>
							<div class="col">
								<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="2"> 2번
							</div>
							<div class="col">
								<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="3"> 3번
							</div>
							<div class="col">
								<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="4"> 4번
							</div>
							<div class="col">
								<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="5"> 5번
							</div>
						</div>
					</div>
					<div>
						1번 보기: <input id="multipleChoiceExample1" class="form-control" type="text" name="multipleChoiceExampleList">
					</div>
					<div>
						2번 보기: <input id="multipleChoiceExample2" class="form-control" type="text" name="multipleChoiceExampleList">
					</div>
					<div>
						3번 보기: <input id="multipleChoiceExample3" class="form-control" type="text" name="multipleChoiceExampleList">
					</div>
					<div>
						4번 보기: <input id="multipleChoiceExample4" class="form-control" type="text" name="multipleChoiceExampleList">
					</div>
					<div>
						5번 보기: <input id="multipleChoiceExample5" class="form-control" type="text" name="multipleChoiceExampleList">
					</div>
					<hr>
					<div>
						<button id="submitMultipleChoiceForm" type="button">생성</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>