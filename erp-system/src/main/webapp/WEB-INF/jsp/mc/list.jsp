<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="oNo" placeholder="请输入工单号" autocomplete="off" class="layui-input">
            <input type="text" name="oNo" placeholder="请输入物料号" autocomplete="off" class="layui-input">
            <input type="text" name="oNo" placeholder="请输入生产计划单号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <shiro:hasPermission name="material:delete">
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="material:add">
        <button class="layui-btn" onclick="x_admin_show('添加新领料单','/materialConsume/edit',730,500)"><i class="layui-icon"></i>添加
        </button>
        </shiro:hasPermission>
        <span class="x-right" style="line-height:40px">共有数据: ${consumes.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>生产工单编号</th>
            <th>物料编号</th>
            <th>生产计划编号</th>
            <th>需求数量</th>
            <th>审核人</th>
            <th>确认人</th>
            <th>领取数量</th>
            <th>领料日期</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${consumes.total > 0}">
            <c:forEach items="${consumes.rows}" var="consume">
                <tr>
                    <td>
                        <c:if test="${consume.mcStatus!='4'}">
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${consume.mcId}'><i
                                class="layui-icon">&#xe605;</i></div>
                        </c:if>
                    </td>
                    <td>${consume.mcMoSn}</td>
                    <td>${consume.mcMSn}</td>
                    <td>${consume.mcMpSn}</td>
                    <td>${consume.mcCountNeeded}</td>
                    <td>${consume.mcRequestor}</td>
                    <td>${consume.mcOperator}</td>
                    <td>${consume.mcCountIndeed}</td>
                    <td>${consume.mcDate}</td>
                    <td>
                        <%@include file="../common/mc_status.jsp" %>
                    </td>
                    <td class="td-manage">
                        <shiro:hasPermission name="material:update">
                        <c:if test="${consume.mcStatus=='1'||consume.mcStatus=='2'}">
                        <a title="编辑领料单" onclick="x_admin_show('编辑领料单','/materialConsume/edit?mcId=${consume.mcId}',730,300)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        </c:if>
                        <c:if test="${consume.mcStatus=='1'||consume.mcStatus=='2'}">
                            <a title="审核领料单" onclick="x_admin_show('审核领料单','/materialConsume/show?mcId=${consume.mcId}',730,400)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe672;</i>
                            </a>
                        </c:if>
                        <c:if test="${consume.mcStatus=='3'}">
                            <a title="确认领料单" onclick="x_admin_show('确认领料单','/materialConsume/sure?mcId=${consume.mcId}',730,230)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe605;</i>
                            </a>
                        </c:if>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <a class="num" href="">1</a>
            <span class="current">2</span>
            <a class="num" href="">3</a>
            <a class="num" href="">489</a>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

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

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.post('/order/delete', {"oid": id}, function (res) {
                if (res == '数据删除成功') {
                    // $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    window.location.reload();
                }
            });
            return false;

        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        if (data.length > 0) {
            layer.confirm('确认要删除选定的'+data.length+'记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/materialConsume/delete', {"mcId": data.toString()}, function (res) {
                    layer.msg(res, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                });

            });
        }
        else
            layer.alert("请至少选择一行记录", {icon: 1});
    }

</script>
</body>

</html>