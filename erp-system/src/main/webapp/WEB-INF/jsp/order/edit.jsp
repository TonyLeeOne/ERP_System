<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<style>
    .layui-form-label{
        width: 100px;
    }
    span{
        color:red;
    }
</style>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
<<<<<<< HEAD
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>订单信息</a>
                </legend>
            </fieldset>
=======
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
            <div class="layui-row">
                <div class="layui-col-md6">
                    <input type="text" hidden name="oId" value="${order.OId}">
                    <label for="oNo" class="layui-form-label">
<<<<<<< HEAD
                        订单号<span>*</span>
=======
                        订单号
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oNo" name="oNo" value="${order.ONo}" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
<<<<<<< HEAD
                    <label for="oComNo" class="layui-form-label">
                        公司单号<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oComNo" name="oComNo" value="${order.OComNo}" autocomplete="off"
                               class="layui-input" lay-verify="required">
=======

                    <label for="oComNo" class="layui-form-label">
                        公司单号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oComNo" name="oComNo" value="${order.OComNo}" autocomplete="off"
                               class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oProductCode" class="layui-form-label">
<<<<<<< HEAD
                        产品编号<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                            <select name="oProductCode" lay-verify="" lay-search id="oProductCode">
                            </select>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oCount" class="layui-form-label">
                        订单数量<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oCount" name="oCount"
                               value="${order.OCount}" autocomplete="off" class="layui-input" lay-verify="number|required">
=======
                        产品编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oProductCode" name="oProductCode"
                               value="${order.OProductCode}" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="oCount" class="layui-form-label">
                        订单数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oCount" name="oCount"
                               value="${order.OCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oIndeedCount" class="layui-form-label">
                        实际出货数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oIndeedCount" name="oIndeedCount"
                               value="${order.OIndeedCount}" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="oCustomName" class="layui-form-label">
                        客户名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oCustomName" name="oCustomName"
                               value="${order.OCustomName}" autocomplete="off" class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
<<<<<<< HEAD

                    <label for="oExchangeRate" class="layui-form-label">
                        汇率
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oExchangeRate" name="oExchangeRate"
                               value="${order.OExchangeRate}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oShipmentMethod" class="layui-form-label">
                        交货方式
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oShipmentMethod" name="oShipmentMethod"
                               value="${order.OShipmentMethod}" autocomplete="off" class="layui-input">
=======
                    <label for="oPay" class="layui-form-label">
                        结算单价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oPay" name="oPay"
                               value="${order.OPay}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oPayCategory" class="layui-form-label">
                        币种
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oPayCategory" name="oPayCategory"
                               value="${order.OPayCategory}" autocomplete="off" class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
<<<<<<< HEAD
                    <label for="oPay" class="layui-form-label">
                        结算单价<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oPay" name="oPay"
                               value="${order.OPay}" autocomplete="off" class="layui-input" lay-verify="number|required">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oPayCategory" class="layui-form-label">
                        币种
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oPayCategory" name="oPayCategory"
                               value="${order.OPayCategory}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>客户信息</a>
                </legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oCustomName" class="layui-form-label">
                        客户<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oCustomName" name="oCustomName"
                               value="${order.OCustomName}" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="oContacts" class="layui-form-label">
                        联系人<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oContacts" name="oContacts"
                               value="${order.OContacts}" autocomplete="off" class="layui-input" lay-verify="required">
=======

                    <label for="oExchangeRate" class="layui-form-label">
                        汇率
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oExchangeRate" name="oExchangeRate"
                               value="${order.OExchangeRate}" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-col-md6">

                        <label for="oShipmentMethod" class="layui-form-label">
                            交货方式
                        </label>
                        <div class="layui-input-inline">
                            <input type="text" id="oShipmentMethod" name="oShipmentMethod"
                                   value="${order.OShipmentMethod}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="oContacts" class="layui-form-label">
                        联系人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oContacts" name="oContacts"
                               value="${order.OContacts}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="oTel" class="layui-form-label">
                        联系人电话
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oTel" name="oTel"
                               value="${order.OTel}" autocomplete="off" class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

<<<<<<< HEAD
                    <label for="oTel" class="layui-form-label">
                        联系方式<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oTel" name="oTel"
                               value="${order.OTel}" autocomplete="off" class="layui-input" lay-verify="required|phone">
=======
                    <label for="oAddress" class="layui-form-label">
                        客户地址
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oAddress" name="oAddress"
                               value="${order.OAddress}" autocomplete="off" class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>

                <div class="layui-col-md6">

<<<<<<< HEAD
                    <label for="oAddress" class="layui-form-label">
                        客户地址
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oAddress" name="oAddress"
                               value="${order.OAddress}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>业务员信息</a>
                </legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="oSalesman" class="layui-form-label">
                        业务员<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesman" name="oSalesman"
                               value="${order.OSalesman}" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oSalesmanContact" class="layui-form-label">
                        联系方式<span >*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesmanContact" name="oSalesmanContact"
                               value="${order.OSalesmanContact}" autocomplete="off" class="layui-input" lay-verify="required|phone">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="oSalesmanDepart" class="layui-form-label">
                        业务员部门
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesmanDepart" name="oSalesmanDepart"
                               value="${order.OSalesmanDepart}" autocomplete="off" class="layui-input">
=======
                    <label for="oSalesman" class="layui-form-label">
                        业务员名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesman" name="oSalesman"
                               value="${order.OSalesman}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="oSalesmanDepart" class="layui-form-label">
                        业务员部门
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesmanDepart" name="oSalesmanDepart"
                               value="${order.OSalesmanDepart}" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-col-md6">

                    <label for="oSalesmanContact" class="layui-form-label">
                        业务员联系方式
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesmanContact" name="oSalesmanContact"
                               value="${order.OSalesmanContact}" autocomplete="off" class="layui-input">
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <c:if test="${not empty order.OId}">
                    <button class="layui-btn" lay-filter="edit" lay-submit="">
                        更新
                    </button>
                </c:if>
                <c:if test="${empty order.OId}">
                    <button class="layui-btn" lay-filter="add" lay-submit="">
                        新增
                    </button>
                </c:if>

            </div>
        </div>
    </form>
</div>
<script>
    //重新渲染表单
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }
    $(function () {
        $.ajax({
            url:"/product/populateProCodes",
            method:"get",
            success:function (data) {
                if(data){
                    $.each(data,function (index,pro) {
                        $("#oProductCode").append("<option value='"+pro.substring(pro.indexOf("(")+1,pro.indexOf(")"))+"'>"+pro+"</option>");
                    });
                }
                renderForm();
            },
            error:function () {
                alert("获取数据失败");
            }
        });
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
                    url: "/order/add",
                    data: data.field,
                    type: "POST",
                    // dataType: "json",
                    // contentType: "application/json",
                    async: false,
                    success: function (res) {
                            //发异步，把数据提交给php
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
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
                    url: "/order/update",
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