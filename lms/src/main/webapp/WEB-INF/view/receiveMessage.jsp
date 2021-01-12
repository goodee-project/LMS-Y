<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>받은 쪽지함</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        	$(document).ready(function() {
            	// 검색어 값 고정
            	if('${search}' != null) {
            		$('#search').val('${search}');
                }
                // 검색 유효성
                $('#searchBtn').click(function() {
					if($('#search').val() == '') {
						alert('검색어를 입력하세요.');
						return;
					}else {
						$('#searchForm').submit();
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
				<h1>받은 쪽지함</h1>
			</div>
		</div>
		
		<div class="container">
			<div>
				<div class="float-left">
					<form action="${pageContext.request.contextPath}/messageForm" method="post">
						<input type="hidden" name="accountId" value="${accountId}">
						<button class="btn btn-outline-primary">쪽지 보내기</button>
					</form>
				</div>
				<div class="float-right mb-5">
					<a href="${pageContext.request.contextPath}/sendMessage" class="btn btn-outline-success">보낸 쪽지함</a>
					<a href="${pageContext.request.contextPath}/receiveMessage" class="btn btn-outline-success">받은 쪽지함</a>
				</div>	
				<form action="${pageContext.request.contextPath}/receiveMessage" method="get" id="searchForm">
					<div class="input-group mb-3 justify-content-end">
						<div class="input-group-prepend">
							<select class="form-control" name="sel">
								<option value="id" ${sel == 'id' ? 'selected="selected"' : ''}>아이디</option>
								<option value="content" ${sel == 'content' ? 'selected="selected"' : ''}>내용</option>
							</select>
						</div>	
						<input type="text" class="form-control col-sm-2" id= "search" name="search" placeholder="Search">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" id="searchBtn">검색</button>		
						</div>				
					</div>
				</form>
			</div><hr>
			<!-- 메세지 목록 출력 -->
			<table class="table table-hover">
				<tr>
					<th>발신자 ID</th>
					<th>발신자 이름</th>
					<th>보낸 날짜</th>
					<th>확인여부</th>
					<th>쪽지보기</th>
					<th>삭제하기</th>
				</tr>
				<c:choose>
					<c:when test="${list.isEmpty()}">
						<tr>
							<td colspan="5">조회된 내용이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${list}">
							<tr>
								<td>${list.fromId}</td>
								<td>${list.fromName}</td>
								<td>${list.messageDateTime}</td>
								<td>${list.isConfirm}</td>
								<td>
									<form action="${pageContext.request.contextPath}/receiveMessageDetail" method="post">
										<input type="hidden" value="${list.toId}" name="id">
										<input type="hidden" value="${list.messageNo}" name="messageNo">
										<button class="btn btn-outline-info">쪽지보기</button>
									</form>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/removeReceiveMessage?messageNo=${list.messageNo}" class="btn btn-outline-danger">삭제하기</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<!-- 페이징 네비게이션 -->
			<ul class="pagination justify-content-center" style="margin:20px">
				<c:choose>
					<c:when test="${lastPage == 1 || lastPage == 0}">
						<li class="page-item disabled"><a class="page-link">&lt;&lt;</a></li>
						<li class="page-item disabled"><a class="page-link">&lt;</a></li>
						<li class="page-item active"><a class="page-link">1</a></li>
						<li class="page-item disabled"><a class="page-link">&gt;</a></li>
						<li class="page-item disabled"><a class="page-link">&gt;&gt;</a></li>
					</c:when>
					<c:when test="${lastPage != 1 && navStartPage < 11}">
						<li class="page-item disabled"><a class="page-link">&lt;&lt;</a></li>
						<li class="page-item disabled"><a class="page-link">&lt;</a></li>
						<c:forEach var="i" begin="${navStartPage}" end="${navEndPage}">
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class="page-item active"><a class="page-link">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${i}&sel=${sel}&search=${search}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${lastPage < 11}">
								<li class="page-item disabled"><a class="page-link">&gt;</a></li>
								<li class="page-item disabled"><a class="page-link">&gt;&gt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${navStartPage+navPerPage}&sel=${sel}&search=${search}">&gt;</a></li>
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${lastPage}&sel=${sel}&search=${search}">&gt;&gt;</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${navEndPage > navPerPage && navEndPage%navPerPage == 0}">
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=1&sel=${sel}&search=${search}">&lt;&lt;</a></li>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${navStartPage-1}&sel=${sel}&search=${search}">&lt;</a></li>
						<c:forEach var="i" begin="${navStartPage}" end="${navEndPage}">
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class="page-item active"><a class="page-link">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${i}&sel=${sel}&search=${search}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${navStartPage+navPerPage}&sel=${sel}&search=${search}">&gt;</a></li>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${lastPage}&sel=${sel}&search=${search}">&gt;&gt;</a></li>
					</c:when>
					<c:when test="${lastPage != 1 && navEndPage%navPerPage != 0}">
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=1&sel=${sel}&search=${search}">&lt;&lt;</a></li>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${navStartPage-1}&sel=${sel}&search=${search}">&lt;</a></li>
						<c:forEach var="i" begin="${navStartPage}" end="${navEndPage}">
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class="page-item active"><a class="page-link">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/receiveMessage?currentPage=${i}&sel=${sel}&search=${search}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li class="page-item disabled"><a class="page-link">&gt;</a></li>
						<li class="page-item disabled"><a class="page-link">&gt;&gt;</a></li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</body>
</html>