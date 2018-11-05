<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户主页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="row">
    <%@include file="nav.jsp" %>
    <div class="container-fluid col-sm-offset-2" style="margin-top: 3%">
        <form class="form-horizontal" action="/blog/save" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="subject" placeholder="博客标题" id="subject">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10 ">
                    <input type="text" class="form-control" name="tag" placeholder="标签" id="tag">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <div id="editor">
                    </div>
                </div>
            </div>
            <input type="hidden" name="content" id="content"></br>
            <div class="form-group">
                <div class="col-sm-5">
                    <button class="btn btn-success btn-block" type="submit" id="send">发布</button>
                </div>
                <div class="col-sm-5">
                    <input class="btn btn-primary btn-block" type="button" value="预览" data-toggle="modal"
                           data-target="#myModal" id="preView"/>
                </div>
            </div>
        </form>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"></h4>
                    </div>
                    <div class="modal-body">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal" id="close">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="/js/wangEditor.min.js"></script>
<script type="text/javascript" src="/js/editorCustomize.js"></script>
<script type="text/javascript" src="/js/writeBlog.js"></script>
</body>
</html>