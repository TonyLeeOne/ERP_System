<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/header.jsp" %>
<style>
    .layui-col-md2 {
        margin: 2px;
    }
</style>
<body>
<%--<%@include file="../common/breadcrumb.jsp" %>--%>
<div class="x-body">
    <shiro:hasPermission name="material:add">
        <button class="layui-btn" onclick="x_admin_show('新增清单信息','/detail/edit/${bCode}',700,280)"><i
                class="layui-icon"></i>新增清单信息
        </button>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
           href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="line-height:30px">ဂ</i></a>
        <button class="layui-btn" onclick="history.back(1)">
            <i class="layui-icon">&#xe65a;</i>返回上一页
        </button>
    </shiro:hasPermission>
    <span class="x-right" style="line-height:40px">共有数据: ${details.size()} 条</span>
    <%--</xblock>--%>

    <div class="layui-col-md-offset1" style="margin-top: 3%">
        <c:if test="${! empty details}">
            <c:forEach items="${details}" var="detail">
                <div class="layui-col-md2">
                    <div class="layui-card" style="background-color: rgba(42,137,249,0.31);border-radius: 5%;">
                        <div class="layui-card-header">
                                <%--<c:if test="${fn:length(detail.BdCode)>'4'}">--%>
                                <%--<i class="layui-icon">&#xe656;</i> BOM编号: ${fn:substring(detail.BdCode,0 ,4)}...　--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${fn:length(detail.BdCode)<='4'}">--%>
                                    <c:if test="${fn:length(detail.bdBcode)>'4'}">
                                        <i class="layui-icon">&#xe62b;</i>OM编号: ${fn:substring(detail.bdBcode,0 ,4)}...　
                                    </c:if>
                                    <c:if test="${fn:length(detail.bdBcode)<='4'}">
                                        <i class="layui-icon">&#xe62b;</i>OM编号: ${detail.bdBcode}　
                                    </c:if>
                                <%--</c:if>--%>
                                <%--<c:if test="${fn:length(detail.material.mName)>'6'}">--%>
                                <%--<i class="layui-icon">&#xe857;</i>产品:${fn:substring(bom.product.proName,0 ,6)}...--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${fn:length(detail.product.proName)<='6'}">--%>
                                <%--<i class="layui-icon">&#xe857;</i>产品:${detail.material.mName}--%>
                                <%--</c:if>--%>
                            　<a title="编辑清单" onclick="x_admin_show('修改BOM清单信息','/detail/edit/${bCode}?bdId=${detail.bdId}',700,280)"><i
                                class="layui-icon">&#xe642;</i></a>
                            <a title="删除清单" id="delete" href="/detail/delete?bdId=${detail.bdId}"><i class="layui-icon">&#xe640;</i></a>
                        </div>
                        <div class="layui-card-body">
                            <ul>
                                <li><i class="layui-icon">&#xe602;</i>　物料名: ${detail.material.mName} </li>
                                <li><i class="layui-icon">&#xe602;</i>　物料编号: ${detail.material.mSn}</li>
                                <li><i class="layui-icon">&#xe602;</i>　数量: ${detail.bdNum}</li>
                                <li><i class="layui-icon">&#xe602;</i>　单位: ${detail.material.mUnit}</li>
                                <li><i class="layui-icon">&#xe602;</i>　损耗率: ${detail.bdRate}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>

</html>