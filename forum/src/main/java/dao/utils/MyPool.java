package dao.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class MyPool {
    private static final Properties config = new Properties();
    private static ArrayList<Connection> pool;

    static {
        try (InputStream input = MyPool.class.getClassLoader().getResourceAsStream("myPool.properties")) {
            if (input == null) {
                throw new RuntimeException("未找到配置文件 myPool.properties");
            }

            // 加载配置文件
            config.load(input);

            // 注册驱动
            Class.forName(config.getProperty("driverClassName"));

            // 初始化连接池
            int initialSize = Integer.parseInt(config.getProperty("initialSize"));
            pool = new ArrayList<>(initialSize);

            // 创建初始连接
            for (int i = 0; i < initialSize; i++) {
                Connection connection = DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password")
                );
                pool.add(connection);
            }
        } catch (Exception e) {
            throw new RuntimeException("连接池初始化失败", e);
        }
    }

    public static synchronized Connection getConnection() {
        try {
            if (!pool.isEmpty()) {
                return pool.remove(0); // 从池中取连接
            } else {
                // 池为空时，检查是否允许新建连接
                int maxActive = Integer.parseInt(config.getProperty("maxActive"));
                if (pool.size() < maxActive) {
                    return DriverManager.getConnection(
                            config.getProperty("url"),
                            config.getProperty("username"),
                            config.getProperty("password")
                    );
                } else {
                    throw new RuntimeException("连接数已达上限: " + maxActive);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获取连接失败", e);
        }
    }

}