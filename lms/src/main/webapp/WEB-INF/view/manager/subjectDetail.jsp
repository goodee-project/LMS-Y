<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 상세정보</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                // code here...
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>과목 상세정보</h1>
			
			<!-- 생성일자, 수정일자 -->
			<div>
				<table border="1">
					<tr>
						<td>생성일자</td>
						<td>${subjectDetail.subjectCreateDate}</td>
					</tr>
					<tr>
						<td>수정일자</td>
						<td>${subjectDetail.subjectUpdateDate}</td>
					</tr>
				</table>
			</div>
			
			<!-- 수정버튼 -->
			<div>
				<a href="${pageContext.request.contextPath}/manager/modifySubject?subjectNo=${subjectDetail.subjectNo}">
					[수정]
				</a>
			</div>
			
			<!-- 내용 -->
			<div>
				<table border="1">
					<tr>
						<td>과목 고유번호</td>
						<td>${subjectDetail.subjectNo}</td>
					</tr>
					<tr>
						<td>과목명</td>
						<td>${subjectDetail.subjectName}</td>
					</tr>
					<tr>
						<td>총 이수일수</td>
						<td>${subjectDetail.subjectTotalDay}</td>
					</tr>
					<tr>
						<td>정보</td>
						<td>${subjectDetail.subjectInfo}</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>