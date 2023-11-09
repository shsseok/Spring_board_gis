<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="js/login.js"></script>
<meta charset="UTF-8">
<title>게시판 로그인 페이지</title>
<body>
	<div class="container d-flex justify-content-center align-items-center vh-100">
		
		<div id="login-box" class="card shadow-lg w-50">
			<%
				String userid = (String)session.getAttribute("userId");//세션이 있는가 없는가를 알기 위해서
				System.out.println(userid);
			%>

			<%
				if (userid == null) {
			%>
			
			<label class="form-label" for="userId">아이디 <input type="text" class="form-control form-control-lg" name="userId" id="userId" required></label><br>
			<label class="form-label" for="userPassword">패스워드 <input type="password" class="form-control form-control-lg"name="userPassword" id="userPassword" required></label><br>
			<button id="loginButton" class="btn btn-info">로그인</button>
			<input type="button" class="btn btn-success" value="회원가입" onClick="location.href='register.do'">
			<%
				} else {
			%>
				<button onClick="location.href='board.do'" id="boardGo" class="btn btn-info">게시판을 보러 가기</button><br>
				<button onClick="location.href='map.do'" id="mapGo" class="btn btn-info">지도 보러 가기</button><br>
				<button id="logoutButton" class="btn btn-danger">로그아웃</button><br>
				
			<%
				}
			%>

		</div>
	</div>
</body>
</html>