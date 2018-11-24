<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp"%>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="init()">初始化URL配置</button>
        <button class="layui-btn" onclick="async()">同步配置信息</button>
        <span class="x-right" style="line-height:40px">共有数据: ${urls.size()} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>URL路径</th>
            <th>权限配置</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${urls.size() > 0}">
            <c:forEach items="${urls}" var="url">
                <tr>
                    <td>${url.url}</td>
                    <td>
                        <input type="text" value="${url.authority}">
                    </td>
                    <td class="td-manage">
                        <a title="更新" onclick="" href="javascript:;" value="${url.id}" id="update">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    function init() {
        $.post({
            url:"/urlConfigure/init",
            method:"get",
            async:false,
            success:function (res) {
                layer.msg(res, {icon: 1, time: 1000});
            },
            error:function () {
                layer.msg("初始化失败", {icon: 1, time: 1000});
            }
        });
    }


    function async() {
        $.post({
            url:"/urlConfigure/updatePerm",
            method:"get",
            async:false,
            success:function (res) {
                layer.msg(res, {icon: 1, time: 1000});
            },
            error:function () {
                layer.msg("初始化失败", {icon: 1, time: 1000});
            }
        });
    }

    $("[id='update']").each(function () {
        $(this).click(function () {
                window.location.href="/urlConfigure/update/"+$(this).attr("value")+"/"+$(this).parent().prev().children("input").val();
        });
    });

</script>
</body>

</html>