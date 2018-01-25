package com.eaton.json;

import java.util.List;

/**
 * Created by yitgeng on 1/25/2018.
 */
public class User {

    private String name;
    private Gender gender;
    private List<UserInfo> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<UserInfo> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserInfo> accounts) {
        this.accounts = accounts;
    }
}
