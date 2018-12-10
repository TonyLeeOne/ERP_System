<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="hisId" value="${device.hisId}">
            <label for="hisDeviceCode" class="layui-form-label">
                <span class="x-red">*</span>设备编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="hisDeviceCode" name="hisDeviceCode" required="" lay-verify="required"
                       value="${device.hisDeviceCode}" autocomplete="off" value="admin" class="layui-input">
            </div>
            <%--<label for="hisDate" class="layui-form-label">--%>
                <%--<span class="x-red">*</span>保养日期--%>
            <%--</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" id="hisDate" name="hisDate" required="" lay-verify="required"--%>
                       <%--value="${device.hisDate}" autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        </div>
        <div class="layui-form-item">
            <label for="hisOperator" class="layui-form-label">
                <span class="x-red">*</span>保养人员签字
            </label>
            <div class="layui-input-inline">
                <input type="text" id="hisOperator" name="hisOperator" required="" lay-verify="required"
                       value="${device.hisOperator}" autocomplete="off" class="layui-input">
            </div>
            <label for="hisResult" class="layui-form-label">
                <span class="x-red">*</span>检查结果
            </label>
            <div class="layui-input-inline">
                <select name="hisResult" id="hisResult" required="" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${device.hisResult == 1}"> selected</c:if>>良好</option>
                    <option value="2" <c:if test="${device.hisResult == 2}"> selected</c:if>>故障</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="hisNote" class="layui-form-label">
                备注
            </label>
            <div class="layui-input-inline">
                <textarea name="hisNote" id="hisNote" cols="69" rows="9">${device.hisNote}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
            </label>
            <c:if test="${not empty device.hisId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty device.hisId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    新增
                </button>
            </c:if>
        </div>
    </form>
</div>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        laydate.render({
            elem: '#hisDate' //指定元素
        });
    });
    layui.use(['form', 'layer'], function () {
        // $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //监听提交
        form.on('submit(add)', function (data) {
            var obj = data.field;
            var msg = "新增";
            if (obj.hisId != '') {
                msg = "更新";
            }
            jQuery.ajax({
                url: "/deviceHis/add",
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