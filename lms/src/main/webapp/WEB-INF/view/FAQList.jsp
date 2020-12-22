<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자주하는질문(FAQ)</title>
		
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
			<h1>자주하는질문(FAQ)</h1>
			
	
			
			<div>
				<table border="1">
					<tr>
						<th>FAQ 번호</th>
						<th>FAQ 카테고리</th>
						<th>FAQ 제목</th>
						<th>FAQ 작성자</th>
						<th>FAQ 조회수</th>
						<th>FAQ 수정날짜</th>
						
					</tr>
				<c:forEach items="${faqList}" var="f">
					<tr>
						<td>${f.faqNo}</td>
						<td>${f.faqCategory}</td>
						<td>${f.faqTitle}</td>
						<td>${f.faqWriter}</td>
						<td>${f.faqCount}</td>
						<td>${f.faqUpdateDate}</td>
					
						
					</tr>
					
				
				</c:forEach>	
	
				
			</table>
			
				
			</div>
		</div>
	</body>
</html>