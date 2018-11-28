<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加角色','/role/edit',700,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${roles.size()} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>角色名</th>
            <th>拥有权限规则</th>
            <%--<th>描述</th>--%>
            <%--<th>状态</th>--%>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${role.rid}'><i
                            class="layui-icon">&#xe605;</i>
                    </div>
                </td>
                <td>${role.rname}</td>
                <td>
                    <c:forEach items="${role.modules}" var="module" varStatus="status">
                        <c:if test="${status.index != 0}">，</c:if> ${module.remark}
                    </c:forEach>
                </td>
                    <%--<td>具有至高无上的权利</td>--%>
                    <%--<td class="td-status">--%>
                    <%--<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>--%>
                    <%--</td>--%>
                <td class="td-manage">
                        <%--<a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">--%>
                        <%--<i class="layui-icon">&#xe601;</i>--%>
                        <%--</a>--%>
                    <a title="编辑" onclick="x_admin_show('编辑','/role/edit?rid=${role.rid}',700,400)" href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,'${role.rid}')" href="javascript:;">
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
            $.get('/role/delete?rid=' + id, function (res) {
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
            $.post('/role/batchDelete', {"rids": data.toString()}, function (res) {
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