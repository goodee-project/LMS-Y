<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FAQDetail</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
    		$('#delBtnId').click(function() {
				let remove = confirm('정말 삭제하시겠습니까?');
				if(remove) {
					location.replace('${pageContext.request.contextPath}/manager/removeFAQ?faqNo=${faq.faqNo}');
					alert('삭제하였습니다.');
				} else {
					alert('취소하였습니다.');
					return false;
				}
    		});
    	});
        </script>  
</head>
<body>
	<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class=container>
		<h1>자주하는 질문 상세보기</h1>
			<a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/FAQList">목록</a>
		</div>	
		<div class=container>
			
				<table class="table">
				  
				   <tr>
						<td>FAQ 번호</td>
						<td>${faq.faqNo}</td>
					</tr>
				   	<tr>
						<td>FAQ 카테고리</td>
						<td><a href="${pageContext.request.contextPath}/manager/FAQList?categoryFaqSearch=${faq.faqCategory}">${faq.faqCategory}</a></td>
					</tr>
					<tr>
						<td>FAQ 제목</td>
						<td>${faq.faqTitle}</td>
					</tr>
					<tr>
						<td>FAQ 작성자</td>
						<td>${faq.faqWriter}</td>
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
				</table>
			</div>
		<div class="d-flex justify-content-end">
			<a id="testBtnId" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyFAQ?faqNo=${faq.faqNo}">수정</a>
			<a id="delBtnId" class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/removeFAQ?faqNo=${faq.faqNo}">삭제</a>
		</div>	
</body>
</html>