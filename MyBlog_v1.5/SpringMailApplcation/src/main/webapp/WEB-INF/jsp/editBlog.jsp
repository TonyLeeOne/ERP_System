<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>编辑博客</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/userhome.css" rel="stylesheet">
</head>
<body>
<div class="row">
    <%@include file="nav.jsp" %>
    <div class="container-fluid col-sm-offset-2" style="margin-top: 3%">
        <form class="form-horizontal" action="/blog/update" method="post">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="subject" placeholder="博客标题" id="subject"
                           value="${blog.subject}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10 ">
                    <input type="text" class="form-control" name="tag" placeholder="标签" id="tag" value="${blog.tag}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <div id="editor">
                        ${blog.contents}
                    </div>
                </div>
            </div>
            <input type="hidden" name="content" id="content"></br>
            <input type="hidden" name="bid" value="${blog.id}"></br>
            <div class="form-group">
                <div class="col-sm-5">
                    <button class="btn btn-success btn-block" type="submit" id="send">更新</button>
                </div>
                <div class="col-sm-5">
                    <input class="btn btn-primary btn-block" type="button" id="preView" value="预览" data-toggle="modal"
                           data-target="#myModal"/>
                </div>
            </div>
        </form>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title text-center" id="myModalLabel">
                            </h3>
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
<script src="/js/browseBlog.js"></script>
<script>
    //读取编辑器内容并赋值给content传到前端
    document.getElementById('send').addEventListener('click', function () {
        // 读取 html
        document.getElementById('content').value = editor.txt.html();
    }, false);
    $(function () {
        $("#preView").click(function () {
            $("#myModalLabel").html($("#subject").val()+'<span class="label label-default">'+$("#tag").val()+'</span>');
            $(".modal-body").html(editor.txt.html());
            $("#editor").hide();
        });

        $("#close").click(function () {
            $("#editor").show();
        });
        $("#myModal").modal({backdrop: "static", keyboard: false,show:false});
    });

</script>
</body>
</html>