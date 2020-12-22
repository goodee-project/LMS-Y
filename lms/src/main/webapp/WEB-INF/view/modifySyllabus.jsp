<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강의계획서 수정</title>
		
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
			<h1>강의계획서 수정</h1>
			
			<div>
				<form action="${pageContext.request.contextPath}/teacher/modifySyllabus?syllabusNo=${syllabus.syllabusNo}">
					<table>
						<tr>
							<td>
								<!-- syllabusContent -->
							</td>
						</tr>
					</table>
					<button type="submit">수정</button>
				</form>
			</div>
		</div>
	</body>
</html>