<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>������û</title>
		
		<!-- jQuery ��ũ��Ʈ -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // �� ��ȿ�� �˻�
                // code here...
            });
        </script>
	</head>
	
	<body>
		<!-- �޴�+CSS ��Ŭ��� -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		 
		<div class="container">
			<h1>���� ���</h1>
			<table class="table">
					<tr>
						<th>���� ��ȣ</th>
						<th>���� ��ȣ</th>
						
						<th>���� ����(����)</th>
						<th>����(�ؽ�Ʈ)</th>
					</tr>
				<tbody>
					<c:forEach var="r" items="${classRegistrationList}">
					<tr>
						<td>${r.classRegistrationNo}</td>
						<td><a href="${pageContext.request.contextPath}/student/classRegistartionDetail?subjectNo=${subjectNo}">${r.lectureNo}</a></td>
						
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
		
		<c:if test="${null eq classregistrationList}">
				<div>
					<!-- ó������, ���� -->
					<c:choose>
						<c:when test="${currentPage > 1}">
							<a href="${pageContext.request.pathInfo}?currentPage=1">[ó������]</a>
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage-1}"><</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<!-- ���������� �׺�� -->
					<c:forEach var="i" begin="${navBeginPage}" end="${navLastPage}">
						<c:if test="${i <= lastPage}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<a href="#">[${i}]</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.pathInfo}?currentPage=${i}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					<!-- ����, ���������� -->
					<c:choose>
						<c:when test="${currentPage < lastPage}">
							<a href="${pageContext.request.pathInfo}?currentPage=${currentPage+1}">></a>
							<a href="${pageContext.request.pathInfo}?currentPage=${lastPage}">[����������]</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
		</div>
	</body>
</html>