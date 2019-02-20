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
<body class="login-bg">

<div class="login">
    <div class="message">Arcsoft文件管理系统</div>
    <div id="darkbannerwrap"></div>

    <form method="get" class="layui-form">
        <input name="loginName" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="pwd" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input class="loginin" value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script type="text/javascript">

    layui.extend({
        admin: '{/}./static/js/admin'
    });
    layui.use(['form','admin'], function(){
        var form = layui.form
            ,admin = layui.admin;
        form.on('submit(login)', function(data){
            layui.jquery.ajax({
                type: "get",
                url: "/fileSysUserInfo/login",
                data: data.field,
                dataType: "json",
                success: function(data){
                    console.log(data);
                    if (data.code==0) {
                        layui.sessionData('userInfo', {
                            key: 'nickname'
                            ,value: data.data
                        });
                        location.href = "/index";
                    }else{
                        layui.layer.alert(data.msg,{icon:5});
                    }
                }
            });
            console.log(data);
            return false;
        });
    });
</script>
<!-- 底部结束 -->
</body>
</html>
