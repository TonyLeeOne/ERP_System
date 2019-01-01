<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-ERP管理系统1.0</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/xadmin.css">
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up">
        <fieldset class="layui-elem-field">
            <legend>系统状态</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside"
                                 lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs4">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h2>总体状态</h2>
                                                <cite id="status"></cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs4">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h2>磁盘状态</h2>
                                                <cite id="disk"></cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs4">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h2>数据库状态</h2>
                                                <cite id="db"></cite>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>未完成的订单数</h3>
                                                <p>
                                                    <cite>${dataEntity.orderCount}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>仓库产品数量</h3>
                                                <p>
                                                    <cite>${dataEntity.productCount}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>进行中的生产计划</h3>
                                                <p>
                                                    <cite>${dataEntity.manPlanCount}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>进行中的生产工单</h3>
                                                <p>
                                                    <cite>${dataEntity.manOrderCount}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>合作客户数量</h3>
                                                <p>
                                                    <cite>${dataEntity.customCount}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>本应用用户数量</h3>
                                                <p>
                                                    <cite>${dataEntity.userCount}</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>

        <fieldset class="layui-elem-field">
            <legend>状态盘点</legend>
            <div class="layui-field-box">
                <div id="order_chart" style="width: 48%;height:400px; float: left;text-align: center;"></div>
                <div id="product_chart" style="width: 48%;height:400px;float: right;text-align: center"></div>
                <div id="plan_chart" style="width: 48%;height:400px;float: left;text-align: center"></div>
                <div id="material_chart" style="width: 48%;height:400px;float: right;text-align: center"></div>
            </div>
        </fieldset>
        <%--<fieldset class="layui-elem-field">--%>
            <%--<legend>开发团队</legend>--%>
            <%--<div class="layui-field-box">--%>
                <%--<table class="layui-table">--%>
                    <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<th>版权所有</th>--%>
                            <%--<td>xxxxx(xxxx)--%>
                                <%--<a href="http://www.xxx.com/" class='x-a' target="_blank">访问官网</a></td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<th>开发者</th>--%>
                            <%--<td>马志斌(113664000@qq.com)</td></tr>--%>
                    <%--</tbody>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</fieldset>--%>
        <%--<blockquote class="layui-elem-quote layui-quote-nm">感谢layui,百度Echarts,jquery,本系统由x-admin提供技术支持。</blockquote>--%>
    </div>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var orderChart = echarts.init(document.getElementById('order_chart'));
        var productChart = echarts.init(document.getElementById('product_chart'));
        var planChart = echarts.init(document.getElementById('plan_chart'));
        var materialChart = echarts.init(document.getElementById('material_chart'));

        $(function () {
            $.ajax({
                url:"/orderData",
                method:'get',
                success:function (data) {
                   if(data.order_data)
                    // 指定图表的配置项和数据
                    var optionOrder = {
                        color : [
                            '#009688', '#87cefa', '#da70d6', '#32cd32', '#6495ed',
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
                            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'
                        ],
                        title : {
                            text: '订单数量',
                            subtext: '变化趋势图'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['订单数','']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : data.order_date
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'订单数',
                                type:'bar',
                                data:data.order_data,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };
                   else
                       $("#order_chart").text("暂无订单数据");
                   if(data.productCount)
                    // 指定图表的配置项和数据
                    var optionProduct = {
                        color : [
                            '#87cefa', '#009688', '#da70d6', '#32cd32', '#6495ed',
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
                            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'
                        ],
                        title : {
                            text: '产品统计',
                            subtext: '库存数量'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['产品数','']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : data.productName
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'产品数',
                                type:'bar',
                                data:data.productCount,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };
                   else
                    $("#product_chart").text("暂无产品数据");
                   if(data.materialName)
                    // 指定图表的配置项和数据
                    var optionPlan = {
                        color : [
                            '#da70d6', '#009688', '#87cefa', '#32cd32', '#6495ed',
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
                            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'
                        ],
                        title : {
                            text: '物料统计',
                            subtext: '库存数量'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['物料数','']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : data.materialName
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'物料数',
                                type:'bar',
                                data:data.materialCount,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };
                   else
                       $("#plan_chart").text("暂无物料数据");

                    // 指定图表的配置项和数据
                    var optionMaterial = {
                        color : [
                            '#32cd32', '#009688', '#87cefa', '#da70d6', '#6495ed',
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
                            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'
                        ],
                        title : {
                            text: '最近一周',
                            subtext: '变化趋势图'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['产品数','']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : ['2018.11.13','2018.11.14','2018.11.15','2018.11.16','2018.11.17','2018.11.18','2018.11.19']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'产品数',
                                type:'bar',
                                data:[2, 4, 7, 23, 25, 76, 135],
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    productChart.setOption(optionProduct);
                    planChart.setOption(optionPlan);
                    // materialChart.setOption(optionMaterial);
                    orderChart.setOption(optionOrder);
                }
            });


            $.get({
                url: '/actuator/health',
                async: false,
                success: function (data) {
                    if (data) {
                        if (data.status == 'UP')
                            $("#status").html("<i class='layui-icon' style='color: green'>" + "&#xe619;   运行中" + "</i>");
                        else
                            $("#status").html("<i class='layui-icon' style='color: red'>" + "&#xe61a;   异常 " + "</i>");

                        if (data.details.diskSpace.status == 'UP') {
                            $("#disk").html("<i class='layui-icon' style='color: green'>" + "&#xe619;   正常  <span style='color: #2372dd'>总空间:" + transfer(data.details.diskSpace.details.total) + "  未使用:" + transfer(data.details.diskSpace.details.free) + "</span></i>");
                        } else
                            $("#disk").html("<i class='layui-icon' style='color: red'>" + "&#xe619;   异常  <span style='color: #e4554a'>总空间:" + transfer(data.details.diskSpace.details.total) + "  未使用:" + transfer(data.details.diskSpace.details.free) + "</span></i>");

                        if (data.details.db.status == 'UP')
                            $("#db").html("<i class='layui-icon' style='color: green'>" + "&#xe619;   运行中 <span style='color: #2372dd'>数据库类型:" + data.details.db.details.database + "</span></i>");
                        else
                            $("#db").html("<i class='layui-icon' style='color: green'>" + "&#xe619;   异常 <span style='color: #2372dd'>数据库类型:" + data.details.db.details.database + "</span></i>");

                    }
                },
                error:function(){
                    console.log("拉取数据失败");
                }
            });

        });

        function transfer(value) {
            return (value / (1024 * 1024 * 1024)).toFixed(2) + "GB";
        }

        </script>
    </body>
</html>