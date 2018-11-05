<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>index.jsp</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%@include file="nav.jsp" %>
<div class="row container">
    <div class="jumbotron col-md-offset-1">
        <span>${msg}</span>
        <p><a class="btn btn-success btn-mid" href="/sendEmail" role="button">再来一个</a></p>
    </div>
</div>
<%@include file="commonForJs.jsp"%>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
</body>
</html>