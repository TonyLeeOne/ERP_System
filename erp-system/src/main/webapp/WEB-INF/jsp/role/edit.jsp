<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form action="" method="post" class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label for="rname" class="layui-form-label">
                <span class="x-red">*</span>角色名
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="rid" id="rid" value="${role.rid}">
                <input type="text" id="rname" name="rname" required="" value="${role.rname}" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <table class="layui-table layui-input-block">
                <tbody>
                <div class="layui-input-block" id="mids">
                    <label class="layui-form-label">
                        <span class="x-red">*</span>权限分配
                    </label>
                    <c:forEach items="${modules}" var="module" varStatus="status">
                        <%--<c:if test="${fn:contains(module.remark,'模块')}">--%>

                        <c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
                            <tr>
                        </c:if>
                        <td>
                            <input name="mid[]" type="checkbox"
                            <c:forEach items="${roleModules}" var="roleModule">
                            <c:if test="${module.mid == roleModule}"> checked </c:if>
                            </c:forEach>
                                   title="${module.remark}"
                                   value="${module.mid}"
                            >

                        </td>
                        <c:if test="${status.count % 5 eq 0 || status.count eq 5}">
                            </tr>
                        </c:if>
                        <%--<tr>--%>
                        <%--<td>--%>
                        <%--<input name="mid[]" lay-skin="primary" type="checkbox"--%>
                        <%--<c:forEach items="${roleModules}" var="roleModule">--%>
                        <%--<c:if test="${module.mid == roleModule}"> checked </c:if>--%>
                        <%--</c:forEach>--%>
                        <%--title="${module.remark}"--%>
                        <%--value="${module.mid}"--%>
                        <%-->--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</c:if>--%>
                    </c:forEach>
                </div>
                </tbody>
            </table>
        </div>
        <%--<div class="layui-form-item layui-form-text">--%>
        <%--<label for="desc" class="layui-form-label">--%>
        <%--描述--%>
        <%--</label>--%>
        <%--<div class="layui-input-block">--%>
        <%--<textarea placeholder="请输入内容" id="desc" name="desc" class="layui-textarea"></textarea>--%>
        <%--</div>--%>
        <%--</div>--%>
        <c:if test="${not empty role.rid}">
            <button class="layui-btn" lay-filter="add" lay-submit="">
                更新
            </button>
        </c:if>
        <c:if test="${empty role.rid}">
            <button class="layui-btn" lay-filter="add" lay-submit="">
                新增
            </button>
        </c:if>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;
        //监听提交
        form.on('submit(add)', function (data) {
            var obj = data.field, param = new Object();
            var arr = new Array();
            for (key in obj) {
                if (key.substr(0, 4) == 'mid[') arr.push(obj[key]);

            }
            var url = "/role/add?roleName=" + obj['rname'];
            var msg = "增加";
            if (obj.rid != '') {
                url = url + '&rid=' + obj.rid;
                msg = "更新";
            }
            jQuery.ajax({
                url: url,
                type: "POST",
                data: JSON.stringify(arr),
                // dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res == '数据新增成功' || res == '数据更新成功') {
                        //发异步，把数据提交给php
                        layer.alert(msg + "成功", {icon: 6}, function () {
                            // 获得frame索引

                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            window.parent.location.reload();


                        });
                    } else {
                        layer.alert(msg + "失败")
                    }
                }
            });
            return false;
        });
    });
</script>
</body>

</html>