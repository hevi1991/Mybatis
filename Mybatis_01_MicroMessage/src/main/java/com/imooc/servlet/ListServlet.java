package com.imooc.servlet;

import com.imooc.bean.Message;
import com.imooc.service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 列表初始化控制
 * Created by Hevi on 2017/4/26.
 */
public class ListServlet extends HttpServlet {

    ListService ls = new ListService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决编码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //接收值，设置值
        String command = request.getParameter("command");
        String description = request.getParameter("description");
        request.setAttribute("command",command);
        request.setAttribute("description",description);
        //调服务
        List<Message> messageList = ls.queryMessageList(command, description);
        //填入数据，转发
        request.setAttribute("messageList", messageList);
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}