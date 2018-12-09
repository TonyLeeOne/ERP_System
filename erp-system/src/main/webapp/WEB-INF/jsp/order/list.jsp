<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="下单日期" name="oCreateDate" id="start">
            <div class="layui-input-inline">
                <select name="oStatus">
                    <option value="">订单状态</option>
                    <option value="1">待审核</option>
                    <option value="2">审核未通过</option>
                    <option value="3">待出货</option>
                    <option value="4">已安排出货</option>
                </select>
            </div>
            <input type="text" name="oNo" placeholder="请输入订单号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
<shiro:hasPermission name="order:delete">

<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
</shiro:hasPermission>
<shiro:hasPermission name="order:add">

<button class="layui-btn" onclick="x_admin_show('添加订单','/order/edit',730,750)"><i class="layui-icon"></i>添加
        </button>
</shiro:hasPermission>
        <span class="x-right" style="line-height:40px">共有数据: ${orders.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>订单编号</th>
            <th>客户名</th>
            <th>联系人</th>
            <th>联系人电话</th>
            <th>下单日期</th>
            <th>产品名称</th>
            <th>订单数量</th>
            <th>成交单价</th>
            <th>产品单价</th>
            <th>订单状态</th>
            <th>出货数量统计</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${orders.total > 0}">
            <c:forEach items="${orders.rows}" var="order">
                <tr>
                    <td>
                        <c:if test="${order.OStatus!='3'&&order.OStatus!='4'}">
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${order.ONo}'><i
                                class="layui-icon">&#xe605;</i></div>
                        </c:if>
                    </td>
                    <td>${order.ONo}</td>
                    <td>${order.OCustomName}</td>
                    <td>${order.OContacts}</td>
                    <td>${order.OTel}</td>
                    <td>${order.OCreateDate}</td>
                    <td>${order.product.proName}</td>
                    <td>${order.OCount}</td>
                    <td>${order.OPay}</td>
                    <td>${order.product.proPrice}</td>
                    <td>
                        <%@include file="../common/order_status.jsp" %>
                    </td>
                    <td>
                        <c:if test="${! empty order.OIndeedCount}">
                            <div class="layui-progress" lay-showpercent="true">
                                <div class="layui-progress-bar layui-bg-orange"
                                     lay-percent="${order.OIndeedCount}/${order.OCount}"></div>
                            </div>
                        </c:if>
                    </td>
                    <td class="td-manage">
                        <shiro:hasPermission name="order:update">
                        <a title="查看订单详情"
                           onclick="x_admin_show('查看订单信息，当前订单号【${order.ONo}】','/order/show?oId=${order.OId}')"
                           href="javascript:;">
                            <i class="layui-icon">&#xe63c;</i>
                        </a>
                        <c:if test="${order.OStatus!='3'&&order.OStatus!='4'}">
                        <a title="编辑订单信息" onclick="x_admin_show('编辑订单信息','/order/edit?oId=${order.OId}',730,750)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        </c:if>
                        </shiro:hasPermission>
                        <a title="查看出货记录" onclick="x_admin_show('查看出货记录','/ship/findByOrderNo?sOrderNo=${order.ONo}',730)" href="javascript:;">
                            <i class="layui-icon">&#xe60e;</i>
                        </a>
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
            layer.confirm('确认要删除订单编号为【' + data + '】的记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/order/batchDelete', {"orderNos": data.toString()}, function (res) {
                    layer.msg(res, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                });

            });
        }
        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }

</script>
</body>
</html>