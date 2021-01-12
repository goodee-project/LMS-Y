<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 리스트</title>
		
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
			<h1>강좌 리스트</h1>
			
			
				<!-- 강좌 테이블 -->
				<table class="table">
					<tr>
						<th>강좌 번호</th>
						<th>담당 강사</th>
						<th style="width:40%">강좌 제목</th>
						<th>강좌 시작날짜</th>
						<th>강좌 종료날짜</th>
						<th>강좌 정원</th>
						
					</tr>
				<c:forEach items="${ManagerLectureList}" var="ml">
					<tr>
						<td>${ml.lectureNo}</td>
						<td>${ml.teacherName}</td>
						<td><a href="${pageContext.request.contextPath}/manager/managerLectureDetail?lectureNo=${ml.lectureNo}">${ml.lectureName}</a></td>
						<td>${ml.lectureStartDate}</td>
						<td>${ml.lectureEndDate}</td>
						<td>${ml.lectureTotal}</td>
					</tr>
				</c:forEach>	
			</table>
						<!-- 페이지 네비게이션 바 -->
			<c:if test="${lastPage != 0}">
				<div>
					<!-- 페이징 가운데 정렬 -->
					<ul class="pagination small justify-content-center " >
						<%-- 처음 버튼 --%>
						<c:if test="${currentPage != 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=1">
									처음
								</a>
							</li>
						</c:if>
						<c:if test="${currentPage == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									처음
								</a>
							</li>
						</c:if>
						
						<%-- 이전 버튼 --%>
						<c:if test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}">
									이전
								</a>
							</li>
						</c:if>
						<!-- 페이지가 1일경우 동작x -->
						<c:if test="${currentPage == 1}">
							<li class="page-item disabled">
								<a class="page-link">
									이전
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
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">
									다음
								</a>
							</li>
						</c:if>
						<!--  -->
						<c:if test="${currentPage == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									다음
								</a>
							</li>
						</c:if>
						
						<%-- 마지막 버튼 --%>
						<c:if test="${currentPage != lastPage}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.pathInfo}?currentPage=${lastPage}">
									마지막
								</a>
							</li>
						</c:if>
						<c:if test="${currentPage == lastPage}">
							<li class="page-item disabled">
								<a class="page-link">
									마지막
								</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
			</div>
	</body>
</html>