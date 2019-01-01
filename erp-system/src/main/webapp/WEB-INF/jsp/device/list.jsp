<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="get" action="/device/getAllDevices/1">
            <input type="text" name="devicePurDate" id="devicePurDate" value="${device.devicePurDate}"
                   placeholder="设备采购日期"
                   autocomplete="off" class="layui-input">
            <input type="text" name="deviceName" value="${device.deviceName}" placeholder="设备名称" autocomplete="off"
                   class="layui-input">
            <input type="text" name="deviceCode" value="${device.deviceCode}" placeholder="设备编号" autocomplete="off"
                   class="layui-input">
            <div class="layui-input-inline">
                <select name="deviceStatus" id="deviceStatus">
                    <option value="">设备状态</option>
                    <option value="1" <c:if test="${device.deviceStatus=='1'}"> selected</c:if>>良好</option>
                    <option value="2"<c:if test="${device.deviceStatus=='2'}"> selected</c:if>>待维修</option>
                    <option value="3"<c:if test="${device.deviceStatus=='3'}"> selected</c:if>>已维修</option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <shiro:hasPermission name="device:delete">
            <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/device/batchDelete"><i
                    class="layui-icon"></i>批量删除
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="device:delete">
            <button class="layui-btn" onclick="x_admin_show('添加设备','/device/edit',700,500)"><i class="layui-icon"></i>添加
            </button>
        </shiro:hasPermission>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>设备名称</th>
            <th>设备采购日期</th>
            <th>设备单价</th>
            <th>设备编号</th>
            <th>设备供应商</th>
            <th>供应商联系电话</th>
            <th>设备使用截止日期</th>
            <th>设备状态</th>
            <%--<th>备注</th>--%>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="device">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary"
                             data-name="${device.deviceName}" data-id="${device.deviceId}">
                            <i class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${device.deviceName}</td>
                    <td>${device.devicePurDate}</td>
                    <td>${device.devicePrice}</td>
                    <td>${device.deviceCode}</td>
                    <td>${device.vendor.VName}</td>
                    <td>${device.vendor.VTel}</td>
                    <td>${device.deviceUsedPeriod}</td>
                    <td>
                        <%@ include file="../common/device_status.jsp" %>
                    </td>
                        <%--<td>${device.deviceNote}</td>--%>
                    <td class="td-manage">
                        <shiro:hasPermission name="device:edit">
                            <a title="编辑" class="layui-btn layui-btn layui-btn-xs"
                               onclick="x_admin_show('编辑','/device/edit?deviceId=${device.deviceId}',700,500)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe642;</i>编辑
                            </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="custom:delete">
                            <a title="删除" class="layui-btn-danger layui-btn layui-btn-xs" id="delete" href="/device/delete?deviceId=${device.deviceId}">
                                <i class="layui-icon">&#xe640;</i>删除
                            </a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true">
        <jsp:param name="pageurl" value="/device/getAllDevices/"/>
        <jsp:param name="query" value="<%= request.getQueryString() %>"/>
    </jsp:include>
</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#devicePurDate' //指定元素
        });

    });
</script>
</body>

</html>