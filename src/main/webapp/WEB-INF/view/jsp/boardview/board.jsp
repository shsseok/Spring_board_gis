<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geomex.notice.model.BoardVo"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<script src="js/boardList.js"></script>
<meta charset="UTF-8">
<title>게시판 테이블 페이지</title>
<body>
	<div class="board-container mt-5 mx-auto p-4" style="max-width: 900px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
		<div class="board-title text-center mb-4">
			<h2>자유로운 게시판</h2>
			<p id="comment">원하는 게시물들을 관람하실 수 있습니다</p>
		</div>

		<div class="board-content">
			<table class="table table-success table-striped table-hover">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>개시 날짜</th>
					<th>작성자 이름</th>
					<th>조회수</th>

				</tr>
				<tbody id="boards-list">
				
				</tbody>
			</table>
		</div>
		<div class="board-option">
    	<div class="input-group mb-3">
       	 <select class="form-select" id="searchType" aria-label="Default select example">
         	   <option value="title" selected>제목</option>
        	    <option value="content">내용</option>
        	    <option value="author">작성자 이름</option>
      	  </select>
       			 <input type="text" id="searchText" class="form-control" placeholder="검색어를 입력하세요" aria-label="Search">
       	 		 <button id="search-btn" class="btn btn-secondary" type="button">검색</button>
    	</div>
		
			<input type="button" id="write"  class="btn btn-danger" value="글쓰기" onClick="location.href='boardWrite.do'">
			<button id="prev-btn" class="btn btn-primary">이전</button>
    		<span id="pageList"></span>
    		<button id="next-btn" class="btn btn-primary">다음</button>
    		<button onClick="location.href='login.do'" class="btn btn-primary">메인으로 가기</button>
    		
		</div>
		
	</div>
	<script>
</script>
</body>
</html>