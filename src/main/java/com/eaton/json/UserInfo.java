package com.eaton.json;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yitgeng on 1/25/2018.
 */
public class UserInfo {

    private Integer id;
    private String addr;
    private String school;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
