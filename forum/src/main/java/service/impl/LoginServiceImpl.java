package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.LoginService;
import service.utils.HashSaltUtil;

import java.util.Set;

public class LoginServiceImpl implements LoginService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserDao userDao = new UserDaoImpl();


    @Override
    public User loginCheck(String account, String password) throws Exception {

        Set<User> userSet = userDao.getUserSet();


        /*-------------------------------------    遍历集合，找到该用户    -------------------------------------*/
        for (User user : userSet) {


            if (user.getPhone().equals(account) || user.getEmail().equals(account)) {

                /*-------------------------------    如果sql中存储的是明文密码    -------------------------------*/
                if (isPlainPassword(user.getPassword())) {
                    if(!user.getPassword().equals(password)) return null;
                    String hashedPassword = HashSaltUtil.creatHashPassword(password);
                    user.setPassword(hashedPassword);
                }

                /*--------------------------------------    验证密码    --------------------------------------*/
                if (HashSaltUtil.verifyHashPassword(password, user.getPassword())) {
                    userDao.updatePassword(user.getPassword(), user.getId());
                    return user;
                } else {
                    break;
                }

            }
        }

        return null;
    }

    /*-----------------------------------       判断密码是不是明文密码       -----------------------------------*/
    private boolean isPlainPassword(String password) {

        /*-----------------------------------   BCrypt哈希值固定为60字符    -----------------------------------*/
        return password.length() < 60;
    }


}
