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
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/weadmin.css">
    <script src="/static/layui/layui.js" charset="utf-8"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>-->
    <!--<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->
</head>

<body>
<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">用户管理</a>
        <a><cite>用户管理</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            <div class="layui-inline">
                <input type="text" name="loginName" placeholder="登录名" autocomplete="off" class="layui-input" th:value="${query.getLoginName()}">
            </div>
            <div class="layui-inline">
                <input type="text" name="mobile" placeholder="手机" autocomplete="off" class="layui-input" th:value="${query.getMobile()}">
            </div>
            <div class="layui-input-inline">
                <select name="roleId">
                    <option value="">角色</option>
                    <option th:each="item : ${sysRoleList}" th:value="${item.id}" th:text="${item.roleName}" th:selected="${item.id eq query.roleId}"></option>
                </select>
            </div>
            <button class="layui-btn" lay-submit="./list" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <div class="weadmin-block">
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="WeAdminShow('添加类别','/fileSysUserInfo/to_add',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="fr" style="line-height:40px" th:text="'共有数据：' + ${page.total}+ ' 条'"></span>
    </div>
    <table class="layui-table">
        <thead>
        <tr>
            <th style="width: 20px;">
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th style="width: 30px">序号</th>
            <th>登录名</th>
            <th>用户名</th>
            <th>邮箱</th>
            <th>手机</th>
            <th>地址</th>
            <th>角色</th>
            <th style="width: 40px;">状态</th>
            <th style="width: 60px">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="item,status : ${page.records}">
            <td>
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary" th:data-id="${item.id}"><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td th:text="${status.index+1}"></td>
            <td th:text="${item.loginName}"></td>
            <td th:text="${item.nickName}"></td>
            <td th:text="${item.email}"></td>
            <td th:text="${item.mobile}"></td>
            <td th:text="${item.addr}"></td>
            <td th:text="${item.roleName}"></td>
            <td th:each="i : ${sysUserInfoStatusEnums}" th:if="${i.id eq item.statusId}" th:text="${i.name}"></td>
            <td class="td-manage">
                <a title="查看" th:onclick="|WeAdminEdit('查看' , '/fileSysUserInfo/to_view?id=${item.id}' , 1, 600, 550)|" href="javascript:;">
                    <i class="layui-icon">&#xe63c;</i>
                </a>
                <a title="编辑" th:onclick="|WeAdminEdit('编辑' , '/fileSysUserInfo/to_edit?id=${item.id}' , 1, 600, 600)|" href="javascript:;">
                    <i class="layui-icon">&#xe642;</i>
                </a>
                <a title="删除" th:onclick="|member_del(this,${item.id})|" href="javascript:;">
                    <i class="layui-icon">&#xe640;</i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="page" >
        <div id="page">
        </div>
    </div>

</div>
<script th:inline="javascript">
    layui.extend({
        admin: '{/}/static/js/admin'
    });
    layui.use(['jquery','admin','laypage','form'], function() {
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page'
            ,count: [[${page.total}]]
            ,curr: [[${page.current}]]
            ,jump: function(obj, first){
                if(!first){
                    location.href = '/fileSysUserInfo/list?page=' + obj.curr+ "&" + layui.jquery('form').serialize();
                }
            }
        });
    });

    function member_del(obj, id) {
        layui.layer.confirm('确认要删除吗？', function(index) {
            layui.jquery.ajax({
                type: "POST",
                url: "/fileSysUserInfo/delete",
                data: {ids: id},
                dataType: "json",
                success: function(data){
                    if (data.code==0){
                        parent.parent.layui.layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                        setTimeout(function () {
                            parent.location.reload();
                        },1000);
                    }else{
                        parent.parent.layui.layer.alert(data.msg,{icon:5});
                    }
                }
            });
            layui.layer.close(index);
        });
    }

    function delAll(argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？' + data, function(index) {
            layui.jquery.ajax({
                type: "POST",
                url: "/fileSysUserInfo/delete",
                data: {ids: data.join(',')},
                dataType: "json",
                success: function(data){
                    if (data.code==0){
                        parent.parent.layui.layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                        setTimeout(function () {
                            parent.location.reload();
                        },1000);
                    }else{
                        parent.parent.layui.layer.alert(data.msg,{icon:5});
                    }
                }
            });
            layui.layer.close(index);
        });
    }

</script>
</body>

</html>
