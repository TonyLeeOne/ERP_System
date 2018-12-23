<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-col-md6">
                <input type="hidden" name="pid" value="${profile.pid}">
                <label for="pName" class="layui-form-label">
                    <span class="x-red">*</span>用户名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pName" name="pName" required="" lay-verify="required" readonly
                           autocomplete="off" value="${user.uname}" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md6">
                <label class="layui-form-label">
                    <span class="x-red">*</span>联系电话
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pTel" name="pTel" required="" lay-verify="required|phone"
                           autocomplete="off" value="${profile.pTel}" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                学历
            </label>
            <div class="layui-input-inline">
                <input type="text" id="pEdu" name="pEdu" required=""
                       autocomplete="off" value="${profile.pEdu}" class="layui-input">
            </div>

            <label class="layui-form-label">
                专业
            </label>
            <div class="layui-input-inline">
                <input type="text" id="pMajor" name="pMajor" required=""
                       autocomplete="off" value="${profile.pMajor}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                入职日期
            </label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="入职日期" name="pIndate" id="pIndate"
                       value="${profile.pIndate}" >
            </div>
            <label class="layui-form-label">
                身份证号码
            </label>
            <div class="layui-input-inline">
                <input type="text" id="pId" name="pId" required=""
                       autocomplete="off" value="${profile.pId}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>性别
            </label>
            <div class="layui-input-inline" val="${profile.pSex}" id="pSex">
                <input type="radio" name="pSex"  id="man"　
                       value="男"><i class="layui-icon">&#xe662;</i>　　
                <input type="radio" name="pSex" id="woman"
                       value="女"><i class="layui-icon">&#xe661;</i>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <c:if test="${not empty profile.pid}">
                <button class="layui-btn" lay-filter="edit" lay-submit="">
                    更新
                </button>
            </c:if>
            <c:if test="${empty profile.pid}">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    新增
                </button>
            </c:if>
        </div>
    </form>
</div>
<script>

    $(function () {
        if($("#pSex").attr("val")){
            if($("#pSex").attr("val")=='男'){
                $("#man").attr('checked',true);
            }else
                $("#woman").attr('checked',true);
        }else
            $("#man").attr('checked',true);
    });

    layui.use(['form', 'layer', 'laydate'], function () {
        // $ = layui.jquery;
        var laydate = layui.laydate;
        var form = layui.form
            , layer = layui.layer;

        laydate.render({
            elem: '#pIndate' //指定元素
        });


        //监听提交
        form.on('submit(add)', function (data) {
            jQuery.ajax({
                url: '/addProfile',
                type: "POST",
                data: data.field,
                success: function (res) {
                    if (res == '数据新增成功') {
                        //发异步，把数据提交给php
                        layer.alert(res, {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            // window.parent.location.reload();
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert(res,{icon:2})
                    }
                }
            });
            return false;
        });

        //监听提交
        form.on('submit(edit)', function (data) {
            jQuery.ajax({
                url: '/upProfile',
                type: "POST",
                data: data.field,
                success: function (res) {
                    if (res == '数据更新成功') {
                        //发异步，把数据提交给php
                        layer.alert(res, {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            // window.parent.location.reload();
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert(res,{icon:2})
                    }
                }
            });
            return false;
        });

    });
</script>
</body>

</html>