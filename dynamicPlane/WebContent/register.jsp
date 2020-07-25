<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>注册页</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="js/jquery.js"></script>
<script src="js/layer/layer.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
	<div class="register">
		<div>
			<p class="text1">管理员注册</p>
			<p class="text2">航班动态可视化系统后台</p>
		</div>
		<div class="div1">
			<label class="label1"></label><input type="text" name="phone"
				placeholder="手机" class="phone" id="phone">
		</div>
		<div class="div1">
			<label class="label2"></label><input type="password" name="password"
				placeholder="密码" class="password" id="password">
		</div>
		<div class="div1">
			<label class="label3"></label><input type="password"
				name="repassword" placeholder="确认密码" class="repassword"
				id="repassword">
		</div>
		<div class="div1">
			<label class="label4"></label><input type="text" name="name"
				placeholder="用户名" class="name" id="name">
		</div>
		<div class="layui-upload">
			<button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>上传头像</button>
			<div class="layui-upload-list" id="img">
				<img class="layui-upload-img" id="demo1" width="90px" height="80px">
				<p id="demoText"></p>
			</div>
		</div>
		<div class="div2">
			<input type="checkbox" name="checkbox" class="checkbox" id="checkbox"><a href="javascript:void(0);" class="a1" onclick="abc()">同意用户协议</a>
		</div>
		<div>
			<button class="submit" name="submit" id="submit">注册</button>
		</div>
		<div class="aaa">
			<label class="shejiao">社交账号注册</label> <a href="#"><label
				class="qq"></label></a> <a href="#"><label class="wechat"></label></a> <a
				href="#"><label class="weibo"></label></a> <a href="login.jsp"><label
				class="login">用已有账号登入</label></a>
		</div>
	</div>
</body>
<script type="text/javascript">
function abc(){
	document.getElementById("checkbox").checked=true;
}

var path;	
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
			  var uploadInst = upload.render({
	    elem: '#test1'
	    ,url: '/test/uploadServlet' //改成您自己的上传接口
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo1').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	      //如果上传失败
	      if(res.code > 0){
	        return layer.msg('上传失败');
	      } else{
	    	  path = res.path;
	      }
	    },
	    error: function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      });
	    }
	  });
			})
	var btn = document.getElementById("submit");
	btn.addEventListener("click", function() {
		var phone = document.getElementById("phone").value;
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		var name = document.getElementById("name").value;
		
		if (phone == "") {
			layer.msg('手机号不能为空', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		} else if (password == "") {
			layer.msg('密码不能为空', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		} else if (repassword == "") {
			layer.msg('确认密码不能为空', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		} else if (name == "") {
			layer.msg('昵称不能为空', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		} else if (password != repassword) {
			layer.msg('两次输入密码不一致', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		} else if (document.getElementById("checkbox").checked == false){
			layer.msg('请同意用户协议', {
				icon : 5,
				shade : [ 0.5, '#000000' ],
				shadeClose : true
			});
		}
		else {
			var ii = layer.load();
			setTimeout(function() {
				layer.close(ii);
			}, 1000);
			$.ajax({
				"url" : "/test/registerServlet",
				"type" : "post",
				"data" : {
					"tel" : phone,
					"pwd" : password,
					"name" : name,
					"path" : path
				},
				"dataType" : "json",
				"success" : function(json) {
					if (json.msg == "1") {
						layer.alert('注册成功！', {
							icon : 1,
							time: 3000,
							skin : 'layer-ext-moon' 
						})
						setTimeout(function(){ window.location = "login.jsp"; }, 2000);
						
					}else{
						layer.alert('已有相同手机号或者用户名！', {
							icon : 2,
							skin : 'layer-ext-moon' 
						})
					}
				}
			})
		}
	})
</script>
</html>