<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userid = (String) session.getAttribute("userId");
	String message=(String)request.getAttribute("msg");	
%>
<!DOCTYPE html>
<html>
<head>
<script>
window.onload = function() {	
    var message = "<%= message %>";
    console.log(message);
    if (message != null && message !== "") {
        alert(message);
    } 
   
};
</script>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<title>게시판 글쓰기 페이지</title>
</head>
<body>
	<div class="container-md">
		<h2 class="text-center">게시판 글쓰기 페이지</h2>
			<form class="row g-5" id="fileForm" action="insertBoardAction.do" method="post" enctype="multipart/form-data"> 
			 	<div class="mb-2">
					<label for="userId" class="form-label">작성자ID</label> 
					<input type="text" class="form-control" name="userId" id="userId" value=<%=userid%> readonly>
				</div>
				<div class="mb-2">
					<label for="boardTitle" class="form-label">제목</label> 
					<input type="text" name="boardTitle" id="boardTitle" class="form-control" placeholder="제목을 입력하세요"> 
				</div>
				<div class="mb-2">
					<label for="boardContent" class="form-label">내용</label> 
					<textarea rows="8" cols="85" name="boardContent" id="boardContent" class="form-control" placeholder="내용을 입력하세요"></textarea>
				</div>
				<div class="mb-2">
					 <label for="boardFiles" class="form-label">파일</label>
					 <input type="file" name="boardFiles" class="form-control form-control-lg" id="formFileLg" placeholder="다중 파일 등록 가능합니다" multiple>
				</div>
				<div class="mb-2">
					<input type="submit" value="등록" id="boardwrite"  class="btn btn-sm btn-primary">
					<input type="button"  class="btn btn-sm btn-primary" value="게시판으로 돌아가기" id="boardGo" onClick="location.href='board.do'">
				</div>	
			</form>

		
	</div>
	
</body>
</html>
