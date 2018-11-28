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
            <a>领料单信息展示区</a>
        </legend>
    </fieldset>
    <table class="layui-table">
        <c:if test="${! empty consume}">
            <tr>
                <td>【待生产产品名】</td>
                <td>${consume.product.proName}</td>
                <td>【产品代码】</td>
                <td>${consume.product.proCode}</td>
            </tr>
            <tr>
                <td>【工单计划数量】</td>
                <td>${consume.manOrder.moCount}</td>
                <td>【工单开始日期】</td>
                <td>${consume.manOrder.moStartDate}</td>
            </tr>
            <tr>
                <td>【物料代码】</td>
                <td>${consume.mcMSn}</td>
                <td>【物料名】</td>
                <td>${consume.material.mName}</td>
            </tr>
            <tr>
                <td>【物料申请数量】</td>
                <td>${consume.mcCountNeeded}</td>
                <td>【库存数量】</td>
                <td>${consume.material.mCount}</td>
            </tr>
        </c:if>
    </table>
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>
            <a>领料单信息审核区</a>
        </legend>
    </fieldset>
    <form class="layui-form">
        <div class="layui-row">
            <div class="layui-col-md6 layui-col-md-offset3">
                <label class="layui-form-label">审核结果</label>
                <div class="layui-input-block">
                    <input type="radio" name="mcStatus" value="2" title="领料单信息有误，拒绝领料">
                    <input type="radio" name="mcStatus" value="3" title="可以领料">
                    <input type="hidden" name="mcId" value="${consume.mcId}" name="mcId">
                    <button class="layui-btn" lay-filter="add" lay-submit="" >
                        审核
                    </button>
                </div>
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
            form.on('submit(add)', function (data) {
                $.ajax({
                    url: "/materialConsume/verify",
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