<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<style>
    .layui-form-label {
        width: 100px;
    }

    span {
        color: red;
    }
</style>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>订单信息</a>
                </legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <input type="text" hidden name="oId" value="${order.OId}">
                    <label for="oNo" class="layui-form-label">
                        订单号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oNo" name="oNo" value="${order.ONo}" autocomplete="off"
                        <c:if test="${! empty order.ONo}">readonly</c:if>
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oComNo" class="layui-form-label">
                        公司单号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oComNo" name="oComNo" value="${order.OComNo}" autocomplete="off"
                               class="layui-input" lay-verify="required">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oProductCode" class="layui-form-label">
                        产品编号<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="oProductCode" lay-verify="" val="${order.OProductCode}" lay-search
                                id="oProductCode" lay-verify="required">
                        </select>
                    </div>
                </div>

                <div class="layui-col-md6">
                    <label for="oCount" class="layui-form-label">
                        订单数量<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="number" id="oCount" name="oCount"
                               value="${order.OCount}" autocomplete="off" class="layui-input"
                               lay-verify="number|required">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oPay" class="layui-form-label">
                        结算单价<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oPay" name="oPay"
                               value="${order.OPay}" autocomplete="off" class="layui-input"
                               lay-verify="number|required">
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
                <div class="layui-col-md6">
                    <label for="oExchangeRate" class="layui-form-label">
                        汇率
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oExchangeRate" name="oExchangeRate"
                               value="${order.OExchangeRate}" autocomplete="off" class="layui-input">
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
                        客户<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <select name="oCustomName" lay-verify="" lay-search id="oCustomName" lay-filter="orders"
                                val="${order.OCustomName}" lay-verify="required">
                            <option>请选择</option>
                        </select>

                        <%--<input type="text" id="oCustomName" name="oCustomName"--%>
                        <%--value="${order.OCustomName}" autocomplete="off" class="layui-input" lay-verify="required">--%>
                    </div>
                </div>
                <div class="layui-col-md6">

                    <label for="oContacts" class="layui-form-label">
                        联系人<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oContacts" name="oContacts"
                               value="${order.OContacts}" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">

                    <label for="oTel" class="layui-form-label">
                        联系方式<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oTel" name="oTel"
                               value="${order.OTel}" autocomplete="off" class="layui-input" lay-verify="required|phone">

                    </div>
                </div>

                <div class="layui-col-md6">

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
                        业务员<span>*</span>
                    </label>


                    <div class="layui-input-inline">
                        <select name="oSalesman" lay-verify="" lay-search id="oSalesman" lay-filter="sales"
                                val="${order.OSalesman}" lay-verify="required">
                            <option>请选择</option>
                        </select>
                        <%--<input type="text" id="oSalesman" name="oSalesman"--%>
                        <%--value="${order.OSalesman}" autocomplete="off" class="layui-input" lay-verify="required">--%>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <label for="oSalesmanContact" class="layui-form-label">
                        联系方式<span>*</span>
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="oSalesmanContact" name="oSalesmanContact"
                               value="${order.OSalesmanContact}" autocomplete="off" class="layui-input"
                               lay-verify="required|phone">
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
    function renderForm() {
        layui.use('form', function () {
            var form = layui.form;
            form.render();
        });
    }

    $(function () {

        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form, layer = layui.layer;

            $.ajax({
                url: "/product/populateProCodes",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#oProductCode").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#oProductCode").attr('val') == pro.substring(pro.indexOf("(") + 1, pro.indexOf(")"))) {
                                    $("#oProductCode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#oProductCode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#oProductCode").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                    }
                    renderForm();
                },
                error: function () {
                    layer.alert("获取数据失败");
                }
            });


            $.ajax({
                url: "/custom/getAllCusNames",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#oCustomName").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#oCustomName").attr('val') == pro.substring(0, pro.indexOf("("))) {
                                    $("#oCustomName").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#oCustomName").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#oCustomName").append("<option value='" + pro.substring(pro.indexOf("(") + 1, pro.indexOf(")")) + "'>" + pro + "</option>");
                            });
                    }
                    renderForm();
                },
                error: function () {
                    layer.alert("获取数据失败", {icon: 2});
                }
            });

            form.on('select(orders)', function (data) {
                $.ajax({
                    url: "/custom/getCustomByCode",
                    method: "post",
                    data: {customCode: data.elem[data.elem.selectedIndex].value},
                    dataType: 'json',
                    success: function (custom) {
                        if (custom) {
                            $("#oContacts").val(custom.customPublish);
                            $("#oTel").val(custom.customTel);
                            $("#oAddress").val(custom.customAddress);
                        }

                    },
                    error: function () {
                        layer.alert("你选择的客户信息可能不存在");
                    }
                });

            });

            $.ajax({
                url: "/getAllUnames",
                method: "get",
                success: function (data) {
                    if (data) {
                        if ($("#oSalesman").attr('val')) {
                            $.each(data, function (index, pro) {
                                if ($("#oSalesman").attr('val') == pro) {
                                    $("#oSalesman").append("<option value='" + pro + "' selected='selected'>" + pro + "</option>");
                                } else
                                    $("#oSalesman").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                        } else
                            $.each(data, function (index, pro) {
                                $("#oSalesman").append("<option value='" + pro + "'>" + pro + "</option>");
                            });
                    }
                    renderForm();
                },
                error: function () {
                    layer.alert("获取数据失败");
                }
            });


            form.on('select(sales)', function (data) {
                $.ajax({
                    url: "/getProfile",
                    method: "post",
                    data: {uname: data.elem[data.elem.selectedIndex].value},
                    dataType: 'json',
                    success: function (profile) {
                        if (profile) {
                            $("#oSalesmanContact").val(profile.pTel);
                        }

                    },
                    error: function () {
                        layer.alert("你选择的业务员信息可能不存在");
                    }
                });

            });


            layui.use(['form', 'layer'], function () {
                // $ = layui.jquery;
                var form = layui.form
                    , layer = layui.layer;
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
                            if (res == "数据新增成功")
                            //发异步，把数据提交给php
                                layer.alert(res, {icon: 6}, function () {
                                    // 获得frame索引
                                    var index = parent.layer.getFrameIndex(window.name);
                                    //关闭当前frame
                                    window.parent.location.reload();
                                    parent.layer.close(index);

                                });
                            else
                                layer.alert(res, {icon: 2});
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
                                layer.alert("更新失败",{icon:2});
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
    })
</script>
</body>

</html>