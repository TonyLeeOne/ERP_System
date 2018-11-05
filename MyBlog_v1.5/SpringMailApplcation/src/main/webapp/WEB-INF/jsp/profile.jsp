<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>${blog.subject}</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        ul li {
            float: left;
            list-style: none;
        }

        .toolbar {
            border: 1px solid rgba(147, 197, 255, 0);
        }

        .text {
            border: 1px solid rgba(1, 143, 255, 0);
            height: 50px;
        }

        .table th, .table td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp" %>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true" style="z-index: 1060">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="btn-info modal-header">
                        <h5>请选择你的头像</h5>
                    </div>
                    <div class="modal-body">
                        <c:if test="${! empty images}">
                            <c:forEach items="${images}" var="image">
                                <img src="${image}" data-dismiss="modal" id="img">
                            </c:forEach>
                        </c:if>
                    </div>
                </div><!-- /.modal-content -->
            </div>
        </div> <!-- /.modal -->
        <form method="post" action="/profile/add" class="form-horizontal" role="form" id="myForm" onsubmit="return ">
            <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="btn-info modal-header">
                            <h4 class="text-center">添加资料</h4>
                        </div>
                        <div class="modal-body text-center">
                            <form class="form-inline" method="post">
                                <table class="table table-condensed text-center">
                                    <tr>
                                        <input type="hidden" class="form-control" name="img" id="imgHead">
                                    </tr>
                                    <tr>
                                        <td>请选择你的头像</td>
                                        <td>
                                            <div class="col-sm-6">
                                                <img id="headImg" src="/images/default.PNG" data-toggle="modal"
                                                     data-target="#myModal">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>签名</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="signature">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>邮箱</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="email" class="form-control" name="email">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>联系方式</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="number" class="form-control" name="tel">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的标签</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="tag">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的QQ</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="number" class="form-control" name="qq">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的微信</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="wechat">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的github仓库</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="url" class="form-control" name="github">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的博客主页</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="url" class="form-control" name="blog">
                                            </div>
                                        </td>
                                    </tr>

                                </table>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <!--  模态框底部样式，一般是提交或者确定按钮 -->
                            <button class="btn btn-success" type="submit">保存</button>
                            <input class="btn btn-primary" type="button" value="取消" id="cancle" data-dismiss="modal">
                        </div>

                    </div><!-- /.modal-content -->
                </div>
            </div> <!-- /.modal -->
        </form>

        <form method="post" action="/profile/update" class="form-horizontal" role="form" id="myForm" onsubmit="return ">
            <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="btn-info modal-header">
                            <h4 class="text-center">编辑资料</h4>
                        </div>
                        <div class="modal-body text-center">
                            <form class="form-inline" method="post">
                                <table class="table table-condensed text-center">
                                    <tr>
                                        <input type="hidden" class="form-control" name="img" id="imgHead1">
                                    </tr>
                                    <tr>
                                        <td>请选择你的头像</td>
                                        <td>
                                            <div class="col-sm-6">
                                                <img id="headImg1" src="${profile.img}" data-toggle="modal"
                                                     data-target="#myModal">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>签名</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="signature" value="${profile.signature}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>邮箱</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="email" class="form-control" name="email" value="${profile.email}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>联系方式</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="number" class="form-control" name="tel" value="${profile.tel}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的标签</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="tag" value="${profile.tag}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的QQ</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="number" class="form-control" name="qq" value="${profile.qq}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的微信</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="text" class="form-control" name="wechat" value="${profile.wechat}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的github仓库</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="url" class="form-control" name="github" value="${profile.github}">
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>我的博客主页</td>
                                        <td>
                                            <div class="form-group col-sm-10">
                                                <input type="url" class="form-control" name="blog" value="${profile.blog}">
                                            </div>
                                        </td>
                                    </tr>

                                </table>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <!--  模态框底部样式，一般是提交或者确定按钮 -->
                            <button class="btn btn-success" type="submit">保存</button>
                            <input class="btn btn-primary" type="button" value="取消" data-dismiss="modal">
                        </div>

                    </div><!-- /.modal-content -->
                </div>
            </div> <!-- /.modal -->
        </form>

        <div style="margin-top: 2%;border-radius:5px;background-color: rgba(215,0,247,0);box-shadow:0 0 0 2px gold;height:fit-content" id="profileView" class="col-sm-3 col-sm-offset-2">
            <c:if test="${empty profile}">
                <p>你还没有任何资料，你可以　
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal1">添加资料</button>
                </p>
            </c:if>
            <c:if test="${!empty profile}">
                <div style="padding: 5px">
                    <img src="${profile.img}" class="img-responsive center-block"/>
                </div>
                <div>
                    <table class="table table-striped text-center">
                        <tr>
                            <td><span class="glyphicon glyphicon-info-sign"></span>　简介</td>
                            <td>${profile.signature}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-earphone"></span>　手机</td>
                            <td>${profile.tel}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-envelope"></span>　邮箱</td>
                            <td>${profile.email}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-tags"></span>　标签</td>
                            <td>${profile.tag}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-road"></span>　博客</td>
                            <td>${profile.blog}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-road"></span>　GitHub</td>
                            <td>${profile.github}</td>
                        </tr>
                        <tr id="op">
                            <td colspan="2">
                                <button class="btn btn-success btn-small" data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-edit"></span></button>
                                <button class="btn btn-danger btn-small"><span class="glyphicon glyphicon-trash"></span></button>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script>
    $("[id='img']").each(function () {
        $(this).click(function () {
            $("#headImg").attr("src", $(this).attr('src'));
            $("#imgHead").val($(this).attr('src'));

            $("#headImg1").attr("src", $(this).attr('src'));
            $("#imgHead1").val($(this).attr('src'));
        });
    });
    $(function () {
        $("#op").hide();
    });

    $("#profileView").mouseover(function () {
        $("#op").show();
    });

    $("#profileView").mouseleave(function () {
        $("#op").hide();
    });

</script>
</body>
</html>