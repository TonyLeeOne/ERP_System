<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录-注册页面</title>
    <style>
        .container {
            display: table;
            height: 100%;
        }

        .row {
            display: table-cell;
            vertical-align: middle;
        }
    </style>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container col-sm-4 col-sm-offset-4">
    <div class="row" id="register">
        <div class="col-sm-10">
            <h4 style="text-align: center">益初随笔  ▪  注册</h4>
        </div>
        <form class="form-horizontal" action="/user/register" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username"  placeholder="用户名" id="username">
                </div>
                <div class="col-sm-2">
                    <img src="" id="nameGif"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="pwd" placeholder="密码">
                </div>
                <div class="col-sm-2 ">
                    <img src="" id="pwdGif"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="secPwd" placeholder="密码确认">
                </div>
                <div class="col-sm-2 ">
                    <img src="" id="conGif"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="gifCode" id="gifCode" placeholder="验证码">
                </div>
                <div class="col-sm-4">
                    <img onclick="chageCode()" width="100" height="25" src="../captcha/kaptcha.jpg" id="codeImage"/>
                </div>
                <div class="col-sm-2 ">
                    <img src="" id="logGif1"/>
                </div>
            </div>
            <div class="form-group">
            <div class="col-sm-5">
                <button class="btn btn-success btn-block" type="submit" id="doReg">注册</button>
            </div>
            <div class="col-sm-5">
                <input class="btn btn-warning btn-block" type="button" id="doLogin" value="去登录"/>
            </div>
            </div>
        </form>
    </div>

    <div class="row" id="login">
        <div class="col-sm-10">
        <h4 style="text-align: center">益初随笔  ▪  登录</h4>
        </div>
        <form class="form-horizontal" action="/user/login" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="name" placeholder="用户名" value="${user.username}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="password" class="form-control" name= "password" id="password" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="gifCode1" name="gifCode1" placeholder="验证码">
                </div>
                <div class="col-sm-4">
                    <img onclick="chageCodes()" width="100" height="25" src="../captcha/kaptcha.jpg" id="codeImage1"/>
                </div>
                <div class="col-sm-2">
                    <img src="" id="logGif"/>
                </div>
            </div>
            <div class="form-group">
                <div class="checkbox" style="padding-left: 15px">
                    <label>
                        <input type="checkbox" name="remember"> 记 住 我
                    </label>
                </div>
            </div >

            <div class="form-group">
                <div class="col-sm-5">
                    <button class="btn btn-success btn-block" type="submit" id="todoLog">登录</button>
                </div>
                <div class="col-sm-5">
                    <input class="btn btn-danger btn-block" type="button" id="doRegis" value="去注册"/>
                </div>
            </div>
        </form>
        <div class="col-sm-12">
            <h5 style="alignment: center">${info}</h5>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script src="js/user.js"></script>
</body>
</html>