        <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .col-sm-4 {
            font-size: 10px
        }
    </style>

</head>
<body>
<%@include file="nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <c:if test="${!empty proverbList}">
                <c:forEach items="${proverbList}" var="item">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <span id="itemId">${item.id}</span>　　　${item.author}　　　${item.ptime}　　　${item.tag}　　　
                            <button type="button" class="btn btn-primary" id="delete" value="${item.id}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </button>
                                    <button type="button" class="btn btn-primary" id="update" value="${item.id}">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </button>

                        </div>
                        <div class="panel-body">
                                ${item.content}
                        </div>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <div class="col-md-6">
            <form method="post" action="/proverb/add" class="form-horizontal" id="addForm">
                <div class="form-group col-sm-12">
                    <div class="input-group input-group-mid ">
                        <span class="input-group-addon">作者</span>
                        <input type="text" class="form-control" name="author" placeholder="请作者名字"
                               aria-describedby="sizing-addon3">
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <textarea class="form-control" rows="3" name="content"></textarea>
                </div>
                <div class="form-group col-sm-12">
                    <button type="submit" class="btn btn-success btn-block" >添加</button>
                </div>
                <div class="form-group col-sm-12">
                    <button class="btn btn-default btn-block" onclick="window.location='/proverb/home'" >取消</button>
                </div>
            </form>
            <form method="post" id="updateForm" action="/proverb/update">
                <div class="form-group col-sm-12">
                    <div class="input-group input-group-mid ">
                        <span class="input-group-addon">作者</span>
                        <input type="text" class="form-control" name="author" placeholder="请输入作者名字"
                               id="pauthor" aria-describedby="sizing-addon3" value="">
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <textarea class="form-control" rows="3" name="content" id="pcontent" value=""></textarea>
                </div>
                <div class="form-group col-sm-12">
                    <input type="hidden" name="id" id="pid" value="">
                </div>
                <div class="form-group col-sm-12">
                    <button type="submit" class="btn btn-success btn-block">更新</button>
                </div>
                <div class="form-group col-sm-12">
                    <button class="btn btn-default btn-block" type="reset" >重置</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="commonForJs.jsp" %>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script>
    $(function(){
        $("#updateForm").hide();
        $("[id='update']").click(function () {
            $("#addForm").hide();
            $("#updateForm").show();
            $.ajax({
                method:"POST",
                url:"/proverb/preUpdate/",
                data:{id:$(this).attr('value')},
                error:function(err){
                    console.log("error");
                },
                success:function (data) {
                    $("#pcontent").val(data.content);
                    $("#pid").val(data.id);
                    $("#pauthor").val(data.author);
                }
            })

        });

        $("[id='delete']").click(function () {
            window.location="/proverb/delete/"+$(this).attr('value');

        });
    });
</script>
</body>
</html>