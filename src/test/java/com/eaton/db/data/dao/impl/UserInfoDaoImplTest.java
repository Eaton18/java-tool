package com.eaton.db.data.dao.impl;

import com.eaton.db.data.model.UserInfo;
import com.eaton.db.mysql.JDBCUtils;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by yitgeng on 1/30/2018.
 */
public class UserInfoDaoImplTest {

    public static ArrayList<UserInfo> generateUsers(int userCount) {
        ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();

        int id = 0;
        String name = null;
        int sex = 0;
        int age = 0;
        int salary = 0;

        for (int i = 0; i < userCount; i++) {
            UserInfo userInfo = new UserInfo();

            userInfo.setId(i);
            userInfo.setName("user_" + i);
            userInfo.setSex(i % 2 == 0 ? 1 : 0);
            userInfo.setAge(i + 10);
            userInfo.setSalary(i + 10000);

            userInfos.add(userInfo);
        }


        return userInfos;
    }

    @org.junit.Test
    public void addUsers() throws Exception {

        ArrayList<UserInfo> userInfos = generateUsers(10000);

        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        long startTime = System.currentTimeMillis();  //get start time
        System.out.println("Start time: " + startTime);
        userInfoDao.addUsers(userInfos);
        long endTime = System.currentTimeMillis();  //get end time
        System.out.println("End time: " + endTime);
        System.out.println("Application running time: " + (endTime - startTime) + "ms");

        JDBCUtils.close(JDBCUtils.getConnection(), null);
    }


    @org.junit.Test
    public void addUsersBatch() throws Exception {

        ArrayList<UserInfo> userInfos = generateUsers(10000);

        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        long startTime = System.currentTimeMillis();  //get start time
        System.out.println("Start time: " + startTime);
        userInfoDao.addUsersBatch(userInfos);
        long endTime = System.currentTimeMillis();  //get end time
        System.out.println("End time: " + endTime);
        System.out.println("Application running time: " + (endTime - startTime) + "ms");

        JDBCUtils.close(JDBCUtils.getConnection(), null);
    }

}