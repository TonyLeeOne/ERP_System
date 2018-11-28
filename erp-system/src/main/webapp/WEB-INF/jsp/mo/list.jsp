<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <input class="layui-input" placeholder="截止日" name="end" id="end">
            <div class="layui-input-inline">
                <select name="contrller">
                    <option value="">订单状态</option>
                    <option value="1">待审核</option>
                    <option value="2">审核未通过</option>
                    <option value="3">待出货</option>
                    <option value="4">已安排出货</option>
                </select>
            </div>
            <input type="text" name="oNo" placeholder="请输入订单号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加新生产工单','/manOrder/edit',730,500)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据: ${manOrders.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>工单编号</th>
            <th>生产计划编号</th>
            <th>开始日期</th>
            <th>结束日期</th>
            <th>计划数量</th>
            <th>已生产数量</th>
            <th>当前状态</th>
            <th>进度</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${manOrders.total > 0}">
            <c:forEach items="${manOrders.rows}" var="mOrder">
                <tr>
                    <td>
                        <c:if test="${mOrder.moStatus!='2'}">
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${mOrder.moId}'><i
                                class="layui-icon">&#xe605;</i></div>
                        </c:if>
                    </td>
                    <td>${mOrder.moSn}</td>
                    <td>${mOrder.moMpSn}</td>
                    <td>${mOrder.moStartDate}</td>
                    <td>${mOrder.moEndDate}</td>
                    <td>${mOrder.moCount}</td>
                    <td>${mOrder.moWaitCount}</td>
                    <td>
                        <%@include file="../common/mo_status.jsp" %>
                    </td>
                    <td width="100px">
                        <c:if test="${! empty mOrder.moWaitCount}">
                            <div class="layui-progress">
                                <div class="layui-progress-bar layui-bg-orange"
                                     lay-percent="${mOrder.moWaitCount}/${mOrder.moCount}"></div>
                            </div>
                        </c:if>
                    </td>
                    <td class="td-manage">
                        <c:if test="${mOrder.moStatus=='1'}">
                        <a title="编辑生产计划" onclick="x_admin_show('编辑生产计划','/manOrder/edit?moId=${mOrder.moId}',730,500)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        </c:if>
                        <a title="查看生产工单记录" onclick="x_admin_show('生产工单记录','/ship/findByOrderNo?sOrderNo=${order.ONo}',730)" href="javascript:;">
                            <i class="layui-icon">&#xe60a;</i>
                        </a>
                            <%--<a title="删除" onclick="member_del(this,'${order.OId}')" href="javascript:;">--%>
                            <%--<i class="layui-icon">&#xe640;</i>--%>
                            <%--</a>--%>
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
                $.post('/manOrder/delete', {"moId": data.toString()}, function (res) {
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