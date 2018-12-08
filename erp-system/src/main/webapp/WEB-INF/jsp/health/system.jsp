<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-ERP管理系统1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
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
        <legend>虚拟机监控</legend>
        <div class="layui-field-box">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body" id="jvm">
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
</div>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
<script>
    $(function () {
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
        $.get({
            url:'/actuator/env',
            success:function(data){
                var jvm=data.propertySources[2].properties;
                if(data){
                    $("#jvm").append(" <table class=\"layui-table\">\n" +
                        "            <tr>\n" +
                        "            <td>虚拟机类型</td>\n" +
                        "            <td>"+jvm[9].value+"</td>\n" +
                        "            <td>版本</td>\n" +
                        "            <td>1.8</td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "\n" +
                        "            </tr>\n" +
                        "            </table>");
                }
            }
        });
    })

    function transfer(value) {
        return (value / (1024 * 1024 * 1024)).toFixed(2) + "GB";
    }
</script>
</body>
</html>