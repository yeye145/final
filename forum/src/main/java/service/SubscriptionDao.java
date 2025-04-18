package service;

import pojo.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
    List<Subscription> getOneSubscriptionList(Integer userId) throws SQLException;
}
