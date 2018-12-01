<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/device/batchDelete"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加设备','/device/edit',700,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${devices.total} 条</span>
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
            <th>设备状态，1代表良好，2代表待维修，3代表维修OK</th>
            <th>备注</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${devices.total > 0}">
            <c:forEach items="${devices.rows}" var="device">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${device.deviceId}'>
                            <i
                                    class="layui-icon">&#xe605;</i></div>
                    </td>
                        <%--<td>${vendor.id}</td>--%>
                    <td>${device.deviceName}</td>
                    <td>${device.devicePurDate}</td>
                        <%--<td>${device.device_price}</td>--%>
                        <%--<td>${device.device_code}</td>--%>
                        <%--<td>${device.device_vendor}</td>--%>
                        <%--<td>${device.Tel}</td>--%>
                        <%--<td>${device.VPublish}</td>--%>
                        <%--<td>${device.VFullname}</td>--%>
                        <%--<td>${device.VStatus}</td>--%>
                        <%--<td>${device.VNote}</td>--%>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑','/user/device?deviceId=${device.deviceId}',700,350)"
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
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <a class="num" href="">1</a>
            <span class="current">2</span>
            <a class="num" href="">3</a>
            <a class="num" href="">489</a>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
</body>

</html>