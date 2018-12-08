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
                    <c:if test="${!empty storage.stoId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${storage.stoId}" name="stoId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="stoMoSn" class="layui-form-label">
                        工单编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="stoMoSn" lay-verify="required" lay-search id="stoMoSn" lay-filter="orders"
                                val="${storage.stoMoSn}">
                            <option>请选择工单号</option>
                        </select>
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="stoMpSn" class="layui-form-label">
                        生产计划<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="stoMpSn" name="stoMpSn" value="${storage.stoMpSn}" lay-verify="required"
                               autocomplete="off"
                               readonly="readonly"
                               class="layui-input">
                    </div>
                </div>
            </div>


            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="stoProCode" class="layui-form-label">
                        产品编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="stoProCode" name="stoProCode" value="${storage.stoProCode}" lay-verify="required"
                               readonly="readonly"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="stoSender" class="layui-form-label">
                        入库人<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="stoSender" name="stoSender" value="${storage.stoSender}" lay-verify="required"
                               class="layui-input">
                    </div>
                </div>

            </div>

            <div class="layui-row">
                <div class="layui-col-md6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">当前状态</label>
                        <div class="layui-input-block">
                            <c:if test="${empty storage.stoStatus}">
                                <input type="radio" title="待确认" checked="checked">
                            </c:if>
                            <c:if test="${!empty storage.stoStatus}">
                                <input type="radio" title=" <%@include file="../common/sto_status.jsp" %>"
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
                    <c:if test="${! empty storage.stoId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty storage.stoId}">
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

            $.ajax({
                url: "/manOrder/getFinishedMoSn",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#stoMoSn").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#stoMoSn").attr('val') == pro) {
                                    $("#stoMoSn").append("<option value='" + pro+ "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#stoMoSn").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#stoMoSn").append("<option value='" + pro + "'>" + pro + "</option>");
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
                    success: function (order) {
                        if (order) {
                            $("#stoMpSn").val(order.moMpSn);
                            $("#stoProCode").val(order.manPlan.mpProCode);
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
                    url: "/storage/add",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
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
                    url: "/storage/update",
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