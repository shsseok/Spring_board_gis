<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script src="js/boardService.js"></script>
    <script src="js/bookmark.js"></script>
    <meta charset="UTF-8">
    <title>게시판 상세 페이지</title>
</head>
<body>
    <div class="container my-5">
        <h2 class="text-center mb-4">게시판 상세 페이지</h2>
        <div class="card">
            <div class="card-body">
                <div class="mb-3">
                    <label class="form-label">게시물 번호</label>
                    <input type="text" class="form-control" id="boardId" value="<%=board.getBoardId()%>" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">작성자ID</label>
                    <input type="text" class="form-control" id="userId" value="<%=board.getUserId()%>" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">작성자 이름</label>
                    <input type="text" class="form-control" id="userName" value="<%=board.getUserName()%>" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">제목</label>
                    <input type="text" class="form-control" id="boardTitle" value="<%=board.getBoardTitle()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label">내용</label>
                    <input type="text" class="form-control" id="boardContent" value="<%=board.getBoardContent()%>">
                </div>
            </div>
        </div>

        <!-- 파일 목록 및 버튼 구성 -->
        <!-- 파일 목록 및 버튼 구성 -->
<!-- 파일 목록 및 버튼 구성 -->
<div class="mt-4">
    <h3 class="mb-3">첨부파일</h3>
    <div class="d-flex flex-wrap">
    <%
        ArrayList<FileVo> files = (ArrayList<FileVo>)request.getAttribute("files");
        if(files != null && !files.isEmpty()) { 
            for(FileVo file : files) {
    %>
                <div class="p-2">
                    <a href="download.do?fileName=<%= file.getStoredFileName() %>" class="btn btn-primary mb-2"><%= file.getFileName()%></a>
                </div>
    <%
            } 
        } else {
    %>
        <p>현재 이 게시물은 첨부 파일이 없습니다</p>
    <%
        }
    %>
    </div>
</div>       
        <div class="mt-4 text-center">
            <a href="board.do" class="btn btn-secondary">게시판으로 돌아가기</a>
            <% if(userid.equals(board.getUserId())) { %>
                <button type="button" id="boarddelete" class="btn btn-danger">삭제</button>
                <button type="button" id="boardupdate" class="btn btn-info">수정</button>
            <% } else { %>
                <button type="button" id="bookmarkToggle" class="btn btn-warning">즐겨찾기</button>
            <% } %>
            <a href="reviewWrite.do?boardId=<%=board.getBoardId()%>" class="btn btn-success">댓글 쓰러가기</a>
            <a href="reviewList.do?boardId=<%=board.getBoardId()%>" class="btn btn-success">리뷰 목록 보러가기</a>
        </div>      
</div>


</body>
</html>






