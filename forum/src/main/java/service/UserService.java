package service;

import java.sql.SQLException;

public interface UserService {
    String getAvatar(Integer userId) throws SQLException;
}
