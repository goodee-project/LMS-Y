<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 개설</title>
		
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
			<h1>강좌 개설</h1>
			
			<div>
				<form method="post" action="${pageContext.request.contextPath}/manager/createLectureManager">
				
				<table class="table"> 
					<tr>
						<td>강사 Id</td>							
						<td><input type="text" name="accountId" id="teacherNameId"></td>
					</tr>
					<tr>
						<td>과목 이름</td>
						<td>
							<select name="subjectNo">
								<c:forEach items="${subjectList}" var="subject">	
									<option value="${subject.subjectNo}">${subject.subjectName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>강사 이름</td>							
						<td><input type="text" name="teacherName" id="teacherNameId"></td>
					</tr>
					<tr>
						<td>교재 이름</td>							
						<td>
							<select name="textbookISBN">
								<c:forEach items="${textbookList}" var="textbook">	
									<option value="${textbook.textbookISBN}">교재이름:${textbook.textbookTitle}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<!-- 교재 리스트  -->
						<td>강좌 이름</td>							
						<td><input type="text" name="lectureName" id="lectureNameId" ></td>
					</tr>
					<tr>	
						<!--  시간설정  -->
						<td>강좌 시작날짜</td>							
						<td><input type="date"  name="lectureStartDate" id="lectureStartDateId" ></td>
					</tr>
					<tr>	
						<!--  시간설정  -->
						<td>강좌 종료날짜</td>							
						<td><input type="date"  name="lectureEndDate" id="lectureEndDateId" ></td>
					</tr>
					<tr>	
						<!-- 숫자만입력 -->
						<td>강좌 정원</td>							
						<td><input type="text"  name="lectureTotal" id="lectureTotalId" ></td>
					</tr>
					<tr>
						<td>강의 계획서</td>
						<td>
							<select name="syllabusNo">
								<c:forEach items="${syllabusList}" var="syllabus">	
									<option value="${syllabus.syllabusNo}">${syllabus.syllabusContent}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
					
						<!-- 리스트를 보여주는 식  -->
						<td>강의실</td>	
					<td>						
						<select name="classroomNo">
								<c:forEach items="${classroomList}" var="c">	
									<option value="${c.classroomNo}">${c.classroomNumber}</option>
								</c:forEach>
							</select>
						</td>
							
						
					</tr>
				</table>
						<button type="submit">입력</button>
				</form>
			</div>
		</div>
	</body>
</html>