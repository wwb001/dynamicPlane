<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Leaflet 加载 Echarts3</title>
<link rel="stylesheet" href="../echarts/leaflet.css">
<link rel="stylesheet"
	href="<%=basePath%>layui/css/layui.css?t=1545041465480" media="all">
<!-- 引入 ECharts 文件 -->
<script src="<%=request.getContextPath()%>/js/echarts.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<style>
html, body, #map {
	height: 100%;
	padding: 0;
	margin: 0;
}

#forkongithub a {
	background: #000;
	color: #fff;
	text-decoration: none;
	font-family: arial, sans-serif;
	text-align: center;
	font-weight: bold;
	padding: 5px 40px;
	font-size: 1rem;
	line-height: 2rem;
	position: relative;
	transition: 0.5s;
}

#forkongithub a:hover {
	background: #c11;
	color: #fff;
}

#forkongithub a::before, #forkongithub a::after {
	content: "";
	width: 100%;
	display: block;
	position: absolute;
	top: 1px;
	left: 0;
	height: 1px;
	background: #fff;
}

#forkongithub a::after {
	bottom: 1px;
	top: auto;
}

@media screen and (min-width:800px) {
	#forkongithub {
		position: fixed;
		display: block;
		top: 0;
		right: 0;
		width: 200px;
		overflow: hidden;
		height: 200px;
		z-index: 9999;
	}
	#forkongithub a {
		width: 200px;
		position: absolute;
		top: 60px;
		right: -60px;
		transform: rotate(45deg);
		-webkit-transform: rotate(45deg);
		-ms-transform: rotate(45deg);
		-moz-transform: rotate(45deg);
		-o-transform: rotate(45deg);
		box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.8);
	}
}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">航班动态可视化系统后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
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
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> 贤心
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="<%=basePath%>admin.jsp">退了</a></li>
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
			<div id="map"></div>
			<script src="../echarts/main.min.js"></script>
			<script>
				var map = L.map('map');
				var baseLayers = {
					"高德地图" : L
							.tileLayer(
									'http://webrd0{s}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=8&x={x}&y={y}&z={z}',
									{
										subdomains : "1234"
									}),
					'高德影像' : L
							.layerGroup([
									L
											.tileLayer(
													'http://webst0{s}.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}',
													{
														subdomains : "1234"
													}),
									L
											.tileLayer(
													'http://t{s}.tianditu.cn/DataServer?T=cta_w&X={x}&Y={y}&L={z}',
													{
														subdomains : "1234"
													}) ]),
					"立体地图" : L
							.tileLayer(
									'https://a.tiles.mapbox.com/v3/examples.c7d2024a/{z}/{x}/{y}.png',
									{
										attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
										key : 'BC9A493B41014CAABB98F0471D759707',
										styleId : 22677
									}),
					"Foursquare" : L
							.tileLayer(
									'https://a.tiles.mapbox.com/v3/foursquare.map-0y1jh28j/{z}/{x}/{y}.png',
									{
										attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
										key : 'BC9A493B41014CAABB98F0471D759707',
										styleId : 22677
									}),
					'GeoQ灰色底图' : L
							.tileLayer(
									'http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}')
							.addTo(map)
				};
				L
						.tileLayer(
								'https://a.tiles.mapbox.com/v3/foursquare.map-0y1jh28j/{z}/{x}/{y}.png',
								{
									attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
									key : 'BC9A493B41014CAABB98F0471D759707',
									styleId : 22677
								});
				var layercontrol = L.control.layers(baseLayers, {}, {
					position : "topleft"
				}).addTo(map);
				map.setView(L.latLng(37.550339, 104.114129), 4);
				var overlay = new L.echartsLayer3(map, echarts);
				var chartsContainer = overlay.getEchartsContainer();
				var myChart = overlay.initECharts(chartsContainer);
				var geoCoordMap = {
					'阿克苏' : [ 80.29042285945036, 41.2616781521119 ],
					'阿勒泰' : [ 88.08930833333334, 47.74985833333334 ],
					'安康' : [ 108.9324361669054, 32.70900522244992 ],
					'安庆' : [ 117.0480887539759, 30.58241688258063 ],
					'保山' : [ 99.16731799176861, 25.05230538365678 ],
					'包头' : [ 109.9980811762962, 40.56081752709947 ],
					'昌都' : [ 97.11066233144705, 30.55107210167363 ],
					'北海' : [ 109.2908255932122, 21.53624018324877 ],
					'北京' : [ 116.5883553580003, 40.07680509701226 ],
					'长春' : [ 125.685278, 43.996 ],
					'常德' : [ 111.6400197118735, 28.91898211204387 ],
					'长沙' : [ 113.2186399439183, 28.19099518464814 ],
					'长治' : [ 113.1259055555556, 36.24876666666667 ],
					'常州' : [ 119.7783840779406, 31.91771923777169 ],
					'朝阳' : [ 120.473352444858, 41.55512170832727 ],
					'成都' : [ 103.9505139224111, 30.57936972258302 ],
					'赤峰' : [ 118.9076583676409, 42.23489527965609 ],
					'重庆' : [ 106.6400729259405, 29.72018042111331 ],
					'达州' : [ 107.427498, 31.12833399999999 ],
					'大阪' : [ 135.441162, 34.7919847 ],
					'大连' : [ 121.5410760513244, 38.96311731483182 ],
					'大理' : [ 100.3209347947209, 25.64892136854264 ],
					'丹东' : [ 124.2851220224851, 40.02268144904562 ],
					'大同' : [ 113.4844201665012, 40.05824702316217 ],
					'张家界' : [ 110.4450036709215, 29.10361089511774 ],
					'敦煌' : [ 94.8022852614359, 40.1617338502801 ],
					'恩施' : [ 109.4826969380089, 30.32137199295715 ],
					'阜阳' : [ 115.7345860864405, 32.88220491698386 ],
					'福州' : [ 119.666522622269, 25.93305257146096 ],
					'赣州' : [ 114.9119338617848, 25.82569437225301 ],
					'广州' : [ 113.2968270569367, 23.38822689623794 ],
					'桂林' : [ 110.0413469105834, 25.22196616108465 ],
					'贵阳' : [ 106.8014386900009, 26.53922897425328 ],
					'哈尔滨' : [ 126.2447011670925, 45.62744684475938 ],
					'海口' : [ 110.4607005227169, 19.93806282594675 ],
					'杭州' : [ 120.4290755435234, 30.23261027360853 ],
					'汉中' : [ 107.0083422079489, 33.06420043066169 ],
					'合肥' : [ 117.3012278883016, 31.78074823836898 ],
					'黑河' : [ 127.3102541084192, 50.17878140158385 ],
					'香港' : [ 113.9271022787075, 22.31277665324488 ],
					'黄山' : [ 118.2561456972944, 29.73373208093955 ],
					'台州' : [ 121.4277712718143, 28.56238464218763 ],
					'呼和浩特' : [ 111.8258621693542, 40.85119780816149 ],
					'吉安' : [ 114.766839, 26.86107700000001 ],
					'佳木斯' : [ 130.4628214996785, 46.84243743397011 ],
					'济南' : [ 117.20914, 36.86070 ],
					'晋江' : [ 118.5902901669336, 24.79782867703928 ],
					'锦州' : [ 121.0634195084268, 41.10074227231737 ],
					'九江' : [ 115.8144478870376, 29.50771840065325 ],
					'昆明' : [ 102.7428071318929, 24.99300941269912 ],
					'兰州' : [ 103.620847852405, 36.51702741983628 ],
					'临沧' : [ 100.024487, 23.73739300000001 ],
					'连云港' : [ 118.8766942219648, 34.57245979350974 ],
					'丽江' : [ 100.2451878232407, 26.67699026477684 ],
					'临沂' : [ 118.4121061306999, 35.04869887991676 ],
					'洛阳' : [ 112.386811474446, 34.73954750937665 ],
					'澳门' : [ 113.5874455714736, 22.14326067871405 ],
					'绵阳' : [ 104.7432103971671, 31.42781985735502 ],
					'南昌' : [ 115.9029048093558, 28.86047626056136 ],
					'南充' : [ 106.0658152110783, 30.75587298269567 ],
					'南京' : [ 118.8680117050873, 31.7389999307536 ],
					'南宁' : [ 108.1703976044503, 22.61117113074829 ],
					'南通' : [ 120.9744042331402, 32.07116087972219 ],
					'南阳' : [ 112.6159532492567, 32.98102156187935 ],
					'宁波' : [ 121.461906, 29.82668299999 ],
					'青岛' : [ 120.3768990925031, 36.26295088720078 ],
					'三亚' : [ 109.4106917632219, 18.30595783104419 ],
					'上海虹桥' : [ 121.3382940938617, 31.19680580674751 ],
					'上海浦东' : [ 121.7998542277154, 31.14212328392675 ],
					'汕头' : [ 116.7597380276957, 23.42775356512007 ],
					'深圳' : [ 113.8095777004192, 22.64026863629767 ],
					'沈阳' : [ 123.4871834214191, 41.63752326693739 ],
					'石家庄' : [ 114.6961910813239, 38.27648705423538 ],
					'太原' : [ 112.631972387512, 37.7500035598485 ],
					'通化' : [ 125.70472, 42.25500100000001 ],
					'通辽' : [ 122.2021588197106, 43.55762078550471 ],
					'铜仁' : [ 109.305445, 27.881975 ],
					'万州' : [ 108.426958, 30.799787 ],
					'潍坊' : [ 119.1170488179701, 36.64581064379052 ],
					'威海' : [ 122.2296069804696, 37.18685705175377 ],
					'温州' : [ 120.8511693500449, 27.91155801172009 ],
					'武汉' : [ 114.2089958910336, 30.78097791389272 ],
					'乌兰浩特' : [ 122.0044441213782, 46.19599901296927 ],
					'乌鲁木齐' : [ 87.47568611, 43.90544444 ],
					'厦门' : [ 118.1305904088776, 24.5453359174946 ],
					'西安' : [ 108.7539573819522, 34.44560041619356 ],
					'襄樊' : [ 112.2907202470285, 32.14851276223575 ],
					'锡林浩特' : [ 115.9660252855755, 43.91615269813289 ],
					'西宁' : [ 102.039342, 36.525114 ],
					'延安' : [ 109.5525470474416, 36.63645368765424 ],
					'烟台' : [ 121.3697031128856, 37.40083313585384 ],
					'宜宾' : [ 104.5445662621883, 28.80087212347459 ],
					'宜昌' : [ 111.4803900939728, 30.55416632105598 ],
					'银川' : [ 106.3910611111111, 38.32308055555556 ],
					'义乌' : [ 120.0328528395844, 29.3449941792821 ],
					'郑州' : [ 113.8404790442855, 34.51938822783202 ],
					'珠海' : [ 113.3751532852782, 22.01011161127402 ],
					'台北松山' : [ 121.551513, 25.063631 ],
					'桃园' : [ 121.2387998695946, 25.08185919608926 ],
					'乌海' : [ 106.7983576880971, 39.79224297114562 ],
					'满洲里' : [ 117.3297148379127, 49.56937049374628 ],
					'怀化' : [ 109.69986, 27.433946 ],
					'盐城' : [ 120.2154040604229, 33.4271825601598 ],
					'东营' : [ 118.784721, 37.514446 ],
					'天水' : [ 105.860052, 34.55683 ],
					'兴义' : [ 104.971275, 25.157094 ],
					'遵义' : [ 106.92738899999995, 27.725654 ],
					'惠州' : [ 114.41619600000001, 23.111847 ],
					'大庆' : [ 125.10463700000003, 46.59019 ],
					'固原' : [ 106.24261000000001, 36.015855 ],
					'淮安' : [ 119.01528800000006, 33.61036 ],
					'新加坡' : [ 103.987660, 1.35627194 ],
					'首尔仁川' : [ 126.451364, 37.4481032 ],
					'舟山普陀山' : [ 122.364799, 29.933226 ],
					'海拉尔' : [ 119.7000, 49.2000 ],
					'阿姆斯特丹' : [ 4.76310455, 52.3082091 ],
					'甲米' : [ 98.9167, 8.0667 ],
					'那霸' : [ 23.8167, -1.1833 ],
					'青森' : [ 145.7667, -7.9667 ],
					'安克雷奇' : [ -149.98228, 61.1747719 ],
					'哥本哈根' : [ 12.6477797, 55.6293513 ],
					'吉隆坡' : [ 101.707405, 2.74981526 ],
					'莫斯科' : [ 37.2757557, 55.6040262 ],
					'曼德勒' : [ 96.0909979, 21.9409841 ],
					'东京羽田' : [ 139.783877, 35.5524122 ],
					'沈阳' : [ 123.483806, 41.6326895 ],
					'名古屋' : [ 136.814967, 34.8604386 ],
					'东京成田' : [ 140.387067, 35.7739533 ],
					'泉州' : [ 118.5833, 24.9000 ],
					'天津滨海国际机场' : [ 117.335368, 39.1266173 ],
				};

				var BJData = [ [ {
					name : '天津滨海国际机场'
				}, {
					name : '杭州',
					value : 5
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '兰州',
					value : 4
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '长沙',
					value : 4
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '西安',
					value : 10
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '昆明',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '上海浦东',
					value : 5
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '成都',
					value : 7
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '沈阳',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '南通',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '东京羽田',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '南京',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '大连',
					value : 7
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '青岛',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '新加坡',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '哈尔滨',
					value : 6
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '首尔仁川',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '广州',
					value : 7
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '长春',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '深圳',
					value : 6
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '武汉',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '泉州',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '上海虹桥',
					value : 7
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '重庆',
					value : 7
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '宁波',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '温州',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '烟台',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '丽江',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '澳门',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '威海',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '临沂',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '赤峰',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '包头',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '义乌',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '名古屋',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '乌鲁木齐',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '贵阳',
					value : 4
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '海口',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '东京成田',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '呼和浩特',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '固原',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '银川',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '厦门',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '固原',
					value : 3
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '桂林',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '台北松山',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '珠海',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '南宁',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '郑州',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '太原',
					value : 2
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '福州',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '大阪',
					value : 1
				} ], [ {
					name : '天津滨海国际机场'
				}, {
					name : '铜仁',
					value : 1
				} ], ];

				var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

				var convertData = function(data) {
					var res = [];
					for (var i = 0; i < data.length; i++) {
						var dataItem = data[i];
						var fromCoord = geoCoordMap[dataItem[0].name];
						var toCoord = geoCoordMap[dataItem[1].name];
						if (fromCoord && toCoord) {
							res.push([ {
								coord : fromCoord
							}, {
								coord : toCoord
							} ]);
						}
					}
					return res;
				};

				var color = [ '#a6c84c', '#ffa022', '#46bee9' ];
				var series = [];
				[ [ '天津滨海国际机场', BJData ] ].forEach(function(item, i) {
					series.push({
						name : item[0] + ' Top10',
						type : 'lines',
						zlevel : 1,
						effect : {
							show : true,
							period : 6,
							trailLength : 0.7,
							color : '#fff',
							symbolSize : 3
						},
						lineStyle : {
							normal : {
								color : color[i],
								width : 0,
								curveness : 0.2
							}
						},
						data : convertData(item[1])
					}, {
						name : item[0] + ' Top10',
						type : 'lines',
						zlevel : 2,
						effect : {
							show : true,
							period : 6,
							trailLength : 0,
							symbol : planePath,
							symbolSize : 15
						},
						lineStyle : {
							normal : {
								color : color[i],
								width : 1,
								opacity : 0.4,
								curveness : 0.2
							}
						},
						data : convertData(item[1])
					}, {
						name : item[0] + ' Top10',
						type : 'effectScatter',
						coordinateSystem : 'geo',
						zlevel : 2,
						rippleEffect : {
							brushType : 'stroke'
						},
						label : {
							normal : {
								show : true,
								position : 'right',
								formatter : '{b}'
							}
						},
						symbolSize : function(val) {
							return val[2] / 8;
						},
						itemStyle : {
							normal : {
								color : color[i]
							}
						},
						data : item[1].map(function(dataItem) {
							return {
								name : dataItem[1].name,
								value : geoCoordMap[dataItem[1].name]
										.concat([ dataItem[1].value ])
							};
						})
					});
				});

				option = {
					visualMap : {
						min : 0,
						max : 10,
						splitNumber : 5,
						color : [ '#d94e5d', '#eac736', '#50a3ba' ],
						textStyle : {
							color : '#fff'
						}
					},
					//        backgroundColor: '#404a59',
					title : {
						text : '天津滨海国际机场6月1日计划飞往各地航班迁徙图',
						subtext : 'CAUC',
						left : 'center',
						textStyle : {
							color : '#fff'
						}
					},
					tooltip : {
						trigger : 'item'
					},
					legend : {
						orient : 'vertical',
						top : 'bottom',
						left : 'right',
						data : [ '天津滨海国际机场 每日飞往各地航班' ],
						textStyle : {
							color : '#fff'
						},
						selectedMode : 'single'
					},
					geo : {
						map : '',
						label : {
							emphasis : {
								show : false
							}
						},
						roam : true,
						itemStyle : {
							normal : {
								areaColor : '#323c48',
								borderColor : '#404a59'
							},
							emphasis : {
								areaColor : '#2a333d'
							}
						}
					},
					series : series
				};
				// 使用刚指定的配置项和数据显示图表。
				overlay.setOption(option);
			</script>
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