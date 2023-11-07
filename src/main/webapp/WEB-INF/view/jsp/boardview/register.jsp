<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../css/register.css" rel="stylesheet" type="text/css"/>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>게시판 회원가입 페이지</title>
<script>
function onRegister() {
	console.log("dd");
    var userId = $('#userId').val();
    var userPassword = $('#userPassword').val();
    var userName = $('#userName').val();

    var userInfo = {
        userId: userId,
        userPassword: userPassword,
        userName: userName
    };

    $.ajax({
        type: "POST",
        url: "registerAction.do",
        data: JSON.stringify(userInfo),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(res) {
            console.log(res);
            if (res.result == 1) {
                alert(res.message);
                location.href = 'login.do';
            }
            else
            {
            	if(res.error)
            	{
            		alert(res.error);
            	}
            	else
            	{
            		alert(res.message);
            	}    	
            }
            
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}
</script>
<body>
	<div class="container">
        <div class="row justify-content-center align-items-center" style="height:100vh">
            <div class="col-4">
                <div class="card">
                    <div class="card-body">
                        <h2 class="text-center mb-4">회원가입</h2>
                        <div class="mb-3">
                            <label for="userId" class="form-label">아이디:</label>
                            <input type="text" class="form-control" id="userId" name="userId" required>
                        </div>
                        <div class="mb-3">
                            <label for="userPassword" class="form-label">패스워드:</label>
                            <input type="password" class="form-control" id="userPassword" name="userPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="userName" class="form-label">이름:</label>
                            <input type="text" class="form-control" id="userName" name="userName" required>
                        </div>
                        <div class="mb-2 text-center">
                            <input type="button" class="btn btn-primary" value="가입하기" onClick="onRegister()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

