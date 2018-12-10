<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/role/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加角色信息','/role/edit',1000,600)"><i class="layui-icon"></i>添加
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
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-name="${role.rname}"
                         data-id='${role.rid}'><i
                            class="layui-icon">&#xe605;</i>
                    </div>
                </td>
                <td style="width: 80px">${role.rname}</td>
                <td>
                    <div class="layui-form">
                            <c:forEach items="${role.modules}" var="module" varStatus="status">
                                <input type="checkbox" title="${module.remark}" lay-skin="primary" checked>
                            </c:forEach>
                    </div>
                        <%--<c:forEach items="${role.modules}" var="module" varStatus="status">--%>
                        <%--<input lay-skin="primary" type="checkbox" checked value="${module.mid}"--%>
                        <%--title="${module.remark}">--%>
                        <%--&lt;%&ndash;<c:if test="${status.index != 0}">，</c:if> ${module.remark}&ndash;%&gt;--%>
                        <%--</c:forEach>--%>
                </td>
                    <%--<td>具有至高无上的权利</td>--%>
                    <%--<td class="td-status">--%>
                    <%--<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>--%>
                    <%--</td>--%>
                <td class="td-manage">
                        <%--<a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">--%>
                        <%--<i class="layui-icon">&#xe601;</i>--%>
                        <%--</a>--%>
                    <a title="编辑" onclick="x_admin_show('编辑角色信息','/role/edit?rid=${role.rid}',1000,600)"
                       href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                        <%--<a title="删除" id="delete" href="/role/delete?rid=${role.rid}">--%>
                        <%--<i class="layui-icon">&#xe640;</i>--%>
                        <%--</a>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>

</script>
</body>

</html>