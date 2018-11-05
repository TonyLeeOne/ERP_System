<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>有偿问答</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        li a {
            font-weight: bold;
        }

        span {
            font-size: medium;
        }
    </style>
</head>
<body>
<%@include file="paganation.jsp" %>
<div class="container" style="margin-top: 5%">
    <%--<div class="page-header">--%>
    <%--<h3>Example page header--%>
    <%--<small>Subtext for header</small>--%>
    <%--</h3>--%>
    <%--</div>--%>
    <ul id="myTab" class="nav nav-tabs">

        <li class="active">
            <a href="#unresolve" data-toggle="tab">待解决 <span class="badge"
                                                             style="background-color: darkred">${unresolved.size()}</span></a>
        </li>

        <li><a href="#resolve" data-toggle="tab">已解决 <span class="badge"
                                                           style="background-color: #0bb643">${resolved.size()}</span></a>
        </li>

        <li><a href="#category" data-toggle="tab">分类 <span class="badge"
                                                           style="background-color: #955ae7" id="type">0</span></a>
        </li>
        <li class="dropdown">
            <a href="#" id="myTabDrop1" class="dropdown-toggle"
               data-toggle="dropdown">我的问答 <span class="badge"
                                                 style="background-color: #955ae7">0</span><b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                <li><a href="#myResolve" tabindex="-1" data-toggle="tab">
                    已解决 <span class="badge" style="background-color: #0bb643 "></span></a>
                </li>
                <li><a href="#myUnrosolve" tabindex="-1" data-toggle="tab">
                    待解决 <span class="badge" style="background-color: darkred"></span></a>
                </li>
            </ul>
        </li>
        <li><a href="#question" data-toggle="tab">提问 </a>
        </li>

    </ul>


    <div id="myTabContent" class="tab-content">

        <div class="tab-pane fade in active" id="unresolve" style="margin-top: 3%">
            <div class="row">
                <c:if test="${! empty unresolved}">
                    <table class="col-sm-offset-1 col-sm-10 table-hover text-center">
                        <c:forEach items="${unresolved}" var="unrevolve">
                            <tr class="input-lg">
                                <td class="col-sm-1">
                                    <img src="${unrevolve.profile.img}" class="img-circle"
                                         style="width: 30px;height: 30px"/>
                                </td>
                                <td class="col-sm-3" value="${unrevolve.id}" id="unresolve1">
                                    <span class="glyphicon glyphicon-info-sign">
                                      <c:choose>
                                          <c:when test="${fn:length(unrevolve.subject) > 20}">
                                              <c:out value="${fn:substring(unrevolve.subject, 0, 20)}..."/>
                                          </c:when>
                                          <c:otherwise>
                                              <c:out value="${unrevolve.subject}"/>
                                          </c:otherwise>
                                      </c:choose>
                                     </span>
                                </td>
                                <td class="col-sm-1">
                                    <span class="glyphicon glyphicon-tag"> ${unrevolve.category}</span>
                                </td>
                                <td class="col-sm-3">
                                    <span class="glyphicon glyphicon-time"> ${unrevolve.ctime}</span>
                                </td>
                                <td class="col-sm-1">
                                    <span class="glyphicon glyphicon-comment"> ${unrevolve.comments.size()}</span>
                                </td>
                                <shiro:hasPermission name="topicManagement">
                                    <td class="col-sm-1">
                                        <button class="btn btn-danger" id="del" value="${unrevolve.id}"><span
                                                class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </td>
                                </shiro:hasPermission>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>

        <div class="tab-pane fade" id="resolve" style="margin-top: 3%">
            <div class="row">
                <c:if test="${! empty resolved}">
                    <table class="col-sm-offset-1 col-sm-10 table-hover text-center">
                        <c:forEach items="${resolved}" var="revolve">
                            <tr class="input-lg">
                                <td class="col-sm-1">
                                    <img src="${revolve.profile.img}" class="img-circle"
                                         style="width: 30px;height: 30px"/>
                                </td>
                                <td class="col-sm-4" id="resolve1" value="${revolve.id}">
                                      <span class="glyphicon glyphicon-info-sign">
                                        <c:choose>
                                            <c:when test="${fn:length(revolve.subject) > 20}">
                                                <c:out value="${fn:substring(revolve.subject, 0, 20)}..."/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${revolve.subject}"/>
                                            </c:otherwise>
                                        </c:choose>
                                   </span>
                                </td>
                                <td class="col-sm-1">
                                    <span class="glyphicon glyphicon-tag"> ${revolve.category}</span>
                                </td>
                                <td class="col-sm-3">
                                    <span class="glyphicon glyphicon-time"> ${revolve.ctime}</span>
                                </td>
                                <td class="col-sm-1">
                                    <span class="glyphicon glyphicon-comment"> ${revolve.comments.size()}</span>
                                </td>
                                <shiro:hasPermission name="topicManagement">
                                    <td class="col-sm-1">
                                        <button class="btn btn-danger" id="delete" value="${revolve.id}"><span
                                                class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </td>
                                </shiro:hasPermission>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>

        <div class="tab-pane fade" id="category">
            <div class="col-sm-offset-1 col-sm-10" style="margin-top: 1%">
                <div class="form-inline">
                    <input class="form-control" placeholder="请输入要搜索的内容">
                    <button type="submit" class="btn-success form-control">搜索</button>
                </div>
                <div id="tags" style="margin-top: 2%">分类:　</div>
                <div id="checkTag" class="col-sm-10" style="margin-top: 2%"></div>
                <table class="col-sm-10 table-hover text-center" id="tagContent" style="margin-top: 2%">
                </table>
            </div>
        </div>

        <div class="tab-pane fade" id="myResolve">
            <c:if test="${empty sessionScope.user}">
                <div class="text-center" style="margin-top: 3%">
                    oops,你还没有登录，请先<a href="/user">登录</a>
                </div>
            </c:if>
        </div>
        <div class="tab-pane fade" id="myUnrosolve">
            <c:if test="${empty sessionScope.user}">
                <div class="text-center" style="margin-top: 3%">
                    oops,你还没有登录，请先<a href="/user">登录</a>
                </div>
            </c:if>
        </div>

        <div class="tab-pane fade" id="question">
            <c:if test="${empty sessionScope.user}">
                <div class="text-center" style="margin-top: 3%">
                    oops,你还没有登录，请先<a href="/user">登录</a>
                </div>
            </c:if>
            <c:if test="${! empty sessionScope.user}">
                <div class="container col-sm-offset-2" style="margin-top: 3%">
                    <form class="form-horizontal" action="/topic/add" method="post">
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="subject" placeholder="问题描述" id="subject">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 ">
                                <input type="text" class="form-control" name="category" placeholder="提问分类" id="tag">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <div id="editor">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="content" id="content"></br>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <button class="btn btn-success btn-block" type="submit" id="send">提 问</button>
                            </div>
                            <div class="col-sm-4">
                                <input class="btn btn-primary btn-block" type="button" value="预 览" data-toggle="modal"
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
            </c:if>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="/js/wangEditor.min.js"></script>
