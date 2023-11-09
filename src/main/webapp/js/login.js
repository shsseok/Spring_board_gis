$(document).ready(function() {

	//로그인
    $("#loginButton").on("click", function() {
        console.log("dd");
    	var loginData = {
            userId: $("#userId").val(),
            userPassword: $("#userPassword").val()
        };

        $.ajax({
            url: "loginAction.do",
            method: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json",
            success: function(response) {
                if (response.result == true) {
                    alert('로그인에 성공하셨습니다.');
                    location.href = 'login.do';
                } else { 
                    alert('로그인에 실패하였습니다.');
                    location.href = 'login.do';
                }
            },
            error: function(err) {
                console.error("로그인 중 오류 발생:", err);
            }
        });
    });

    // 로그아웃
    $("#logoutButton").on("click", function() {
        $.ajax({
            url: "logoutAction.do",
            method: "POST",
            success: function(response) {
                if (response.result == 1) {
                    alert('로그아웃 되었습니다.');
                    location.href = 'login.do';
                }
            },
            error: function(err) {
                console.error("로그아웃 중 오류 발생:", err);
            }
        });
    });

});