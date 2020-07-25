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
<script src="../js/jquery.js"></script>
<script src="../js/echarts.min.js"></script>
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
				<fieldset>
					<legend>查询条件</legend>
					日期: <select id="select" style="width: 100px; height: 30px;"  onchange="GaiBian(this)">
					</select>
				</fieldset>
				<h2 align="center" id="h2"></h2>
    			<div style="padding: 15px;"><div id="main" style="width:1200px;height:500px;"></div>
			</div>
<script type="text/javascript">
	$.ajax({
		"url":"../DateServlet",
		"type":"post",
		"data":{},
		"dataType":"json",
		"success":function(data){
			var date = data.date;
			$("#select").append("<option value='1'>"+"请选择"+"</option>");
			for(var i=0;i<date.length;i++){       
         	   $("#select").append("<option value='"+date[i]+"'>"+date[i]+"</option>");
            }
		}
	})
	setInterval(function(){
		document.getElementById("select").length=0;
//		$("#select").empty();
		$.ajax({
			"url":"../DateServlet",
			"type":"post",
			"data":{},
			"dataType":"json",
			"success":function(data){
				var date = data.date;
				$("#select").append("<option >"+"请选择"+"</option>");
				for(var i=0;i<date.length;i++){       
	         	   $("#select").append("<option value='"+date[i]+"'>"+date[i]+"</option>");
	            }
			}
		})
	},300000)	
	
	var temp;
	function GaiBian(osel){
    	temp = osel.options[osel.selectedIndex].text;
    	$.post(
                "../dothingServlet",//访问地址
                {"optionval":temp},//携带的参数
                function (data) {
                    generateChart(data);
                },
                "json"
            );
    }
	
	setInterval(function(){
    	$.post(
                "../dothingServlet",//访问地址
                {"optionval":temp},//携带的参数
                function (data) {
                    generateChart(data);
                },
                "json"
            );
	},300000)

	    //生成图标的方法
	    function generateChart(data) {
	    $("#h2").html(temp+"航班延误动态统计表");
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        option = {
        legend: {},
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
                label: {
                    show: true
                }
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        dataset: {
            source: [
                data.listdate,
                data.daogang,
                data.ligang,
                data.yanwu
            ]
        },
        dataZoom: [
            {
                show: true,
                start: 50,
                end: 70
            },
            {
                type: 'inside',
                start: 94,
                end: 100
            },
            {
                show: true,
                yAxisIndex: 0,
                filterMode: 'empty',
                width: 30,
                height: '80%',
                showDataShadow: false,
                left: '93%'
            }
        ],
        xAxis: {type: 'category'},
        yAxis: {gridIndex: 0},
        grid: {top: '55%'},
        series: [
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {type: 'line', smooth: true, seriesLayoutBy: 'row'},
            {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['50%', '25%'],
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                encode: {
                    itemName: 'product',
                    value: '2012',
                    tooltip: '2012'
                }
            }
        ]
    };

    myChart.on('updateAxisPointer', function (event) {
        var xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
            var dimension = xAxisInfo.value + 1;
            myChart.setOption({
                series: {
                    id: 'pie',
                    label: {
                        formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                    },
                    encode: {
                        value: dimension,
                        tooltip: dimension
                    }
                }
            });
        }
    });

     // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);	
		}
	
</script>

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