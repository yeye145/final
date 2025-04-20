package dao.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class MySearch {


    //结果封装到list集合中
    public static <T> List<T> searchToList(String sql, Class<T> clazz, Object... args) throws SQLException {

        List<T> result = new ArrayList<T>();

        Connection connection = MyPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            preparedStatement = connection.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //执行sql，获取结果集
            resultSet = preparedStatement.executeQuery();

            //获取结果集中的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();


            while (resultSet.next()) {
                T t = clazz.newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {

                    //获取结果集对应值
                    Object value = resultSet.getObject(metaData.getColumnLabel(i));


                    //利用反射获取属性值
                    Field field = clazz.getDeclaredField(metaData.getColumnLabel(i));

                    //忽略private
                    field.setAccessible(true);

                    //将结果封装
                    field.set(t, value);
                }

                result.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return result;

    }

    //结果封装到Set集合中
    public static <T> Set<T> searchToSet(String sql, Class<T> clazz, Object... args) throws SQLException {

        Set<T> result = new HashSet<>();
        Connection connection = MyPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            preparedStatement = connection.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //执行sql，获取结果集
            resultSet = preparedStatement.executeQuery();

            //获取结果集中的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();


            while (resultSet.next()) {
                T t = clazz.newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {

                    //获取结果集对应值
                    Object value = resultSet.getObject(metaData.getColumnLabel(i));


                    //利用反射获取属性值
                    Field field = clazz.getDeclaredField(metaData.getColumnLabel(i));

                    //忽略private
                    field.setAccessible(true);

                    //将结果封装
                    field.set(t, value);
                }

                result.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return result;

    }

    //结果封装到单个？中
    public static <T> T searchToOne(String sql, Class<T> clazz, Object... args) throws SQLException {

        T t = null;
        Connection connection = MyPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            preparedStatement = connection.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            //执行sql，获取结果集
            resultSet = preparedStatement.executeQuery();

            //获取结果集中的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();


            if (resultSet.next()) {

                t = clazz.newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {

                    //获取结果集对应值
                    Object value = resultSet.getObject(metaData.getColumnLabel(i));


                    //利用反射获取属性值
                    Field field = clazz.getDeclaredField(metaData.getColumnLabel(i));

                    //忽略private
                    field.setAccessible(true);

                    //将结果封装
                    field.set(t, value);
                }

            }
            if (resultSet.next()) {
                System.out.println("检测到多个结果，但本方法只能返回一个，若要全部返回，请更换searchToList或searchToSet方法");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return t;

    }

    // 结果封装到Map集合中
    public static <K, V> Map<K, V> searchToMap(String sql, Class<V> clazz, String keyProperty, Object... args) throws SQLException {
        Map<K, V> resultMap = new HashMap<>();
        Connection connection = MyPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                V value = clazz.newInstance();
                K key = null;

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnLabel = metaData.getColumnLabel(i);
                    Object columnValue = resultSet.getObject(columnLabel);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(value, columnValue);

                    // 提取键值（例如postId）
                    if (columnLabel.equals(keyProperty)) {
                        key = (K) columnValue;
                    }
                }

                if (key != null) {
                    resultMap.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return resultMap;
    }

}
