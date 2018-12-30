<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<%@include file="../common/breadcrumb.jsp" %>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md4 x-so">
            <input type="text" name="mSn" value="${material.mSn}" placeholder="请输入物料号" autocomplete="off"
                   class="layui-input">
            <input type="text" name="mName" value="${material.mName}" placeholder="请输入物料名" autocomplete="off"
                   class="layui-input">
            <div class="layui-input-inline">
                <select name="mStatus" id="mStatus">
                    <option value="">请选择物料状态</option>
                    <option value="1" <c:if test="${material.mStatus=='1'}"> selected</c:if>>充足</option>
                    <option value="2" <c:if test="${material.mStatus=='2'}"> selected</c:if>>短缺</option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <shiro:hasPermission name="material:delete">
            <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="material:add">
            <button class="layui-btn" onclick="x_admin_show('添加物料信息','/material/edit',730,500)"><i
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
            <th>物料名</th>
            <th>物料编号</th>
            <th>库存数量</th>
            <th>单位</th>
            <th>单价</th>
            <th>状态</th>
            <%--<th>锁定</th>--%>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${page.total > 0}">
            <c:forEach items="${page.rows}" var="material">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${material.mId}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${material.mName}</td>
                    <td>${material.mSn}</td>
                    <td>${material.mCount}</td>
                    <td>${material.mUnit}</td>
                    <td>${material.mPrice}</td>
                    <td>
                        <div class="layui-form">
                            <c:if test="${material.mStatus=='1'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="可用|禁用" checked>
                            </c:if>
                            <c:if test="${material.mStatus=='2'}">
                                <input type="checkbox" name="yyy" lay-skin="switch" lay-text="可用|禁用">
                            </c:if>
                        </div>
                            <%--<%@include file="../common/material_status.jsp" %>--%>
                    </td>
                        <%--<td>${material.mLocked}</td>--%>
                    <td>${material.mNote}</td>
                    <td class="td-manage">
                        <a title="编辑物料信息"
                           onclick="x_admin_show('编辑物料信息','/material/edit?mSn=${material.mSn}',730,500)"
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
        <jsp:param name="pageurl" value="/material/getAll/"/>
        <jsp:param name="query" value="<%= request.getQueryString() %>"/>
    </jsp:include>
</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

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

    function delAll(argument) {

        var data = tableCheck.getData();

        if (data.length > 0) {
            layer.confirm('确认要删除选定的' + data.length + '记录吗？', function (index) {
                //捉到所有被选中的，发异步进行删除
                $.post('/material/delete', {"mId": data.toString()}, function (res) {
                    layer.msg(res, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                });

            });
        }
        else
            layer.alert("请至少选择一行记录", {icon: 2});
    }

</script>
</body>

</html>