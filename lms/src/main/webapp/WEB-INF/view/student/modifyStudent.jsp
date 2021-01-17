<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 정보수정</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                // 폼 유효성 검사
                // code here...
                
				$('#studentEmail').blur(function(){
					if($('#studentEmail').val()=='' || $('#studentEmail').val().length>200 || $('#studentEmail').val().replace(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,"")){
						$('#ckEmail').text('이메일을 확인하세요!');
						$('#studentEmail').focus();
						return;
					}else{
						$('#ckEmail').text('사용가능한 이메일입니다');
					}
				});
				//이메일 중복검사
				$('#btnEmail').click(function(){
				$.ajax({
					url: '${pageContext.request.contextPath}/student/studentEmailCk',
					type: 'post',
					data: {accountId:$('#accountId').val(),studentEmail:$('#studentEmail').val()},
					success: function(data) {
						if(data == 'noPass') {
							$('#ckEmail').text('기존 이메일입니다! (사용가능)');
							$('#studentEmail').focus();
							return;
						}else {
							$('#ckEmail').text('사용가능한 이메일입니다!');
						}
					}
				});
			});
			
			//전화번호 숫자만 입력
			$("#studentPhone").on("keyup", function() {
				 $(this).val($(this).val().replace(/[^0-9]/g,""));
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
			//기존 이미지 삭제에 대한 유효성 검사
			$('#removeBtn').click(function() {
				let remove = confirm('정말 이미지를 삭제하시겠습니까?');
				
				if(remove) {
					location.replace('${pageContext.request.contextPath}/student/removeStudentFile?accountId=${accountId}');
					alert('삭제하였습니다.');
				} else {
					alert('취소하였습니다.');
					return;
				}
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
			            		   <select multiple class="form-control" onchange="$('#studentAddressMain').val(this.options[this.selectedIndex].value)" >`;
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
			$('#submitStudentOne').click(function() {
				if($('#studentEmail').val()==''|| $('#studentEmail').val().replace(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,"")|| $('#studentName').val()==''|| $('#studentPhone').val()=='' ||$('#studentAddressMain')=='' ||$('#studentAddressSub')==''){
						alert('입력부분을 다시 확인하세요');
						return;
				}else{
					// 유효성 검사를 만족했을 경우 submit
					$('#modifyStduentForm').submit();
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
				<h1>학생 정보 수정</h1>
			</div>
		</div>
		<div class=container>
			<form class="" enctype="multipart/form-data" id="modifyStduentForm" method="post" action="${pageContext.request.contextPath}/student/modifyStudent">
				<table class="table">
					<tr>
						<td>학생 아이디</td>
						<td class="form-inline"><input class="form-control form-control-alternative" type="text" name="accountId" id="accountId" value="${map.student.accountId}"></td>
					</tr>
					<tr>
						<td>학생 이메일</td>
						<td><input class="form-control form-control-alternative"  type="text" name="studentEmail" id="studentEmail" placeholder="이메일을 입력해주세요!" value="${map.student.studentEmail}"></td>
						<td><div Style="color:red;" id="ckEmail"></div>
							<button class="btn btn-outline-primary" type="button" id="btnEmail">중복검사</button>
						</td>
					</tr>
					
					<tr>
						<td>학생 이름</td>
						<td class="form-inline"><input class="form-control form-control-alternative" type="text" name="studentName" id="studentName" value="${map.student.studentName}" placeholder="이름 입력"></td>
					</tr>
					
					<tr>
						<td>학생 전화번호</td>
						<td class="form-inline"><input class="form-control form-control-alternative" type="text" name="studentPhone" id="studentPhone" value="${map.student.studentPhone}" placeholder="전화번호 입력"></td>
					</tr>
					
					<tr>
						<td>학생 성별</td>
						<td><input type="radio" name="studentGender" id="studentGender" value="남" checked="checked">남
							<input type="radio" name="studentGender" id="studentGender" value="여">여
						</td>
					</tr>
					
					<tr>
						<td>학생 생년월일</td>
						<td class="form-inline"><input type="date" name="studentBirth" id="studentBirth" value="${map.student.studentBirth}"></td>	
					</tr>
					
					<tr> 
						<td>메인주소</td>
							<td><input class="form-control form-control-alternative" type="text" name="studentAddressMain" id="studentAddressMain" value="${map.student.studentAddressMain}" readonly="readonly">
							<div class="input-group">
							<input class="form-control col-sm-3" type="text" id="zipCode" placeholder="우편번호">
							<button class="btn btn-outline-primary" type="button" id="zipCodeSearch">우편번호 검색</button>
						</div>
						<div id="addAddr"></div>
						</td>
					</tr>
					<tr>
						<td>상세주소</td>
							<td class="form-inline"><input class="form-control form-control-alternative" type="text" name="studentAddressSub" id="studentAddressSub" value="${map.student.studentAddressSub}" placeholder="상세주소 입력"></td>
							<td></td>
					</tr>
										
					<tr>
						<td>프로필 사진</td>
						<td><img src="${map.imageURI}" id="preview" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""style="width: 170px; height: 200px;" />
						<c:if test="${not empty myImage.imageFileUUID}">
							<a class="btn btn-outline-danger" href="#" id="removeBtn">삭제</a>
						</c:if>
						<input type="file" name="imageFileList" id="imageFileList"></td>
						<td></td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td></td>
						
						<td>
							<div style="text-align:right">
								<button class="btn btn-outline-success" id="submitStudentOne" type="button">수정</button>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>