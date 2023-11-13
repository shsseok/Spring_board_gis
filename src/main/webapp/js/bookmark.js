$(document).ready(function() {
    var boardId = $('#boardId').val(); 
    var bookmarkBtn = $('#bookmarkToggle');
    var isBookmarked = false;

    // 북마크 상태 확인
    checkBookmarkStatus();

    // 북마크 버튼 클릭 이벤트
    bookmarkBtn.click(function() {
    	var method = isBookmarked ? 'DELETE' : 'POST';
        var url = 'bookmarks/' + boardId+'.do';

        $.ajax({
            type: method,
            url: url,
            success: function(response) {
                isBookmarked = !isBookmarked;
                updateBookmarkButton();
                alert(response);
            },
            error: function() {
                alert('북마크 처리 중 문제가 발생했습니다.');
            }
        });
    });

    // 북마크 상태 확인
    function checkBookmarkStatus() {
        $.ajax({
            type: 'GET',
            url: 'bookmarks/' + boardId + '/findBySingle.do',
            success: function(response) {
                isBookmarked = response;
                updateBookmarkButton();
            },
            error: function() {
                alert('북마크 상태 확인 중 문제가 발생했습니다.');
            }
        });
        
    }

    // 버튼 상태 업데이트
    function updateBookmarkButton() {
        if (isBookmarked) {
            bookmarkBtn.text('즐겨찾기 해제');
            bookmarkBtn.removeClass('btn-warning').addClass('btn-success');
        } else {
            bookmarkBtn.text('즐겨찾기 추가');
            bookmarkBtn.removeClass('btn-success').addClass('btn-warning');
        }
    }
});
