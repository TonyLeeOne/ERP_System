<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div style="padding-left: 30%">
            <ul class="nav navbar-nav ">
                <li><a href="/index"><span class="	glyphicon glyphicon-home"></span> 首页</a></li>
                <li><a href="/question"><span class="glyphicon glyphicon-question-sign"></span></span> 有偿问答</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                       <span class="glyphicon glyphicon-tags"></span> 技术博客 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/index">全部</a></li>
                        <c:if test="${! empty tags}">
                            <c:forEach items="${tags}" var="tag">
                                <li><a href="#" id="tagSearch">${tag}</a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
                <li><a href="#"><span class="glyphicon glyphicon-book"></span> 漫生活</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-th"></span> 程序员必备</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-pencil"></span> 留言板</a></li>
                <li><a href="#"><span class="	glyphicon glyphicon-record"></span> 时光邮件</a></li>
                <c:if test="${empty sessionScope.user}">
                <li><a href="/user" id="login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </c:if>
                <c:if test="${! empty sessionScope.user}">
                <li class="dropdown" id="info">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span> ${sessionScope.user.username} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/blog/add">写博客</a></li>
                        <li><a href="/profile/view">我的资料</a></li>
                        <li><a href="/user/home">我的主页</a></li>
                        <li><a href="/user/logout">退出登录</a></li>
                    </ul>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>