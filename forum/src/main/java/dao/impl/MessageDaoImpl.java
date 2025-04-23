package dao.impl;

import dao.MessageDao;
import dao.utils.MyUpdate;

public class MessageDaoImpl implements MessageDao {


    @Override
    public void creatMessage(String content, Integer userIdReceive, Integer userIdSend, String type) throws Exception {
        MyUpdate.update("INSERT INTO `forum`.`message`" +
                " (`content`, `user_id_receive`, `user_id_send`, `type`)" +
                " VALUES (?, ?, ?, ?)", content, userIdReceive, userIdSend, type);
    }


}
