$(document).ready(
		function() {			
			$("#reviewUpdate").on(
					"click",
					function() {

						var reviewData = {
							reviewTitle : $("#reviewTitle").val(),
							reviewContent : $("#reviewContent").val(),
							writerId : $("#writerId").val(),
							reviewId : $("#reviewId").val()
						};

						$.ajax({
							url : "review/Update.do",
							method : "POST",
							data : JSON.stringify(reviewData),
							contentType : "application/json",
							success : function(response) {
								if (response.result == 1) {
									alert('리뷰가 성공적으로 수정되었습니다.');
									location.href = 'reviewList.do?boardId='
											+ $("#boardId").val();
								} else {
									alert('리뷰 수정에 실패했습니다.');
								}
							},
							error : function(err) {
								console.error("리뷰 수정 중 오류 발생:", err);
							}
						});
					});

			$("#reviewDelete").on(
					"click",
					function() {

						var reviewData = {
							writerId : $("#writerId").val(),
							reviewId : $("#reviewId").val()
						};

						$.ajax({
							url : "review/Delete.do",
							method : "POST",
							data : JSON.stringify(reviewData),
							contentType : "application/json",
							success : function(response) {
								if (response.result == 1) {
									alert('리뷰가 성공적으로 삭제되었습니다.');
									location.href = 'reviewList.do?boardId='
											+ $("#boardId").val();
								} else {
									alert('리뷰 삭제에 실패s했습니다.');
								}
							},
							error : function(err) {
								console.error("리뷰 삭제 중 오류 발생:", err);
							}
						});
					});
		});