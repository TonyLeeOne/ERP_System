<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台登录-ERP管理系统1.0</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="/index">ERP管理系统 v1.0</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('资讯','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>资讯</a></dd>
                <dd><a onclick="x_admin_show('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a></dd>
                <dd><a onclick="x_admin_show('用户','http://www.baidu.com')"><i class="iconfont">&#xe6b8;</i>用户</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${sessionScope.user.uname}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('个人信息','admin-edit.html',600)">个人信息</a></dd>
                <dd><a href="/user/logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a href="/">前台页面</a></li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>订单管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/order/getAllOrders">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>所有订单</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>客户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/custom/getAllCustoms">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>所有客户</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>供应商管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/vendor/getAllVendors">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>所有供应商</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>生产管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/manPlan/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>生产计划管理</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="/manOrder/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>生产工单管理</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="/materialConsume/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>领料单管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6db;</i>
                    <cite>产品管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/product/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>所有产品</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="/ship/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>出货记录</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe75f;</i>
                    <cite>物料管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/material/getAll">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>物料信息</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>采购记录</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>领料记录</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6d4;</i>
                    <cite>设备管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>所有设备</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>设备维修记录</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>设备保养记录</cite>
                        </a>
                    </li>
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>设备采购记录</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe70b;</i>
                    <cite>人员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="admin-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>管理员列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="admin-role.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>角色管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="admin-cate.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>权限分类</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="admin-rule.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>权限管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ae;</i>
                    <cite>系统权限配置</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/urlConfigure/getAllUrls">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>URL权限配置</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="admin-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>角色配置</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src="/welcome" frameborder="0" scrolling="yes" class="x-iframe"></iframe>
                <%--<%@include file="welcome.jsp"%>--%>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2018 ERP管理系统 v1.0 All Rights Reserved　
          当前用户：
            <span class="x-red">${sessionScope.user.uname}</span> 　当前时间： <span id="timer"></span>
    </div>
</div>
<script>
    function time(){
        var timer=new Date();
        var date=timer.getDate();
        var month=timer.getMonth();
        var hour=timer.getHours();
        var min=timer.getMinutes();
        var sec=timer.getSeconds();
        var year=timer.getFullYear();
        if(sec<10)
            sec="0"+sec;
        if(hour<10)
            hour="0"+hour;
        if(month<10)
            month="0"+month;
        if(date<0)
            date="0"+date;
        if(min<10)
            min="0"+min;

        document.getElementById("timer").innerHTML=year+"年"+(month+1)+"月"+date+"日"+" "+hour+":"+min+":"+sec;
    }

    setInterval("time()",1000);
</script>
<!-- 底部结束 -->
</body>
</html>