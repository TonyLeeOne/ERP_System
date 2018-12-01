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
    <%--<xblock>--%>
        <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
        <%--<button class="layui-btn" onclick="x_admin_show('添加权限','/module/edit',700,350)"><i class="layui-icon"></i>添加--%>
        <%--</button>--%>
        <%--<span class="x-right" style="line-height:40px">共有数据：88 条</span>--%>
    <%--</xblock>--%>
    <table class="layui-table">
        <thead>
        <tr>
            <%--<th>--%>
                <%--<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i--%>
                        <%--class="layui-icon">&#xe605;</i></div>--%>
            <%--</th>--%>
            <th>权限名称</th>
            <%--<th>拥有权限规则</th>--%>
            <%--<th>描述</th>--%>
            <%--<th>状态</th>--%>
            <%--<th>操作</th>--%>
        </thead>
        <tbody>
        <c:forEach items="${modules}" var="module">
            <tr>
                <%--<td>--%>
                    <%--<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i--%>
                            <%--class="layui-icon">&#xe605;</i>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <td>${module.mname}</td>
                <%--<td>这里显示权限列表</td>--%>
                    <%--<td>具有至高无上的权利</td>--%>
                    <%--<td class="td-status">--%>
                    <%--<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span>--%>
                    <%--</td>--%>
                <%--<td class="td-manage">--%>
                        <%--<a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">--%>
                        <%--<i class="layui-icon">&#xe601;</i>--%>
                        <%--</a>--%>
                    <%--<a title="编辑" onclick="x_admin_show('编辑','/role/edit?customId=${module.mid}',700,350)" href="javascript:;">--%>
                        <%--<i class="layui-icon">&#xe642;</i>--%>
                    <%--</a>--%>
                    <%--<a title="删除" onclick="member_del(this,'${module.mid}')" href="javascript:;">--%>
                        <%--<i class="layui-icon">&#xe640;</i>--%>
                    <%--</a>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>

</html>