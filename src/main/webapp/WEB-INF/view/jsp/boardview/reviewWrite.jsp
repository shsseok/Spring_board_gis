<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId"); //현재 로그인하고 있는 사람이 작성
	int boardId = Integer.parseInt(request.getParameter("boardId"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<script src="js/reviewRegister.js"> </script>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="card">
                    <div class="card-body">
                        <h2 class="text-center mb-4">리뷰 작성하기</h2>
                       
                            <div class="mb-3">
                                <label for="boardId" class="form-label">게시물 번호</label>
                                <input type="text" class="form-control" id="boardId" name="boardId" value="<%=boardId%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="writerId" class="form-label">작성자ID</label>
                                <input type="text" class="form-control" id="writerId" name="writerId" value="<%=userId%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="reviewTitle" class="form-label">제목:</label>
                                <input type="text" class="form-control" id="reviewTitle" name="reviewTitle" required>
                            </div>
                            <div class="mb-3">
                                <label for="reviewContent" class="form-label">내용:</label>
                                <textarea class="form-control" id="reviewContent" name="reviewContent" rows="5" required></textarea>
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-primary" id="reviewRegister">댓글 등록</button>
                            </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
