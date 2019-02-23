<%@ page import="java.util.List" %>
<%@ page import="com.wjysushe.bean.User" %>
<%@ page import="com.wjysushe.bean.Apartment" %>
<%@ page import="com.wjysushe.bean.WaterElectricity" %>
<%@ page import="static com.wjysushe.service.UserService.SESSION_USER" %><%--
  Created by IntelliJ IDEA.
  User: ly6333
  Date: 2019/2/20
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute(SESSION_USER);
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>宿舍管理系统</title>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">
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
                <li><a href="#"><%=user.getName()%></a></li>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="index.jsp">首页</a><span
                    class="crumb-step">&gt;</span><span class="crumb-name">水电管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/waterElectricity_action" method="get">
                    <table class="search-tab">
                        <tbody>
                        <tr>
                            <th width="120">选择公寓:</th>
                            <td>
                                <select name="apartmentId" id="catid" class="required">
                                    <option value="">全部</option>
                                    <%
                                        List<Apartment> apartmentList = (List<Apartment>) request.getAttribute("apartmentList");
                                        for (Apartment item: apartmentList) {
                                    %>
                                    <option value="<%=item.getId()%>"><%=item.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                            <th width="70">寝室名字:</th>
                            <td>
                                <input class="common-text" placeholder="请输入名称匹配" name="dormitoryName" value="" id=""
                                       type="text">
                            </td>
                            <td>
                                <input name="action" value="list" type="hidden">
                                <input class="btn btn-primary btn2" value="查询" type="submit">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <%
                            if (user.getRole()!=3){
                            	out.println(" <a href=\"/waterElectricity_action?action=add\"><i class=\"icon-font\">\uE026</i>新增公寓</a>");
                            }
                        %>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tbody>
                        <tr>
                            <th>ID</th>
                            <th>公寓</th>
                            <th>寝室</th>
                            <th>时间</th>
                            <th>用水量</th>
                            <th>水费</th>
                            <th>用电量</th>
                            <th>电费</th>
                            <%
                                if (user.getRole()!=3){
                                	out.println("      <th>操作</th>");
                                }
                            %>
                        </tr>
                        <%
                            List<WaterElectricity> waterElectricityList = (List<WaterElectricity>) request.getAttribute("list");
                            for (WaterElectricity item : waterElectricityList) {
                        %>
                        <tr>
                            <td><%=item.getId()%>
                            </td>
                            <td><%=item.getApartmentName()%>
                            </td>
                            <td><%=item.getDormitoryName()%>
                            </td>
                            <td><%=item.getStatDate()%>
                            </td>
                            <td><%=item.getWaterNum()%>
                            </td>
                            <td><%=item.getWaterCost()%>
                            </td>
                            <td><%=item.getElectricityNum()%>
                            </td>
                            <td><%=item.getElectricityCost()%>
                            </td>
                            <%
                                if (user.getRole()!=3){
                            %>
                                <td>
                                    <a class="link-update" href="/waterElectricity_action?action=edit&id=<%=item.getId()%>">修改</a>
                                    <a class="link-del" href="/waterElectricity_action?action=delete&id=<%=item.getId()%>"
                                       onClick="return confirm('确定要删除该用户吗？')">删除</a>
                                </td>
                            <%
                                }
                            %>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <div class="list-page"> 1/1 页</div>
                </div>
            </form>
        </div>
    </div>

</div>

<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>

</body>
</html>