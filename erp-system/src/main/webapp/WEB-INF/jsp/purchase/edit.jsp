<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <div class="layui-col-md6">
                        <label for="mphSn" class="layui-form-label">
                            物料编号
                        </label>
                        <div class="layui-input-inline">
                            <select name="mphSn" lay-verify="required" lay-search id="mphSn" lay-filter="orders"
                                    val="${purchase.mphSn}">
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-input-inline">
                            <input type="hidden" id="mphId" name="mphId"
                                   value="${purchase.mphId}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="mphPrice" class="layui-form-label">
                        采购单价
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="mphPrice" name="mphPrice"
                               value="${purchase.mphPrice}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="mphCount" class="layui-form-label">
                        采购数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="mphCount" name="mphCount"
                               value="${purchase.mphCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="mphNote" placeholder="请输入内容"
                                      class="layui-textarea">${purchase.mphNote}</textarea>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${not empty purchase.mphId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            更新
                        </button>
                    </c:if>
                    <c:if test="${empty purchase.mphId}">
                        <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                            提交申请
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
        /**
         * 填充物料编号
         */
        $.ajax({
            url: "/material/getAvailableMaterials",
            method: "get",
            success: function (data) {
                if (data) {
                    if ($("#mphSn").attr('val')) {
                        $.each(data, function (index, pro) {
                            if ($("#mphSn").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                $("#mphSn").append("<option value='" + pro + "' selected='selected'>" + pro + "</option>");
                            } else
                                $("#mphSn").append("<option value='" + pro + "'>" + pro + "</option>");
                        });
                    } else
                        $.each(data, function (index, pro) {
                            $("#mphSn").append("<option value='" + pro + "'>" + pro + "</option>");
                        });
                }
                renderForm();
            },
            error: function () {
                alert("获取数据失败");
            }
        });


        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form
                , layer = layui.layer;
            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/materialPurchase/add",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
                        if (res == '数据新增成功') {
                            //发异步，把数据提交给php
                            layer.alert("增加成功", {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        } else {
                            layer.alert("新增失败")
                        }
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
                    url: "/material/update",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
                    async: false,
                    success: function (res) {
                        if (res == "数据更新成功") {
                            //发异步，把数据提交给php
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        } else {
                            layer.alert("更新失败");
                        }

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
    })
</script>
</body>

</html>