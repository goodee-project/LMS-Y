<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>교재 목록</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>교재 목록</h1>
			</div>
		</div>
			
		<div class="container">
			<!-- 추가 버튼 -->
			<div>
				<c:if test="${accountLevel == 3}">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/createTextbook">
						추가
					</a>
				</c:if>
			</div>
			
			<!-- 검색 바 -->
			<div class="mb-3">
				<form method="get" id="searchForm" action="${pageContext.request.pathInfo}">
					<div class="d-flex justify-content-end input-group">
						<!-- 검색조건 -->
						<div class="input-group-prepend">
							<select id="searchType" name="searchType" class="form-control" style="width:110px;">
								<option value="all"
									<c:if test="${searchType == 'all'}">selected</c:if>>전체</option>
								<option value="ISBN"
									<c:if test="${searchType == 'ISBN'}">selected</c:if>>ISBN</option>
								<option value="title"
									<c:if test="${searchType == 'title'}">selected</c:if>>교재명</option>
								<option value="writer"
									<c:if test="${searchType == 'writer'}">selected</c:if>>저자</option>
								<option value="publisher"
									<c:if test="${searchType == 'publisher'}">selected</c:if>>출판사</option>
							</select>
						</div>
						
						<!-- 검색어 입력 -->
						<input type="text" id="searchKeyword" name="searchKeyword" class="form-control col-sm-2" value="${searchKeyword}" placeholder="Search">
						
						<!-- 검색 버튼 -->
						<div class="input-group-append">
							<button type="submit" class="form-control btn btn-success">
								검색
							</button>
						</div>
					</div>
				</form>
			</div>
			
			<!-- 내용 -->
			<div>
				<table class="table">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>교재명</th>
							<th>저자</th>
							<th>출판사</th>
						</tr>
					</thead>
					
					<tbody>
						<!-- 검색된 항목이 있을 시 출력 -->
						<c:if test="${lastPage != 0}">
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
						</c:if>
						
						<!-- 검색된 항목이 없을 시 출력 -->
						<c:if test="${lastPage == 0}">
							<tr>
								<td colspan="4">
									<div class="d-flex justify-content-center">
										검색된 항목이 없습니다
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<!-- 페이지 네비게이션 바 -->
			<!-- 검색된 항목이 있을 시에만 출력 -->
			<c:if test="${lastPage != 0}">
				<div class="d-flex justify-content-center">
					<ul class="pagination small">
						<%-- 처음 버튼 --%>
						<c:if test="${currentPage != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=1&searchType=${searchType}&searchKeyword=${searchKeyword}">
									&lt;&lt;
								</a>
							</li>
						</c:if>
						<c:if test="${currentPage == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									&lt;&lt;
								</a>
							</li>
						</c:if>
						
						<%-- 이전 버튼 --%>
						<c:if test="${pageNaviBegin != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${pageNaviBegin-1}&searchType=${searchType}&searchKeyword=${searchKeyword}">
									&lt;
								</a>
							</li>
						</c:if>
						<c:if test="${pageNaviBegin == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									&lt;
								</a>
							</li>
						</c:if>
						
						<%-- 각 페이지 이동 버튼 --%>
						<c:forEach var="p" begin="${pageNaviBegin}" end="${pageNaviEnd}" step="1">
							<c:if test="${p != currentPage}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${p}&searchType=${searchType}&searchKeyword=${searchKeyword}">
										${p}
									</a>
								</li>
							</c:if>
							<c:if test="${p == currentPage}">
								<li class="page-item active">
									<a class="page-link">
										${p}
									</a>
								</li>
							</c:if>
						</c:forEach>
						
						<%-- 다음 버튼 --%>
						<c:if test="${pageNaviEnd != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${pageNaviEnd+1}&searchType=${searchType}&searchKeyword=${searchKeyword}">
									&gt;
								</a>
							</li>
						</c:if>
						<c:if test="${pageNaviEnd == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									&gt;
								</a>
							</li>
						</c:if>
						
						<%-- 마지막 버튼 --%>
						<c:if test="${currentPage != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${lastPage}&searchType=${searchType}&searchKeyword=${searchKeyword}">
									&gt;&gt;
								</a>
							</li>
						</c:if>
						<c:if test="${currentPage == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									&gt;&gt;
								</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
	</body>
</html>