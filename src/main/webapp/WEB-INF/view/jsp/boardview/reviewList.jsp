<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String boardId=request.getParameter("boardId");
%>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="js/reviewList.js"></script>
    <meta charset="UTF-8">
    <title>게시판 리뷰 목록 페이지</title>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-center mb-4">게시물 리뷰 목록</h2>
        <div id="reviews-list" class="list-group" data-board-id="<%= boardId %>">
           
        </div>
        <a href="boardView.do?boardId=<%=boardId%>" class="btn btn-success">게시판 상세 페이지 보러가기</a>
    </div>
</body>
</html>
