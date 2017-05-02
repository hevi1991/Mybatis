package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * 和Message表相关的数据库操作
 * Created by Hevi on 2017/4/27.
 */
public class MessageDao {

    /**
     * 根据查询条件，查询消息列表
     */
    public List<Message> queryMessageList(String command, String description){
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            messageList = sqlSession.selectList("Message.queryMessageList");//对应Message.xml里面 命名空间+.+select的id
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }

        return messageList;
    }

    public static void main(String[] args) {
        MessageDao md = new MessageDao();
        List<Message> messageList = md.queryMessageList("", "");
        System.out.println(messageList.size());
    }



    /**
     * 根据查询条件，查询消息列表
     * 使用jdbc
     */
    /*public List<Message> queryMessageList(String command, String description) {

        List<Message> messageList = null;
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message?useSSL=true&useUnicode=true&characterEncoding=UTF-8", "root", "password");

            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1 ");

            List<String> paramList = new ArrayList<String>();
            if (command != null && !"".equals(command.trim())) {
                sql.append(" and COMMAND like '%' ? '%'");
                paramList.add(command);
            }
            if (description != null && !"".equals(description.trim())) {
                sql.append(" and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                preparedStatement.setString(i + 1, paramList.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            messageList = new ArrayList<Message>();
            while (resultSet.next()) {
                Message message = new Message();
                messageList.add(message);
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
                message.setContent(resultSet.getString("CONTENT"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }*/
}
