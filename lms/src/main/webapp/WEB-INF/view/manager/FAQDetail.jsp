<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQDetail</title>
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
			
				<h1>FAQ 상세보기</h1>
			
		</div>
		<div class=container>
				<table class="table">
				   
				   <tr>
						<td>FAQ 번호</td>
						<td>${faq.faqNo}</td>
					</tr>
				   
					<tr>
						<td> 계정 id</td>
						<td>${faq.accountId}</td>
					</tr>
					
					<tr>
						<td>FAQ 작성자</td>
						<td>${faq.faqWriter}</td>
					</tr>
					
					<tr>
						<td>FAQ 제목</td>
						<td>${faq.faqTitle}</td>
					</tr>
					
					<tr>
						<td>FAQ 내용</td>
						<td>${faq.faqContent}</td>
					</tr>
					
					<tr>
						<td>FAQ 작성날짜</td>
						<td>${faq.faqCreateDate}</td>
					</tr>
					<tr>
						<td>FAQ 수정날짜</td>
						<td>${faq.faqUpdateDate}</td>
					</tr>
					<tr>
						<td>FAQ 조회수</td>
						<td>${faq.faqCount}</td> 
					</tr>
					
					<tr>
						<td>FAQ 카테고리</td>
						<td>${faq.faqCategory}</td>
					</tr>
					
			
				</table>
			</div>
</body>
</html>