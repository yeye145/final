package service;

import pojo.User;

import java.sql.SQLException;

public interface LoginService {

    User loginCheck(String account, String password) throws Exception;
}
