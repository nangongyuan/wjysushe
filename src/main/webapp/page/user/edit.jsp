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
            <label for="L_loginName" class="layui-form-label">
                <span class="we-red">*</span>登录名
            </label>
            <div class="layui-input-inline">
                <input type="text" disabled id="L_loginName" name="loginName" lay-verify="required" autocomplete="" class="layui-input" th:value="${fileSysUserInfoVo.loginName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_nickName" class="layui-form-label">
                <span class="we-red">*</span>用户名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_nickName" name="nickName" lay-verify="required" autocomplete="" class="layui-input" th:value="${fileSysUserInfoVo.nickName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="email" lay-verify="required" autocomplete="" class="layui-input" th:value="${fileSysUserInfoVo.email}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_mobile" class="layui-form-label">
                <span class="we-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_mobile" name="mobile" lay-verify="required" autocomplete="" class="layui-input" th:value="${fileSysUserInfoVo.mobile}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_addr" class="layui-form-label">
                <span class="we-red">*</span>地址
            </label>
            <div class="layui-input-inline">
                <textarea name="addr" id="L_addr" class="layui-textarea" th:text="${fileSysUserInfoVo.addr}"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_roleId" class="layui-form-label">
                <span class="we-red">*</span>角色
            </label>
            <div class="layui-input-inline">
                <select name="roleId" id="L_roleId">
                    <option th:each="item : ${sysRoleList}" th:value="${item.id}" th:selected="${fileSysUserInfoVo.roleId eq item.id}" th:text="${item.roleName}"></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_status" class="layui-form-label">状态</label>
            <div class="layui-input-block" id="L_status">
                <input type="radio" th:each="item,status : ${sysUserInfoStatusEnums}" name="statusId" th:value="${item.id}" th:title="${item.name}" th:checked="${fileSysUserInfoVo.statusId eq item.id}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">确定</button>
            <input type="hidden" name="id" th:value="${fileSysUserInfoVo.id}" />
        </div>
    </form>
</div>
<script th:src="@{/layui/layui.js}" charset="utf-8"></script>
<script type="text/javascript">
    layui.extend({
        admin: '{/}/js/admin'
    });
    layui.use(['form', 'jquery', 'admin','layer'], function() {
        var form = layui.form,
            $ = layui.jquery,
            admin = layui.admin,
            layer = layui.layer;


        //监听提交
        form.on('submit(add)', function(data) {
            var content = data.field;
            $.ajax({
                type: "POST",
                url: "/fileSysUserInfo/do_edit",
                data: content,
                dataType: "json",
                success: function(data){
                    if (data.code==0){
                        layer.alert("修改成功", {
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
