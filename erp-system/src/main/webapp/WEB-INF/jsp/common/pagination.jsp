<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<div class="page">
    <div>
        <a class="prev" href="" title="上一页">&lt;&lt;</a>
        <%--<a class="num" href="">1</a>--%>
        <%--<span class="current">2</span>--%>
        <%--<a class="num" href="">3</a>--%>
        <%--<a class="num" href="">489</a>--%>
        <a class="next" href="" title="下一页">&gt;&gt;</a>
        <span>共 <%=request.getParameter("total")%> 条</span>
    </div>
</div>