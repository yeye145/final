package service.impl;

import dao.LogDao;
import dao.UserDao;

import dao.impl.LogDaoImpl;
import dao.impl.UserDaoImpl;

import pojo.User;
import service.LoginService;
import service.utils.HashSaltUtil;
import utils.Constants;


import java.util.Set;

public class LoginServiceImpl implements LoginService {

    /*--------------------------------------------    私有变量    ------------------------------------------*/
    private UserDao userDao = new UserDaoImpl();
    private LogDao logDao = new LogDaoImpl();

    /*-----------------------------------       判断密码是不是明文密码       ----------------------------------*/
    private boolean isPlainPassword(String password) {
        // 因为BCrypt哈希值固定为60字符
        return password.length() < 60;
    }


    /*-----------------------------------      注册新的账号       -------------------------------------------*/
    @Override
    public Boolean register(String phone, String email, String password) throws Exception {

        Integer uid = 1;
        Set<User> userSet = userDao.getUserSet();

        for (User user : userSet) {
            uid++;
            if (user.getPhone().equals(phone) || user.getEmail().equals(email)) {
                return false;
            }
        }
        userDao.insertUser(phone, email, password);

        // 记录到日志中
        logDao.recordThisActionInLog(uid, "用户" + phone, Constants.ACTION_REGISTER);
        return true;

    }


    /*-----------------------------------       忘记密码，重设密码       -------------------------------------*/
    @Override
    public Boolean forgetPassword(String account, String password) throws Exception {
        // 初始化用户
        Set<User> userSet = userDao.getUserSet();

        for (User user : userSet) {
            if (user.getPhone().equals(account) || user.getEmail().equals(account)) {
                // 找得到该用户
                userDao.updatePassword(password, user.getId());

                // 记录到日志中
                logDao.recordThisActionInLog(user.getId(), user.getName()
                        , Constants.ACTION_UPDATE_PASSWORD);
                return true;
            }
        }
        // 用户不存在
        return false;
    }


    /*-----------------------------------       用户登录，密码校验       -------------------------------------*/
    @Override
    public User loginCheck(String account, String password) throws Exception {
        // 获取用户信息Set集合
        Set<User> userSet = userDao.getUserSet();

        // 遍历集合，找到该用户
        for (User user : userSet) {

            if (user.getPhone().equals(account) || user.getEmail().equals(account)) {
                // 如果sql中存储的是明文密码
                if (isPlainPassword(user.getPassword())) {
                    String hashedPassword = HashSaltUtil.creatHashPassword(password);
                    user.setPassword(hashedPassword);
                    // 把数据库中的明文密码加密
                    userDao.updatePassword(hashedPassword, user.getId());
                }

                // 验证密码
                if (HashSaltUtil.verifyHashPassword(password, user.getPassword())) {
                    // 登陆成功！记录到日志中
                    logDao.recordThisActionInLog(user.getId(), user.getName()
                            , Constants.ACTION_LOGIN);
                    return user;
                } else {
                    break;
                }
            }
        }

        return null;
    }


}
