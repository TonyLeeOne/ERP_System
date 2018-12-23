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
                    <c:if test="${!empty bom.BId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${bom.BId}" name="bId">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="bPcode" class="layui-form-label">
                        产品编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="bPcode" lay-verify="required" lay-search id="bPcode" lay-filter="orders"
                                val="${bom.BPcode}">
                        </select>
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="bCode" class="layui-form-label">
                        BOM编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="bCode" name="bCode" value="${bom.BCode}" lay-verify="required"
                        <c:if test="${! empty bom.BCode}">readonly</c:if>
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="bName" class="layui-form-label">
                        BOM名称<span>*</span>
                    </label>
                    <div class="layui-input-inline">

                        <div class="layui-input-inline">
                            <input type="text" id="bName" name="bName" value="${bom.BName}" lay-verify="required"
                                   autocomplete="off"
                                       class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty bom.BId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty bom.BId}">
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
                url: "/product/populateProCodes",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#bPcode").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#bPcode").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                    $("#bPcode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#bPcode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#bPcode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                    }
                    renderForm();
                },
                error: function () {
                    layer.alert("获取数据失败");
                }
            });


            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/bom/add",
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
                    url: "/bom/update",
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