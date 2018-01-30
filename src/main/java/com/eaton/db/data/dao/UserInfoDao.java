package com.eaton.db.data.dao;

import com.eaton.db.data.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by yitgeng on 1/30/2018.
 */
public interface UserInfoDao {

    public void addUsers(ArrayList<UserInfo> userInfos);

    public void addUsersBatch(ArrayList<UserInfo> userInfos);

}
