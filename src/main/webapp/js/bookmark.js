$(document).ready(function() {
    var isBookmarked = false; 

    
    updateBookmarkButton();

    $('#bookmarkToggle').click(function() {
        var boardId = $('#boardId').val(); 
        var url = isBookmarked ? '/board/' + boardId + '/RemoveBookmark' : '/board/' + boardId + '/AddBookmark';

        $.ajax({
            type: 'POST',
            url: url,
            success: function(response) {
                isBookmarked = !isBookmarked;
                updateBookmarkButton();
                alert(isBookmarked ? '북마크 되었습니다.' : '북마크가 해제되었습니다.');
            },
            error: function() {
                alert('북마크 처리 중 문제가 발생했습니다.');
            }
        });
    });

    function updateBookmarkButton() {
        var $bookmarkBtn = $('#bookmarkToggle');
        if (isBookmarked) {
            $bookmarkBtn.text('즐켜찾기 해제');
            $bookmarkBtn.removeClass('btn-warning').addClass('btn-success');
        } else {
            $bookmarkBtn.text('하기');
            $bookmarkBtn.removeClass('btn-success').addClass('btn-warning');
        }
    }
});

