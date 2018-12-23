<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    .layui-col-md3 {
        margin: 2px;
    }
</style>
<body>
<%--<%@include file="../common/breadcrumb.jsp" %>--%>
<div class="x-body">
    <%--<div class="layui-row">--%>
    <%--<form class="layui-form layui-col-md12 x-so">--%>
    <%--<input type="text" name="oNo" placeholder="请输入工单号" autocomplete="off" class="layui-input">--%>
    <%--<input type="text" name="oNo" placeholder="请输入物料号" autocomplete="off" class="layui-input">--%>
    <%--<input type="text" name="oNo" placeholder="请输入生产计划单号" autocomplete="off" class="layui-input">--%>
    <%--<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--<xblock>--%>
    <%--<shiro:hasPermission name="material:delete">--%>
    <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
    <%--</shiro:hasPermission>--%>
    <shiro:hasPermission name="material:add">
        <button class="layui-btn" onclick="x_admin_show('新增BOM','/bom/edit',700,280)"><i
                class="layui-icon"></i>新增
        </button>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </shiro:hasPermission>
    <span class="x-right" style="line-height:40px">共有数据: ${boms.size()} 条</span>
    <%--</xblock>--%>

    <div class="layui-col-md-offset2">
        <c:if test="${! empty boms}">
            <c:forEach items="${boms}" var="bom">
                <div class="layui-col-md3">
                    <div class="layui-card"
                         style="background-color: rgba(121,93,163,0.31);border-radius: 5%;height: 400px">
                        <div class="layui-card-header">
                            <i class="layui-icon">&#xe857;</i> 产品: <b style="color: #090abd">${bom.product.proName}</b>
                        </div>
                        <div class="layui-card-header">
                            <i class="layui-icon">&#xe62b;</i>OM编号:<B style="color: #bd552d"> ${bom.BCode}　</B>
                        </div>
                        <div class="layui-card-header">
                            <i class="layui-icon">&#xe62b;</i>OM名:<b> ${bom.BName}　</b>
                        </div>
                        <div class="layui-card-header">
                            <i class="layui-icon">&#xe65b;操作:</i>
                            　<a title="编辑BOM" onclick="x_admin_show('修改BOM单','/bom/edit?bId=${bom.BId}',700,280)"><i
                                class="layui-icon">&#xe642;</i></a>　
                            <a title="删除BOM" id="delete" href="/bom/delete?bId=${bom.BId}"><i class="layui-icon">&#xe640;</i></a>　
                            <a title="编辑BOM清单"
                                <%--onclick="x_admin_show('编辑BOM清单','/detail/getAll?bCode=${bom.BCode}',800,580)"--%>
                               href="/detail/getAll?bCode=${bom.BCode}"><i class="layui-icon"
                                                                           style="color: #bd2c00">&#xe654;</i>
                            </a>
                        </div>
                        <div class="layui-card-body">
                            <div class="layui-form">
                                <i class="layui-icon">&#xe62a; </i>清单详情:

                                <ul>
                                    <c:if test="${! empty bom.details}">
                                        <c:forEach items="${bom.details}" var="detail">
                                            <div class="layui-col-md6">
                                                <li>
                                                    <i class="layui-icon"
                                                       style="color: #127F74">&#xe643;</i>　${detail.material.mName}
                                                    * ${detail.bdNum}/${detail.material.mUnit}
                                                </li>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                        <%--<li><a title="编辑BOM清单"--%>
                                        <%--&lt;%&ndash;onclick="x_admin_show('编辑BOM清单','/detail/getAll?bCode=${bom.BCode}',800,580)"&ndash;%&gt;--%>
                                        <%--href="/detail/getAll?bCode=${bom.BCode}"><i class="layui-icon"--%>
                                        <%--style="color: #bd2c00">&#xe654;编辑清单</i>--%>
                                        <%--</a></li>--%>
                                </ul>

                            </div>
                        </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>

</html>