<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>쪽지 보내기</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
				// 수신자 정보 검색
				$('#search').click(function() {
					$.ajax({
						url: '${pageContext.request.contextPath}/serchToId',
						type: 'POST',
						data: {accountId:$('#toId').val()},
						success: function(data) {
							console.log(data);
							if(data.toId != null) {
								$('#textInfo').text('수신자');
								let str = '<button type="button" class="btn btn-info mt-2" id="userId">'+data.toId+' ('+data.accountName+')'+'</button>';
								$('#userId').remove();
								$('#accountInfo').append(str);
							}
						}
					});
				});            
                // 쪽지 글자수 카운팅
            	$(document).on('keyup', '#messageContent', function(e){
            	    let messageContent = $(this).val();
            	    $('#cnt').text(getBytes(messageContent));
            	});           	 
            	function getBytes(str){
            	    let cnt = 0;
            	    for(let i =0; i<str.length;i++) {
            	        cnt += (str.charCodeAt(i) >128) ? 2 : 1;
            	    }
            	    if(cnt > 1000) {
						alert('1000자까지 입력이 가능합니다.')
						return cnt;
                	}
            	    return cnt;
            	}
            	// 유효성 검사
            	$('#submitBtn').click(function() {
            		if($('#toId').val() == '') {
            			alert('수신자를 입력하세요');
            			return;
                	}else if($('#textInfo').text() == '') {
						alert('수신자를 검색하세요');
						return;
                    }else if($('#messageContent').val() == '') {
                    	alert('쪽지 내용을 입력하세요');
						return;
                    }else {
						$('#messageForm').submit();
                    }
            	});
            	
            });
        </script>
	</head>
	
	<body>
		<!-- 메뉴+CSS 인클루드 -->
		<jsp:include page="/WEB-INF/view/inc/menu.jsp"></jsp:include>
		
		<div class="container">
			<div class="jumbotron">
				<h1>쪽지 보내기</h1>
			</div>
			
			<div align="center">
				<form action="${pageContext.request.contextPath}/sendMessage" method="post" id="messageForm">
					<input type="hidden" name="fromId" value="${fromId}">
					<input type="hidden" name="fromName" value="${fromName}">
					<table>
						<tr>
							<td>받는 사람</td>
							<td>
								<input type="text" class="form-control" id="toId" name="toId" placeholder="아이디를 검색하세요">
							</td>
							<td>
								<button type="button" class="btn btn-outline-primary" id="search">검색</button>
							</td>
						</tr>
						<tr>
							<td><div id="textInfo"></div></td>
							<td colspan="2">
								<div id="accountInfo"></div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<textarea class="form-control mt-3 mb-2" rows="8" cols="60" maxlength="1000" id="messageContent" name="messageContent"></textarea>
								 <div align="right">
							        <span id="cnt" >0</span>&nbsp;/&nbsp;<span>1000bytes</span>
							    </div>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="right">
								<button type="button" class="btn btn-success mt-3" id="submitBtn">보내기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>