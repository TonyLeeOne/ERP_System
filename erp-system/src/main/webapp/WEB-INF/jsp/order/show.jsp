<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    .layui-form-label {
        width: 100px;
    }
</style>
<body>
<div class="x-body">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>订单信息</a>
        </legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-row">
            <label for="oNo" class="layui-form-label">
                订单号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oNo" name="oNo" value="${order.ONo}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oComNo" class="layui-form-label">
                公司单号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oComNo" name="oComNo" value="${order.OComNo}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oCount" class="layui-form-label">
                订单数量
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCount" name="oCount" value="${order.OCount}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-row">
            <label for="oPayCategory" class="layui-form-label">
                币种
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oPayCategory" name="oCount" value="${order.OPayCategory}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oExchangeRate" class="layui-form-label">
                汇率
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oExchangeRate" name="oCount" value="${order.OExchangeRate}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oPay" class="layui-form-label">
                成交价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oPay" name="oPay" value="${order.OPay}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>产品信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <label for="oProductCode" class="layui-form-label">
                产品编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oProductCode" name="oProductCode" value="${order.OProductCode}"
                       readonly="readonly"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oProductCode" class="layui-form-label">
                产品名称
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${order.product.proName}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oProductCode" class="layui-form-label">
                库存数量
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${order.product.proCount}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-row">
            <label for="oProductCode" class="layui-form-label">
                产品标价
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${order.product.proPrice}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>客户信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <label for="oCustomName" class="layui-form-label">
                客户
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCustomName" name="oCustomName" value="${order.OCustomName}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oContacts" class="layui-form-label">
                联系人
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oContacts" name="oContacts" value="${order.OContacts}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oTel" class="layui-form-label">
                联系方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oTel" value="${order.OTel}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-row">

            <label for="oAddress" class="layui-form-label">
                客户地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oAddress" name="oAddress" value="${order.OAddress}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>生产计划及生产工单信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <c:if test="${empty order.plans}">
                　　　暂无相关信息
            </c:if>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>出货信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <label for="oIndeedCount" class="layui-form-label">
                出货数量
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oIndeedCount" name="oSalesman" value="${order.OIndeedCount}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
            <label for="oShipmentDate" class="layui-form-label">
                出货日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oShipmentDate" name="oSalesman" value="${order.OShipmentDate}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
            <label for="oShipmentMethod" class="layui-form-label">
                出货方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oShipmentMethod" name="oSalesman" value="${order.OShipmentMethod}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>业务员信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <label for="oSalesman" class="layui-form-label">
                业务员名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesman" name="oSalesman" value="${order.OSalesman}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oSalesmanDepart" class="layui-form-label">
                业务员部门
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesmanDepart" name="oSalesmanDepart" value="${order.OSalesmanDepart}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>


            <label for="oSalesmanContact" class="layui-form-label">
                业务员联系方式
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oSalesmanContact" name="oSalesmanContact" value="${order.OSalesmanContact}"
                       readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>其他信息</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <label for="oCreator" class="layui-form-label">
                创建人
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCreator" value="${order.OCreator}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oAuditor" class="layui-form-label">
                审核人
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oAuditor" value="${order.OAuditor}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label for="oModifier" class="layui-form-label">
                修改人
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oModifier" value="${order.OModifier}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-row">
            <label for="oCreateDate" class="layui-form-label">
                创建日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oCreateDate" value="${order.OCreateDate}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>
            <label for="oAuditDate" class="layui-form-label">
                审核日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="oAuditDate" value="${order.OAuditDate}" readonly="readonly"
                       autocomplete="off" class="layui-input">
            </div>

            <label class="layui-form-label">
                订单状态
            </label>
            <div class="layui-input-inline">
                <input type="text" value="<%@include file="../common/order_status.jsp" %>"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>审核结果</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <p>${order.ONote}</p>
        </div>
    </div>

    <c:if test="${order.OStatus=='1'||order.OStatus=='2'||order.OStatus=='5'}">
        <form class="layui-form">
            <fieldset class="layui-elem-field layui-field-title site-title">
                <legend>
                    <a>审核信息填写区</a>
                </legend>
            </fieldset>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label for="oStatus" class="layui-form-label">
                        产品编号
                    </label>
                    <div class="layui-input-inline">
                        <select name="oStatus" lay-verify="" lay-search id="oStatus">
                            <option value="3">审核通过</option>
                            <option value="2">审核不通过</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label class="layui-form-label">
                    </label>
                    <div class="layui-input-block">
                        <textarea name="oNote" placeholder="备注" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <label class="layui-form-label">
                </label>
                <input type="hidden" value="${order.OId}" name="oId"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    审核
                </button>
            </div>
        </form>
    </c:if>
</div>
<script>
    $(function () {
        layui.use(['form', 'layer'], function () {
            // $ = layui.jquery;
            var form = layui.form
                , layer = layui.layer;
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/order/confirm",
                    data: data.field,
                    type: "POST",
                    async: false,
                    success: function (res) {
                        console.log(res);
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

        });
    })
</script>
</body>

</html>