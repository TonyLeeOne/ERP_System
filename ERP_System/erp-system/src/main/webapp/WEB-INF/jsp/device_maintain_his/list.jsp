<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/deviceHis/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加设备','/deviceHis/edit',700,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${deviceHis.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>设备编号</th>
            <th>设备保养日期</th>
            <th>设备保养人员签字</th>
            <th>设备检查结果</th>
            <th>备注</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${deviceHis.total > 0}">
            <c:forEach items="${deviceHis.rows}" var="device">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary"
                             data-name="${device.hisId}" data-id="${device.hisId}">
                            <i class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${device.hisDeviceCode}</td>
                    <td>${device.hisDate}</td>
                    <td>${device.hisOperator}</td>
                    <td>${device.hisResult}</td>
                    <td>${device.hisNote}</td>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑','/deviceHis/edit?hisId=${device.hisId}',700,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" id="delete" href="/deviceHis/delete?hisId=${device.hisId}">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true">
        <jsp:param name="pageurl" value="/deviceHis/getAllDevices/"/>
    </jsp:include>

</div>
</body>

</html>