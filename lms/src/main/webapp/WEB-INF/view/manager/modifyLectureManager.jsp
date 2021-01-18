<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>강좌 수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
		$(document).ready(function(){
        	// 강사 Id 유효성검사
			$('#teacherId').focus();	
            $('#teacherId').blur(function(){
                // 강사id 공백 검사 
				if($('#teacherId').val() =="" ){
					$('#teacherId').focus();
					$('#teacherIdMsg').text("강사 id를 입력하여주세요");
					return
					
				}else{
					
					$('#teacherIdMsg').text("");
					
					}
			});		
         // 강사 이름 유효성 검사 
			$('#nameId').blur(function(){
				// 강사 이름 공백 검사
				if($('#nameId').val() ==''){
					$('#nameIdMsg').text("강사 이름을 입력해주세요");
					$('#nameId').focus();
					return
					
				}else{
					
					$('#nameIdMsg').text("");
					
					}
			});
			// 강사id 를 이용하여 강사 이름 추가 
			$('#Search').click(function() {
				if($('#accountId').val() == '') {
					alert('강사 아이디를 입력하시오');
					return;
				}
				$.ajax({			             
		            url : '${pageContext.request.contextPath}/manager/teacherName',
		            type : 'GET',
		            data : {accountId:$('#accountId').val()},
		            error : function(){
		                alert('데이터에 오류가 있습니다');
		            },
		            success : function(data){			           
			            let str = `<td class="form-group">
			           			 <select name="teacherName">`;
			            for(let i=0; i<data.length; i++) {
						  str += '<option>' + data[i] + '</option>';
					 }
					     str += '</select> </div>';
			            $('input[name = teacherName]').attr('value',data); 
		                $('#addAddr').empty();
						$('#addAddr').append(str);
		            }
		        });
				$('#nameMsg').text('');
			});
				
			// 강좌 이름 유효성 검사	
			$('#lectureNameId').blur(function(){
				// 강좌 이름 공백 검사
				if($('#lectureNameId').val() ==''){
					$('#lectureNameIdMsg').text("강좌명을 입력해주세요");
					$('#lectureNameId').focus();
					return
					
				}else{
					
					$('#lectureNameIdMsg').text("");
					
					}
			});		
			// 강좌 시작 날짜 유효성 검사	
			$('#startDateId').blur(function(){
				// 강좌 시작 날짜 공백 검사
				if($('#startDateId').val() ==''){
					$('#startDateIdMsg').text("강좌 시작날짜를 입력해주세요");
					$('#startDateIdId').focus();
					return
					
				}else{
					
					$('#startDateIdIdMsg').text("");
					
					}
			});
			// 강좌 종료 날짜 유효성 검사	
			$('#endDateId').blur(function(){
				// 강좌 종료 날짜 공백 검사
				if($('#endDateId').val() ==''){
					$('#endDateIdMsg').text("강좌 종료날짜를 입력해주세요");
					$('#endDateId').focus();
					return
					
				}else{
					
					$('#endDateIdMsg').text("");
					
					}
			});		
					
			// 강좌 정원 유효성 검사
			$('#totalId').blur(function(){
				// 강좌 정원 공백 검사
				if($('#totalId').val() == ''){
					$('#totalMsg').text("강좌 수용인원을 입력해주세요");
					$('#totalId').focus();
					return
					
				}else{
					
					$('#totalMsg').text("");
					
					}
			});
			$('#btnId').click(function() {
				// 공백 검사
				if($('#nameId').val() == '' || $('#lectureNameId').val() == ''  || $('#teacherName').val() == ''
					|| $('#startDateId').val() == ''|| $('#endDateId').val() == ''|| $('#totalId').val() == '') {
					alert('기입에 누락된 부분이 없는지 확인해주세요');
				} else if (new Date($('#endDateId').val())
					- new Date($('#startDateId').val()) <= 0) {	
					alert(' 강좌의 시작일과 강좌의 종료일이 올바르지 않습니다!');
					$('#startDateId').focus();
				}else if($('#accountId').val() == '') {
						alert('강사 아이디를 입력하시오');
						return;
						
					}// 강사의 ID 와 강사 이름 확인
					$.ajax({
						url: '${pageContext.request.contextPath}/manager/lectureNameCheck',
						type: 'post',
						data: {accountId:$('#accountId').val(),teacherName:$('#teacherName').val()},
						success: function(data) {
							if(data == 0) {
								alert(' 강사ID 강사 이름이 올바르지 않습니다!');
								$('#teacherName').focus();
								$('#nameMsg').text('검색을 눌러주세요!');
								return;
							}else {
								$('#postId').submit();
							}	
						}
					});
									
			});
			console.log();  				 	
     });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
	
		<div class="jumbotron">
 			<div class="container">
    			<h1>강좌 수정</h1>
 			 </div>
			</div>
				<form id="postId" method="post" action="${pageContext.request.contextPath}/manager/modifyLectureManager?lectureNo=${lecture.lectureNo}">
					<div class="container">
				<table class="table"> 
					<tr>
						<td>강사 Id</td>							
						<td>
							<select class ="form-control col-sm-4" id="accountId" name="accountId">
								<c:forEach items="${teacherList}" var="teacher">	
									<c:if  test="${teacher.accountId == lecture.accountId}">
										<option  value="${teacher.accountId}" selected="selected">${teacher.accountId}</option>
									</c:if>
									<c:if  test="${teacher.accountId != lecture.accountId}">
										<option value="${teacher.accountId}">${teacher.accountId}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>과목명</td>
						<td>
							<select class ="form-control col-sm-4" name="subjectNo" >
								<c:forEach items="${subjectList}" var="subject"> 	
								<c:if test="${subject.subjectNo == lecture.subjectNo}">
									<option value="${subject.subjectNo}" selected="selected">${subject.subjectName}</option>
								</c:if>
								<c:if test="${subject.subjectNo != lecture.subjectNo}">
									<option value="${subject.subjectNo}">${subject.subjectName}</option>
								</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>강사 이름</td>
						<td class="input-group">
							<input readonly="readonly" class ="form-control col-sm-4" value="" type="text"  name="teacherName" id="teacherName">
							<button class="btn btn-success" type="button" id="Search">검색</button>
							<span id="nameMsg" class="col-sm-4"></span>
						</td>
					</tr>
					<tr>
						<td>교재명</td>
						<td>
							<select class ="form-control col-sm-4"name="textbookISBN" >
								<c:forEach items="${textbookList}" var="textbook">	
									<c:if test="${textbook.textbookISBN == lecture.textbookISBN }"    >
										<option  value="${textbook.textbookISBN}" selected="selected">교재이름:${textbook.textbookTitle}</option>
									</c:if>
									<c:if test="${textbook.textbookISBN =! lecture.textbookISBN }"    >
										<option  value="${textbook.textbookISBN}" >교재이름:${textbook.textbookTitle}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>강좌명</td>							
						<td><input class ="form-control col-sm-4"type="text" name="lectureName" id="lectureNameId" value="${lecture.lectureName}">
							<span id="lectureNameIdMsg"></span>
						</td>
					</tr>
					<tr>	
						<td>강좌 시작일</td>							
						<td><input class ="form-control col-sm-4" type="date"  name="lectureStartDate" id="startDateId" value="${lecture.lectureStartDate}">
							<span id="startDateIdMsg"></span>
						</td>
					</tr>
					<tr>	
						<td>강좌 종료일</td>							
						<td><input class ="form-control col-sm-4"type="date"  name="lectureEndDate" id="endDateId" value="${lecture.lectureEndDate}">
							<span id="endDateIdMsg"></span>
						</td>
					</tr>
					<tr>	
						<td>강좌 정원</td>							
						<td><input class ="form-control col-sm-4"type="number"  name="lectureTotal" id="lectureTotalId" value="${lecture.lectureTotal}" >
							<span id="totalIdMsg"></span>
						</td>
					</tr>
					<tr>
						<td>강의실</td>	
					<td>						
						<select name="classroomNo" class ="form-control col-sm-4">
								<c:forEach items="${classroomList}" var="c">
									<c:if test="${c.classroomNo == lecture.classroomNo}"    >
										<option value="${c.classroomNo}" selected="selected">${c.classroomNumber}호실</option>
									</c:if>
									<c:if test="${c.classroomNo != lecture.classroomNo}"    >
										<option value="${c.classroomNo}">${c.classroomNumber}호실</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
						<div class="text-right">
							<button class="btn btn-outline-success" id="btnId" type="button">수정</button>		
						</div>	
					</div>	
				</form>
	</body>
</html>