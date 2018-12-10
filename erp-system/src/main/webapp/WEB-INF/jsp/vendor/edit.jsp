<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="vId" value="${vendor.VId}">
            <label for="vName" class="layui-form-label">
                <span class="x-red">*</span>供应商名字
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vName" name="vName" required="" lay-verify="required"
                       value="${vendor.VName}" autocomplete="off" value="admin" class="layui-input">
            </div>
            <label for="vAddress" class="layui-form-label">
                <span class="x-red">*</span>供应商地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vAddress" name="vAddress" required="" lay-verify="required"
                       value="${vendor.VAddress}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="vTel" class="layui-form-label">
                <span class="x-red">*</span>联系方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vTel" name="vTel" required="" lay-verify="required"
                       value="${vendor.VTel}" autocomplete="off" class="layui-input">
            </div>
            <label for="vPublish" class="layui-form-label">
                <span class="x-red">*</span>法人代表
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vPublish" name="vPublish" required="" lay-verify="required"
                       value="${vendor.VPublish}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="vFullname" class="layui-form-label">
                <span class="x-red">*</span>供应商全名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vFullname" name="vFullname" required="" lay-verify="required"
                       value="${vendor.VFullname}" autocomplete="off" class="layui-input">
            </div>
            <label for="vStatus" class="layui-form-label">
                <span class="x-red">*</span>供应商状态
            </label>
            <div class="layui-input-inline">
                <select name="vStatus" id="vStatus" required="" lay-verify="required">
                    <option value="1" <c:if test="${vendor.VStatus == 1}"> selected</c:if>>正常</option>
                    <option value="2" <c:if test="${vendor.VStatus == 2}"> selected</c:if>>终止合作</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="vNote" class="layui-form-label">
                备注
            </label>
            <div class="layui-input-inline">
                <textarea name="vNote" id="vNote" cols="69" rows="9"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
            </label>
            <c:if test="${not empty vendor.VId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty vendor.VId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    新增
                </button>
            </c:if>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        // $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //监听提交
        form.on('submit(add)', function (data) {
            var obj = data.field;
            var msg = "新增";
            var url = "/vendor/add";
            if (obj.vId != '') {
                msg = "更新";
                url = url + "?vid=" + obj.vId;
            }
            jQuery.ajax({
                url: url,
                type: "POST",
                data: obj,
                // dataType: "json",
                // contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res == '数据新增成功' || res == '数据更新成功') {
                        //发异步，把数据提交给php
                        layer.alert(msg + "成功", {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            window.parent.location.reload();
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert(msg + "失败")
                    }
                }
            });
            return false;
        });


    });
</script>
</body>

</html>