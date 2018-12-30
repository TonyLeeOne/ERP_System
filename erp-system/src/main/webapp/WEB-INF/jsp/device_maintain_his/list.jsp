<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">

    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" method="get" action="/deviceHis/getAllDeviceHis/1">
            <input type="text" name="hisDate" id="hisDate" value="${deviceHis.hisDate}"
                   placeholder="设备保养日期"
                   autocomplete="off" class="layui-input">
            <input type="text" name="hisDeviceCode" value="${deviceHis.hisDeviceCode}" placeholder="设备编号"
                   autocomplete="off"
                   class="layui-input">
            <div class="layui-input-inline">
                <select name="hisResult" id="hisResult">
                    <option value="">设备状态</option>
                    <option value="1" <c:if test="${deviceHis.hisResult=='1'}"> selected</c:if>>良好</option>
                    <option value="2"<c:if test="${deviceHis.hisResult=='2'}"> selected</c:if>>故障</option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/deviceHis/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加设备保养记录','/deviceHis/edit',700,350)"><i
                class="layui-icon"></i>添加
        </button>
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
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="device">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary"
                             data-name="${device.hisId}" data-id="${device.hisId}">
                            <i class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${device.hisDeviceCode}</td>
                    <td>${device.hisDate}</td>
                    <td>${device.hisOperator}</td>
                    <td>
                        <c:if test="${device.hisResult == 1}">
                            良好
                        </c:if>
                        <c:if test="${device.hisResult == 2}">
                            故障
                        </c:if>
                    </td>
                    <td>${device.hisNote}</td>
                    <td class="td-manage">
                            <%--<shiro:hasPermission name="">--%>
                        <a title="编辑" onclick="x_admin_show('编辑','/deviceHis/edit?hisId=${device.hisId}',700,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                            <%--</shiro:hasPermission>--%>
                            <%--<shiro:hasPermission name="">--%>
                        <a title="删除" id="delete" href="/deviceHis/delete?hisId=${device.hisId}">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                            <%--</shiro:hasPermission>--%>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true">
        <jsp:param name="pageurl" value="/deviceHis/getAllDevices/"/>
        <jsp:param name="query" value="<%= request.getQueryString() %>"/>
    </jsp:include>

</div>
<script>

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#hisDate' //指定元素
        });

    });
</script>
</body>

</html>