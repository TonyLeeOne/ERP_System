<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户主页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table th, .table td {
            vertical-align: middle !important;
        }
        .table {
            border: 0px solid transparent;
        }
        a:hover {
            text-decoration-line: none;
        }
    </style>
</head>
<body>
<div class="row">
    <%@include file="nav.jsp" %>
    <div class="container col-sm-offset-1 col-sm-10"
         style="background-color: rgba(1,24,44,0.03);border-radius: 20px;margin-top:2%;margin-bottom: 2%;box-shadow: -5px -5px 0 0 rgba(16,230,209,0.42);">
        <div class="panel panel-default">
            <div class="panel-heading">
                原创 <span class="badge">${blogs.size()}</span>　
                转发 <span class="badge">${collections.size()}</span>　　
                <a href="/blog/add" type="button" class="text-right" style="color: rgb(212, 106, 64);"><span
                        class="glyphicon glyphicon-pencil"></span> 写博客</a>　　
                <div class="btn-group" id="myDropdown">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="true">
                        分类<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:if test="${!empty tags}">
                            <li><a href="#" id="all">全部</a></li>
                            <c:forEach items="${tags}" var="tag">
                                <li><a href="#" id="tagSearch">${tag}</a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
            <table class="table table-responsive">
                <c:if test="${!empty blogs}">
                    <c:forEach items="${blogs}" var="item">
                        <tr>
                            <td><span class="glyphicon glyphicon-copyright-mark">原</span></td>
                            <td><span class="glyphicon glyphicon-time"></span>　${item.ptime}</td>
                            <td><a href="/blog/browse/${item.id}"><span
                                    class="glyphicon glyphicon-list-alt"></span>　${item.subject}</a></td>
                            <td><span class="glyphicon glyphicon-tag"></span>　${item.tag}</td>
                            <td id="utime"><span class="glyphicon glyphicon-pencil"></span>　${item.utime}</td>
                            <td>
                                <button type="button" class="btn btn-danger" id="delete"
                                        value="${item.id}">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                                <button type="button" class="btn btn-success" id="edit"
                                        value="${item.id}">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </button>

                                <button type="button" class="btn btn-primary" id="share"
                                        value="${item.id}">
                                    <span class="glyphicon glyphicon-share" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${!empty collections}">
                    <c:forEach items="${collections}" var="collection">
                        <tr>
                            <td><span class="glyphicon glyphicon-heart"></span>转</td>
                            <td><span class="glyphicon glyphicon-time"></span>　${collection.blog.ptime}</td>
                            <td><a href="/blog/browse/${collection.bid}"><span
                                    class="glyphicon glyphicon-list-alt"></span>　${collection.blog.subject}</a></td>
                            <td><span class="glyphicon glyphicon-tag"></span>　${collection.blog.tag}</td>
                            <td><span class="glyphicon glyphicon-pencil"></span>　${collection.blog.utime}</td>
                            <td>
                                <button type="button" class="btn btn-danger" id="delete1"
                                        value="${collection.id}">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                                <button type="button" class="btn btn-primary" id="share1"
                                        value="${collection.id}">
                                    <span class="glyphicon glyphicon-share" aria-hidden="true"></span>
                                </button>
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
<script src="/js/userhome.js"></script>
<script>
    $("[id='tagSearch']").each(function () {
        $(this).click(function () {
            window.location.href="/blog/search/"+$(this).text();
        });
    });
    $("#all").click(function () {
        window.location.href="/user/home";
    });

    $("[id='delete1']").each(function () {
        $(this).click(function () {
            var res=window.confirm("确定删除？？");
            if(res==true){
                window.location.href = "/collect/delete/" + $(this).val();
            }
        });
    });

</script>
</body>
</html>