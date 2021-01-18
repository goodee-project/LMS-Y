<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<!-- <input type="file" name="teacherImage" id="teacherImage" accept="image/png,image/jpeg"> -->
<!-- jQuery 스크립트 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
      $(document).ready(function() {
         // 이미지 변경 값이 있는지 확인하는 변수
         var imageCheck = '';
         // 이미지에 대한 제약조건 명시
         $('#ManagerImageFileFrame').change(function(){
               ext = $(this).find('input').val().split('.').pop().toLowerCase(); //확장자
               //배열에 추출한 확장자가 존재하는지 체크
               if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'jfif']) == -1) {
                   alert('ERROR: 이미지 파일이 아닙니다');
                   $(this).find('input').val('');
               } else {
                   myImageFile(this, '#Imageview');
                   imageCheck = 'check';
               }
               
         });
         // 이미지 미리보기
      	$('#Imageview').bind(function myImageFile(input, expression) {
            if (input.files && input.files[0]) {
                 var reader = new FileReader();
                 reader.onload = function (e) {
                     $(expression).attr('src', e.target.result);
                 }
                 reader.readAsDataURL(input.files[0]);
                 }
            });
         
         //이미지 추가버튼 한번만 클릭되게해주는 함수
         var click = true;
         // 첨부파일 추가버튼에 대한 이벤트 처리를 등록함
         $('#createLectureArchiveFile').click(function() {
             if (click) {
                   console.log("클릭됨");
                   click = !click;
              } else {
                 alert('한번만 클릭됩니다.');
                 return;
              }
            // 첨부파일 프레임의 마지막 부분에 첨부파일 input 태그 및 삭제 버튼을 추가함
            $('#ManagerImageFileFrame').append(`
               <div class="input-group mb-3">
                  <input class="imageFileList" type="file" name="imageFileList" id="ManagerImageFileFrame"/>
                  <button class="removelectureArchiveFile btn btn-outline-danger" type="button">삭제</button>
               </div>
            `);
            
            // (바로 위의 코드에서 추가한) 삭제버튼에 대한 이벤트 처리를 등록함
            $('#ManagerImageFileFrame:last-child .removelectureArchiveFile').click(function(event) {
               // 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
               $(event.target).parent().remove();
            });
         });
         
         // 빈 첨부파일 칸이 있을 경우 모두 삭제
         $('.imageFileList').each(function(index, element) {
            if ($(element).val() == '') {
               // 삭제버튼의 부모(위 코드의 div 태그)를 HTML상에서 완전히 지워버림
               $(element).parent().remove();
            }
         });
   
         //
         //이메일 형식검사
         //이메일 체크값이 있는지 확인하는 변수
         $('#teacherEmail').blur(function(){
            if($('#managerEmail').val()=='' || $('#managerEmail').val().length>200 || $('#managerEmail').val().replace(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,"")){
               $('#ckEmail').text('이메일을 확인하세요!');
               $('#managerEmail').focus();
               return;
            }else{
               $('#ckEmail').text('');
            }
         });
         //이메일 중복검사
         $('#btnEmail').click(function(){
            $.ajax({
               url: '${pageContext.request.contextPath}/manager/managerEmailCk',
               type: 'post',
               data: {accountId:$('#accountId').val(),managerEmail:$('#managerEmail').val()},
               success: function(data) {
                  if(data == 'noPass') {
                     $('#ckEmail').text('기존 이메일입니다! (사용가능)');
                     $('#managerEmail').focus();
                     return;
                  }else {
                     $('#ckEmail').text('사용가능한 이메일입니다!');
                  }
               }
            });
         });
         //전화번호 숫자만 입력
         $("#managerPhone").on("keyup", function() {
             $(this).val($(this).val().replace(/[^0-9]/g,""));
         });
      
         //기존 이미지 삭제에 대한 유효성 검사
         $('#removeBtn').click(function() {
            let remove = confirm('정말 이미지를 삭제하시겠습니까?');
            
            if(remove) {
               location.replace('${pageContext.request.contextPath}/manager/removeManagerFile?accountId=${accountId}');
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
                                 <select multiple class="form-control" name="managerAddressMain" onchange="$('#managerAddressMain').val(this.options[this.selectedIndex].value)" >`;
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
            $('#submitManagerDetail').click(function() {
               if($('#managerEmail').val()==''|| $('#managerEmail').val().replace(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,"")|| $('#managerName').val()==''|| $('#managerPhone').val()=='' ||$('#managerAddressMain')=='' ||$('#managerAddressSub')==''||$('#managerPosition')==''){
                     alert('입력부분을 다시 확인하세요');
                     return;
               }else{
                  // 유효성 검사를 만족했을 경우 submit
                  $('#modifyManagerForm').submit();
                  }
            });
            
         });
      </script>
</head>
<body>
   <!-- 부트스트랩(CSS) 인클루드 -->
   <jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>

   <div class="jumbotron">
      <div class=container>
         <h1>내 정보수정</h1>
      </div>
   </div>
   <div class=container>
      <form class="" id="modifyManagerForm" method="post" action="${pageContext.request.contextPath}/manager/modifyManager" enctype="multipart/form-data">
         <table class="table">
            <tr>
               <td>운영자 아이디</td>
               <td class="form-inline"><input class="form-control form-control-alternative" type="text" name="accountId" id="accountId" value="${map.manager.accountId}" readonly="readonly"></td>
               <td></td>
            </tr>
            <tr>
               <td>운영자 이메일</td>
               <td><input class="form-control form-control-alternative"  type="text" name="managerEmail" id="managerEmail" placeholder="이메일을 입력해주세요!" value="${map.manager.managerEmail}">
               <div style="color: red;" id="ckEmail"></div></td>
               <td><button class="btn btn-outline-primary" type="button" id="btnEmail">중복검사</button></td>
            </tr>
            <tr>
               <td>운영자 이름</td>
               <td class="form-inline"><input  class="form-control form-control-alternative" type="text" name="managerName" id="managerName" placeholder="이름을 입력해주세요!" value="${map.manager.managerName}"></td>
               <td></td>
            </tr>
            <tr>
               <td>운영자 전화번호</td>
               <td class="form-inline"><input class="form-control form-control-alternative"  type="text" name="managerPhone" id="managerPhone" placeholder=" -를 제외한 숫자만 입력해주세요" value="${map.manager.managerPhone}"></td>
               <td></td>
            </tr>
            <tr>
               <td>운영자 성별</td>
               <td><input type="radio" name="managerGender" id="managerGender" value="남" checked="checked">남 
               	   <input  type="radio" name="managerGender" id="managerGender" value="여">여
               </td>
               <td></td>
            </tr>
            <tr>
               <td>운영자 생년월일</td>
               <td class="form-inline"><input class="form-control form-control-alternative" type="date" name="managerBirth" id="managerBirth"
                  value="${map.manager.managerBirth}"></td>
               <td></td>
            </tr>
            <tr>
               <td>메인주소</td>
               <td>
                  <input class="form-control" type="text" id="managerAddressMain" class="managerAddressMain" value="${map.manager.managerAddressMain}" readonly="readonly">
                  <div class="input-group">
                     <input class="form-control col-sm-3" type="text" id="zipCode" placeholder="우편번호 입력">
                     <button class="btn btn-outline-primary" type="button" id="zipCodeSearch">우편번호 검색</button>
                  </div>
                  <div id="addAddr"></div>
               </td>
               <td></td>
            </tr>
            <tr>
               <td>상세주소</td>
               <td><input class="form-control" type="text" name="managerAddressSub" id="managerAddressSub" value="${map.manager.managerAddressSub}" placeholder="상세 주소를 입력하세요."></td>
               <td></td>
            </tr>
               <tr>
               <td>운영자 직책</td>
               <td>
               	<select class="form-control col-sm-4" name="managerPosition">
               		<option value='대리'>대리</option>
               		<option value='사원'>사원</option>
               		<option value='팀장'>팀장</option>
              	</select>
               </td>
               <td></td>
            </tr>
            <tr>
               <td>프로필 사진</td>
               <td><img src="${map.imageURI}" name="managerImage" id="Imageview" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" style="width: 170px; height: 200px;" />
                  <c:if test="${not empty map.manager.managerImage}">
                     <a class="btn btn-outline-danger" href="#" id="removeBtn">삭제</a>
                  </c:if>
                  <button class="btn btn-outline-primary" id="createLectureArchiveFile" type="button" onclick="overClick()">이미지변경</button>
                  <!-- jQuery로 추가되는 첨부파일 리스트의 틀(Frame) -->
                  <div id="ManagerImageFileFrame"></div>
               <td></td>
            </tr>
            <tr>
               <td></td>
               <td></td>
               <td>
                  <div style="text-align:right;">
                     <button class="btn btn-outline-success" id="submitManagerDetail" type="button">수정</button>
                  </div>
               </td>
            </tr>
         </table>
      </form>
   </div>
</body>
</html>