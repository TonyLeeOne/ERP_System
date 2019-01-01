<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="get" action="/pO/getAll/1">
            <input type="text" name="mphDate" id="mphDate" value="${po.mphDate}"
                   placeholder="请选择入库日期"
                   autocomplete="off" class="layui-input">
            <input type="text" name="mphSn" value="${po.mphSn}" placeholder="请输入物料号" autocomplete="off"
                   class="layui-input">
            <div class="layui-input-inline">
                <select name="mphStatus" id="mphStatus">
                    <option value="">请选择状态</option>
                    <option value="1" <c:if test="${po.mphStatus=='1'}"> selected</c:if>>待入库</option>
                    <option value="2"<c:if test="${po.mphStatus=='2'}"> selected</c:if>>已入库</option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除采购记录</button>
        <button class="layui-btn" onclick="x_admin_show('添加物料采购信息','/materialPurchase/edit',730,500)"><i
                class="layui-icon"></i>添加采购订单
        </button>
    </xblock>
    <div class="layui-collapse" lay-accordion>
        <c:if test="${! empty pos.rows}">
            <c:forEach items="${pos.rows}" var="po" varStatus="status">
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">
                        <div class="layui-col-md10">
                            【创建日期】:${po.poCdate} 【订单号】:${po.poOno} 【订单数量】: ${po.poCount}
                            【BOM编号】:${po.poBcode} 【当前状态】:
                            <%@include file="../common/po_status.jsp" %>
                            <c:if test="${! empty po.poVerifier}">
                                【审核人】: ${po.poVerifier}
                                【审核日期】: ${po.poDate}
                            </c:if>
                            　 　
                        </div>
                        <div class="layui-col-md2">
                            <c:if test="${po.poStatus=='1'||po.poStatus=='2'}">
                                <a title="新增物料采购清单"
                                   onclick="x_admin_show('新增物料采购清单','/materialPurchase/edit/${po.poBcode}/${po.poId}',800,280)">
                                    <i class="layui-icon" style="color: #bd10b6;font-size: 25px">&#xe654;</i>
                                </a>　
                            </c:if>
                            <c:if test="${po.poStatus=='1'||po.poStatus=='2'}">
                                <a title="删除" id="delete" href="/pO/delete?poId=${po.poId}"><i class="layui-icon"
                                                                                               style="color: #dd122c;font-size: 25px">&#xe640;</i></a>　
                            </c:if>
                            <c:if test="${po.poStatus=='1'||po.poStatus=='2'}">
                                <a title="去库存"
                                   onclick="member_storage(this,'${po.poId}')">
                                    <i class="layui-icon" style="color: #06bd39;font-size: 25px">&#xe698;</i>
                                </a>　
                            </c:if>
                            <c:if test="${po.poStatus=='1'||po.poStatus=='2'}">
                                <a title="审核采购订单"
                                   onclick="x_admin_show('审核采购申请','/pO/verify?poId=${po.poId}',530,200)">
                                    <i class="layui-icon" style="color: #090abd;font-size: 20px">&#xe672;</i>
                                </a>
                            </c:if>
                        </div>
                    </h2>
                    <div class="layui-colla-content <c:if test="${status.count eq 1}">layui-show</c:if>">
                        <table class="layui-table">
                            <thead>
                            <tr>
                                <th>
                                    <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                                            class="layui-icon">&#xe605;</i></div>
                                </th>
                                <th>物料名</th>
                                <th>物料编号</th>
                                <th>采购单价</th>
                                <th>采购数量</th>
                                <th>供应商</th>
                                <th>入库日期</th>
                                <th>确认人</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${! empty po.purchases}">
                                <c:forEach items="${po.purchases}" var="purchase">
                                    <tr>
                                        <td>
                                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary"
                                                 data-id='${purchase.mphId}'>
                                                <i class="layui-icon">&#xe605;</i></div>
                                        </td>
                                        <td>${purchase.mphName}</td>
                                        <td>${purchase.mphSn}</td>
                                        <td>${purchase.mphPrice}</td>
                                        <td>${purchase.mphCount}</td>
                                        <td>${purchase.vendor.VName}</td>
                                        <td>${purchase.mphDate}</td>
                                        <td>${purchase.mphOperator}</td>
                                        <td>
                                            <%@include file="../common/purchase_status.jsp" %>
                                        </td>
                                        <td class="td-manage">
                                            <c:if test="${po.poStatus=='1'||po.poStatus=='2'}">
                                                <a title="编辑采购信息"
                                                   onclick="x_admin_show('编辑采购信息','/materialPurchase/edit/${po.poBcode}/${po.poId}?mphId=${purchase.mphId}',730,280)"
                                                   href="javascript:;">
                                                    <i class="layui-icon">&#xe642;</i>
                                                </a>
                                            </c:if>
                                                <%--<c:if test="${purchase.mphStatus=='1'||purchase.mphStatus=='2'}">--%>
                                                <%--<a title="确认入库"--%>
                                                <%--onclick="x_admin_show('审核采购申请','/materialPurchase/verify?mphId=${purchase.mphId}',530,200)">--%>
                                                <%--<i class="layui-icon">&#xe672;</i>--%>
                                                <%--</a>--%>
                                                <%--</c:if>--%>
                                            <c:if test="${po.poStatus=='3'}">
                                                <a title="确认入库" onclick="member_confirm(this,'${purchase.mphId}')"
                                                   href="javascript:;"
                                                   id="confirm">
                                                    <i class="layui-icon">&#x1005;</i>
                                                </a>
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
        </c:if>
    </div>

    <jsp:include page="../common/pagination.jsp">
        <jsp:param name="pageurl" value="/pO/getAll/"/>
        <jsp:param name="query" value="<%= request.getQueryString() %>"/>
    </jsp:include>
</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#mphDate' //指定元素
        });
    });

    function delAll(argument) {
        var data = tableCheck.getData();
        if (data.length > 0) {
            layer.confirm('确认要删除选定的' + data.length + '记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/materialPurchase/delete', {"mpid": data.toString()}, function (res) {
                    if (res == '数据删除成功') {
                        layer.msg(res, {icon: 6});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    }
                    else
                        layer.msg(res, {icon: 1});
                });

            });
        }
        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }


    function member_confirm(obj, id) {
        layer.confirm('确认采购数量与入库数量一致<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe607;</i>', function (index) {
            var purchase = new Object();
            purchase.mphId = id;
            purchase.mphStatus = '2';
            //捉到所有被选中的，发异步进行删除
            $.post('/materialPurchase/confirm', purchase, function (res) {
                if (res == "数据更新成功")
                    layer.alert(res, {icon: 6}, function () {
                        window.location.reload();
                    });
                else
                    layer.alert(res, {icon: 1});
            });
            return false;
        });
    }

    function member_storage(obj, id) {
        layer.confirm('确认去库存<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe607;</i>', function (index) {
            //捉到所有被选中的，发异步进行删除
            $.post('/pO/calculate', {poId: id}, function (res) {
                if (res == "数据更新成功")
                    layer.alert(res, {icon: 6}, function () {
                        window.location.reload();
                    });
                else
                    layer.alert(res, {icon: 1});
            });
            return false;
        });
    }

</script>
</body>

</html>