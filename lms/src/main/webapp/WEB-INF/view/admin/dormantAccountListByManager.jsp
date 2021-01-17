<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자 휴면계정 목록</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
			$(document).ready(function() {
				let currentPage = '<c:out value="${currentPage}"/>';
				let searchType = $('#searchType option:selected').val();
				let searchKeywordUnencode = $('#searchKeyword').val();
				let searchKeyword = encodeURI(searchKeywordUnencode, "UTF-8");
				
				$('button[name=ActiveBtn]').click(function() {
					let accountStateActive = confirm('정말 해당계정을 활성화하시겠습니까?');
					
					if(accountStateActive) {
						let activeAccountId = $('button[name=ActiveBtn]').val();
						location.replace('${pageContext.request.contextPath}/admin/dormantAccountStateActiveByManager?accountId=' + activeAccountId + '&currentPage=' + currentPage + '&searchType=' + searchType + '&searchKeyword=' + encodeURI(searchKeyword, "UTF-8"));
						alert('계정 상태가 활성화로 전환되었습니다.');
					} else {
						alert('취소하였습니다.');
						return;
					}
        		});
			});
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container">
				<h1>운영자 휴면계정 목록</h1>
			</div>
		</div>
		
		<div class="container">
			<!-- 검색 바 -->
			<div class="mb-3">
				<form method="get" id="searchForm" action="${pageContext.request.pathInfo}">
					<div class="d-flex justify-content-end input-group">
						<!-- 검색조건 -->
						<div class="input-group-prepend">
							<select id="searchType" name="searchType" class="form-control" style="width:110px;">
								<option value="all"
									<c:if test="${searchType == 'all'}">selected</c:if>>전체</option>
								<option value="name"
									<c:if test="${searchType == 'name'}">selected</c:if>>이름</option>
								<option value="email"
									<c:if test="${searchType == 'email'}">selected</c:if>>Email</option>
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
							<th>아이디</th>
							<th>이름</th>
							<th>E-mail</th>
							<th>계정 활성화</th>
						</tr>
					</thead>
					
					<tbody>
						<!-- 검색된 항목이 있을 시 출력 -->
						<c:if test="${lastPage != 0}">
							<c:forEach var="dormantAccountListByManager" items="${dormantAccountListByManager}">
								<tr>
									<td>${dormantAccountListByManager.accountId}</td>
									<td>${dormantAccountListByManager.managerName}</td>
									<td>${dormantAccountListByManager.managerEmail}</td>
									<td>
										<button type="button" id="ActiveBtn" name= "ActiveBtn" class="btn btn-outline-success" value="${dormantAccountListByManager.accountId}">
											활성화
										</button>
									</td>
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