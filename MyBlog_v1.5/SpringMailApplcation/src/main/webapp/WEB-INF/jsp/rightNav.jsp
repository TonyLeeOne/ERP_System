<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="col-sm-offset-11 col-sm-1"
     style="background: rgba(255,255,255,0);height:100%;position: fixed;padding-top: 15%;margin-right: 5px;width: fit-content">
    <div class="span2" id="sidebar">
        <ul class="nav nav-pills nav-stacked" style="text-align: center;font-size: 22px">
            <li><a id="thumb"><span class="glyphicon glyphicon-thumbs-up" style="color: #00cc66"></span></a></li>
            <li><a href="#" data-toggle="modal" data-target="#myModal"><span
                    class="glyphicon glyphicon-text-background" style="color: #cc5967"></span></a></li>
            <li><a href="#"><span
                    class="glyphicon glyphicon-share-alt" style="color: #a45efa"></span></a></li>
            <li><a href="#" value="${blog.id}" id="plus"><span
                    class="glyphicon glyphicon-plus" style="color: #03f8fa"></span></a></li>
        </ul>
    </div>
</div>
