$(document).ready(function() {
	   function listReviews() {
		   console.log("dd");
	        $.ajax({
	            url: "reviewList/Select.do",
	            method: "POST",
	            data: { boardId: $("#boardId").val() },
	            success: function(response) {
	                if (response && response.reviewList) {
	                    var reviewList = response.reviewList;
	                    console.log(reviewList);
	                    reviewList.forEach(function(review) {
	                        $("#reviews-list").append(
	                            `<tr>
	                                <td>${review.writerId}</td>
	                                <td><span>${review.reviewTitle}<span><button class="btn btn-outline-danger" onClick="location.href='reviewView.do?reviewId=${review.reviewId}'">상세보기</button></td>
	                             </tr>`
	                        );
	                    });
	                }
	            },
	            error: function(err) {
	                console.error("리뷰 불러오기 중 오류 발생:", err);
	            }
	        });
	    }	    
	    listReviews();
});