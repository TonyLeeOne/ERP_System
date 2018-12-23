<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../common/header.jsp" %>

<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="sOrderNo" class="layui-form-label">
                        订单编号
                    </label>
                    <div class="layui-input-inline">
                        <select name="sOrderNo" lay-verify="" lay-search id="sOrderNo" lay-filter="orders"
                                val="${ship.SOrderNo}">
                            <option>请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <c:if test="${!empty ship.SId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${ship.SId}" name="sId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="sProCode" class="layui-form-label">
                        产品编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="sProCode" name="sProCode" value="${ship.SProCode}" readonly="readonly"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="sShipCount" class="layui-form-label">
                        出货数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="sShipCount" name="sShipCount"
                               value="${ship.SShipCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <c:if test="${!empty ship.SId}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前状态</label>
                        <div class="layui-input-block">
                            <input type="radio" title="<%@include file='../common/ship_status.jsp'%>" checked>
                        </div>
                    </div>
                </c:if>
            </div>

            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty ship.SId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty ship.SId}">
                        <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                            新增
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </form>
</div>
<script>


    $(function () {
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form, layer = layui.layer;

            /**
             * 填充订单编号
             */
            $.ajax({
                url: "/order/getONos",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#sOrderNo").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#sOrderNo").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                    $("#sOrderNo").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#sOrderNo").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#sOrderNo").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });

                    }
                    form.render();
                },
                error: function () {
                    alert("获取数据失败");
                }
            });

            form.on('select(orders)', function (data) {
                $.ajax({
                    url: "/order/getOrderByONo",
                    method: "post",
                    data: {oNo: data.elem[data.elem.selectedIndex].value},
                    dataType: 'json',
                    success: function (o) {
                        $("#sProCode").val(o.oproductCode);
                        $("#sShipCount").val(o.ocount);
                    },
                    error: function () {

                    }
                });

            });
            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/ship/add",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
                        //发异步，把数据提交给php
                        if (res == "数据新增成功")
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        else
                            layer.alert(res, {icon: 6});

                        return false;
                    },
                    error: function (res) {
                        console.log("error:");
                        console.log(res);
                    }
                });
                return false;
            });
            //监听提交
            form.on('submit(edit)', function (data) {
                $.ajax({
                    url: "/ship/update",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
                        if (res == "数据更新成功")
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        else
                            layer.alert(res, {icon: 6});
                        return false;
                    },
                    error: function (res) {
                        console.log("error:");
                        console.log(res);
                    }
                });
                return false;
            });

        });
    });

</script>
</body>

</html>