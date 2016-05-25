<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="resources/js/common/jquery-1.12.4.js"></script>
<title>app</title>
</head>
<body>
	<h2>Hello World! 请登录</h2>
	<form action="dologin.do" method="post">
		<input type="text" name="username" id="username"/><br>
		<input type="password" name="password" id="password" />
		<input type="submit"  id="tijiao" value="登录" />
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$('#tijiao').click(function(){
			var username = $('#username').val();
			var password = $('#password').val();
			$.ajax({
				  type: 'post',
				  url: 'dologin.do',
				  contentType : 'application/json', 
				  data: {
					  "username":username,
					  "password":password
				  },
				  success: function(){
					  
				  }
				});
		});
	});

</script>
</html>
