<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>내 정보수정</title>
      
      <!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
         //이메일 형식검사
         //이메일 체크값이 있는지 확인하는 변수
         $('#emailId').blur(function(){
            if($('#emailId').val()=='' || $('#emailId').val().length>200 || $('#emailId').val().replace(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,"")){
               $('#ckEmail').text('이메일을 확인하세요!');
               $('#emailId').focus();
               return;
            }else{
            	 
               $('#ckEmail').text('사용 가능한 이메일 입니다');
            }
         });
         //전화번호 숫자만 입력
         $("#managerPhone").on("keyup", function() {
        		$(this).val($(this).val().replace(/[^0-9]/g,""));
				// 숫자 크기 체크
				if($(this).val().length > 11 ) {
					$('#ckPhone').text('전화번호를 확인하세요');
					 $('#managerPhone').focus();
					return;
				}else {
					$('#ckPhone').text('');
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
            // 우편번호 검색시 요소 추가
            $('#zipCodeSearch').click(function() {
               if($('#zipCode').val() == '') {
                  alert('우편번호를 입력하시오');
                     return;
               }if ($('#zipCode').val().length != 5) {
                   alert('5자리를 입력해 주세요');
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
       
            // 작성 버튼 클릭 시 유효성 검사 실시  이메일,연락처,이름.직책,생일 
            $('#btnId').click(function() {
	
               if($('#emailId').val()=='' ||  $('#managerPhone').val()==''||   $('#managerName').val()=='' || $('#managerPosition').val()=='' ||
            	    $('#managerPosition').val()=='' ||$('#managerBirth').val()=='' ){
                     alert('누락된 부분이 없는지 확인하세요');
                     return;
               }else{
                  // 유효성 검사를 만족했을 경우 submit
                  $('#postId').submit();
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
         <h1>내 정보수정</h1>
      </div> 
      </div>  
        <div class="container">
            <form id="postId" method="post" action="${pageContext.request.contextPath}/manager/modifyManager" enctype="multipart/form-data">
               <table class=table>
                  <tr>
                     <td>운영자 아이디</td>
                     <td><input  class ="form-control col-sm-3" type="text" name="accountId" value="${map.manager.accountId}" readonly="readonly"></td>
                  </tr>
                  <tr>
                     <td>운영자 이메일</td>
                     <td><input class ="form-control col-sm-7"  id="emailId" type="text" name="managerEmail" value="${map.manager.managerEmail}">
                        <div  style="color: red;" id="ckEmail"></div>
                     </td>
                  </tr>
                  <tr>
                     <td>운영자 연락처('-'없이 번호만 입력해주세요)</td>
                     <td><input class ="form-control col-sm-7"  id="managerPhone" type="text" name="managerPhone" value="${map.manager.managerPhone}">
                     	<div style="color: red;" id="ckPhone"></div>
                     </td> 
                  </tr>
                  <tr>
                     <td>운영자 이름</td>
                     <td><input class ="form-control col-sm-3" id="managerName" type="text" name="managerName" value="${map.manager.managerName}"> </td>
                  </tr>
                  <tr>
                     <td>운영자 성별</td>
                     <td>
                        <input type="radio" name="managerGender" value="남" id="female"checked="checked">남자
                        <input type="radio" name="managerGender" value="여" id="male">여자
                     </td>
                  </tr>
                  <tr>
                     <td>운영자 생년월일</td>
                     <td><input id="managerBirth" type="date" id="managerBirth" name="managerBirth" value="${map.manager.managerBirth}"></td>
                  </tr>
                  <tr>
                     <td>운영자 직책</td>
                     <td><input class ="form-control col-sm-3 " id="managerPosition" type="text" name="managerPosition" value="${map.manager.managerPosition}"></td>
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
               
            </tr>
            
            <tr>
               <td>상세주소</td>
               <td><input class="form-control" type="text" name="managerAddressSub" id="managerAddressSub" value="${map.manager.managerAddressSub}" placeholder="상세 주소를 입력하세요."></td>
            </tr>
                  <tr>
                     <td>프로필 사진</td>
                     <td><img src="${map.imageURI}" id="preview" onerror="this.src='https://www.flaticon.com/svg/static/icons/svg/149/149071.svg';" alt=""
                           style="width: 170px; height: 200px;" />
                        <c:if test="${not empty myImage.imageFileUUID}">
                           <a href="${pageContext.request.contextPath}/manager/removeManagerFile?accountId=${accountId}">삭제</a>
                        </c:if> 
                     <input type="file" name="imageFileList" id="imgSel" />
               <td></td>
                  </tr>
               </table>
                 <div class="d-flex justify-content-end">
                    <button id="btnId" type="button" class="btn btn-outline-success">수정</button>
                    <a  class="btn btn-outline-danger mx-2" href="${pageContext.request.contextPath}/manager/managerDetail">취소</a>
                 </div>   
            </form>
         </div>
   </body>
</html>