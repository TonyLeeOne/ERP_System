<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>首页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        article {
            margin: 1%;
            border-radius: 15px;
            padding: 5%;
            background-color: #FFFFFF;
            box-shadow: 5px 5px 0 0 rgba(2, 139, 247, 0.07);
        }

        body {
            background-color: rgba(0, 0, 0, 0.12);
        }


        footer{
            margin-top: 5%;
        }
        img{
            height: 30%;
            width: 80%;
            border-radius: 10px;
            background-color: rgba(250, 249, 210, 0.15);
        }

    </style>
</head>
<body>
<%@include file="paganation.jsp"%>
<div class="container" style="margin-top: 5%">
    <div class="row">
        <div class="col-sm-7" id="blogs">
            <c:if test="${! empty blogs}">
                <c:forEach items="${blogs}" var="blog">
            <article>
                <header>
                    <h4 class="text-center">${blog.subject}
                        <span class="label label-default">${blog.tag}</span>　
                        <p style="color: rgb(13,95,255);font-size: 12px;margin-top: 10px">
                            <span class="glyphicon glyphicon-time"></span> ${blog.ptime}　
                            <span class="glyphicon glyphicon-user"></span> ${blog.user.username}　
                        </p>
                    </h4>
                </header>
                <div id="contents" style="margin-top: 4%;font-size: medium">
                    ${blog.contents}
                </div>
                <footer>
                    <button class="btn btn-warning btn-small" value="${blog.id}">阅读更多</button>
                </footer>
            </article>
                </c:forEach>
            </c:if>
        </div>
        <div class="col-sm-3">

        </div>
        <div class="col-sm-2">

        </div>
    </div>
</div>
<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script>
    $(function () {
        var myName="<%=session.getAttribute("user")%>";
        if(myName.length<=4){
            $("#info").hide();
            $("#login").show();
        }else{
            $("#login").hide();
            $("#info").show();
        }
        $("[id='contents']").each(function () {
            if(getImgUrl($(this).html())){
                $(this).html("<p class='text-center'>"+getImgUrl($(this).html())+"</p>"+GetChinese($(this).html()));
            }else{
                $(this).html(GetChinese($(this).html()));

            }
        });
    });

    function GetChinese(strValue) {
        if(strValue!= null && strValue!= ""){
            var reg = /[\u4e00-\u9fa5]/g;
            var str=strValue.match(reg).join("");
            if(str.length>97){
                return str.substring(0,97)+" ...";
            }else{
                return str.substring(0,str.length)+" ...";
            }
        }
        else
            return "";
    }

    $("button").each(function () {
        $(this).click(function () {
            window.location.href="/blog/browse/"+$(this).val();
        });
    });

    function getImgUrl(strValue) {
        if(strValue!= null && strValue!= ""){
            var reg = /<img.*?(?:>|\/>)/;
            var str=strValue.match(reg);
            return str;
        }
        else
            return "";
    }
    $("[id='tagSearch']").each(function () {
        $(this).click(function () {
            window.location.href="/blog/"+$(this).text();
        });
    });
</script>
</body>
</html>