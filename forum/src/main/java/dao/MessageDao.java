package dao;

import pojo.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {


    void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception;
    
    List<Message> getOneMessageList(Integer userId) throws SQLException;
}
