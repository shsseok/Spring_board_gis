<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geomex.notice.model.BoardVo"%>
<%@ page import="geomex.notice.model.FileVo"%>
<%
	String userid = (String) session.getAttribute("userId");
	BoardVo board=(BoardVo)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="js/reviewlist.js"></script>
</head>
<script>
$(document).ready(function() {
	$('#boardupdate').click(function() {

		let data = {
			boardTitle : $('#boardTitle').val(),
			boardContent : $('#boardContent').val(),
			userId : $('#userId').val(),
			boardId : $('#boardId').val()
		};

		$.ajax({
			type : 'POST',
			url : 'board/Update.do', // 서버에 요청할 URL
			data : JSON.stringify(data),
			contentType : 'application/json',
			success : function(response) {
				if (response.result == 1) {
					alert('글이 정상적으로 수정되었습니다.');
					location.href = "board.do"

				} else {
					alert('글 수정에 실패 하였습니다.');

				}
			},
			error : function(err) {
				alert('서버 통신 오류');
			}
		});
	});

	$('#boarddelete').click(function() {

		let data = {
			boardId : $('#boardId').val(),
			userId : $('#userId').val()
		};

		$.ajax({
			type : 'POST',
			url : 'board/Delete.do', // 서버에 요청할 URL
			data : JSON.stringify(data),
			contentType : 'application/json',
			success : function(response) {
				if (response.result == 1) {
					alert('글이 정상적으로 삭제 되었습니다.');
					location.href = "board.do"

				} else {
					alert('글 삭제에 실패 하였습니다.');

				}
			},
			error : function(err) {
				alert('서버 통신 오류');
			}
		});
	});
});


</script>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>

<body>
	<div class="container">
		<h2 class="text-center">게시판 상세 페이지</h2>

				<table class="table table-dark" border=1">
					<tr>
						<td>게시물 번호</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="boardId" id="boardId" value=<%=board.getBoardId()%> readonly></td>
					</tr>
					<tr>
						<td>작성자ID</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="userId" id="userId" value=<%=board.getUserId()%> readonly></td>
					</tr>
					<tr>
						<td>작성자 이름</td>
					</tr>
					<tr>
						<td class="table-primary"><input type="text" name="userName" id="userName"
							value=<%=board.getUserName()%> readonly></td>
					</tr>
					<tr>
						<td>제목</td>
					</tr>
					<tr>
						<td class="table-primary"><input type="text" name="boardTitle" id="boardTitle"
							value=<%=board.getBoardTitle()%>></td>
					</tr>
					<tr>
						<td>내용</td>
					</tr>
					<tr>
						<td class="table-primary"><textarea rows="8" cols="85" name="boardContent"
								id="boardContent"><%=board.getBoardContent()%></textarea></td>
					</tr>
					<%
						ArrayList<FileVo> files = (ArrayList<FileVo>)request.getAttribute("files");
					%>

					<% if(files != null && !files.isEmpty()) { %>
					<tr class="table-primary">
    				<td class="table-primary">첨부파일</td>
    				<td class="table-primary">
       				 <% for(FileVo file : files) { %>
        				<a href="download.do?fileName=<%= file.getStoredFileName() %>" id="fileDouwnload"><%= file.getFileName()%></a><br></td></tr>
        			<% } 
        			}
        			else
					{
					%>
						<td>현재 이 게시물은 첨부 파일이 없습니다</td></tr>
					<% 
					}
					%>
								
					<tr class="table-primary">
						<td><input type="button" value="게시판으로 돌아가기" id="boardGo" onClick="location.href='board.do'">

							<%
							if(userid.equals(board.getUserId()))
							{
								%>
									<input type="button" value="삭제" id="boarddelete">
        							<input type="button" value="수정" id="boardupdate">
								<%
							}

							%>
						<a href="reviewWrite.do?boardId=<%=board.getBoardId()%>" class="btn">댓글 쓰러가기</a></td>

					</tr>
				</table>
		</div>
		<div class="container mt-5">
    	<div class="reviews-section">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th class="text-center">댓글 작성자</th>
                    <th class="text-center">댓글 제목</th>
                </tr>
            </thead>
            <tbody id="reviews-list">
               
            </tbody>
        </table>
    </div>
</div>


</body>
</html>






