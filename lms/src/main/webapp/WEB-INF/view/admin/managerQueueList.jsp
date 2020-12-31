<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 승인대기 중인 운영자 목록</title>
		
		<!-- jQuery 스크립트 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
			// 선택된 검색조건 유지
				$('#searchType').val('${searchType}').prop('selected', ture);
			});
		</script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<h1>회원가입 승인대기 중인 운영자 목록</h1>
			
			<div>
				<table border="1">
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>Email</th>
							<th>핸드폰 번호</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="managerQueueList" items="${managerQueueList}">
							<!-- 검색된 항목이 있을 시 출력 -->
							<c:if test="${managerQueueList.managerEmail != null}">
								<tr>
									<td>${managerQueueList.accountId}</td>
									<td>
										<a href="${pageContext.request.contextPath}/admin/managerQueueDetail?accountId=${managerQueueList.accountId}">
											${managerQueueList.managerName}
										</a>
									</td>
									<td>${managerQueueList.managerEmail}</td>
									<td>${managerQueueList.managerPhone}</td>
								</tr>
							</c:if>
							
							<!-- 검색된 항목이 없을 시 출력 -->
							<c:if test="${managerQueueList.managerEmail == null}">
								<tr>
									<td colspan="4">
										검색된 항목이 없습니다
									</td>
								</tr>
							</c:if>
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
			
			<!-- 검색 바 -->
			<div>
				<form method="get" id="searchForm" action="${pageContext.request.pathInfo}">
					<!-- 검색조건 -->
					<select id="searchType" name="searchType">
						<option value="all">전체</option>
						<option value="name">이름</option>
						<option value="email">Email</option>
						<option value="phone">전화번호</option>
					</select>
					
					<!-- 검색어 입력 -->
					<input type="text" id="searchKeyword" name="searchKeyword" value="${searchKeyword}" placeholder="검색어를 입력하세요">
					
					<!-- 검색 버튼 -->
					<button type="submit">
						검색
					</button>
				</form>
			</div>
		</div>
	</body>
</html>