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
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加供应商','/vendor/edit',700,350)"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${vendors.total} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>供应商名字</th>
            <th>供应商地址</th>
            <th>供应商联系方式</th>
            <th>供应商法人代表</th>
            <th>供应商全名</th>
            <th>供应商状态 1代表正常 2代表终止合作</th>
            <th>备注</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:if test="${vendors.total > 0}">
            <c:forEach items="${vendors.rows}" var="vendor">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${vendor.id}'><i
                                class="layui-icon">&#xe605;</i></div>
                    </td>
                        <%--<td>${vendor.id}</td>--%>
                    <td>${vendor.VName}</td>
                    <td>${vendor.VAddress}</td>
                    <td>${vendor.VTel}</td>
                    <td>${vendor.VPublish}</td>
                    <td>${vendor.VFullname}</td>
                    <td>${vendor.VStatus}</td>
                    <td>${vendor.VNote}</td>
                    <td class="td-manage">
                        <a title="编辑" onclick="x_admin_show('编辑','/user/vendor?vId=${vendor.vId}',700,350)"
                           href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" onclick="member_del(this,${vendor.vId})" href="javascript:;">
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
            $.get('/vendor/delete?vId=' + id, function (res) {
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
            $.post('/vendor/batchDelete', {"vIds": data.toString()}, function (res) {
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