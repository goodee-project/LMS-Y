<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FAQ 수정</title>
		
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
		<div class="jumbotron">
			<h1>자주하는 질문 (FAQ)수정</h1>
		</div>	
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/modifyFAQ?faqNo=${faq.faqNo}">
					<table class=table  >
					<tr>
						<td>FAQ 번호</td>
						<td><input type="text" name="faqNo" value="${faq.faqNo}" readonly="readonly"></td>
					</tr>
				   
					<tr>
						<td> 계정 id</td>
						<td><input type="text" name="accountId" value="${faq.accountId}"></td>
					</tr>
					
					<tr>
						<td>FAQ 작성자</td>
						<td><input type="text" name="faqWriter" value="${faq.faqWriter}"></td>
					</tr>
					
					<tr>
						<td>FAQ 제목</td>
						<td><input type="text" name="faqTitle" value="${faq.faqTitle}"></td>
					</tr>
					
					<tr>
						<td>FAQ 내용</td>
						<td><input type="text" name="faqContent" value="${faq.faqContent}"></td>
					</tr>
					<tr>
						<td>FAQ 카테고리</td>
						<td>
							<select name="faqCategory">
								<c:forEach items="${categoryList}" var="cl">	
									<option value="${cl.faqCategory}">${cl.faqCategory}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					</table>
			  
			  		<button type="submit">입력</button>
				</form>
			</div>
		</div>
	</body>
</html>