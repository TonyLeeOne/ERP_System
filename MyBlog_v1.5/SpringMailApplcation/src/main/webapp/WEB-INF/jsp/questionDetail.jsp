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

        a:hover {
            text-decoration: none;
        }

        .text {
            height: 15%;
        }
    </style>
</head>
<body>
<%@include file="paganation.jsp" %>
<div class="container" style="margin-top: 5%">
    <div class="row">
        <c:if test="${empty topic}">
            <p>你查询的问答不存在,<a href="/question" style="text-decoration: none">返回问答</a></p>
        </c:if>
        <c:if test="${! empty topic}">
            <table class="col-sm-11 cols-sm-offset-1">
                <tr>
                    <td class="col-sm-1"><img src="${topic.profile.img}"></td>
                    <td class="col-sm-10">
                        <h4>${topic.subject}
                            <small>${topic.ctime} 楼主• ${topic.user.username}</small>
                        </h4>
                    </td>
                </tr>
            </table>
            <div class="col-sm-11 col-sm-offset-1" style="margin-bottom: 3%">
                    ${topic.content}
            </div>
            <c:if test="${empty sessionScope.user}">
                <div class="text-center" style="margin-top: 3%">
                    oops,你还没有登录，请先 <a href="/user" type="button" class="btn-danger btn-sm">登录</a> 后 <a href="/user"
                                                                                                       type="button"
                                                                                                       class="btn-primary btn-sm">回答问题</a>
                </div>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
                <div class="col-sm-offset-1 col-sm-10">
                    <form class="form-inline" action="/comment/addT" method="post">
                        <input type="hidden" id="tid" name="tid" value="${topic.id}">
                        <input type="hidden" id="content" name="content">
                        <a class="btn btn-default" id="comment" data-toggle="tooltip" data-placement="top"
                           title="我来回答"><span class="glyphicon glyphicon-pencil"></span></a>
                        <button type="submit" class="btn btn-default" id="sub" data-toggle="tooltip"
                                data-placement="top"
                                title="提交"><span class="glyphicon glyphicon-ok"></span></button>
                        <a class="btn btn-default" id="cancel" data-toggle="tooltip" data-placement="top"
                           title="取消"><span class="glyphicon glyphicon-remove"></span></a>
                    </form>
                </div>
                <div class=" col-sm-offset-1 col-sm-10" id="comments">
                    <div id="div1" class="toolbar">
                    </div>
                    <div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                    </div>
                </div>
            </c:if>
        </c:if>

    </div>

    <%--评论显示区--%>
    <c:if test="${! empty topic}">
    <div class="col-sm-offset-1 col-sm-10" style="margin-top: 2%">
        <h5><span class="glyphicon glyphicon-comment"></span>　已有回答(${comments.size()})</h5>
        <hr>
        <c:if test="${! empty comments}">
            <c:forEach items="${comments}" var="comment" varStatus="com">
                <table class="col-sm-11 cols-sm-offset-1" id="tcomment" value="${comment.uid}">
                    <tr>
                        <td class="col-sm-1" style="alignment: right"><img
                                src="${comment.profile.img}"></td>
                        <td class="col-sm-5" style="alignment: left">
                            <c:set var="index" value="${com.index}"></c:set>
                            <c:set var="index1" value="1"></c:set>
                            <c:set var="test" value="${index+index1}"></c:set>
                            <small>${comment.ctime} ${test}楼 • ${comment.user.username}</small>
                        </td>
                        <td>
                            <small><a href="#" id="reply" style="color: rgba(0,0,0,0.30);"><span
                                    class="glyphicon glyphicon-pencil"></span> 回复</a></small>
                            　
                            <c:set var="guest" value="${sessionScope.user.username}"></c:set>
                            <c:set var="host" value="${topic.user.username}"></c:set>
                            <c:if test="${guest==host}">
                                <c:if test="${comment.status!='1'}">
                                    <small><a href="#" id="get" style="color: rgba(0,0,0,0.30)" value="${comment.id}"
                                              aa="${topic.id}"><span class="glyphicon glyphicon-thumbs-up"></span>
                                        采纳</a></small>
                                </c:if>
                            </c:if>
                        </td>
                        <c:if test="${comment.status=='1'}">
                            <td>
                                <img src="/images/ok.gif"> 已采纳
                            </td>
                        </c:if>
                        <shiro:hasPermission name="topicManagement">
                            <td class="col-sm-1">
                                <button class="btn btn-danger" id="delete" value="${comment.id}" aa="${topic.id}"><span
                                        class="glyphicon glyphicon-trash"></span>
                                </button>
                            </td>
                        </shiro:hasPermission>
                    </tr>
                </table>
                <div class="col-sm-11 col-sm-offset-1">
                        ${comment.content}
                </div>
            </c:forEach>
        </c:if>
    </div>
    </c:if>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="/js/wangEditor.min.js"></script>
<script src="/js/comment-wang.js"></script>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
        $("#sub").hide();
        $("#comments").hide();
        $("#cancel").hide();
        $("#comment").click(function () {
            $("#sub").show();
            $("#comments").show();
            $("#cancel").show();
            $(this).attr('disabled', 'disabled');
        });

        $("#cancel").click(function () {
            $("#sub").hide();
            $("#comments").hide();
            $(this).hide();
            $("#comment").removeAttr('disabled');
        });
    });
    $("#sub").click(function () {
        $("#content").val(editor1.txt.html());
    });

    $("[id='get']").each(function () {
        $(this).click(function () {
            if (confirm("确定采纳该回答？？")) {
                location.href = "/comment/update/" + $(this).attr("value") + "/" + $(this).attr("aa");
            }
        });
    });
    $("[id='delete']").each(function () {
        $(this).click(function () {
            if (confirm("确定删除该回答？？")) {
                location.href = "/topic/comment/delete/" + $(this).attr("value") + "/" + $(this).attr("aa");
            }
        });
    });
</script>
</body>
</html>