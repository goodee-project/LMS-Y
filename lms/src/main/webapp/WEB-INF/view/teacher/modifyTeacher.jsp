<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사정보 수정</title>
<!-- <input type="file" name="teacherImage" id="teacherImage" accept="image/png,image/jpeg"> -->
<!-- jQuery 스크립트 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
		$(document).ready(function() {
			//이메일 형식검사
			//이메일 체크값이 있는지 확인하는 변수
			let emailCheck = false;
			function validateEmail(sEmail){
				let filter = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				if(filter.test(sEmail)){
					return true;
				}
				else{
					return false;
				}
			}
			$('#btnEmail').click(function(){
				let sEmail = $('#teacherEmail').val();
				if($.trim(sEmail).length == 0){
					alert('Please enter valid email address');
					e.preventDefault();
				}
				if(validateEmail(sEmail)){
					alert('올바른 이메일입니다!');
				}else{
					alert('잘못된 이메일 입니다!');
					$('#teacherEmail').focus();
					e.preventDefault();
				}
			});
			
			// 이미지 변경 값이 있는지 확인하는 변수
			var imageCheck = '';
			// 이미지에 대한 제약조건 명시
			$('#imgSel').change(function(){
	            ext = $(this).val().split('.').pop().toLowerCase(); //확장자
	            //배열에 추출한 확장자가 존재하는지 체크
	            if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'jfif']) == -1) {
	                alert('ERROR: 이미지 파일이 아닙니다');
	                $(this).val('');
	            } else {
	            	 setImageFromFile(this, '#preview');
	            	 imageCheck = 'check';
	            }
			});
			// 이미지 미리보기
			function setImageFromFile(input, expression) {
			    if (input.files && input.files[0]) {
			        var reader = new FileReader();
			        reader.onload = function (e) {
			            $(expression).attr('src', e.target.result);
			        }
			        reader.readAsDataURL(input.files[0]);
			    }
			}
			// 첨부파일 추가버튼에 대한 이벤트 처리를 등록함
			$('#createLectureArchiveFile').click(function() {
					// 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
					$('#lectureArchiveFileFrame').append(`
						<div>
							<input class="lectureArchiveFile" type="file" name="imageFileList" accept="image/png,image/jpeg">
							<button class="removelectureArchiveFile" type="button">삭제</button>
						</div>
					`);
					
					// (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
					$('#lectureArchiveFileFrame:last-child .removelectureArchiveFile').click(function(event) {
						// 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
						$(event.target).parent().remove();
					});
				});

				// 우편번호 검색시 요소 추가
				$('#zipCodeSearch').click(function() {
					if($('#zipCode').val() == '') {
						alert('우편번호를 입력하시오');
							return;
					}
					$.ajax({			             
			            url : '${pageContext.request.contextPath}/address',
			            type : 'get',
			            data : {zipCode:$('#zipCode').val()},
			            error : function(){
			                alert('데이터에 오류가 있습니다');
			            },
			            success : function(data){
				            let str = `<div class="form-group">
				            		   <select multiple class="form-control" name="teacherAddressMain" onchange="$('#teacherAddressMain').val(this.options[this.selectedIndex].value)" >`;
				            for(let i=0; i<data.length; i++) {
				            	str += '<option>' + data[i] + '</option>';
					        }
							str += '</select> </div>';
			                $('#addAddr').empty();
							$('#addAddr').append(str);
			            }
			        });
				});

				// 작성 버튼 클릭 시 유효성 검사 실시
				$('#submitTeacherOne').click(function() {
					if($('#teacherEmail').val().length < 1){
						alert('이메일을 입력해주세요.');
						return
					}else if(emailCheck == "false"){
						$('#teacherEmail').focus();
						alert('이메일 중복 확인해주세요.');
						return;
					}else if($('#teacherName').val().length < 1){
						$('#teacherName').focus();
						alert('이름을 입력하세요.');
					}else if($('#teacherPhone').val().length < 1){
						$('#teacherPhone').focus();
						alert('전화번호를 입력하세요.');
					}else if($('#teacherBirth').val().length <1){
						alert('생년월일를 입력하세요.');
					}else if($('#teacherAddressMain').val().length <1){
						$('#teacherAddressMain').focus();
						alert('메인주소를 입력하세요.');
					}else if($('#teacherAddressSub').val().length <1){
						$('#teacherAddressSub').focus();
						alert('서브주소를 입력하세요.');
					}
						// 유효성 검사를 만족했을 경우 submit
						$('#modifyTeacherForm').submit();
					});
				
			});
		</script>
