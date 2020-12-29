<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>교재 정보 목록</title>
		
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
			<h1>교재 목록</h1>
			
			<!-- 추가버튼 -->
			<div>
				<a href="${pageContext.request.contextPath}/manager/createTextbook">
					[추가]
				</a>
			</div>
			
			<!-- 내용 -->
			<div>
				<table border="1">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>교재명</th>
							<th>저자</th>
							<th>출판사</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="textbookList" items="${textbookList}">
							<tr>
								<td>${textbookList.textbookISBN}</td>
								<td>
									<a href="${pageContext.request.contextPath}/manager/textbookDetail?textbookISBN=${textbookList.textbookISBN}">
										${textbookList.textbookTitle}
									</a>
								</td>
								<td>${textbookList.textbookWriter}</td>
								<td>${textbookList.textbookPublisher}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<!-- 페이지 네비게이션 바 -->
			<div>
				<ul class="pagination small">
					<%-- 이전 버튼 --%>
					<c:if test="${pageNaviBegin != 1}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${pageNaviBegin-1}">이전</a>
						</li>
					</c:if>
					<c:if test="${pageNaviBegin == 1}">
						<li class="page-item disabled">
							<a class="page-link">이전</a>
						</li>
					</c:if>
					
					<%-- 각 페이지 이동 버튼 --%>
					<c:forEach var="p" begin="${pageNaviBegin}" end="${pageNaviEnd}" step="1">
						<c:if test="${p != currentPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${p}">${p}</a>
							</li>
						</c:if>
						<c:if test="${p == currentPage}">
							<li class="page-item active">
								<a class="page-link">${p}</a>
							</li>
						</c:if>
					</c:forEach>
					
					<%-- 다음 버튼 --%>
					<c:if test="${pageNaviEnd != lastPage}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${pageNaviEnd+1}">다음</a>
						</li>
					</c:if>
					<c:if test="${pageNaviEnd == lastPage}">
						<li class="page-item disabled">
							<a class="page-link">다음</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</body>
</html>