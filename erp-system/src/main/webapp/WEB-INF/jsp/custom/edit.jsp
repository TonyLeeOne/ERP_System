<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <input type="text" hidden name="customId" value="${custom.customId}">
                    <label for="customName" class="layui-form-label">
                        客户名字
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customName" name="customName" value="${custom.customName}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="customAddress" class="layui-form-label">
                        客户地址
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customAddress" name="customAddress" value="${custom.customAddress}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">

                    <label for="customCode" class="layui-form-label">
                        客户编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customCode" name="customCode"
                               value="${custom.customCode}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="customTel" class="layui-form-label">
                        客户电话
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customTel" name="customTel"
                               value="${custom.customTel}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">

                <div class="layui-col-md6">

                    <label for="customPublish" class="layui-form-label">
                        客户联系人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customPublish" name="customPublish"
                               value="${custom.customPublish}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="customFullname" class="layui-form-label">
                        客户全名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="customFullname" name="customFullname"
                               value="${custom.customFullname}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-form-item">
                    <label class="layui-form-label">客户状态</label>
                    <div class="layui-input-block" id="single" value="${custom.customStatus}">
                        <input type="radio" name="customStatus" value="1" title="有效" id="pro">
                        <input type="radio" name="customStatus" value="2" title="无效" id="stop">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <c:if test="${not empty custom.customId}">
                    <button class="layui-btn" lay-filter="edit" lay-submit="">
                        更新
                    </button>
                </c:if>
                <c:if test="${empty custom.customId}">
                    <button class="layui-btn" lay-filter="add" lay-submit="">
                        新增
                    </button>
                </c:if>

            </div>
        </div>
    </form>
</div>
<script>
    $(function () {

        if($("#single").attr('value')) {
            if ($("#single").attr('value') == '1') {
                $("#pro").attr('checked', true);
            }
            if ($("#single").attr('value') == '2') {
                $("#stop").attr('checked', true);
            }
        }else
            $("#pro").attr('checked', true);

        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form,layer = layui.layer;

            //自定义验证规则
            // form.verify({
            //     nikename: function (value) {
            //         if (value.length < 5) {
            //             return '昵称至少得5个字符啊';
            //         }
            //     }
            //     , pass: [/(.+){6,12}$/, '密码必须6到12位']
            //     , repass: function (value) {
            //         if ($('#L_pass').val() != $('#L_repass').val()) {
            //             return '两次密码不一致';
            //         }
            //     }
            // });

            //监听提交
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/custom/add",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
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
                    url: "/custom/update",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
                    async: false,
                    success: function (res) {
                        if (res == "数据更新成功") {
                            //发异步，把数据提交给php
                            layer.alert("更新成功", {icon: 6}, function () {
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