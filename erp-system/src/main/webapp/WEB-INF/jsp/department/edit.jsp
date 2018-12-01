<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="dId" value="${department.dId}">
            <label for="dName" class="layui-form-label">
                <span class="x-red">*</span>部门名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="dName" name="dName" required="" lay-verify="required"
                       autocomplete="off" value="${department.dName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>部门主管
            </label>
            <div class="layui-input-inline">
                <input type="text" id="dMamager" name="dMamager" required="" lay-verify="required"
                       autocomplete="off" value="${department.dMamager}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>部门职责
            </label>
            <div class="layui-input-inline">
                <input type="text" id="dDuty" name="dDuty" required="" lay-verify="required"
                       autocomplete="off" value="${department.dDuty}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <c:if test="${not empty department.dId}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty department.dId}">
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
            var obj = data.field;
            var msg = "新增";
            console.log(obj);
            var url = "/department/add";
            if (obj.dId != '') {
                msg = "更新";
                url = url + "?rid=" + obj.dId;
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