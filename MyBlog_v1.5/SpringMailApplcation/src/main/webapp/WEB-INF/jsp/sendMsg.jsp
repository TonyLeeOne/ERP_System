<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>发送消息</title>
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
        <strong id="proverb"></strong>
    </div>
    <form action="/kafka/send" method="post">
    <div class="col-md-6">
        <div class="form-group col-sm-10 col-sm-offset-2">
            <div id="editor">
            </div>
        </div>
    </div>
    <input type="hidden" name="msg" id="content"></br>


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
                url: "/kafka/receive",
                method: "GET",
                error: function (e) {
                    console.log(e);
                },
                success: function (data) {
                    $("#proverb").html(data.msg + "  ----   " + data.sendTime);
                }
            });
        }, 8000);
    });
</script>
</body>
</html>