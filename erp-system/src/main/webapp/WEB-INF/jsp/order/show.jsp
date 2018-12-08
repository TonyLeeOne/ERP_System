<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    .layui-form-label {
        width: 100px;
    }

    table {
        background-color: #3add3f;
    }
</style>
<body>
<div class="x-body">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>订单信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【订单号】</td>
            <td>${order.ONo}</td>
            <td>【公司单号】</td>
            <td>${order.OComNo}</td>
            <td>【订单数量】</td>
            <td>${order.OCount}</td>
        </tr>
        <tr>
            <td>【币种】</td>
            <td>${order.OPayCategory}</td>
            <td>【汇率】</td>
            <td>${order.OExchangeRate}</td>
            <td>【成交价】</td>
            <td>${order.OPay}</td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>产品信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【产品编号】</td>
            <td>${order.OProductCode}</td>
            <td>【产品名称】</td>
            <td>${order.product.proName}</td>
            <td>【库存数量】</td>
            <td>${order.product.proCount}</td>
        </tr>
        <tr>
            <td>【产品标价】</td>
            <td colspan="5">${order.product.proPrice}</td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>客户信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【客户】</td>
            <td>${order.OCustomName}</td>
            <td>【联系人】</td>
            <td>${order.OContacts}</td>
            <td>【联系方式】</td>
            <td>${order.OTel}</td>
        </tr>
        <tr>
            <td>【客户地址】</td>
            <td colspan="5">${order.OAddress}</td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>生产计划及生产工单信息</a>
        </legend>
    </fieldset>
    <div class="layui-row">
        <c:if test="${! empty order.plans}">
            <div class="layui-collapse">
                <c:forEach items="${order.plans}" var="plan">
                    <div class="layui-colla-item">
                        <c:if test="${plan.mpStatus=='1'}">
                            <h2 class="layui-colla-title" style="background-color: rgba(42,137,249,0.58)">【生产计划编号】:${plan.mpSn}
                                【产品编号】:${plan.mpProCode}
                                【计划开始日期】:${plan.mpStartDate} 【计划结束日期】:${plan.mpEndDate} 【当前状态】:
                                生产中
                                【待生产数量】:${plan.mpCount}
                            </h2>
                        </c:if>
                        <c:if test="${plan.mpStatus=='2'}">
                            <h2 class="layui-colla-title" style="background-color: rgba(58,221,63,0.7)">【生产计划编号】:${plan.mpSn}
                                【产品编号】:${plan.mpProCode}
                                【计划开始日期】:${plan.mpStartDate} 【计划结束日期】:${plan.mpEndDate} 【当前状态】:
                                已完工
                                【待生产数量】:${plan.mpCount}
                            </h2>
                        </c:if>
                        <div class="layui-colla-content">
                            <table class="layui-table">
                                <thead>
                                <tr>
                                    <th>工单编号</th>
                                    <th>开始日期</th>
                                    <th>结束日期</th>
                                    <th>计划数</th>
                                    <th>生产数</th>
                                    <th>状态</th>
                                    <th>进度</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${! empty plan.manOrders}">
                                    <c:forEach items="${plan.manOrders}" var="mOrder">
                                        <tr>
                                            <td>${mOrder.moSn}</td>
                                            <td>${mOrder.moStartDate}</td>
                                            <td>${mOrder.moEndDate}</td>
                                            <td>${mOrder.moCount}</td>
                                            <td>${mOrder.moWaitCount}</td>
                                            <td>
                                                <%@include file="../common/mo_status.jsp" %>
                                            </td>
                                            <td width="100px">
                                                <c:if test="${! empty mOrder.moWaitCount}">
                                                    <div class="layui-progress">
                                                        <div class="layui-progress-bar layui-bg-orange"
                                                             lay-percent="${mOrder.moWaitCount}/${mOrder.moCount}"></div>
                                                    </div>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

    </div>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>出货信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【出货数量】</td>
            <td>${order.OIndeedCount}</td>
            <td>【出货日期】</td>
            <td>${order.OShipmentDate}</td>
            <td>【出货方式】</td>
            <td>${order.OShipmentMethod}</td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>业务员信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【业务员名称】</td>
            <td>${order.OSalesman}</td>
            <td>【业务员部门】</td>
            <td>${order.OSalesmanDepart}</td>
            <td>【业务员联系方式】</td>
            <td>${order.OSalesmanContact}</td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>其他信息</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <tr>
            <td>【创建人】</td>
            <td>${order.OCreator}</td>
            <td>【审核人】</td>
            <td>${order.OAuditor}</td>
            <td>【修改人】</td>
            <td>${order.OModifier}</td>
        </tr>
        <tr>
            <td>【创建日期】</td>
            <td>${order.OCreateDate}</td>
            <td>【审核日期】</td>
            <td>${order.OAuditDate}</td>
            <td>【订单状态】</td>
            <td>
                <%@include file="../common/order_status.jsp" %>
            </td>
        </tr>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>审核结果</a>
        </legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-col-md-8">
            <textarea name="oNote" class="layui-textarea" readonly="readonly">${order.ONote}</textarea>
        </div>
    </div>
</div>

<c:if test="${order.OStatus=='1'||order.OStatus=='2'}">
    <form class="layui-form">
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a>审核信息填写区</a>
            </legend>
        </fieldset>
        <div class="layui-row">
            <div class="layui-col-md6">
                <label for="oStatus" class="layui-form-label">
                    审核结果
                </label>
                <div class="layui-input-inline" id="oStatus">
                    <input type="radio" value="3" name="oStatus" title="核实订单信息通过">
                    <input type="radio" value="2" name="oStatus" title="核实订单信息不符">
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
                        if (res == "数据更新成功") {
                            layer.alert(res, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                window.parent.location.reload();
                                parent.layer.close(index);

                            });
                        }
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

        });
    })
</script>
</body>

</html>