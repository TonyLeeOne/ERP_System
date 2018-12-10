<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="deviceId" value="${device.deviceId}">
            <label for="deviceName" class="layui-form-label">
                <span class="x-red">*</span>设备名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="deviceName" name="deviceName" required="" lay-verify="required"
                       value="${device.deviceName}" autocomplete="off" value="admin" class="layui-input">
            </div>
            <label for="deviceCode" class="layui-form-label">
                <span class="x-red">*</span>设备编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="deviceCode" name="deviceCode" required="" lay-verify="required"
                       value="${device.deviceCode}" autocomplete="off" class="layui-input">
            </div>

        </div>
        <div class="layui-form-item">
            <label for="devicePrice" class="layui-form-label">
                <span class="x-red">*</span>设备单价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="devicePrice" name="devicePrice" required="" lay-verify="number"
                       value="${device.devicePrice}" autocomplete="off" class="layui-input" >
            </div>
            <label for="deviceVendor" class="layui-form-label">
                <span class="x-red">*</span>设备供应商
            </label>
            <div class="layui-input-inline">
                <select name="deviceVendor" id="deviceVendor" required="" lay-verify="required">
                    <option value="">请选择</option>
                    <c:forEach items="${vendors}" var="vendor">
                        <option
                                <c:if test="${vendor.VId == device.deviceVendor}">selected </c:if>
                                value="${vendor.VId}">${vendor.VName}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
        <div class="layui-form-item">
            <label for="devicePurDate" class="layui-form-label">
                <span class="x-red">*</span>采购日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="devicePurDate" name="devicePurDate" required="" lay-verify="required"
                       value="${device.devicePurDate}" autocomplete="off" class="layui-input">
            </div>
            <label for="deviceUsedPeriod" class="layui-form-label">
                <span class="x-red">*</span>设备使用截止日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="deviceUsedPeriod" name="deviceUsedPeriod" required="" lay-verify="required"
                       value="${device.deviceUsedPeriod}" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">

            <label for="deviceStatus" class="layui-form-label">
                <span class="x-red">*</span>设备状态
            </label>
            <div class="layui-input-inline">
                <select name="deviceStatus" id="deviceStatus" required="" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${device.deviceStatus == 1}"> selected</c:if>>良好</option>
                    <option value="2" <c:if test="${device.deviceStatus == 2}"> selected</c:if>>待维修</option>
                    <option value="2" <c:if test="${device.deviceStatus == 3}"> selected</c:if>>维修OK</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="deviceNote" class="layui-form-label">
                备注
            </label>
            <div class="layui-input-inline">
                <textarea name="deviceNote" id="deviceNote" cols="69" rows="9">${device.deviceNote}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
            </label>
            <c:if test="${not empty device.deviceId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty device.deviceId}">
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
            elem: '#devicePurDate' //指定元素
        });

        laydate.render({
            elem: '#deviceUsedPeriod' //指定元素
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
            if (obj.deviceId != '') {
                msg = "更新";
            }
            jQuery.ajax({
                url: "/device/add",
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