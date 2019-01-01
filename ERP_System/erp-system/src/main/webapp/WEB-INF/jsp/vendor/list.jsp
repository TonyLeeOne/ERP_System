<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <shiro:hasPermission name="vendor:delete">
        <button class="layui-btn layui-btn-danger" id="batch_delete" data-batch-url="/vendor/batchDelete"><i
                class="layui-icon"></i>批量删除
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="vendor:add">
        <button class="layui-btn" onclick="x_admin_show('添加供应商','/vendor/edit',700,500)"><i class="layui-icon"></i>添加
        </button>
    </shiro:hasPermission>
    <span class="x-right" style="line-height:40px">共有数据：${page.total} 条</span>
    <%--</xblock>--%>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>供应商名字</th>
            <th>联系人</th>
            <th>供应商联系方式</th>
            <th>供应商全名</th>
            <th>供应商地址</th>
            <th>供应商状态</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="vendor">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-name="${vendor.VName}"
                             data-id='${vendor.VId}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${vendor.VName}</td>
                    <td>${vendor.VPublish}</td>
                    <td>${vendor.VTel}</td>
                    <td>${vendor.VFullname}</td>
                    <td>${vendor.VAddress}</td>
                    <td>
                        <div class="layui-form">
                            <c:if test="${vendor.VStatus=='1'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="合作|解约" checked>
                            </c:if>
                            <c:if test="${vendor.VStatus =='2'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="合作|解约">
                            </c:if>
                        </div>
                    </td>
                        <%--<td>${vendor.VNote}</td>--%>
                    <td class="td-manage">
                        <shiro:hasPermission name="vendor:update">
                            <a title="编辑" onclick="x_admin_show('编辑','/vendor/edit?vId=${vendor.VId}',700,500)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                            <a title="删除" id="delete" href="/vendor/delete?vId=${vendor.VId}">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp" flush="true">
        <jsp:param name="pageurl" value="/vendor/getAllVendors/"/>
    </jsp:include>

</div>
</body>

</html>