<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
	%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>航班到达本站消息</title>
    <link rel="stylesheet" href="<%=basePath %>layui/css/layui.css?t=1545041465480" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">航班动态可视化系统后台</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="<%=basePath %>admin.jsp">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
        <dd><a href="<%=basePath %>page/index.jsp">主页</a></dd>
          <a class="" href="javascript:;">航班消息表</a>
          <dl class="layui-nav-child">
          	<dd><a href="<%=basePath %>page/listSplitDFDL.jsp">航班整表同步事件</a></dd>
              <dd><a href="<%=basePath %>page/listSplitARRE.jsp">航班到达本站消息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitBLLS.jsp">行李提取转盘动态信息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitCANE.jsp">航班取消消息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitCKLS.jsp">值机柜台动态信息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitDEPE.jsp">航班本站起飞消息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitDLYE.jsp">航班延误消息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitGTLS.jsp">航班登机门动态信息</a></dd>
              <dd><a href="<%=basePath %>page/listSplitSTLS.jsp">航班机位动态信息</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">机场资源图表显示</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="../echart/dfdl.jsp">每日航班动态</a>
							</dd>
							<dd>
								<a href="../echart/blls.jsp">行李转盘使用动态</a>
							</dd>
							<dd>
								<a href="../echart/daoligang.jsp">每日航班到离港动态</a>
							</dd>
							<dd>
								<a href="../echart/daoligangdate.jsp">每时刻航班到离港动态</a>
							</dd>
							<dd>
								<a href="../echart/dothing.jsp">每时刻航班延误动态</a>
							</dd>
							<dd>
								<a href="../echart/map.jsp">每日航班迁徙图</a>
							</dd>
						</dl>
					</li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	<div class="demoTable">
  	航班标识标签：
  <div class="layui-inline">
    <input class="layui-input" name="FLID" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
    	<table id='page' lay-filter='demo'></table>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="<%=basePath %>layui/layui.all.js"></script>
<script>
//JavaScript代码区域
layui.use(['element','table','jquery'], function(){
		  var element = layui.element,
		  table = layui.table;
		  table.render({
			  elem:'#page',
			  page:true,
			  width:1300,
			  url:'../ARREServletSearch',
			  cols:[[
				  {	field:'fLID',title:'航班标识标签',width:250,sort: true,fixed:'left',align:'center'},
				  {	field:'fATT',title:'航班属性',width:300,align:'center'},
				  {	field:'fFID',title:'航班标识',width:300,align:'center'},		  
				  {	field:'fSTA',title:'航班发布状态',width:150,align:'center'},
				  {	field:'fRLT',title:'实际到达时间',width:300,align:'center'},
			  ]],
			  id:"testReload"
		  });
		  var $ = layui.$, active = {
				    reload: function(){
				      var demoReload = $('#demoReload');
				      table.reload('testReload', {
				        page: {
				          curr: 1 
				        },where: {
				        	kw: demoReload.val()
				        }
				      });
				    }
				  };
				  $('.demoTable .layui-btn').on('click', function(){
				    var type = $(this).data('type');
				    active[type] ? active[type].call(this) : '';
				  });	  
		});

setInterval(function(){ 
	layui.use(['element','table','jquery'], function(){
		  var element = layui.element,
		  table = layui.table;
		  table.render({
			  elem:'#page',
			  page:true,
			  width:1300,
			  url:'../ARREServletSearch',
			  cols:[[
				  {	field:'fLID',title:'航班标识标签',width:250,sort: true,fixed:'left',align:'center'},
				  {	field:'fATT',title:'航班属性',width:300,align:'center'},
				  {	field:'fFID',title:'航班标识',width:300,align:'center'},		  
				  {	field:'fSTA',title:'航班发布状态',width:150,align:'center'},
				  {	field:'fRLT',title:'实际到达时间',width:300,align:'center'},
			  ]],
			  id:"testReload"
		  });
		  var $ = layui.$, active = {
				    reload: function(){
				      var demoReload = $('#demoReload');
				      table.reload('testReload', {
				        page: {
				          curr: 1 
				        },where: {
				        	kw: demoReload.val()
				        }
				      });
				    }
				  };
				  $('.demoTable .layui-btn').on('click', function(){
				    var type = $(this).data('type');
				    active[type] ? active[type].call(this) : '';
				  });	  
		});
}, 300000);

</script>

</body>
</html>