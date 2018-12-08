<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>基本信息</a>
                </legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-md10">

                    <input type="hidden" name="id" value="${user.id}">
                    <label for="uname" class="layui-form-label">
                        <span class="x-red">*</span>用户名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="uname" name="uname" required="" lay-verify="required"
                               autocomplete="off" value="${user.uname}" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        将会成为您唯一的登入名
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label class="layui-form-label">
                        <span class="x-red">*</span>部门
                    </label>
                    <div class="layui-input-inline">
                        <select name="departId" id="departId" required="" lay-verify="required">
                            <option value="">请选择</option>
                            <c:forEach items="${departments}" var="department">
                                <option
                                        <c:if test="${user.departId == department.dId}">selected </c:if>
                                        value="${department.dId}">${department.dName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label class="layui-form-label"><span class="x-red">*</span>角色</label>
                    <div class="layui-input-block">
                        <c:forEach items="${roles}" var="role">
                            <input type="checkbox" value="${role.rid}" name="rid[]" lay-skin="primary"
                                   title="${role.rname}"
                                    <c:forEach items="${user.roles}" var="urole"><c:if
                                            test="${role.rid == urole.rid}"> checked </c:if></c:forEach> />
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-col-md6">
                <fieldset class="layui-elem-field layui-field-title site-title">
                    <legend>
                        <a>密码设置</a>
                    </legend>
                </fieldset>
                <c:if test="${not empty user.id}">
                    <div class="layui-row">
                        <label for="upassOld" class="layui-form-label">
                            原始密码
                        </label>
                        <div class="layui-input-inline">
                            <input type="password" id="upassOld" name="upassOld" required=""<%-- lay-verify="pass"--%>
                                   value="" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </c:if>

            </div>
            <div class="layui-row">

                <div class="layui-col-md6">
                    <label for="upass" class="layui-form-label">
                        <c:if test="${empty user.id}"><span class="x-red">*</span></c:if>密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="upass" name="upass" required=""<%-- lay-verify="pass"--%>
                               value="" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                        6到16个字符
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="upass_re" class="layui-form-label">
                        <c:if test="${empty user.id}"><span class="x-red">*</span></c:if>确认密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="upass_re" name="upass_re" required="" lay-verify="repass"
                               value="" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="upass_re" class="layui-form-label">
            </label>
            <c:if test="${! empty user.id}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty user.id}">
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
            var msg = "新增";
            if (obj.id != '') {
                msg = "更新";
            }
            jQuery.ajax({
                url: "/user/add",
                type: "POST",
                data: JSON.stringify(param),
                // dataType: "json",
                contentType: "application/json; charset=utf-8",
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