package service;

import pojo.User;

import javax.servlet.http.Part;
import java.sql.SQLException;

public interface UserService {

    /*--------------------------------------------    更新头像    --------------------------------------------*/
    Boolean uploadAvatar(Part filePart, String savePath, String fileName, Integer userId) throws Exception;

    /*-------------------------------------------    更改昵称    ----------------------------------------------*/
    boolean updateName(Integer id, String newName) throws Exception;

    /*-------------------------------------------    订阅作者    ----------------------------------------------*/
    void subscribeThisUser(Integer authorId, Integer userId) throws Exception;

    /*-----------------------------------------    取消关注用户    --------------------------------------------*/
    void cancelSubscribeThisUser(Integer authorId, Integer userId) throws Exception;

    /*------------------------------------    判断用户是否已经关注该作者    --------------------------------------*/
    boolean checkIfSubcribe(Integer authorId, Integer userId) throws Exception;

    /*-----------------------------------------    获取用户的头像    -------------------------------------------*/
    String getAvatar(Integer userId) throws SQLException;

    /*--------------------------------------    获取用户的个人信息    -------------------------------------------*/
    User getInformation(Integer userId) throws SQLException;


}
