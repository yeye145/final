package dao.utils;

import controller.utils.ControllerToolMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyUpdate {

    public static int update(String sql, Object... args) throws Exception {

        //更新包括增、删、改
        int row = 0;

        Connection connection = MyPool.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            //获取preparedStatement对象
            preparedStatement = connection.prepareStatement(sql);

            //遍历，填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            row = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            ControllerToolMethod.fetchException(e, "，MyUpdate.update方法中出错，");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        }


        //返回更新的行
        return row;
    }
}
