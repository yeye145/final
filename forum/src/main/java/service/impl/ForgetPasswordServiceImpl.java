package service.impl;

import com.alibaba.fastjson.JSON;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.ResponseResult;
import pojo.User;
import service.ForgetPasswordService;
import utils.Constants;

import java.util.Set;

public class ForgetPasswordServiceImpl implements ForgetPasswordService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public Boolean forgetPassword(String account, String password) throws Exception {
        // 初始化用户
        Set<User> userSet = userDao.getUserSet();

        for (User user : userSet) {
            if (user.getPhone().equals(account) || user.getEmail().equals(account)) {
                // 找得到该用户
                userDao.updatePassword(password, user.getId());
                return true;
            }
        }
        // 用户不存在
        return false;
    }
}
