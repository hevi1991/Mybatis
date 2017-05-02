package com.imooc.service;

import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;

import java.sql.SQLException;
import java.util.List;

/**
 * 列表相关的业务功能
 * Created by Hevi on 2017/4/27.
 */
public class ListService {

    MessageDao md = new MessageDao();

    public List<Message> queryMessageList(String command, String description){
        List<Message> messageList = null;
        messageList = md.queryMessageList(command, description);
        return messageList;
    }
}
