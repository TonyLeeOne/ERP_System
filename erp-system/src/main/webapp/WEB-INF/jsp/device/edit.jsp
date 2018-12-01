<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="deviceId" value="${device.DeviceId}">
            <label for="vName" class="layui-form-label">
                <span class="x-red">*</span>设备名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vName" name="vName" required="" lay-verify="required"
                       value="${device.VName}" autocomplete="off" value="admin" class="layui-input">
            </div>
            <label for="vAddress" class="layui-form-label">
                <span class="x-red">*</span>采购日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vAddress" name="vAddress" required="" lay-verify="required"
                       value="${device.VAddress}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="vTel" class="layui-form-label">
                <span class="x-red">*</span>设备单价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vTel" name="vTel" required="" lay-verify="required"
                       value="${device.VTel}" autocomplete="off" class="layui-input">
            </div>
            <label for="vPublish" class="layui-form-label">
                <span class="x-red">*</span>设备编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vPublish" name="vPublish" required="" lay-verify="required"
                       value="${device.VPublish}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="vFullname" class="layui-form-label">
                <span class="x-red">*</span>设备供应商
            </label>
            <div class="layui-input-inline">
                <input type="text" id="vFullname" name="vFullname" required="" lay-verify="required"
                       value="${device.VFullname}" autocomplete="off" class="layui-input">
            </div>
            <label for="vStatus" class="layui-form-label">
                <span class="x-red">*</span>供应商电话
            </label>
            <div class="layui-input-inline">
                <select name="vStatus" id="vStatus" required="" lay-verify="required">
                    <option value="1" <c:if test="${device.VStatus == 1}"> selected</c:if>>正常</option>
                    <option value="2" <c:if test="${device.VStatus == 2}"> selected</c:if>>终止合作</option>
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
            <c:if test="${not empty device.VId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty device.VId}">
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

        //自定义验证规则
        form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var obj = data.field, param = new Object(), arr = new Array();
            for (key in obj) {
                if (key.substr(0, 4) == 'rid[') {
                    arr.push(obj[key]);
                } else {
                    param[key] = obj[key];
                }
            }
            param['rids'] = arr.join(',');
            jQuery.ajax({
                url: "/device/add",
                type: "POST",
                data: JSON.stringify(param),
                // dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res == '数据新增成功' || res == '数据更新成功') {
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
                }
            });
            return false;
        });

    });
</script>
</body>

</html>