<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md8 x-so" method="get"
              action="/custom/getAllCustoms/1">
            <input type="text" name="customName" value="${custom.customName}" placeholder="请输入客户名"
                   autocomplete="off" class="layui-input">
            <div class="layui-input-inline">
                <select name="customStatus" id="customStatus">
                    <option value="">请选择客户状态</option>
                    <option value="1" <c:if test="${custom.customStatus=='1'}"> selected</c:if>>有效</option>
                    <option value="2"<c:if test="${custom.customStatus=='2'}"> selected</c:if>>无效</option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <xblock>
        <shiro:hasPermission name="custom:delete">
            <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="custom:add">
            <button class="layui-btn" onclick="x_admin_show('添加客户','/custom/edit',700,350)"><i
                    class="layui-icon"></i>添加
            </button>
        </shiro:hasPermission>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>客户编号</th>
            <th>客户名</th>
            <th>客户电话</th>
            <th>客户地址</th>
            <th>客户联系人</th>
            <th>客户全名</th>
            <th>客户状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="custom">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${custom.customId}'>
                            <i
                                    class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${custom.customCode}</td>
                    <td>${custom.customName}</td>
                    <td>${custom.customTel}</td>
                    <td>${custom.customAddress}</td>
                    <td>${custom.customPublish}</td>
                    <td>${custom.customFullname}</td>
                    <td>
                        <div class="layui-form">
                            <c:if test="${custom.customStatus=='1'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="有效|无效" checked>
                            </c:if>
                            <c:if test="${custom.customStatus=='2'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="有效|无效">
                            </c:if>
                        </div>
                    </td>
                    <td class="td-manage">
                        <shiro:hasPermission name="custom:update">
                            <a title="编辑"
                               onclick="x_admin_show('编辑','/custom/edit?customId=${custom.customCode}',700,350)"
                               href="javascript:;">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>

    <jsp:include page="../common/pagination.jsp">
        <jsp:param name="pageurl" value="/custom/getAllCustoms/"/>
        <jsp:param name="query" value="<%= request.getQueryString() %>"/>
    </jsp:include>
</div>
<script>
    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.post('/custom/delete', {"customId": id}, function (res) {
                if (res == '数据删除成功') {
                    // $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    window.location.reload();
                }
            });
            return false;

        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        if (data.length > 0)
            layer.confirm('确认要删除续订的' + data.length + "记录吗？", function (index) {
                $.post('/custom/delete', {"customIds": data.toString()}, function (res) {
                    layer.msg(res, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                });
                //捉到所有被选中的，发异步进行删除

            });
        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }
</script>
</body>

</html>