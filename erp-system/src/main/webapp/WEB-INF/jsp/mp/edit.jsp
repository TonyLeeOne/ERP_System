<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../common/header.jsp" %>
<style>
    span {
        color: red;
    }
</style>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <c:if test="${!empty plan.mpId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${plan.mpId}" name="mpId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="mpSn" class="layui-form-label">
                        计划编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mpSn" name="mpSn" value="${plan.mpSn}" lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">

                    <label for="mpCount" class="layui-form-label">
                        待生产数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="mpCount" name="mpCount" lay-verify="required"
                               value="${plan.mpCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>

            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="mpOrderId" class="layui-form-label">
                        订单号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="mpOrderId" lay-verify="required" lay-search id="mpOrderId" lay-filter="orders"
                                val="${plan.mpOrderId}">
                        </select>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="mpProCode" class="layui-form-label">
                        产品编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mpProCode" name="mpProCode" value="${plan.mpProCode}" readonly="readonly" lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="mpStartDate" class="layui-form-label">
                        开始日期<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" placeholder="计划开始日期" name="mpStartDate" id="mpStartDate" value="${plan.mpStartDate}" lay-verify="required">
                    </div>
                </div>

                <div class="layui-col-md6">

                    <label for="mpEndDate" class="layui-form-label">
                        结束日期<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" placeholder="计划结束日期" name="mpEndDate" id="mpEndDate" value="${plan.mpEndDate}" lay-verify="required">
                    </div>
                </div>
            </div>

            <div class="layui-row">

                <div class="layui-col-md6">
                        <div class="layui-form-item">
                            <label class="layui-form-label"> 当前状态</label>
                            <div class="layui-input-block" id="single" value="${plan.mpStatus}">
                                <input type="radio" name="mpStatus" value="1" title="生产中" id="pro">
                                <input type="radio" name="mpStatus" value="2" title="已完工" id="stop">
                            </div>
                        </div>
                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty plan.mpSn}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty plan.mpSn}">
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

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#mpStartDate' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#mpEndDate' //指定元素
        });
    });

    $(function () {

        if($("#single").attr('value')) {
            if ($("#single").attr('value') == '1') {
                $("#pro").attr('checked', true);
            }
            if ($("#single").attr('value') == '2') {
                $("#stop").attr('checked', true);
            }
        }else
            $("#pro").attr('checked', true);


        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form, layer = layui.layer;

            /**
             * 填充订单编号
             */
            $.ajax({
                url: "/order/getMPONos",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#mpOrderId").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#mpOrderId").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                    $("#mpOrderId").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#mpOrderId").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#mpOrderId").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
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
                        $("#mpProCode").val(o.oproductCode);
                    },
                    error: function () {
                        layer.alert("你查找的订单号可能不存在");
                    }
                });

            });


            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/manPlan/add",
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
                    url: "/manPlan/update",
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