package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.MessageDao;
import com.imooc.entity.Page;
import com.imooc.util.Iconst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 列表相关的业务功能
 * Created by Hevi on 2017/4/27.
 */
public class QueryService {

    MessageDao md = new MessageDao();

    public List<Command> queryMessageList(String name, String description, Page page){

        Command command = new Command();
        command.setName(name);
        command.setDescription(description);

        int totalNumber = md.count(command);
        page.setTotalNumber(totalNumber);//set方法中配置其他需要的参数

        Map<String,Object> parameter = new HashMap<String,Object>();
        parameter.put("command",command);
        parameter.put("page",page);

        List<Command> messageList = md.queryMessageList(parameter);
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
            messageList = md.queryMessageList(null);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < messageList.size(); i++) {
                if(i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + messageList.get(i).getName() + "]可以查看" + messageList.get(i).getDescription());
            }
            return result.toString();
        }

        Command command = new Command();
        command.setName(name);
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("command",command);

        messageList = md.queryMessageList(map);
        if(messageList.size() > 0) {
            List<CommandContent> commandContents = messageList.get(0).getContentList();
            int i = new Random().nextInt(commandContents.size());//取随机整数，包含0～size
            return commandContents.get(i).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }

    public static void main(String arg[]){
        QueryService qs = new QueryService();
        String check = qs.queryByCommand("查看");
        System.out.println(check);
    }
}
