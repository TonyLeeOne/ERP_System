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
                    <c:if test="${!empty consume.mcId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${consume.mcId}" name="mcId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="mcMoSn" class="layui-form-label">
                        工单编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="mcMoSn" lay-verify="required" lay-search id="mcMoSn" lay-filter="orders"
                                val="${consume.mcMoSn}">
                            <option>请选择工单号</option>
                        </select>
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="mcMpSn" class="layui-form-label">
                        生产计划<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mcMpSn" name="mcMpSn" value="${consume.mcMpSn}" lay-verify="required"
                               autocomplete="off"
                               readonly="readonly"
                               class="layui-input">
                    </div>
                </div>
            </div>


            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="mcMSn" class="layui-form-label">
                        BOM编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mcMSn" name="mcMSn" value="${consume.mcMSn}" lay-verify="required"
                               autocomplete="off"
                               readonly="readonly"
                               class="layui-input">
                    </div>
                    <%--<div class="layui-input-inline">--%>
                        <%--<select name="mcMSn" lay-verify="required" lay-search id="mcMSn"--%>
                                <%--val="${consume.mcMSn}">--%>
                        <%--</select>--%>
                    <%--</div>--%>
                </div>

                <div class="layui-col-md6">
                    <label for="mcCountNeeded" class="layui-form-label">
                        需求数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input class="layui-input" type="number" name="mcCountNeeded" id="mcCountNeeded"
                               value="${consume.mcCountNeeded}" lay-verify="required">
                    </div>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-col-md6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前状态</label>
                        <div class="layui-input-block">
                            <c:if test="${empty consume.mcStatus}">
                                <input type="radio" title="待审核" checked="checked">
                            </c:if>
                            <c:if test="${!empty consume.mcStatus}">
                                <input type="radio" title=" <%@include file="../common/mc_status.jsp" %>"
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
                    <c:if test="${! empty consume.mcId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty consume.mcId}">
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
    function renderForm() {
        layui.use('form', function () {
            var form = layui.form;
            form.render();
        });
    }

    $(function () {
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form, layer = layui.layer;

            /**
             * 填充物料编号
            //  */
            // $.ajax({
            //     url: "/material/getAvailableMaterials",
            //     method: "get",
            //     success: function (data) {
            //         if (data) {
            //             if ($("#mcMSn").attr('val')) {
            //                 $.each(data, function (index, pro) {
            //                     if ($("#mcMSn").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
            //                         $("#mcMSn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
            //                     } else
            //                         $("#mcMSn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
            //                 });
            //             } else
            //                 $.each(data, function (index, pro) {
            //                     $("#mcMSn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
            //                 });
            //         }
            //         renderForm();
            //     },
            //     error: function () {
            //         alert("获取数据失败");
            //     }
            // });

            $.ajax({
                url: "/manOrder/getAllMoSn",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#mcMoSn").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#mcMoSn").attr('val') == pro) {
                                    $("#mcMoSn").append("<option value='" + pro+ "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#mcMoSn").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#mcMoSn").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                    }
                    renderForm();
                },
                error: function () {
                    alert("获取数据失败");
                }
            });

            form.on('select(orders)', function (data) {
                $.ajax({
                    url: "/manOrder/getByMoSn",
                    method: "post",
                    data: {moSn: data.elem[data.elem.selectedIndex].value},
                    dataType: 'json',
                    success: function (manOrder) {
                        if (manOrder) {
                            $("#mcMpSn").val(manOrder.moMpSn);
                            $("#mcMSn").val(manOrder.mbom.bcode);
                        }

                    },
                    error: function () {
                        layer.alert("你选择的工单信息可能不存在");
                    }
                });

            });

            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/materialConsume/add",
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
                    url: "/materialConsume/update",
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