package service;

import pojo.User;

import java.sql.SQLException;

public interface LoginService {

    Boolean forgetPassword(String account, String password) throws Exception;

    User loginCheck(String account, String password) throws Exception;
}
