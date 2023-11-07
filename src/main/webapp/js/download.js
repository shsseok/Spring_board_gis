$(document).ready(function() {
    $("#fileDouwnload").click(function(e) {
        e.preventDefault();  // 기본 액션을 방지합니다.

        var fileName = $(this).data("fileName");  // 파일 이름 가져오기
        
        $.ajax({
            url: "download.do",
            method: "GET",
            data: {
                filename: filename
            },
            success: function(response) {
                if (response.status == "success") {
                    alert(response.message);
                } else {
                    alert(response.message);
                }
            },
            error: function(err) {
                alert("서버 통신 오류");
            }
        });
    });
});