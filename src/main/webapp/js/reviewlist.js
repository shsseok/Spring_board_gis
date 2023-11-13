$(document).ready(function() {
    var boardId = $("#reviews-list").data("board-id");    
    $.ajax({
        url: "reviewList/Select.do",
        method: "POST",
        data: { boardId: boardId },
        success: function(response) {
        	var reviewCard;
            if (response && response.reviewList) {
                var reviewList = response.reviewList;
                reviewList.forEach(function(review) {
                     reviewCard = `<div class="card mb-3 shadow-sm">
                                          <div class="card-body">
                                              <h5 class="card-title"><strong>리뷰 제목:</strong>${review.reviewTitle}</h5>
                                              <p class="card-text"><strong>작성자:</strong> ${review.writerId}</p>
                                              <p class="card-text"><strong>리뷰 내용:</strong> ${review.reviewContent}</p>
                                              <a href="reviewView.do?reviewId=${review.reviewId}" class="card-link">상세보기</a>
                                          </div>
                                      </div>`;   
                     $("#reviews-list").append(reviewCard);
                });
               
            } 
        },
        error: function(err) {
            console.error("리뷰 불러오기 중 오류 발생:", err);
   
        }
    });
});
