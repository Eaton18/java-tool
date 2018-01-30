package com.eaton.db.data.dao.impl;

import com.eaton.db.data.dao.UserInfoDao;
import com.eaton.db.data.model.UserInfo;
import com.eaton.db.mysql.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by yitgeng on 1/30/2018.
 */
public class UserInfoDaoImpl implements UserInfoDao {

    public void addUsers(ArrayList<UserInfo> userInfos) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO user_info(id,name,sex,age,salary) VALUES(?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < userInfos.size(); i++) {
                preparedStatement.setInt(1, userInfos.get(i).getId());
                preparedStatement.setString(2, userInfos.get(i).getName());
                preparedStatement.setInt(3, userInfos.get(i).getSex());
                preparedStatement.setInt(4, userInfos.get(i).getAge());
                preparedStatement.setInt(5, userInfos.get(i).getSalary());

                preparedStatement.executeUpdate();

                System.out.println(i + "/" + userInfos.size() + " processed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void addUsersBatch(ArrayList<UserInfo> userInfos) {

        Connection connection = null;
        String sql = "INSERT INTO user_info(id,name,sex,age,salary) VALUES(?,?,?,?,?)";

        connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int batchSize = 5000;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < userInfos.size(); i++) {
                preparedStatement.setInt(1, userInfos.get(i).getId());
                preparedStatement.setString(2, userInfos.get(i).getName());
                preparedStatement.setInt(3, userInfos.get(i).getSex());
                preparedStatement.setInt(4, userInfos.get(i).getAge());
                preparedStatement.setInt(5, userInfos.get(i).getSalary());

                preparedStatement.addBatch();

                System.out.println(i + "/" + userInfos.size() + " processed.");

                if (i % batchSize == 0) {

                    preparedStatement.executeBatch();
                    connection.commit();

                    System.out.println(">>>>> Execute Batch <<<<<");

                }
            }

            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, preparedStatement);
        }

    }
}
