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
                    <c:if test="${!empty mOrder.moId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${mOrder.moId}" name="moId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="moSn" class="layui-form-label">
                        工单编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="moSn" name="moSn" value="${mOrder.moSn}" lay-verify="required"
                               autocomplete="off"  <c:if test="${! empty mOrder.moSn}">readonly</c:if>
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="moMpSn" class="layui-form-label">
                        计划编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="moMpSn" lay-verify="required" lay-search id="moMpSn" lay-filter="orders"
                                val="${mOrder.moMpSn}">
                        </select>
                    </div>
                </div>
            </div>


            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="moStartDate" class="layui-form-label">
                        开始日期<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" placeholder="计划开始日期" name="moStartDate" id="moStartDate"
                               value="${mOrder.moStartDate}" lay-verify="required">
                    </div>
                </div>

                <div class="layui-col-md6">

                    <label for="moEndDate" class="layui-form-label">
                        结束日期<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" placeholder="计划结束日期" name="moEndDate" id="moEndDate"
                               value="${mOrder.moEndDate}" lay-verify="required">
                    </div>
                </div>
            </div>


            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="moCount" class="layui-form-label">
                        计划数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="moCount" name="moCount" lay-verify="required"
                               value="${mOrder.moCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <c:if test="${! empty mOrder.moId}">
                    <div class="layui-col-md6">
                        <label for="moWaitCount" class="layui-form-label">
                            已生产数量<span>*</span>
                        </label>
                        <div class="layui-input-inline">
                            <input type="number" id="moWaitCount" name="moWaitCount" lay-verify="required"
                                   value="${mOrder.moWaitCount}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </c:if>

            </div>

            <div class="layui-row">

                <div class="layui-col-md6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前状态</label>
                        <div class="layui-input-block">
                            <c:if test="${empty mOrder.moStatus}">
                                <input type="radio" title="生产中" checked="checked">
                            </c:if>
                            <c:if test="${!empty mOrder.moStatus}">
                                <input type="radio" title=" <%@include file="../common/mo_status.jsp" %>"
                                       checked="checked">
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty mOrder.moId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty mOrder.moId}">
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
            elem: '#moStartDate' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#moEndDate' //指定元素
        });
    });

    $(function () {
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form, layer = layui.layer;

            /**
             * 填充订单编号
             */
            $.ajax({
                url: "/manPlan/getAllMpsns",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#moMpSn").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#moMpSn").attr('val') == pro) {
                                    $("#moMpSn").append("<option value='" + pro + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#moMpSn").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#moMpSn").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                    }
                    form.render();
                },
                error: function () {
                    alert("获取数据失败");
                }
            });

            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/manOrder/add",
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
                            layer.alert(res, {icon: 2});

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
                    url: "/manOrder/update",
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