<%--
  Created by IntelliJ IDEA.
  User: ly6333
  Date: 2019/2/20
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Arcsoft文件管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/weadmin.css">
    <script src="/static/layui/layui.js" charset="utf-8"></script>
</head>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="./index.html">Arcsoft文件管理系统</a>
    </div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;" id="nickname">Admin</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <!--<dd>-->
                <!--<a onclick="WeAdminShow('个人信息','http://www.baidu.com')">个人信息</a>-->
                <!--</dd>-->
                <dd>
                    <a onclick="logout()">切换帐号</a>
                </dd>
                <dd>
                    <a class="loginout" onclick="logout()">退出</a>
                </dd>
            </dl>
        </li>
        <!--<li class="layui-nav-item to-index">-->
        <!--<a href="/">前台首页</a>-->
        <!--</li>-->
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe705;</i>
                    <cite>基础管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/project/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>项目管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/product/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>产品管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/catalogInfo/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>大类管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/fileSysUserInfo/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>工具管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/fileVersionsInfo/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>版本控制</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>日志管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/fileLogInfo/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>日志管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>权限管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/fileSysRole/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>角色管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/fileSysMenu/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>菜单管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/fileSysPlatform/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>平台管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
        <ul class="layui-tab-title" id="tabName">
            <li>欢迎</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/page/welcome.jsp' frameborder="0" scrolling="yes" class="weIframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright © 2015 - 2019 arcsoft文件系统</div>
</div>
<!-- 底部结束 -->
<script type="text/javascript">
    layui.config({
        base: '/static/js/'
        ,version: '101100'
    }).use('admin');
    layui.use(['jquery','admin'], function(){
        var nickname = layui.sessionData('userInfo').nickname;
        layui.jquery('#nickname').text(nickname);
    });

    function logout() {
        layui.sessionData('userInfo', null);
        location.href = "/login";
    }
</script>
</body>
<!--Tab菜单右键弹出菜单-->
<ul class="rightMenu" id="rightMenu">
    <li data-type="fresh">刷新</li>
    <li data-type="current">关闭当前</li>
    <li data-type="other">关闭其它</li>
    <li data-type="all">关闭所有</li>
</ul>

</html>