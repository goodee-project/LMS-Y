<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자주하는 질문 상세보기</title>
		
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
	
		<div class="jumbotron">
			<div class=container>
		
				<h1>자주하는 질문 상세보기</h1>
			</div>	
			
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
						<td height="150" >${faq.faqContent}</td>
					</tr>
			</table>
			<!-- 작성할때 acoountId 와 FAQ에 등록된 accountId가 다를경우 버튼 x -->
			<c:if test="${accountId eq faq.accountId}">
				<div class="d-flex justify-content-end" >
					<a id="testBtnId" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyFAQ?faqNo=${faq.faqNo}">수정</a>
					<a id="delBtnId" class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/removeFAQ?faqNo=${faq.faqNo}">삭제</a>
				</div>
			</c:if>
		</div>
	</body>
</html>