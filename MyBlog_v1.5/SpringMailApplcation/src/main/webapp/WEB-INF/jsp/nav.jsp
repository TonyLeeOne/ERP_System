<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="col-sm-1"
     style="background: rgba(144,197,255,0);height:100%;position: fixed;width: 5%;padding-top: 5%;font-size: 15px">
    <div class="span2">
        <ul class="nav nav-pills nav-stacked">
            <li><a href="#" data-toggle="tooltip" data-placement="right"
                   title="隐藏" id="hide"><span class="glyphicon glyphicon-menu-left"></span></a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="right"
                   title="展开" id="show"><span class="glyphicon glyphicon-menu-right"></span></a></li>
        </ul>
    </div>
    <div class="span2" id="sidebar">
        <ul class="nav nav-pills nav-stacked">
            <shiro:hasPermission name="add">
                <li><a href="/user/logout" data-toggle="tooltip" data-placement="right"
                       title="注销"><span class="glyphicon glyphicon-log-out"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="/profile/view" data-toggle="tooltip" data-placement="right" title="我的资料"><span
                        class="glyphicon glyphicon-user"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="#" data-toggle="tooltip" data-placement="right" title="我的积分"><span
                        class="glyphicon glyphicon-piggy-bank"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="/blog/add" data-toggle="tooltip" data-placement="right"
                       title="写博客"><span class="glyphicon glyphicon-pencil"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="/kafka" data-toggle="tooltip" data-placement="right" title="我的消息"><span
                        class="glyphicon glyphicon-bell"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="/user/home" data-toggle="tooltip" data-placement="right" title="我的博客"><span
                        class="glyphicon glyphicon-th-list"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="add">
                <li><a href="#" data-toggle="tooltip" data-placement="right" title="我的发帖"><span
                        class="glyphicon glyphicon-sunglasses"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="admin">
                <li><a href="/auth/configureUrls" data-toggle="tooltip" data-placement="right" title="权限配置"><span
                        class="glyphicon glyphicon-wrench"></span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="admin">
                <li><a href="/role/management" data-toggle="tooltip" data-placement="right" title="权限角色"><span
                        class="glyphicon glyphicon-cog"></span></a></li>
            </shiro:hasPermission>
            <li><a href="#" data-toggle="tooltip" data-placement="right" title="全屏" onclick="toggleFullScreen()"
                   id="enter"><span
                    class="glyphicon glyphicon-fullscreen"></span></a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="right" title="退出全屏" onclick="toggleFullScreen()"
                   id="exit"><span
                    class="glyphicon glyphicon-resize-small"></span></a></li>
            <li><a href="/index" data-toggle="tooltip" data-placement="right" title="首页"><span
                    class="glyphicon glyphicon-home"></span></a></li>
        </ul>
    </div>
</div>
