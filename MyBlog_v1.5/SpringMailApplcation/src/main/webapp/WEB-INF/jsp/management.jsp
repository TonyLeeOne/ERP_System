<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Url权限配置</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/userhome.css" rel="stylesheet">
    <style>
        .table th, .table td {
            vertical-align: middle !important;
            text-align: center;
        }

        .table {
            border: 0px solid transparent;
        }

        a:hover {
            text-decoration-line: none;
        }
        input{
            text-align: center;
        }
    </style>
</head>
<body>
<shiro:authenticated>
<div class="row">
    <%@include file="nav.jsp" %>
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="myModal">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content text-center">
            </div>
        </div>
    </div>
    <div class="container-fluid col-sm-offset-1 col-sm-10" style="margin-top: 3%">
        <div class="panel panel-default">
            <div class="panel-heading">
                Url权限配置　<span class="badge">${shiros.size()}</span>
                <shiro:hasPermission name="update"><button type="button" class="btn btn-primary" id="get" style="margin-left: 50%">
                    获取最新urls
                </button></shiro:hasPermission>
                <shiro:hasPermission name="delete"><button type="button" class="btn btn-warning" id="default">
                    初始化所有Url
                </button></shiro:hasPermission>
                <shiro:hasPermission name="update"><button type="button" class="btn btn-danger" id="sync">
                    同步服务器配置
                </button></shiro:hasPermission>
            </div>
            <table class="table table-responsive">
                <c:if test="${!empty shiros}">
                    <c:forEach items="${shiros}" var="shiro">
                        <tr >
                            <td>${shiro.url}</td>
                            <td><input type="text" value="${shiro.authority}" class="form-control" style="width: auto;"></td>
                            <td>
                                <shiro:hasPermission name="update">
                                    <button type="button" class="btn btn-success" id="ok"
                                        value="${shiro.id}" data-toggle="modal" data-target="#myModal">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </button>
                                </shiro:hasPermission>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>
</shiro:authenticated>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script src="/js/browseBlog.js"></script>
<script>
    $("[id='ok']").each(function () {
        $(this).click(function () {
            $.ajax({
                url:"/auth/update",
                method:"post",
                data:{id:$(this).val(),authority:$(this).parent().prev().children("input").val()},
                success:function (data) {
                    $(this).parent().prev().children("input").val(data.authority);
                    $('#myModal').modal('show');
                    $("#myModal .modal-content").html("<img src=\"/images/ok.gif\"> <b>数据更新成功</b>");
                    setTimeout(function () {
                        $('#myModal').modal('hide');
                    },3000)
                },
                error:function () {
                    $('#myModal').modal('show');
                    $("#myModal .modal-content").html("<img src=\"/images/ng.gif\"> <b>数据更新失败</b>");
                    setTimeout(function () {
                        $('#myModal').modal('hide');
                    },3000)
                }
            });
        });
    });
    $("#get").click(function () {
        window.location.href="/auth/getUrls";
    });

    $("#default").click(function () {
        window.location.href="/auth/default";
    });

    $("#sync").click(function () {
        $.ajax({
            url:"/auth/updatePerm",
            method:"post",
            success:function (data) {
                $('#myModal').modal('show');
                $("#myModal .modal-content").html("<div class=\"progress\" style='margin-top: 5%'>\n" +
                    "  <div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%\">\n" +
                    "    <span class=\"sr-only\">100% Complete</span>\n" +
                    "  </div>\n" +
                    "</div>");
                setTimeout(function () {
                    $('#myModal').modal('hide');
                },5000)
            },
           error:function () {
            $('#myModal').modal('show');
            $("#myModal .modal-content").html("<img src=\"/images/ng.gif\"> <b>数据更新失败</b>");
            setTimeout(function () {
                $('#myModal').modal('hide');
            },3000)
        }
        });
    });

    $("input").each(function () {
        if($(this).val()=="authc"){
            $(this).css("background-color","#32d7fa");
        }
        if($(this).val()=="anon"){
            $(this).css("background-color","#93c5ff");
        }
    });
</script>
</body>
</html>