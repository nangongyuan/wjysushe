<%@ page import="com.wjysushe.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wjysushe.bean.Apartment" %>
<%@ page import="com.wjysushe.bean.Dormitory" %>
<%@ page import="static com.wjysushe.service.UserService.SESSION_USER" %><%--
  Created by IntelliJ IDEA.
  User: ly6333
  Date: 2019/2/20
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>宿舍管理系统</title>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">
    <link rel="stylesheet" href="/static/css/laydate.css" />
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/laydate.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.jsp" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.jsp">首页</a></li>
                <li><a href="index.jsp">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#"><%=((User)session.getAttribute(SESSION_USER)).getName()%></a></li>
                <li><a href="/resetPassword.jsp">修改密码</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font"></i>菜单列表</a>
                    <ul class="sub-menu">
                        <%
                            User user = (User) session.getAttribute(SESSION_USER);
                            if (user.getRole()==1){
                                out.println(" <li><a href=\"/user_action?action=list\"><i class=\"icon-font\">\uE008</i>用户管理</a></li>");
                            }
                            if (user.getRole()==1){
                                out.println("  <li><a href=\"/apartment_action?action=list\"><i class=\"icon-font\">\uE005</i>宿舍楼管理</a></li>");
                            }
                            if (user.getRole()==1){
                                out.println("  <li><a href=\"/dormitory_action?action=list\"><i class=\"icon-font\">\uE006</i>寝室管理</a></li>");
                            }
                        %>
                        <li><a href="/waterElectricity_action?action=list"><i class="icon-font"></i>水电管理</a></li>
                        <li><a href="/repairs_action?action=list"><i class="icon-font"></i>报修管理</a></li>
                        <li><a href="/examination_action?action=list"><i class="icon-font"></i>查寝管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">查寝管理</a><span class="crumb-step">&gt;</span><span>新增查寝</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="/examination_action?action=add" method="get" id="myform" name="myform" onsubmit="return write_member();">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>寝室：</th>
                            <td>
                                <select name="dormitoryId" id="catid" class="required">
                                    <%
                                        List<Dormitory> dormitoryList = (List<Dormitory>) request.getAttribute("dormitoryList");
                                        for (Dormitory item: dormitoryList) {
                                    %>
                                    <option value="<%=item.getId()%>"><%=item.getApartmentName()+"-"+item.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>时间：</th>
                            <td>
                                <div class="laydate-box">
                                    <input type="text" id="laydateInput" placeholder="xxxx年xx月xx日" name="examinationDate"/>
                                    <img src="/static/images/calendar.png" alt="" class="icon data-icon"/>
                                    <div class="select-date">
                                        <div class="select-date-header">
                                            <ul class="heade-ul">
                                                <li class="header-item header-item-one">
                                                    <select name="" id="yearList"></select>
                                                </li>
                                                <li class="header-item header-item-two" onselectstart="return false">
                                                    <select name="" id="monthList"></select>
                                                </li>
                                                <li class="header-item header-item-three" onselectstart="return false" >
                                                    <span class="reback">回到今天</span>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="select-date-body">
                                            <ul class="week-list">
                                                <li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li>
                                            </ul>
                                            <ul class="day-tabel"></ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>应到人数：</th>
                            <td><input class="common-text" name="peopleNumber" size="50" value="" type="text"></td>
                        </tr>
                        <tr>
                            <th>实到人数：</th>
                            <td><input class="common-text" name="realNumber" size="50" value="" type="text"></td>
                        </tr>
                        <tr>
                            <th>未到人员：</th>
                            <td><input class="common-text" name="studentNames" size="50" value="" type="text"></td>
                        </tr>
                        <tr>
                            <th></th><td>
                                <input name="action" value="save" type="hidden">
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>

<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>

</body>
</html>