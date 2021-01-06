<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>객관식 문제 수정</title>
		
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
		<!-- 강좌 메뉴 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/lectmgr-menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>객관식 문제 수정</h1>
			
			<div>
				<form id="multipleChoiceForm" method="post" action="${pageContext.request.pathInfo}">
					<input type="hidden" name="multipleChoiceNo" value="${param.multipleChoiceNo}">
					
					<div>
						문제 내용: <input id="multipleChoiceQuestion" class="form-control" type="text" name="multipleChoiceQuestion" value="${multipleChoice.multipleChoiceQuestion}">
					</div>
					<div>
						정답:
						<div class="row">
							<div class="col">
								<c:if test="${multipleChoice.multipleChoiceAnswer == '1'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="1" checked="checked"> 1번
								</c:if>
								<c:if test="${multipleChoice.multipleChoiceAnswer != '1'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="1"> 1번
								</c:if>
							</div>
							<div class="col">
								<c:if test="${multipleChoice.multipleChoiceAnswer == '2'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="2" checked="checked"> 2번
								</c:if>
								<c:if test="${multipleChoice.multipleChoiceAnswer != '2'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="2"> 2번
								</c:if>
							</div>
							<div class="col">
								<c:if test="${multipleChoice.multipleChoiceAnswer == '3'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="3" checked="checked"> 3번
								</c:if>
								<c:if test="${multipleChoice.multipleChoiceAnswer != '3'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="3"> 3번
								</c:if>
							</div>
							<div class="col">
								<c:if test="${multipleChoice.multipleChoiceAnswer == '4'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="4" checked="checked"> 4번
								</c:if>
								<c:if test="${multipleChoice.multipleChoiceAnswer != '4'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="4"> 4번
								</c:if>
							</div>
							<div class="col">
								<c:if test="${multipleChoice.multipleChoiceAnswer == '5'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="5" checked="checked"> 5번
								</c:if>
								<c:if test="${multipleChoice.multipleChoiceAnswer != '5'}">
									<input class="multipleChoiceAnswer" type="radio" name="multipleChoiceAnswer" value="5"> 5번
								</c:if>
							</div>
						</div>
					</div>
					<div>
						1번 보기: <input id="multipleChoiceExample1" class="form-control" type="text" name="multipleChoiceExampleList" value="${multipleChoiceExample1}">
					</div>
					<div>
						2번 보기: <input id="multipleChoiceExample2" class="form-control" type="text" name="multipleChoiceExampleList" value="${multipleChoiceExample2}">
					</div>
					<div>
						3번 보기: <input id="multipleChoiceExample3" class="form-control" type="text" name="multipleChoiceExampleList" value="${multipleChoiceExample3}">
					</div>
					<div>
						4번 보기: <input id="multipleChoiceExample4" class="form-control" type="text" name="multipleChoiceExampleList" value="${multipleChoiceExample4}">
					</div>
					<div>
						5번 보기: <input id="multipleChoiceExample5" class="form-control" type="text" name="multipleChoiceExampleList" value="${multipleChoiceExample5}">
					</div>
					<hr>
					<div>
						<button id="submitMultipleChoiceForm" type="button">수정</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>