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
<%@include file="../common/breadcrumb.jsp"%>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="proCode" placeholder="请输入产品编号" autocomplete="off" class="layui-input">
            <input type="text" name="proName" placeholder="请输入产品名称" autocomplete="off" class="layui-input">
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('新增产品','/product/edit')"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共${products.pageNum}页，数据: ${products.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>产品编号</th>
            <th>产品名称</th>
            <th>库存数量</th>
            <th>单价</th>
            <th>图片</th>
            <th>状态</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${products.total > 0}">
            <c:forEach items="${products.rows}" var="product">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${product.proCode}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${product.proCode}</td>
                    <td>${product.proName}</td>
                    <td>${product.proCount}</td>
                    <td>${product.proPrice}</td>
                    <td>
                        <c:if test="${! empty product.proImage}">
                            <c:set value="${fn:split(product.proImage,',')}" var="urls"/>
                            <c:forEach items="${urls}" var="url">
                                <img src="${url}">
                            </c:forEach>
                        </c:if>
                    </td>
                    <td>
                        <%@include file="../common/pro_status.jsp" %>
                    </td>
                    <td>${product.proNote}</td>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑产品','/product/edit?proCode=${product.proCode}',730,550)" href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="入库记录" onclick="x_admin_show('入库记录','/product/edit?proCode=${product.proCode}',730,550)" href="javascript:;">
                            <i class="layui-icon">&#xe63c;</i>
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

        layer.confirm('确认要删除产品编号为【'+data+'】的记录吗？', function (index) {
            //捉到所有被选中的，发异步进行删除
            $.post('/product/batchDelete',{"proCodes":data.toString()},function(res){
                layer.msg(res, {icon: 1});
                $(".layui-form-checked").not('.header').parents('tr').remove();
            });

        });
    }
</script>
</body>

</html>