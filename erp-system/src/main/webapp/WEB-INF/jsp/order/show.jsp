<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp"%>
<style>
    .layui-form-label {
        width: 100px;
    }
</style>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="oNo" class="layui-form-label">
                订单号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oNo" name="oNo"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oComNo" class="layui-form-label">
                公司单号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oComNo" name="oComNo"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oProductCode" class="layui-form-label">
                产品编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oProductCode" name="oProductCode"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oCount" class="layui-form-label">
                订单数量
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCount" name="oCount"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oIndeedCount" class="layui-form-label">
                实际出货数量
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oIndeedCount" name="oIndeedCount"
                       autocomplete="off" class="layui-input">
            </div>
            <label for="oCustomName" class="layui-form-label">
                客户名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCustomName" name="oCustomName"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oPay" class="layui-form-label">
                结算单价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oPay" name="oPay"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oPayCategory" class="layui-form-label">
                币种
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oPayCategory" name="oPayCategory"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oExchangeRate" class="layui-form-label">
                汇率
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oExchangeRate" name="oExchangeRate"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oShipmentMethod" class="layui-form-label">
                交货方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oShipmentMethod" name="oShipmentMethod"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oContacts" class="layui-form-label">
                联系人
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oContacts" name="oContacts"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oTel" class="layui-form-label">
                联系人电话
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oTel" name="oTel"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oAddress" class="layui-form-label">
                客户地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oAddress" name="oAddress"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oSalesman" class="layui-form-label">
                业务员名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesman" name="oSalesman"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oSalesmanDepart" class="layui-form-label">
                业务员部门
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesmanDepart" name="oSalesmanDepart"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oSalesmanContact" class="layui-form-label">
                业务员联系方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesmanContact" name="oSalesmanContact"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    增加
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    $(function () {
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form
                , layer = layui.layer;

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
                    url: "/order/add?test=test",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
                    async: false,
                    success: function (res) {
                        console.log(res);
                        //发异步，把数据提交给php
                        layer.alert("增加成功", {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            window.parent.location.reload();
                            parent.layer.close(index);

                        });
                        return false;

                        // layer.msg(res, {time: 2000});
                        // setTimeout(function () {
                        //     if (res.toString() == "登陆成功") {
                        //         window.location.href = "/index";
                        //     }
                        // }, 3000)
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