<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String flag = request.getParameter("flag"); %>
<html>
<head>
<meta charset="UTF-8">
<title>登录页</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src="js/jquery.js"></script>
<script src="js/layer/layer.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
<% 
		String username = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("name")){
				username = cookie.getValue();
			}
			if(cookie.getName().equals("password")){
				password = cookie.getValue();
			}
		}
		}
	%>
	<div class="login1">
		<div>
			<p class="text1">管理员登陆</p>
			<p class="text2">航班动态可视化系统后台</p>
		</div>
		<div class="div1">
			<label class="label1"></label><input type="text" name="name" placeholder="用户名" class="name" id="name" value="<%=username %>">
		</div>
		<div class="div1">
			<label class="label2"></label><input type="password" name="password" placeholder="密码" class="password" id="password" value="<%=password %>">
		</div>	
		<div class="yanzhengdiv">
			<label class="label3"></label><input type="text" maxlength="4" name="yanzhengma" placeholder="图形验证码" class="yanzhengma" id="yanzhengma"><img class="img1" src="/test/kaptcha.jpg" />
		</div>		
		<div class="div2">
			<input type="checkbox" name="checkbox" class="checkbox" checked="checked" id="checkbox"><a href="javascript:void(0);" class="a1" onclick="abc()">记住密码</a>
		</div>
		<div>
			<button class="submit" name="submit" id="submit">登陆</button>
		</div>
		<div class="aaa">
			<label class="shejiao">社交账号登陆</label>
			<a href="#"><label class="qq"></label></a>
			<a href="#"><label class="wechat"></label></a>
			<a href="#"><label class="weibo"></label></a>
			<a href="register.jsp"><label class="register">注册账号</label></a>
		</div>
	</div>
	<script>
	function abc(){
		document.getElementById("checkbox").checked=true;
	}
		$(function(){
			$(".img1").on("click", function(){
				$(this).attr("src","/test/kaptcha.jpg?d="+new Date().getTime());
			});
			
			$("#submit").on("click", function(){
				var remember = document.getElementById("checkbox").checked;
				var name = $("#name").val();
				var password = $("#password").val();
				var code = $("#yanzhengma").val();
				
				$.ajax({
					"url" : "/test/loginServlet",
					"type" : "post",
					"data" : {
						"name":name, 
						"password":password, 
						"code":code, 
						"remember": remember
					},
					"dataType" : "json",
					"success" : function(datajson) {
						if(datajson.code == "1"){
							layer.alert('账号密码错误！', {
								icon : 2,
								skin : 'layer-ext-moon' 
							})
						}
						if(datajson.code == "2"){
							layer.alert('验证码错误！', {
								icon : 2,
								skin : 'layer-ext-moon' 
							})
						}
						if(datajson.code == "0"){
							layer.alert('登陆成功！', {
								icon : 1,
								skin : 'layer-ext-moon' 
							})
							setTimeout(function(){ window.location = "page/index.jsp"; }, 2000);
						}
					}
				})
				

			})
		});
	</script>
<script type="text/javascript">
  var flag = '<%=flag%>';
  if("1"==flag){
	  layer.alert('您尚未登陆，或者账号在异地登陆，请重新登录！', {
			icon : 2,
			skin : 'layer-ext-moon' 
		})
  }
</script>
</body>
</html>