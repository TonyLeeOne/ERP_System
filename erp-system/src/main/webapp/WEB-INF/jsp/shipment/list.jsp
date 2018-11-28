<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    img {
        height: 50px;
        width: 50px;
    }
</style>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="proCode" placeholder="请输入产品编号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('新增出货记录','/ship/edit',530,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据: ${ships.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>订单编号</th>
            <th>产品编号</th>
            <th>产品名称</th>
            <th>订单数量</th>
            <th>出货数量</th>
            <th>客户</th>
            <th>审核人</th>
            <th>审核日期</th>
            <th>确认人</th>
            <th>出货日期</th>
            <th>当前状态</th>
            <th>当前进度</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${ships.total > 0}">
            <c:forEach items="${ships.rows}" var="ship">
                <tr>
                    <td>
                        <c:if test="${ship.SStatus!='4'||ship.SStatus!='3'}">
                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${ship.SId}'>
                                <i class="layui-icon">&#xe605;</i></div>
                        </c:if>
                    </td>
                    <td>${ship.SOrderNo}</td>
                    <td>${ship.SProCode}</td>
                    <td>${ship.product.proName}</td>
                    <td>${ship.order.OCount}</td>
                    <td>${ship.SShipCount}</td>
                    <td>${ship.order.OCustomName}</td>
                    <td>${ship.SAuditor}</td>
                    <td>${ship.SAuditDate}</td>
                    <td>${ship.SSurer}</td>
                    <td>${ship.SShipDate}</td>
                    <td>
                        <%@include file="../common/ship_status.jsp" %>
                    </td>
                    <td>
                        <div class="layui-progress" lay-showpercent="true">
                            <div class="layui-progress-bar layui-bg-green" lay-percent="${ship.SStatus}/4"></div>
                        </div>
                    </td>
                    <td class="td-manage">
                        <c:if test="${ship.SStatus=='1'||ship.SStatus=='2'}">
                        <a title="编辑" onclick="x_admin_show('编辑出货清单','/ship/edit?sId=${ship.SId}',530,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        </c:if>
                        <c:if test="${ship.SStatus=='1'||ship.SStatus=='2'}">
                        <a title="审核" onclick="x_admin_show('审核出货申请','/ship/verify?sId=${ship.SId}',530,300)">
                            <i class="layui-icon">&#xe672;</i>
                        </a>
                        </c:if>
                        <c:if test="${ship.SStatus=='3'}">
                        <a title="确认出货" onclick="member_confirm(this,'${ship.SId}')" href="javascript:;" id="confirm">
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
        layer.confirm('可以出货？', function (index) {
            $.post('/ship/confirm', shipment, function (res) {
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

        layer.confirm('确认要删除已选的出货记录吗？', function (index) {
            //捉到所有被选中的，发异步进行删除
            $.post('/ship/delete', {"sId": data.toString()}, function (res) {
                if (res == '数据删除成功') {
                    layer.msg(res, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                } else
                    layer.msg(res, {icon: 1});
            });

        });
    }
</script>
</body>

</html>