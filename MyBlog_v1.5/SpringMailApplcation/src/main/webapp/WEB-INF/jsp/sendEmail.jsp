<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>时光邮件</title>
    <style rel="stylesheet" src="/css/wangEditor.min.css"></style>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style rel="stylesheet">
        .row div {
            height: 26%
        }
    </style>
</head>
<body>
<%@include file="nav.jsp" %>
<div class="row container">
    <div class="alert alert-info alert-dismissible col-sm-offset-1" role="alert" style="height: 60px">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong id="proverb">劳于读书，逸于作文。 —— 程端礼</strong>
    </div>
    <div class="col-md-6">
        <div class="form-group col-sm-10 col-sm-offset-2">
            <div id="editor">
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <form method="post" action="/send" class="form-horizontal ">
            <div class="form-group col-sm-12">
                <div class="input-group input-group-mid ">
                    <span class="input-group-addon">收件人</span>
                    <input type="email" class="form-control" name="receiver" placeholder="请输入接受邮件地址"
                           aria-describedby="sizing-addon3">
                </div>
            </div>
            <div class="form-group col-sm-12">
                <div class="input-group input-group-mid">
                    <span class="input-group-addon">抄送人</span>
                    <input type="email" class="form-control" name="cc" placeholder="请输入要抄送的邮件地址"
                           aria-describedby="sizing-addon3">
                </div>
            </div>
            <div class="form-group col-sm-12">
                <div class="input-group input-group-mid">
                    <span class="input-group-addon">主　题</span>
                    <input type="text" class="form-control" name="subject" placeholder="请输入邮件主题"
                           aria-describedby="sizing-addon3">
                </div>
            </div>

            <div class="form-group col-sm-12">
                <div class="input-group input-group-mid">
                    <span class="input-group-addon">定　时</span>
                    <input type="datetime-local" class="form-control" name="time" placeholder="请输入邮件主题"
                           aria-describedby="sizing-addon3">
                </div>
            </div>

            <input type="hidden" name="content" id="content"></br>


            <div class="form-group col-sm-12">
                <button type="submit" class="btn btn-success btn-block" id="send">发送</button>
            </div>
            <div class="form-group col-sm-12">
                <button class="btn btn-default btn-block" type="reset" id="pre">重置</button>
            </div>
        </form>
    </div>

</div>


<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script type="text/javascript" src="/js/wangEditor.min.js"></script>
<script type="text/javascript" src="/js/editorCustomize.js"></script>
<script>
    $(function () {
        setInterval(function () {
            $.ajax({
                url: "/proverb/getRandomProverb",
                method: "GET",
                error: function (e) {
                    console.log(e);
                },
                success: function (data) {
                    $("#proverb").html(data.content  +"  ----   "+ data.author);
                }
            });
        }, 8000);
    });
</script>
</body>
</html>