<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자주하는질문</title>
		
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
		
		<div class="jumbotron">
 			<div class="container">
    			<h1>자주하는질문</h1>
 			 </div>
			</div>
			<!-- 카테고리 -->	
			<div class="container">
				<div class="row">
					<div class="col">
					
			<span><a style="height:45px; width:150px;" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/FAQList?currentPage=1">전체</a></span>
			<c:forEach items="${categoryList}" var="cl" varStatus="status"> 
				<td>
					<a style="height:45px; width:150px;" class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/FAQList?currentPage=1&categoryFaqSearch=${cl.faqCategory}">${cl.faqCategory}</a>
					
				</td>
					<c:if test="${status.count%5 eq 0}"> 
						<tr>
					</c:if>
				</c:forEach>
			<!-- FAQList -->
				</div>
			</div>
			</div>
				<div class="container">
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/createFAQ">작성</a>
				</div>	
			<div class="container">
				<table class="table">
					<tr>
						<th>No.</th>
						<th>카테고리</th>
						<th style="width:40%">제목</th>
						<th>작성자</th>
						<th>수정날짜</th>
						<th>조회수</th>
					</tr>
				<c:forEach items="${faqList}" var="f">
					<tr>
						<td>${f.faqNo}</td>
						<td>${f.faqCategory}</td>
						<td><a href="${pageContext.request.contextPath}/manager/FAQDetail?faqNo=${f.faqNo}">${f.faqTitle}</a></td>
						<td>${f.faqWriter}</td>
						<td>${f.faqUpdateDate}</td>
						<td>${f.faqCount}</td>
					</tr>
				</c:forEach>	
			</table>
			
			<!-- 검색된 항목이 없을 시 출력 -->
			<c:if test="${lastPage == 0}">
				<tr>
					<td colspan="4">
						<div class="d-flex justify-content-center">
							해당하는 게시글이 없습니다.
						</div>
					</td>
				</tr>
		</c:if>
			<!-- 페이지 네비게이션 바 -->
			<c:if test="${categoryFaqSearch ne null }">
			<c:if test="${lastPage != 0}">
				<div>
					<ul class="pagination small justify-content-center " >
						<%-- 처음 버튼 --%>
						<c:if test="${currentPage != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=1&categoryFaqSearch=${categoryFaqSearch}">
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
						<c:if test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}&categoryFaqSearch=${categoryFaqSearch}">
									&lt;
								</a>
							</li>
						</c:if>
						<!-- 페이지가 1일경우 동작x -->
						<c:if test="${currentPage == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									&lt;
								</a>
							</li>
						</c:if>
						
						<%-- 각 페이지 이동 버튼 --%>
						<c:forEach var="p" begin="${navBeginPage}" end="${navLastPage}" step="1">
							<c:if test="${p != currentPage}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${p}&categoryFaqSearch=${categoryFaqSearch}">
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
						<c:if test="${currentPage != lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&categoryFaqSearch=${categoryFaqSearch}">
									&gt;
								</a>
							</li>
						</c:if>
						<!--  -->
						<c:if test="${currentPage == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									&gt;
								</a>
							</li>
						</c:if>
						
						<%-- 마지막 버튼 --%>
						<c:if test="${currentPage != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${lastPage}&categoryFaqSearch=${categoryFaqSearch}">
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
			</c:if>
			<c:if test="${categoryFaqSearch eq null }">
			<c:if test="${lastPage != 0}">
				<div>
					<ul class="pagination small justify-content-center " >
						<%-- 처음 버튼 --%>
						<c:if test="${currentPage != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=1">
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
						<c:if test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}">
									&lt;
								</a>
							</li>
						</c:if>
						<!-- 페이지가 1일경우 동작x -->
						<c:if test="${currentPage == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									&lt;
								</a>
							</li>
						</c:if>
						
						<%-- 각 페이지 이동 버튼 --%>
						<c:forEach var="p" begin="${navBeginPage}" end="${navLastPage}" step="1">
							<c:if test="${p != currentPage}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${p}">
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
						<c:if test="${currentPage != lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}&categoryFaqSearch=${categoryFaqSearch}">
									&gt;
								</a>
							</li>
						</c:if>
						<!--  -->
						<c:if test="${currentPage == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									&gt;
								</a>
							</li>
						</c:if>
						
						<%-- 마지막 버튼 --%>
						<c:if test="${currentPage != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${lastPage}">
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
			</c:if>
			</div>
	</body>
</html>