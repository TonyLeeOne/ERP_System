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
                    <label for="mcCountIndeed" class="layui-form-label">
                        领取数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="mcCountIndeed" name="mcCountIndeed"  lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-col-md12" style="margin-top: 2%">
                    <label class="layui-form-label">
                    </label>
                    <c:if test="${! empty consume.mcId}">
                        <button class="layui-btn" lay-filter="edit" lay-submit="">
                            确定
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
            //监听提交
            form.on('submit(edit)', function (data) {
                $.ajax({
                    url: "/materialConsume/confirm",
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