package service;

import pojo.User;

import java.sql.SQLException;

public interface LoginService {

    /*-----------------------------------      注册新的账号       -------------------------------------------*/
    Boolean register(String phone, String email, String password) throws Exception;

    /*-----------------------------------       忘记密码，重设密码       -------------------------------------*/
    Boolean forgetPassword(String account, String password) throws Exception;

    /*-----------------------------------       用户登录，密码校验       -------------------------------------*/
    User loginCheck(String account, String password) throws Exception;
}
