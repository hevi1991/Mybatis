package com.imooc.servlet;

import com.imooc.bean.Command;
import com.imooc.bean.Message;
import com.imooc.entity.Page;
import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 列表初始化控制
 * Created by Hevi on 2017/4/26.
 */
public class ListServlet extends HttpServlet {

    QueryService queryService = new QueryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决编码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //接收值，设置值
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String currentPage = request.getParameter("currentPage");
        //创建分页对象
        Page page = new Page();
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        if (currentPage == null || !pattern.matcher(currentPage).matches()) {
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(Integer.valueOf(currentPage));
        }

        //调服务
        //List<Command> messageList = queryService.queryMessageList(name, description, page);
        List<Command> messageList = queryService.queryCommandListByPage(name, description, page);

        //填入数据，转发
        request.setAttribute("messageList", messageList);
        request.setAttribute("name",name);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
