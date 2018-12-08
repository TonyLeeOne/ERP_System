<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <%--<div class="layui-row">--%>
    <%--<form class="layui-form layui-col-md12 x-so">--%>
    <%--<input class="layui-input" placeholder="开始日" name="start" id="start">--%>
    <%--<input class="layui-input" placeholder="截止日" name="end" id="end">--%>
    <%--<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">--%>
    <%--<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','/edit',700,750)"><i class="layui-icon"></i>添加
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
            <%--<th>ID</th>--%>
            <th>用户名</th>
            <th>部门</th>
            <th>角色</th>
            <%--<th>密码</th>--%>
            <%--<th>状态</th>--%>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="user">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${user.id}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                        <%--<td>${user.id}</td>--%>
                    <td>${user.uname}</td>
                    <td>${user.department.dName}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role" varStatus="status">
                            <c:if test="${status.index != 0}">,</c:if> ${role.rname}
                        </c:forEach>
                    </td>
                        <%--<td>${user.upass}</td>--%>
                        <%--<td>${user.status}</td>--%>
                        <%--<td class="td-status">--%>
                        <%--<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>--%>
                        <%--</td>--%>
                    <td class="td-manage">
                            <%--<a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">--%>
                            <%--<i class="layui-icon">&#xe601;</i>--%>
                            <%--</a>--%>
                        <a title="编辑" onclick="x_admin_show('编辑','/edit?userId=${user.id}',700,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" id="delete" href="/delete?uid=${user.id}"><i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true">
        <jsp:param name="pageurl" value="/user/getAllUsers/"/>
    </jsp:include>

</div>
<script>
    // layui.use('laydate', function () {
    //     var laydate = layui.laydate;
    //
    //     //执行一个laydate实例
    //     laydate.render({
    //         elem: '#start' //指定元素
    //     });
    //
    //     //执行一个laydate实例
    //     laydate.render({
    //         elem: '#end' //指定元素
    //     });
    // });

    /*用户-停用*/
    // function member_stop(obj, id) {
    //     layer.confirm('确认要停用吗？', function (index) {
    //
    //         if ($(obj).attr('title') == '启用') {
    //
    //             //发异步把用户状态进行更改
    //             $(obj).attr('title', '停用')
    //             $(obj).find('i').html('&#xe62f;');
    //
    //             $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
    //             layer.msg('已停用!', {icon: 5, time: 1000});
    //
    //         } else {
    //             $(obj).attr('title', '启用')
    //             $(obj).find('i').html('&#xe601;');
    //
    //             $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
    //             layer.msg('已启用!', {icon: 5, time: 1000});
    //         }
    //
    //     });
    // }
</script>
</body>

</html>