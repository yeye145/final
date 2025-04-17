package service.impl;

import dao.impl.UserDaoImpl;
import pojo.User;
import service.UpdateService;

import java.util.Set;

public class UpdateServiceImpl implements UpdateService {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean updateName(Integer id, String newName) throws Exception {

        Set<User> userSet = userDao.getUserSet();
        for (User user : userSet) {
            if(user.getName().equals(newName)) {
                System.out.println("--UpdateServiceImpl，昵称已存在");
                return false;
            }
        }
        userDao.updateName(id, newName);
        return true;
    }

}
