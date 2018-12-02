<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page">
    <c:if test="${page.currentPage >1}">
        <a class="prev" href="${param.pageurl}${ page.currentPage -1}">&lt;&lt;</a>
    </c:if>
    <c:forEach begin="1" end="${page.pageNum}" varStatus="sortNum">
        <c:if test="${sortNum.index == page.currentPage}">
            <span class="current">${sortNum.index}</span>
        </c:if>
        <c:if test="${sortNum.index != page.currentPage}">
            <a class="num" href="${param.pageurl}${sortNum.index}">${sortNum.index}</a>
        </c:if>
    </c:forEach>

    <c:if test="${page.currentPage < page.pageNum}">
        <a class="next" href="${param.pageurl}${ page.currentPage +1}">&gt;&gt;</a>
    </c:if>
    <span>共 ${page.pageNum} 页</span>
</div>