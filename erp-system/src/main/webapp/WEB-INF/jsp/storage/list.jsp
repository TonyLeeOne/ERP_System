<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="proCode" placeholder="请输入产品编号" autocomplete="off" class="layui-input">
            <input type="text" name="proCode" placeholder="请输入工单号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('新增入库记录','/storage/edit',730,450)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据: ${storages.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>生产计划编号</th>
            <th>工单编号</th>
            <th>产品编号</th>
            <th>产品名称</th>
            <th>工单数量</th>
            <th>入库数量</th>
            <th>入库日期</th>
            <th>确认人</th>
            <th>入库人</th>
            <th>状态</th>
            <th>进度</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${storages.total > 0}">
            <c:forEach items="${storages.rows}" var="storage">
                <tr>
                    <td>
                        <c:if test="${storage.stoStatus!='3'}">
                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${storage.stoId}'>
                                <i class="layui-icon">&#xe605;</i></div>
                        </c:if>
                    </td>
                    <td>${storage.stoMpSn}</td>
                    <td>${storage.stoMoSn}</td>
                    <td>${storage.product.proCode}</td>
                    <td>${storage.product.proName}</td>
                    <td>${storage.manOrder.moCount}</td>
                    <td>${storage.stoIndeedNum}</td>
                    <td>${storage.stoRealDate}</td>
                    <td>${storage.stoSurer}</td>
                    <td>${storage.stoSender}</td>
                    <td>
                        <%@include file="../common/sto_status.jsp" %>
                    </td>
                    <td>
                        <div class="layui-progress" lay-showpercent="true">
                            <div class="layui-progress-bar layui-bg-green" lay-percent="${storage.stoStatus}/3"></div>
                        </div>
                    </td>
                    <td class="td-manage">
                        <c:if test="${storage.stoStatus=='1'||storage.stoStatus=='2'}">
                            <a title="编辑" onclick="x_admin_show('编辑入库清单','/storage/edit?stoId=${storage.stoId}',730,350)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                        </c:if>
                        <c:if test="${storage.stoStatus=='1'}">
                            <a title="确认入库" onclick="x_admin_show('确认入库记录清单','/storage/confirm?stoId=${storage.stoId}',730,350)"
                               id="confirm">
                                <i class="layui-icon">&#x1005;</i>
                            </a>
                        </c:if>
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
    function member_confirm(obj, id) {
        // layer.confirm('确认出货信息准确无误？', function (index) {
        var shipment = new Object();
        shipment.sId = id;
        shipment.sStatus = '4';
        layer.confirm('确认入库信息无误？', function (index) {
            $.post('/storage/confirm', shipment, function (res) {
                if (res == "数据更新成功")
                    layer.alert(res, {icon: 6}, function () {
                        window.location.reload();

                    });
                else
                    layer.alert(res, {icon: 6});
            });
            return false;
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        if (data.length > 0) {
            layer.confirm('确认要删除已选的入库记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/storage/delete', {"sId": data.toString()}, function (res) {
                    if (res == '数据删除成功') {
                        layer.msg(res, {icon: 1});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    } else
                        layer.msg(res, {icon: 1});
                });

            });
        }

        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }
</script>
</body>

</html>