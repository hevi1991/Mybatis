package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.db.DBAccess;
import com.imooc.entity.Page;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 和Message表相关的数据库操作
 * Created by Hevi on 2017/4/27.
 */
public class MessageDao {

    /**
     * 根据查询条件，查询消息列表
     */
    public List<Command> queryMessageList(Map<String, Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Command> messageList = new ArrayList<Command>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            messageList = mapper.queryCommandList(parameter);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return messageList;
    }

    /**
     * 单条删除
     *
     * @param id
     */
    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            mapper.deleteOne(id);
            sqlSession.commit();//mysql的增删改有事务控制，需要提交事务
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 批量删除
     *
     * @param list
     */
    public void deleteBatch(List<Integer> list) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            mapper.deleteBatch(list);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }


    public int count(Command command) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        int count = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            count = mapper.count(command);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 使用拦截器，完成分页功能
     *
     * @param parameter
     * @return
     */
    public List<Command> queryMessageListByPage(Map<String, Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Command> messageList = new ArrayList<Command>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            messageList = mapper.queryMessageListByPage(parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    public void insertCommand(Command command){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            ICommand mapper = sqlSession.getMapper(ICommand.class);
            mapper.addCommand(command);
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertContents(List<CommandContent> list) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            ICommandContent mapper = sqlSession.getMapper(ICommandContent.class);
            mapper.insertBatch(list);
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
