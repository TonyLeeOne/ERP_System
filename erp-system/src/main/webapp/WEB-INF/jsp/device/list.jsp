<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/device/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加设备','/device/edit',700,500)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${page.total} 条</span>
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
                    <td>${device.deviceVendor}</td>
                    <td>${device.deviceVendorTel}</td>
                    <td>${device.deviceUsedPeriod}</td>
                    <td>
                        <%@ include file="../common/device_status.jsp" %>
                    </td>
                        <%--<td>${device.deviceNote}</td>--%>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑','/device/edit?deviceId=${device.deviceId}',700,500)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" id="delete" href="/device/delete?deviceId=${device.deviceId}">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true"><jsp:param name="pageurl" value="/device/getAllDevices/"/></jsp:include>
</div>
</body>

</html>