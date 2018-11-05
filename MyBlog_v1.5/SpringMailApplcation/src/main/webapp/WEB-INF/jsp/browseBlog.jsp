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

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp" %>
        <%@include file="rightNav.jsp" %>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body text-center">
                        <table class="table table-cell">
                            <tr class="text-center">
                                <td>
                                    <div style="background-color:orange" class="input-sm" data-dismiss="modal"
                                         value="orange">橘黄色
                                    </div>
                                </td>
                                <td>
                                    <div style="background-color:aliceblue" class="input-sm" data-dismiss="modal"
                                         value="aliceblue">冰蓝色
                                    </div>
                                </td>
                                <td>
                                    <div style="background-color:darkgray" class="input-sm" data-dismiss="modal"
                                         value="darkgray">暗黑色
                                    </div>
                                </td>
                                <td>
                                    <div style="background-color:springgreen" class="input-sm" data-dismiss="modal"
                                         value="springgreen">春绿色
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div><!-- /.modal-content -->
            </div>
        </div>
        <div class="container col-sm-offset-1 col-sm-10"
             style="background-color: rgba(1,24,44,0.03);border-radius: 20px;margin-top:2%;margin-bottom: 2%;box-shadow: -5px -5px 0 0 rgba(15,250,153,0.71);">
            <div class="page-header">
                <h3 class="text-center">${blog.subject}
                    <span class="label label-default">${blog.tag}</span>　
                    <p style="color: rgba(12,214,255,0.88);font-size: 15px;margin-top: 10px">
                        <span class="glyphicon glyphicon-time"></span> ${blog.ptime}　
                        <span class="glyphicon glyphicon-thumbs-up"></span> <span id="handUp">${blog.ptime}</span>　
                        <span class="glyphicon glyphicon-user"></span><a href="#" id="popover" value="${blog.user.uid}"
                                                                         style="color: rgba(12,214,255,0.72);text-decoration: none"
                                                                         data-toggle="popover"
                                                                         data-placement="bottom"> ${blog.user.username}　</a>
                        <span class="glyphicon glyphicon-eye-open"></span> <label id="pageView"></label>　
                        <span class="glyphicon glyphicon-comment"></span> ${comment.size()}　
                        <span class="glyphicon glyphicon-pencil"></span> ${blog.utime}
                    </p>
                </h3>
            </div>
            <div class="col-sm-10 col-sm-offset-1">
                ${blog.contents}
            </div>
        </div>
        <div class="col-sm-offset-1 col-sm-10">
            <form class="form-inline" action="/comment/add" method="post">
                <input type="hidden" id="bid" name="bid" value="${blog.id}">
                <input type="hidden" id="content" name="content">
                <a class="btn btn-default" id="comment" data-toggle="tooltip" data-placement="top"
                   title="评论"><span class="glyphicon glyphicon-pencil"></span></a>
                <button type="submit" class="btn btn-default" id="sub" data-toggle="tooltip" data-placement="top"
                        title="提交"><span class="glyphicon glyphicon-ok"></span></button>
                <a class="btn btn-default" id="cancel" data-toggle="tooltip" data-placement="top"
                   title="取消"><span class="glyphicon glyphicon-remove"></span></a>
            </form>
        </div>
        <div class="container col-sm-offset-1 col-sm-10" id="comments"
             style="border-radius: 20px;margin-top:2%;margin-bottom: 2%;box-shadow: 1px 1px 2px 2px rgba(16,230,209,0.42);">
            <div id="div1" class="toolbar">
            </div>
            <div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                <p>大佬，英俊潇洒的你，也吐谈一下呗</p>
            </div>
        </div>
        <div class="col-sm-offset-1 col-sm-10">
            <h5><span class="glyphicon glyphicon-comment"></span>　精彩评论(${comment.size()})</h5>
            <hr>

            <div>
                <c:if test="${!empty comment}">
                    <c:forEach items="${comment}" var="com">
                        <blockquote>
                            <p>${com.content}</p>
                            <small>　<span
                                    class="glyphicon glyphicon-user"></span>${com.user.username}　
                                <cite title="Source Title">
                                    <span class="glyphicon glyphicon-time"></span>${com.ctime}</cite>　
                                <a href="#" style="text-decoration: none"><span
                                        class="glyphicon glyphicon-question-sign"></span>回复</a>
                            </small>
                        </blockquote>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="/js/wangEditor.min.js"></script>
<script src="/js/browseBlog.js"></script>
<script src="/js/comment.js"></script>
<script>
    $(function () {
        $.ajax({
            url: '/handup/query',
            method: 'post',
            data: {bid: $("#bid").val()},
            success: function (data) {
                $("#handUp").text(data);
            },
            error: function () {
                alert("获取数据失败");
            }
        });

        if(!localStorage){
            alert("浏览器不支持localstorage");
        }else{
            var url=window.location.href;
            var data=localStorage.getItem(url);
            var bgColor=localStorage.getItem("bodyColor");
            if(data){
                $("#pageView").text(data);
                var data=parseInt(data);
                localStorage.setItem(url,++data);
            }else{
                var i=1;
                localStorage.setItem(url,i);
            }
            if(bgColor){
                document.body.style.backgroundColor = bgColor;
            }

        }
    });

    $("#thumb").click(function () {
        $.ajax({
            url: "/handup/add",
            method: "post",
            data: {bid: $("#bid").val()},
            success: function (data) {
                if(data=="RUNTIIME EXCEPTION"){
                    window.location.href="/user";
                }else{
                    $("#handUp").text(data);
                }
            },
            error: function () {
                alert("点赞失败");
            }
        });
    });

    $(function () {
        $.ajax({
            url: '/profile/get',
            method: 'post',
            data: {uid: $("#popover").attr('value')},
            success: function (profile) {
                $("#popover").attr("data-content", "<div class='text-center'>" + "<img src='" + profile.img + "'/>"
                    + "<p style='color: grey'>" + profile.signature + "</p>" + "<p>" + copStr(profile.tag) + "</p>" + "</div>");
            },
            error: function () {
                $("#popover").attr("data-content", "暂无此人资料");
            }
        });
    });

    function copStr(str) {
        var result = "";
        $.each(str.split(","), function (index, value) {
            result += "<span class='label label-success'>" + "<span class='glyphicon glyphicon-tag'>" + value + "</span>" + "</span> ";
        })
        return result;
    }

    $("#popover").popover({
        trigger: 'hover',
        html: true
    });

    $(".input-sm").each(function () {
        $(this).click(function () {
            document.body.style.backgroundColor = $(this).attr('value');
            if (!localStorage) {
                alert("浏览器不支持localstorage");
            } else {
                localStorage.setItem("bodyColor",$(this).attr('value'));
            }
        });
    });


    $("#plus").click(function () {
        $.ajax({
            method:'post',
            url:'/collect/add',
            data:{bid:$(this).attr("value")},
            success:function (data) {
                if(data=="EXCEPTION"||data=="RUNTIIME EXCEPTION"){
                    window.location.href="/user";
                }else
                var res= window.confirm(data+",前往查看？");
                if(res){
                    window.location.href="/user/home";
                }
            }
        });
    });

</script>
</body>
</html>