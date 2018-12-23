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
                    <c:if test="${!empty detail.bdId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${detail.bdId}" name="bdId">
                        </div>
                    </c:if>
                </div>
                <div class="layui-col-md6">
                    <c:if test="${!empty detail.bdBcode}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${detail.bdBcode}" name="bdBcode">
                        </div>
                    </c:if>
                    <c:if test="${empty detail.bdBcode}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${bCode}" name="bdBcode">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="bdMsn" class="layui-form-label">
                        物料编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="bdMsn" lay-verify="required" lay-search id="bdMsn" lay-filter="orders"
                                val="${detail.bdMsn}">
                        </select>
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="bdNum" class="layui-form-label">
                        数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="bdNum" name="bdNum" value="${detail.bdNum}" lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="bdRate" class="layui-form-label">
                        损耗率<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="bdRate" name="bdRate" value="${detail.bdRate}" lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty detail.bdId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty detail.bdId}">
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
                url: "/material/getAvailableMaterials",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#bdMsn").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#bdMsn").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                    $("#bdMsn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#bdMsn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#bdMsn").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
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
                    url: "/detail/add",
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
                    url: "/detail/update",
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