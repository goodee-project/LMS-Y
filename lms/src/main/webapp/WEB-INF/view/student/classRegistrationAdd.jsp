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
			<h1>��û ���� ���</h1> <a href="${pageContext.request.contextPath}/student/classRegistrationAdd">������û �ϱ�</a>
			<table class="table table-sm">
					<tr>
						<th>���� ��ȣ</th>
						<th>���� �̸�</th>
						<th>���� ����(����)</th>
						<th>����(�ؽ�Ʈ)</th>
					</tr>
				<tbody>
					<c:forEach var="r" items="${registrationList}">
					<tr>
						<td><a href="${pageContext.request.contextPath}/student/classRegistartionDetail?subjectNo=${subjectNo}">${r.lectureNo}</a></td>
						<td>${r.teacherName}</td>		
						<td>${r.classRegistrationPoint}</td>
						<td>${r.classRegistrationReview}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div style="margin-left:47%">
			<!-- ���� �������� 1�Ͻ� -->
			<a href="${pageContext.request.contextPath}/student/classRegistration?currentPage=${currentPage-1}">����</a>	
			<!-- ���� ������ ǥ�� -->
			<a href="">${currentPage}</a>
			<!-- ���� �������� ������ ������ ���� ������ -->
			<a href="${pageContext.request.contextPath}/student/classRegistration?currentPage=${currentPage+1}">����</a>
			<!-- ������ ������ ǥ�� -->
		<!-- 	<a href="${pageContext.request.contextPath}/student/classRegistration?" -->
		</div>
	</body>
</html>