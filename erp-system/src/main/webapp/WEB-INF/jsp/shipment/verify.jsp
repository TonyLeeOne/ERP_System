<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    img {
        height: 50px;
        width: 50px;
    }
</style>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <label class="layui-form-label">审核结果</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sStatus" value="2" title="订单与出货清单不一致，拒绝出货">
                        <input type="radio" name="sStatus" value="3" title="订单与出货清单一致，可安排出货">
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md6">
                    <c:if test="${!empty ship.SId}">
                        <div class="layui-input-inline">
                            <input type="hidden" value="${ship.SId}" name="sId">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-col-md12" style="margin-top: 2%">
                <label class="layui-form-label">
                </label>
                <button class="layui-btn layui-btn-normal" lay-filter="edit" lay-submit="">
                    提交
                </button>
            </div>
        </div>
    </form>
</div>
<script>
   $(function () {
       layui.use(['form', 'layer'], function () {
           // $ = layui.jquery;
           var form = layui.form, layer = layui.layer;

           form.on('submit(edit)', function (data) {
               $.ajax({
                   url: "/ship/audit",
                   data: data.field,
                   type: "POST",
                   async: false,
                   success: function (res) {
                       if (res == "数据更新成功")
                           layer.alert(res, {icon: 6}, function () {
                               // 获得frame索引
                               var index = parent.layer.getFrameIndex(window.name);
                               //关闭当前frame
                               window.parent.location.reload();
                               parent.layer.close(index);

                           });
                       else
                           layer.alert(res, {icon: 6});
                       return false;
                   },
                   error: function (res) {
                       console.log("error:");
                       console.log(res);
                   }
               });
               return false;
           });
       })
   })
</script>
</body>
</html>