</head>
<body>
	<!-- 부트스트랩(CSS) 인클루드 -->
	<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

	<div class=container>
		<div class="jumbotron">
			<h1>내 정보수정</h1>
		</div>
	</div>
	<div class=container>
		<form id="modifyTeacherForm" method="post"
			action="${pageContext.request.contextPath}/teacher/modifyTeacher"
			enctype="multipart/form-data">
			<table class="table">
				<tr>
					<td>강사 아이디</td>
					<td><input type="text" name="accountId" id="accountId"
						value="${map.teacher.accountId}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>강사 이메일</td>
					<td><input type="text" name="teacherEmail" id="teacherEmail" value="${map.teacher.teacherEmail}"> 
					<!--<button type="button" id="btnEmail">중복검사</button>--> </td> 
					<td><div id="ckEmail"></div></td>
				</tr>
				<tr>
					<td>강사 이름</td>
					<td><input type="text" name="teacherName" id="teacherName"
						value="${map.teacher.teacherName}"></td>
				</tr>
				<tr>
					<td>강사 전화번호</td>
					<td><input type="text" name="teacherPhone" id="teacherPhone"
						value="${map.teacher.teacherPhone}"></td>
				</tr>
				<tr>
					<td>강사 성별</td>
					<td><input type="radio" name="teacherGender"
						id="teacherGender" value="남" checked="checked">남 <input
						type="radio" name="teacherGender" id="teacherGender" value="여">여
					</td>
				</tr>
				<tr>
					<td>강사 생년월일</td>
					<td><input type="date" name="teacherBirth" id="teacherBirth"
						value="${map.teacher.teacherBirth}"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>메인주소:<input type="text" id="teacherAddressMain"
						class="teacherAddressMain"
						value="${map.teacher.teacherAddressMain}" readonly="readonly">
						상세주소:<input type="text" name="teacherAddressSub"
						id="teacherAddressSub" value="${map.teacher.teacherAddressSub}"
						placeholder="상세 주소를 입력하세요.">
					</td>
				</tr>
				<!-- 주소 찾기 -->
				<tr>
					<td>주소찾기</td>
					<td>
						<div class="input-group">
							<input class="form-control col-sm-3" type="text" id="zipCode"
								placeholder="우편번호 입력">
							<button class="btn btn-outline-primary" type="button"
								id="zipCodeSearch">우편번호 검색</button>
						</div>
						<div id="addAddr"></div>
					</td>
				</tr>
				<tr>
					<td>강사 한줄소개</td>
					<td><input type="text" name="teacherInfo" id="teacherInfo"
						value="${map.teacher.teacherInfo}"></td>
				</tr>
				<tr>
					<td>프로필 사진</td>
					<td><img src="${map.imageURI}" id="preview"
						style="width: 170px; height: 200px;" /> <c:if
							test="${not empty myImage.imageFileUUID}">
							<a
								href="${pageContext.request.contextPath}/teacher/removeTeacherFile?accountId=${accountId}">삭제</a>
						</c:if> <input type="file" name="imageFileList" id="imgSel" /> <!-- <input type="hidden" name="accountId" value="${teacher.accountId}">-->
						<!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) 
						<div id="lectureArchiveFileFrame"></div></td>-->
				</tr>

				<tr>
					<td>
						<button id="submitTeacherOne" type="button">수정</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>