<script type="text/javascript" src="/js/editorCustomize.js"></script>
<script type="text/javascript" src="/js/writeBlog.js"></script>
<script>
    $(function () {
        // $('#myTab li:eq(0) a').tab('show');

        $.ajax({
            url: "/topic/findTags",
            method: 'get',
            cache: false,
            success: function (data) {
                $("#type").text(data.length);
                $.each(data, function (index, item) {
                    $("#tags").append("<span class=\"label label-success\" id='tag'>" + "# " + item + "</span>　");
                })
                $("[id='tag']").each(function () {
                    $(this).click(function () {
                        var str = $(this).text().toString();
                        $.ajax({
                            url: '/topic/findTopicsByTag',
                            method: 'post',
                            data: {tag: str.substring(2, str.length).trim()},
                            cache: false,
                            success: function (topics) {
                                $("#checkTag").html("当前标签为:"+str.substring(2, str.length)+" ,共 " + " <span class=\"badge\"\n" +
                                    "style=\"background-color: #0cd6ff\">" + topics.length + "</span>" + " 条数据符合要求");

                                var s = '';
                                $.each(topics, function (index, topic) {

                                    s += "<tr class=\"input-lg\" id='item' value='"+topic.id+"'>\n" +
                                        "                                <td class=\"col-sm-1\">\n" +
                                        "                                    <img src='" + topic.profile.img + "' class=\"img-circle\"\n" +
                                        "                                         style=\"width: 30px;height: 30px\"/>\n" +
                                        "                                </td>\n" +
                                        "                                <td class=\"col-sm-4\" id=\"resolve1\">\n" +
                                        "                                      <span class=\"glyphicon glyphicon-info-sign\" >\n" + topic.subject +
                                        "                                   </span>\n" +
                                        "                                </td>\n" +
                                        "                                <td class=\"col-sm-1\">\n" +
                                        "                                    <span class=\"glyphicon glyphicon-tag\"> " + topic.category + "</span>\n" +
                                        "                                </td>\n" +
                                        "                                <td class=\"col-sm-3\">\n" +
                                        "                                    <span class=\"glyphicon glyphicon-time\"> " + topic.ctime + "</span>\n" +
                                        "                                </td>\n" +
                                        "                            </tr>";
                                })
                                $("#tagContent").html(s);

                                $("[id='item']").each(function () {
                                    $(this).click(function () {
                                        location.href = "/topic/query/" + $(this).attr('value');
                                    });
                                });


                            },
                            error: function () {
                                alert("数据获取失败");
                            }
                        });
                    });
                });
            },
            error: function () {
                alert("数据获取失败");
            }
        });
    });


    $("[id='unresolve1']").each(function () {
        $(this).click(function () {
            location.href = "/topic/query/" + $(this).attr('value');
        });
    });
    $("[id='resolve1']").each(function () {
        $(this).click(function () {
            location.href = "/topic/query/" + $(this).attr('value');
        });
    });
    $("[id='del']").each(function () {
        $(this).click(function () {
            if (window.confirm("该问答及其评论均会被移除？？")) {
                location.href = "/topic/delete/" + $(this).attr('value');
            }
        });
    });

    $("[id='delete']").each(function () {
        $(this).click(function () {
            if (window.confirm("该问答及其评论均会被移除？？")) {
                location.href = "/topic/delete/" + $(this).attr('value');
            }
        });
    });


</script>
</body>
</html>