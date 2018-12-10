<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','/edit',700,550)"><i class="layui-icon"></i>添加
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
            <th>用户名</th>
            <th>部门</th>
            <th>角色</th>
            <th>当前状态</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="user">
                <tr>
                    <td width="5px">
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${user.id}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${user.uname}</td>
                    <td>${user.department.dName}</td>
                    <td>
                        <div class="layui-form">
                            <c:forEach items="${user.roles}" var="role" varStatus="status">
                                <input type="checkbox" checked readonly="readonly" title="${role.rname}">
                                <%--<c:if test="${status.index != 0}">,</c:if> ${role.rname}--%>
                            </c:forEach>
                        </div>
                    </td>
                    <td>
                        <%@include file="../common/user_status.jsp" %>
                    </td>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑用户','/edit?userId=${user.id}',700,430)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
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
</body>

</html>