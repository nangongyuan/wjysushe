<%--
  Created by IntelliJ IDEA.
  User: ly6333
  Date: 2019/2/20
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Arcsoft文件管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" th:href="@{/css/font.css}">
    <link rel="stylesheet" th:href="@{/css/weadmin.css}">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <!--<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>-->
    <!--<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->

    <style>
        .layui-form-item .layui-input-inline{
            width: 390px;
        }
    </style>
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="L_nickName" class="layui-form-label">
                <span class="we-red">*</span>登录名(域账号)
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_nickName" name="nickName" lay-verify="required" autocomplete="" class="layui-input" fillin="false">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_roleId" class="layui-form-label">
                <span class="we-red">*</span>角色
            </label>
            <div class="layui-input-inline">
                <select name="roleId" id="L_roleId">
                    <option th:each="item : ${sysRoleList}" th:value="${item.id}" th:text="${item.roleName}"></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">确定</button>
        </div>
    </form>
</div>
<script th:src="@{/layui/layui.js}" charset="utf-8"></script>

<script>
    layui.extend({
        admin: '{/}/js/admin'
    });
    layui.config({
        base: '/layui/lay/other_modules/'
    }).extend({
        common:'autocomplete'
    });
    layui.use(['form', 'jquery','util','admin', 'layer','autocomplete'], function() {
        var form = layui.form,
            $ = layui.jquery,
            util = layui.util,
            admin = layui.admin,
            layer = layui.layer;
        layui.autocomplete.render({
            elem: '#L_nickName',
            url: '/fileSysUserInfo/find_ladp_user',
            cache: false,
            template_val: '{{d.nick_name}}',
            template_txt: '<span class=\'layui-badge layui-bg-gray\'>{{d.nick_name}}</span>',
            onselect: function (resp) {

            }
        })

        //监听提交
        form.on('submit(add)', function(data) {
            var content = data.field;
            content['loginName'] = $('#L_nickName').attr('data-patent-first');
            $.ajax({
                type: "POST",
                url: "/fileSysUserInfo/do_add",
                data: content,
                dataType: "json",
                success: function(data){
                    if (data.code==0){
                        layer.alert("添加成功", {
                            icon: 6
                        }, function() {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    }else{
                        layer.alert(data.msg,{icon:5});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>

</html>
