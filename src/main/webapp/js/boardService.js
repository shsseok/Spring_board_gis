$(document).ready(function() {
	$('#boardupdate').click(function() {

		let data = {
			boardTitle : $('#boardTitle').val(),
			boardContent : $('#boardContent').val(),
			boardId : $('#boardId').val(),
			userId:$('#userId').val()
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
		};

		$.ajax({
			type : 'POST',
			url : 'board/Delete.do', // 서버에 요청할 URL
			data : data,
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