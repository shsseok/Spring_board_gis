<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="geomex.notice.model.ReviewVo"%>
 <%
	String userid = (String) session.getAttribute("userId");	
	ReviewVo review=(ReviewVo)request.getAttribute("review");
 %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>

<script>
$(document).ready(function() {
    $("#reviewUpdate").on("click", function() {
    
        var reviewData = {          
        	reviewTitle: $("#reviewTitle").val(),
            reviewContent: $("#reviewContent").val(),
            writerId: $("#writerId").val(),
            reviewId: $("#reviewId").val()
        };

        $.ajax({
            url: "review/Update.do",
            method: "POST",
            data: JSON.stringify(reviewData),
            contentType: "application/json",
            success: function(response) {
                if (response.result==1) {
                    alert('리뷰가 성공적으로 수정되었습니다.');
                    location.href = 'boardView.do?boardId=' + $("#boardId").val();
                } else {
                    alert('리뷰 수정에 실패했습니다.');
                }
            },
            error: function(err) {
                console.error("리뷰 수정 중 오류 발생:", err);
            }
        });
    });

    $("#reviewDelete").on("click", function() {
    	
        var reviewData = {
        	writerId: $("#writerId").val(),
            reviewId: $("#reviewId").val()
        };

        $.ajax({
            url: "review/Delete.do",
            method: "POST",
            data: JSON.stringify(reviewData),
            contentType: "application/json",
            success: function(response) {
                if (response.result==1) {
                    alert('리뷰가 성공적으로 삭제되었습니다.');
                    location.href = 'boardView.do?boardId=' + $("#boardId").val();
                } else {
                    alert('리뷰 삭제에 실패했습니다.');
                }
            },
            error: function(err) {
                console.error("리뷰 삭제 중 오류 발생:", err);
            }
        });
    });
});
</script>
<meta charset="UTF-8">
<title>리뷰 상세 페이지</title>

<body>
<div class="container">
		<h2 class="text-center">댓글 상세 목록</h2>
		
				<table class="table" border=1>
					<tr>
						<td>댓글 번호</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="reviewId" id="reviewId"
							value=<%=review.getReviewId()%> readonly></td>
					</tr>
					<tr>
						<td>댓글 단 게시물 번호</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="boardId" id="boardId"
							value=<%=review.getBoardId()%> readonly></td>
					</tr>
					<tr>
						<td>댓글 작성자ID</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="writerId" id="writerId"
							value=<%=review.getWriterId()%> readonly></td>
					</tr>
					<tr>
						<td>댓글 제목</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><input type="text" name="reviewTitle" id="reviewTitle"
							value=<%=review.getReviewTitle()%>></td>
					</tr>
					<tr>
						<td>댓글 내용</td>
					</tr>
					<tr class="table-primary">
						<td class="table-primary"><textarea rows="8" cols="85" name="reviewContent"
								id="reviewContent"><%=review.getReviewContent()%></textarea></td>
					</tr>
					<tr>
						<td><input type="button" class="btn btn-sm btn-primary" value="게시판 상세 페이지로 돌아가기" id="boardViewgo" onClick="location.href='boardView.do?boardId=<%=review.getBoardId()%>'">

							<%
							if(userid.equals(review.getWriterId()))
							{
								%>
									<input type="button" class="btn btn-danger" value="삭제" id="reviewDelete">
        							<input type="button" class="btn btn-sm btn-primary" value="수정" id="reviewUpdate">
								<%
							}

							%>
							</td>
					</tr>
				</table>
	</div>
</body>

</html>