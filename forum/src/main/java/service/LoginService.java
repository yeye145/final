package service;

import pojo.User;

import java.sql.SQLException;

public interface LoginService {

    /*-----------------------------------       忘记密码，重设密码       -------------------------------------*/
    Boolean forgetPassword(String account, String password) throws Exception;

    /*-----------------------------------       用户登录，密码校验       -------------------------------------*/
    User loginCheck(String account, String password) throws Exception;
}
