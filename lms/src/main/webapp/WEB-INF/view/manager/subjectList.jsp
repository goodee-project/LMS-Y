<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>과목 목록</title>
		
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
			<h1>과목 목록</h1>
			
			<!-- 내용 -->
			<div>
				<table border="1">
					<thead>
						<tr>
							<th>고유번호</th>
							<th>과목명</th>
							<th>총 이수일수</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="sl" items="${subjectList}">
							<tr class="clickable-row" data-href="${pageContext.request.contextPath}/manager/subjectDetail?subjectNo=${sl.subjectNo}">
								<td>${sl.subjectNo}</td>
								<td>${sl.subjectName}</td>
								<td>${sl.subjectTotalDay}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<!-- 페이지 네비게이션 바 -->
			<div>
				<!-- 처음버튼 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/subjectList?currentPage=1">
							[처음]
						</a>
					</c:when>
					<c:otherwise>
						<a href="#">
							[처음]
						</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 이전버튼 -->
				<c:choose>
					<c:when test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/manager/subjectList?currentPage=${currnetPage - 1}">
							[이전]
						</a>
					</c:when>
					<c:otherwise>
						<a href="#">
							[이전]
						</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 현재 페이지 표시 -->
				<c:forEach var="i" begin="${navFirstPage}" end="${navLastPage}">
					<c:if test="${i <= lastPage}">
						<c:choose>
							<c:when test="${i == currentPage}">
								<a href="#">
									[${i}]
								</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/manager/subjectList?currentPage=${i}">
									[${i}]
								</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				
				<!-- 다음버튼 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/subjectList?currentPage=${currnetPage + 1}">
							[다음]
						</a>
					</c:when>
					<c:otherwise>
						<a href="#">
							[다음]
						</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 마지막 버튼 -->
				<c:choose>
					<c:when test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/manager/subjectList?currentPage=${lastPage}">
							[마지막]
						</a>
					</c:when>
					<c:otherwise>
						<a href="#">
							[마지막]
						</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>