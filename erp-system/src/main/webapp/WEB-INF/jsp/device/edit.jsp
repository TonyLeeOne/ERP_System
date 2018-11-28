<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="uname" class="layui-form-label">
                <span class="x-red">*</span>设备名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="uname" name="uname" required="" lay-verify="required"
                       autocomplete="off" value="admin" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为您唯一的登入名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="departId" class="layui-form-label">
                <span class="x-red">*</span>部门
            </label>
            <div class="layui-input-inline">
                <input type="text" value="18925139194" id="departId" name="departId" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为您唯一的登入名
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span class="x-red">*</span>角色</label>
            <div class="layui-input-block">
                <input type="checkbox" value="1" name="rid[]" lay-skin="primary" title="超级管理员" checked="">
                <input type="checkbox" value="2" name="rid[]" lay-skin="primary" title="编辑人员">
                <input type="checkbox" value="3" name="rid[]" lay-skin="primary" title="宣传人员">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="upass" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="upass" name="upass" required=""<%-- lay-verify="pass"--%>
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <%--<div class="layui-form-item">--%>
        <%--<label for="upass_re" class="layui-form-label">--%>
        <%--<span class="x-red">*</span>确认密码--%>
        <%--</label>--%>
        <%--<div class="layui-input-inline">--%>
        <%--<input type="password" id="upass_re" name="upass_re" required="" lay-verify="repass"--%>
        <%--autocomplete="off" class="layui-input">--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">
                增加
            </button>
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
                }
            });
            return false;
        });

    });
</script>
</body>

</html>