<%--
  Created by IntelliJ IDEA.
  User: ly6333
  Date: 2019/2/20
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>宿舍管理系统</title>
    <link rel="stylesheet" type="text/css" href="/static/css/admin_login.css"/>
</head>
<body>
<div class="admin_login_wrap">
    <h1>宿舍管理系统</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="/login" method="get">
                <ul class="admin_items">
                    <li>
                        <label for="user">学号：</label>
                        <input type="text" name="sno" value="" id="user" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="password" value="" id="pwd" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" />
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>

</body>
</html>