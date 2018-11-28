<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
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
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${device.DeviceId}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                        <%--<td>${vendor.id}</td>--%>
                    <td>${device.DeviceName}</td>
                    <td>${device.DevicePurDate}</td>
                    <td>${device.VTel}</td>
                    <td>${device.VPublish}</td>
                    <td>${device.VFullname}</td>
                    <td>${device.VStatus}</td>
                    <td>${device.VNote}</td>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑','/user/device?vId=${device.DeviceId}',700,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" onclick="member_del(this,${device.DeviceId})" href="javascript:;">
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
<script>
    /*删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.get('/device/delete?DeviceId=' + id, function (res) {
                //发异步删除数据
                if (res == '数据删除成功') {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                } else {
                    layer.msg(res + '!', {icon: 0, time: 2000});
                }

            });
        });
    }


    function delAll(argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？' + data, function (index) {
            $.post('/device/batchDelete', {"DeviceId": data.toString()}, function (res) {
                //捉到所有被选中的，发异步进行删除
                if (res == '数据删除成功') {
                    // $(".layui-form-checked").not('.header').parents('tr').remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                } else {
                    layer.msg(res + '!', {icon: 0, time: 2000});
                }

            });
        });
    }
</script>
</body>

</html>