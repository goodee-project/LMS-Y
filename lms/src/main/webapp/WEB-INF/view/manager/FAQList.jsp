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
		<div class="jumbotron">
			<h1>자주하는질문(FAQ)</h1>
			</div>
			<div> 종류</div>
				<table class="table">
			<!-- 카테고리 -->		
			<c:forEach items="${categoryList}" var="cl" varStatus="status"> 
			<td>${cl.faqCategory}</td>
				<c:if test="${status.count%5 eq 0}"> 
					<tr>
				</c:if>
				</c:forEach>
				
			</table>
			
			<div>
			<!-- FAQList -->
				<table class="table">
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
						<td><a href="${pageContext.request.contextPath}/manager/FAQDetail?faqNo=${f.faqNo}">${f.faqNo}</a></td>
						<td>${f.faqCategory}</td>
						<td>${f.faqTitle}</td>
						<td>${f.faqWriter}</td>
						<td>${f.faqCount}</td>
						<td>${f.faqUpdateDate}</td>
					</tr>
				</c:forEach>	
	
				
			</table>
			<div>
				<!-- 처음 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/FAQList?currentPage=1">[처음]</a>
					</c:when>
				</c:choose>
				
				<!-- 이전 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/FAQList?currentPage=${currentPage-1}">[이전]</a>
					</c:when>
				</c:choose>
				
				<!-- 현재 페이지 표시 -->
				<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">
									[${i}]
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/manager/FAQList?currentPage=${i}">[${i}]</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				
				<!-- 다음 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/FAQList?currentPage=${currentPage+1}">[다음]</a>
					</c:when>
				</c:choose>
				
				<!-- 마지막 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/FAQList?currentPage=${lastPage}">[마지막]</a>
					</c:when>
				</c:choose>
			</div>
				
			</div>
		</div>
	</body>
</html>