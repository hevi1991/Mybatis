<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title>内容列表页面</title>
    <link href="resources/css/all.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="resources/js/common/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="resources/js/back/list.js"></script>
</head>
<body style="background: #e1e9eb;">
<form action="list.action" id="mainForm" method="post">
    <div class="right">
        <div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
        <div class="rightCont">
            <p class="g_title fix">内容列表 <a class="btn03" href="addServlet.action">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03"
                                                                                                    href="javascript:;"
                                                                                                    onclick="deleteBatch()">删
                除</a></p>
            <table class="tab1">
                <tbody>
                <tr>
                    <td width="90" align="right">指令名称：</td>
                    <td>
                        <input type="text" name="name" class="allInput" value="${name}" />
                    </td>
                    <td width="90" align="right">描述：</td>
                    <td>
                        <input type="text" name="description" class="allInput" value="${description}" />
                    </td>
                    <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
                </tr>
                </tbody>
            </table>
            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody>
                    <tr>
                        <th><input type="checkbox" id="all" onclick="clickAll(this)"></th>
                        <th>序号</th>
                        <th>指令</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${messageList}" var="message" varStatus="status">
                        <tr <c:if test="${status.index%2!=0}">style="background-color:#ECF6EE;"</c:if>>
                                <%--同样的name会生成一个String数组，提交到servlet--%>
                            <td><input type="checkbox" name="id" value=${message.id}><input type="hidden" name="commandId"></td>
                            <td>${status.index+1}</td>
                            <td>${message.name}</td>
                            <td>${message.description}</td>
                            <td>
                                <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
                                <a href="javascript:;" onclick="deleteOne(${message.id})">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class='page fix'>
                    <input type="hidden" id="currentPage" name="currentPage">
                    共 <b>${page.totalNumber}</b> 条
                    <c:if test="${page.currentPage != 1}">
                        <a href="javascript:changeCurrentPage('1')" class='first'>首页</a>
                        <a href="javascript:changeCurrentPage(${page.currentPage-1})" class='pre'>上一页</a>
                    </c:if>
                    当前第<span>${page.currentPage}/${page.totalPage}</span>页
                    <c:if test="${page.currentPage != page.totalPage}">
                        <a href="javascript:changeCurrentPage(${page.currentPage+1})" class='next'>下一页</a>
                        <a href="javascript:changeCurrentPage(${page.totalPage})" class='last'>末页</a>
                    </c:if>
                    跳至&nbsp;<input type='text' value='1' class='allInput w28'/>&nbsp;页&nbsp;
                    <a href='###' class='go'>GO</a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>