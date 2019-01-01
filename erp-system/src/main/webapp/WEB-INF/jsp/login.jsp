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
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">ERP管理系统1.0-管理登录</div>
    <div id="darkbannerwrap"></div>

    <form class="layui-form">
        <input name="uname" placeholder="用户名" value="" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="upass" lay-verify="required" value="" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="button">
        <hr class="hr20">
    </form>
</div>

<script>
    $(function () {
        if (window.top!=null && window.top.document.URL!=document.URL){
            window.top.location= document.URL;
        }

        layui.use('form', function () {
            var form = layui.form;
            form.on('submit(login)', function (data) {
                $.ajax({
                    url: "/doLogin",
                    data: JSON.stringify(data.field),
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    async: false,
                    success: function (res) {
                        layer.msg(res, {time: 1000});
                        setTimeout(function () {
                            if (res.toString() == "登陆成功") {
                                window.location.href = "/index";
                            }
                        }, 1000)
                    },
                    error: function (res) {
                        console.log("error:");
                        console.log(res);
                    }
                });
                return false;
            });
        });
    })
</script>
<!-- 底部结束 -->
</body>
</html>