<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%-- Create a hash map and store it in session scope --%>
<jsp:useBean id='map2' class='java.util.HashMap' scope='session'>
    <%-- Add initial key/value pairs stored in the hash
    map. The following <c:set> actions are only invoked
    when the hash map is created. --%>
    <c:set target='${map2}' property='1' value='可用'/>
    <c:set target='${map2}' property='2' value='禁用'/>
</jsp:useBean>
<c:out value="${map2[material.mStatus]}"/>
