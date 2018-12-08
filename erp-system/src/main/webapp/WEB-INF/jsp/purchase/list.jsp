<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="oNo" placeholder="请输入物料号" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加物料采购信息','/materialPurchase/edit',730,500)"><i
                class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据: ${purchases.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>物料名</th>
            <th>物料编号</th>
            <th>采购单价</th>
            <th>采购数量</th>
            <th>供应商</th>
            <th>状态</th>
            <th>审核人</th>
            <th>入库日期</th>
            <th>入库员</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${purchases.total > 0}">
            <c:forEach items="${purchases.rows}" var="purchase">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${purchase.mphId}'>
                            <i
                                    class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${purchase.mphName}</td>
                    <td>${purchase.mphSn}</td>
                    <td>${purchase.mphPrice}</td>
                    <td>${purchase.mphCount}</td>
                    <td>${purchase.vendor.VName}</td>
                    <td>
                        <%@include file="../common/purchase_status.jsp" %>
                    </td>
                    <td>${purchase.mphSender}</td>
                    <td>${purchase.mphDate}</td>
                    <td>${purchase.mphOperator}</td>
                    <td>${purchase.mphNote}</td>
                    <td class="td-manage">
                        <c:if test="${purchase.mphVendorId=='1'||purchase.mphVendorId=='2'}">
                            <a title="编辑物料信息"
                               onclick="x_admin_show('编辑物料信息','/materialPurchase/edit?mphId=${purchase.mphId}',730,400)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                        </c:if>
                        <c:if test="${purchase.mphVendorId=='1'||purchase.mphVendorId=='2'}">
                            <a title="审核"
                               onclick="x_admin_show('审核采购申请','/materialPurchase/verify?mphId=${purchase.mphId}',530,200)">
                                <i class="layui-icon">&#xe672;</i>
                            </a>
                        </c:if>
                        <c:if test="${purchase.mphVendorId=='3'}">
                            <a title="确认出货" onclick="member_confirm(this,'${purchase.mphId}')" href="javascript:;"
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

    function delAll(argument) {

        var data = tableCheck.getData();

        if (data.length > 0) {
            layer.confirm('确认要删除选定的' + data.length + '记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/materialPurchase/delete', {"mpid": data.toString()}, function (res) {
                    if(res=='数据删除成功'){
                        layer.msg(res, {icon: 6});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    }
                    else
                        layer.msg(res, {icon: 1});
                });

            });
        }
        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }


    function member_confirm(obj, id) {
        layer.confirm('确认采购数量与入库数量一致<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe607;</i>', function (index) {
            var purchase = new Object();
            purchase.mphId = id;
            purchase.mphVendorId = '4';
            //捉到所有被选中的，发异步进行删除
            $.post('/materialPurchase/confirm', purchase, function (res) {
                if (res == "数据更新成功")
                    layer.alert(res, {icon: 6}, function () {
                        window.location.reload();

                    });
                else
                    layer.alert(res, {icon: 1});
            });
            return false;
        });
    }

</script>
</body>

</html>