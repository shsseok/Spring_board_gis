$(document).ready(
		function() {
			$("#reviewRegister").on(
					"click",
					function() {
						var reviewData = {
							boardId : $("#boardId").val(),
							reviewTitle : $("#reviewTitle").val(),
							reviewContent : $("#reviewContent").val(),
							writerId : $("#writerId").val()
						};
						$.ajax({
							url : "review/Insert.do",
							method : "POST",
							data : JSON.stringify(reviewData),
							contentType : "application/json",
							success : function(response) {
								if (response.result == 1) {
									alert(response.message);
									location.href = 'boardView.do?boardId='
											+ reviewData.boardId;
								} else {
									if (response.error) {
										alert(response.error);
									} else {
										alert(response.message);
									}

								}
							},
							error : function(err) {
								console.error("리뷰 작성 중 오류 발생:", err);
							}
						});
					});
		});