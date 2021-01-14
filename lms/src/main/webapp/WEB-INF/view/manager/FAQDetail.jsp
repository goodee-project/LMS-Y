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
			<div class="jumbotron">
				<h1>자주하는 질문 상세보기</h1>
			</div>	
			<a  class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/FAQList">목록</a>
		</div>	
		<div class=container>
			
				<table class="table table-striped ">
				  
				   <tr>
						<!--FAQ카테고리,FAQ 제목>-->
						<th style="font-size:20px; width: 100%">[${faq.faqCategory}]${faq.faqTitle}</th>
					</tr>
					<tr>
						<!-- FAQ 작성자, 조회 ,수정 날짜-->
						<th><pre>${faq.faqWriter}    <span class="col-md-8" >조회:${faq.faqCount}  ${faq.faqUpdateDate}</span></pre></th>
					</tr>
					<tr >
						<!--  FAQ 내용  -->
						<td  height="150" class="viewcon" >${faq.faqContent}</td>
					</tr>
					<tr>
					</tr>
				</table>
						<div class="d-flex justify-content-end" >
							<a id="testBtnId" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyFAQ?faqNo=${faq.faqNo}">수정</a>
							<a id="delBtnId" class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/removeFAQ?faqNo=${faq.faqNo}">삭제</a>
						</div>
			</div>
</body>
</html>