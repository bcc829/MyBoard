<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.login-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.form button:hover,.form button:active,.form button:focus {
	background: #43A047;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

.form .register-form {
	display: none;
}

.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before,.container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

body {
	background: #76b852; /* fallback for old browsers */
	background: -webkit-linear-gradient(right, #76b852, #8DC26F);
	background: -moz-linear-gradient(right, #76b852, #8DC26F);
	background: -o-linear-gradient(right, #76b852, #8DC26F);
	background: linear-gradient(to left, #76b852, #8DC26F);
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="registration-page">
		<div class="form">
			<form class="registration" action="/first/userRegistration/inserUserInfo.do" method="post">
				<input type="text" id="userId" name="USER_ID" placeholder="ID"/> 
				<button type="button" id="check_duplication">ID 중복 확인</button>
				<input type="password" name="PASSWORD" placeholder="password" />
				<input type="text" name="USER_NAME" placeholder="your name" />
				<input type="text" name="ADDRESS" placeholder="address"/>
				<button type="submit" id="submit" style="display : none;">회원 가입</button>
			</form>
		</div>
	</div>
	
<script>
	$(document).ready(function(){

		$("#check_duplication").click(function(){
			var id = $("#userId").val();
	        $.ajax({      
	            type : "GET",
	            url : "rest/user/" + id +".rest",
	            dataType : "text",
	            success : function(data){
	     			data = JSON.parse(data);
	            	//해당하는 아이디의 유저 정보를 받아 올 수 없으므로 반환값은 false
					if(data.result == "false"){
						alert("해당 아이디를 사용 할 수 있습니다.");
						$("#userId").prop("disabled", true);
						$("#check_duplication").hide();
						$("#submit").show();
					}
					else{
						alert("중복된 ID 입니다.");
					}
	            }
	             
	        });

		});
		$("#submit").click(function(){
			alert("회원 가입이 되었습니다.")
			$("#userId").prop("disabled", false);
		})
		
		
	});
</script>	
</body>

</html>