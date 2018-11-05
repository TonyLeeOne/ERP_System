<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户权限配置</title>
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

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="row">
    <%@include file="nav.jsp" %>
    <form method="post" action="/role/add" class="form-horizontal" role="form" id="myForm" onsubmit="return ">
        <div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="btn-info modal-header">
                        <h5>新增角色及权限信息</h5>
                    </div>
                    <div class="modal-body text-center">
                        <form class="form-inline" method="post">
                            <input type="text" placeholder="请输入角色名" name="roleName">　
                            <c:if test="${! empty modules}">
                                <c:forEach items="${modules}" var="module">
                                    <input type="checkbox" name="${module.mname}"  value="${module.mid}"> ${module.mname}
                                </c:forEach>
                            </c:if>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <!--  模态框底部样式，一般是提交或者确定按钮 -->
                        <button class="btn btn-success" type="submit" >保存</button>
                        <input class="btn btn-primary" type="button" value="取消" id="cancle" data-dismiss="modal">
                    </div>

                </div><!-- /.modal-content -->
            </div>
        </div> <!-- /.modal -->
    </form>
    <div class="container-fluid col-sm-offset-1 col-sm-10" style="margin-top: 3%">
        <div class="panel panel-default">
            <div class="panel-heading">
                角色权限配置　<span class="badge">${roles.size()}</span>
                <shiro:hasPermission name="admin">
                <button type="button" class="btn btn-warning" id="addRole" data-toggle="modal" data-target="#myModal" style="margin-left: 50%">
                    添加角色
                </button>
                </shiro:hasPermission>
            </div>
            <table class="table table-responsive">
                <thead>
                <td>角色</td>
                <td>权限</td>
                </thead>
                <c:if test="${!empty roles}">
                    <c:forEach items="${roles}" var="role">
                        <tr>
                            <td>${role.rname}(${role.users.size()})</td>
                            <td>
                                <c:if test="${!empty role.module}">
                                    <c:forEach items="${role.module}" var="module">
                                        <input type="checkbox" checked="checked" value="${module.mid}">${module.mname}　
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td>
                                <shiro:hasPermission name="admin">
                                    <button type="button" class="btn btn-success" id="ok"
                                            value="${role.rid}">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="admin">
                                    <button type="button" class="btn btn-danger" id="remove"
                                            value="${role.rid}" val="${role.users.size()}">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </button>
                                </shiro:hasPermission>
                            </td>
                            <td>
                                <shiro:hasPermission name="admin">
                                <div class="btn-group" id="myDropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="true">
                                        添加权限<span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <c:if test="${!empty modules}">
                                            <c:forEach items="${modules}" var="module">
                                                <li><a href="#" id="module" value="${module.mid}" class="${role.rid}">${module.mname}</a></li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                                </shiro:hasPermission>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>

    <div class="container-fluid col-sm-offset-1 col-sm-10" style="margin-top: 3%">
        <div class="panel panel-default">
            <div class="panel-heading">
                用户角色管理　<span class="badge">${users.size()}</span>
            </div>
            <table class="table table-responsive">
                <thead>
                <td>用户名</td>
                <td>用户角色</td>
                </thead>
                <c:if test="${!empty users}">
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>
                                <c:if test="${!empty user.role}">
                                    <c:forEach items="${user.role}" var="role">
                                        <input type="checkbox" checked="checked" value="${role.rid}">${role.rname}　
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td>
                                <shiro:hasPermission name="admin">
                                <div class="btn-group" >
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="true">
                                        角色切换 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <c:if test="${!empty roles}">
                                            <c:forEach items="${roles}" var="role">
                                                <li><a href="#" id="role" value="${role.rid}" class="${user.uid}">${role.rname}</a></li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                                </shiro:hasPermission>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script src="/js/browseBlog.js"></script>
<script>

    $(function () {
        $("[id='remove']").each(function () {
           if($(this).attr("val")>0){
               $(this).hide();
           }
        });
    });
    var mArray=new Array();
    $("[id='ok']").each(function () {
        $(this).click(function () {
            var i=0;
            $(this).parent().prev().children("input:checkbox:checked").each(function () {
                mArray[i]=$(this).val();
                i++;
            });
            window.location.href="/role/change/"+mArray+"/"+$(this).val();
        });
    });

    $("[id='remove']").each(function () {
        $(this).click(function () {
            if(window.confirm("你确定删除此角色吗？")){
                window.location.href="/role/delete/"+$(this).val();
            }
        });
    });

    $("[id='module']").each(function () {
        $(this).click(function () {
            if(window.confirm("你确定添加此权限吗？")){
                window.location.href="/role/add/"+$(this).attr("class")+"/"+$(this).attr("value");
            }
        });
    });

    $("[id='role']").each(function () {
        $(this).click(function () {
            if(window.confirm("你确定切换用户角色吗？")){
                window.location.href="/role/changerole/"+$(this).attr("class")+"/"+$(this).attr("value");

            }
        });
    });
</script>
</body>
</html>