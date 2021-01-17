<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 자격증입력</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#createLicenseBtn').click(function(){
					//자격증명 유효성검사
					if ($('#licenseName').val().replace(/(\s*)/g, "") == "" ) {
						$('#licenseName').focus();
						alert('자격증명을 입력해주세요!');
						return;
					}
					//자격증 일련번호 유효성검사
					if ($('#licenseNumber').val().replace(/(\s*)/g, "") == "" ) {
						$('#licenseNumber').focus();
						alert('자격증 일련번호를 입력해주세요!');
						return;
					}
					//자격증 발급기관 유효성검사
					if ($('#licenseAgency').val().replace(/(\s*)/g, "") == "" ) {
						$('#licenseAgency').focus();
						alert('자격증 발급기관을 입력해주세요!');
						return;
					}
					//자격증 발급일 유효성검사
					if ($('#licenseGetDate').val().replace(/(\s*)/g, "") == "" ) {
						$('#licenseGetDate').focus();
						alert('자격증 발급날짜를 입력해주세요!');
						return;
					}
					$('#createLicenseForm').submit();
				});
			});
		</script>
	</head>
	<body>
			<!-- 부트스트랩(CSS) 인클루드 -->
			<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
			<div class="jumbotron">
				<div class="container">
					<h1>자격증 추가</h1>
				</div>
			</div>
			<form id="createLicenseForm" method="post" action="${pageContext.request.contextPath}/manager/createManagerLicense">
				<div><input type="hidden" name="accountId" value="${accountId}" readonly="readonly"></div>
				<div class="container">
					<div class="container jumbotron bg-light">
						<table class="table">
							<tr>
								<td>자격증명</td>
								<td><input class="form-control" type="text" name="licenseName" id="licenseName"></td>
							</tr>
							<tr>
								<td>자격증 일련번호</td>
								<td><input class="form-control" type="text" name="licenseNumber" id="licenseNumber"></td>
							</tr>
							<tr>
								<td>자격증 발급기관</td>
								<td><input class="form-control" type="text" name="licenseAgency" id="licenseAgency"></td>
							</tr>
							<tr>
								<td>자격증 발급일</td>
								<td><input class="form-control" type="date" name="licenseGetDate" id="licenseGetDate"></td>
							</tr>
						</table>
						<div style="text-align:right;">
							<button id="createLicenseBtn" class="btn btn-outline-primary" type="button">입력</button>
						</div>
					</div>
				</div>
			</form>
	</body>
</html>