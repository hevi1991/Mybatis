package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;
import com.imooc.util.Iconst;

import java.util.List;
import java.util.Random;

/**
 * 列表相关的业务功能
 * Created by Hevi on 2017/4/27.
 */
public class QueryService {

    MessageDao md = new MessageDao();

    public List<Command> queryMessageList(String name, String description){
        List<Command> messageList = null;
        messageList = md.queryMessageList(name, description);
        return messageList;
    }
    /**
     * 通过指令查询自动回复的内容
     * @param name 指令
     * @return 自动回复的内容
     */
    public String queryByCommand(String name) {
        List<Command> messageList = null;
        if(Iconst.HELP_COMMAND.equals(name)) {
            messageList = md.queryMessageList(null, null);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < messageList.size(); i++) {
                if(i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + messageList.get(i).getName() + "]可以查看" + messageList.get(i).getDescription());
            }
            return result.toString();
        }
        messageList = md.queryMessageList(name, null);
        if(messageList.size() > 0) {
            List<CommandContent> commandContents = messageList.get(0).getContentList();
            int i = new Random().nextInt(commandContents.size());//取随机整数，包含0～size
            return commandContents.get(i).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }

    public static void main(String arg[]){
        QueryService qs = new QueryService();
        List<Command> commands = qs.queryMessageList("", "");
        for (Command c :
                commands) {
            System.out.println(c.toString());
        }
    }
}
