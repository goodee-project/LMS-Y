<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LMS 공지사항</title>
		
		<!-- jQuery 스크립트 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
        		$('#removeBtn').click(function() {
					let remove = confirm('정말 삭제하시겠습니까?');
					
					if(remove) {
						location.replace('${pageContext.request.contextPath}/manager/removeLMSNotice?lmsNoticeNo=${lmsNotice.lmsNoticeNo}');
						alert('삭제하였습니다.');
					} else {
						alert('취소하였습니다.');
						return;
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
				<h1>LMS 공지사항</h1>
			</div>
		</div>
		<div class="container">
			<div>
				<table class="table table-striped">
					<tr>
						<td>${lmsNotice.lmsNoticeNo}</td>
						<td>${lmsNotice.lmsNoticeTitle}</td>
						<td>${lmsNotice.lmsNoticeWriter}</td>
						<td>${lmsNotice.lmsNoticeUpdateDate}</td>
						<td>${lmsNotice.lmsNoticeCount}</td>
					</tr>
					<tr >
						<td colspan="5">${lmsNotice.lmsNoticeContent}</td>
					</tr>
					<!-- 세션값이 manager일 경우 -->
					<c:if test="${accountLevel eq managerLevel}">
						<!-- 세션 accountID와 등록한 id가 같으면 -->
						<c:if test="${accountId eq lmsNotice.accountId}">
							<tr>
								<td colspan="5">
									<div class="d-flex justify-content-end">
										<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/manager/modifyLMSNotice?lmsNoticeNo=${lmsNotice.lmsNoticeNo}">수정</a>
										<button type="button" class="btn btn-outline-danger mx-2" id="removeBtn">삭제</button>
									</div>
								</td>
							</tr>
						</c:if>
					</c:if>
				</table>
			</div>
		</div>
	</body>
</html>