<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) session.getAttribute("name");
	String picpath = (String) session.getAttribute("path");
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="js/jquery.js"></script>
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet"
	href="<%=basePath%>layui/css/layui.css?t=1545041465480" media="all">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">航班动态可视化系统后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img" id="picpath">
						<%=username%>
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
					<dd><a href="<%=basePath %>page/index.jsp">主页</a></dd>
					<a class="" href="javascript:;">航班消息表</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath %>page/listSplitDFDL.jsp">航班整表同步事件</a>
							</dd>
							<dd>
								<a href="<%=basePath %>page/listSplitARRE.jsp">航班到达本站消息</a>
							</dd>
							<dd>
								<a href="<%=basePath %>page/listSplitBLLS.jsp">行李提取转盘动态信息</a>
							</dd>
							<dd>
								<a href="<%=basePath %>page/listSplitCANE.jsp">航班取消消息</a>
							</dd>
							<dd>
								<a href="<%=basePath %>page/listSplitCKLS.jsp">值机柜台动态信息</a>
							</dd>
							<dd>
								<a href="<%=basePath %>page/listSplitDEPE.jsp">航班本站起飞消息</a>
							</dd>
							<dd>
								<a href="">航班取消表</a>
							</dd>
							<dd>
								<a href="">航班更换飞机</a>
							</dd>
						</dl></li>
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
			<div class="shouye">
				<div class="float1">
					<img class="img" src="../img/1.jpg" alt="">
				</div>
				<h1>天津滨海国际机场</h1>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;天津滨海国际机场（Tianjin
					Binhai International Airport，IATA：TSN，ICAO：ZBTJ），
					位于中国天津市东丽区，距市中心13公里，为4E级民用国际机场 ，是中国国际航空物流中心
					、国际定期航班机场、对外开放的国家一类航空口岸和中 国主要的航空货运中心之一。</p>

				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;天津滨海国际机场前身为天津张贵庄机场，始建于1939年11月；1950年正式通航；2007年完成一
					期扩建工程；2014年8月完成二期扩建工程。</p>
				<div class="float2">
					<img class="img" src="../img/2.jpg" alt="">
				</div>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;天津滨海国际机场拥有航站楼两座，分别为T1（国际及地区）、T2（国内），总建筑面积36.4万
					平方米、货库面积7.4万平方米；拥有跑道2条，长度分别为3600米、3200米；机位59个。截至2016年底，机场开通航线180条、通航城市132个。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2019年，天津机场完成旅客吞吐量2381.3万人次，同比增长0.9%；货邮吞吐量22.6万吨，同比
					下降12.6%；运输架次16.7万架次，同比下降6.4%。</p>
				<div class="float1">
					<img class="img" src="../img/3.jpg" alt="">
				</div>
				<div class="float1">
					<img class="img" src="../img/4.jpg" alt="">
				</div>
				<div class="float1">
					<img class="img" src="../img/5.jpg" alt="">
				</div>
			</div>

		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
	<script src="<%=basePath%>layui/layui.all.js"></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>