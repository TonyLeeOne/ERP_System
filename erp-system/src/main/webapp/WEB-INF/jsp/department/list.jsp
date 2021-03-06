<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加部门','/department/edit',700,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${departments.size()} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>部门名称</th>
            <th>部门主管</th>
            <th>部门职责</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${department.dId}'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${department.dName}</td>
                <td>${department.dMamager}</td>
                <td>${department.dDuty}</td>
                <td class="td-manage">
                    <a title="编辑" onclick="x_admin_show('编辑','/department/edit?dId=${department.dId}',700,350)"
                       href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,${department.dId})" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    /*删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.get('/department/delete?dId=' + id, function (res) {
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
            $.post('/department/batchDelete', {"dIds": data.toString()}, function (res) {
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