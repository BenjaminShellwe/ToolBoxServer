package top.shellwe.toolbox.controller;

public class UserDTO2 {
//    USER相关表中数据
    private String USER_LOGIN_ID;
    private String USER_NAME;
    private String DEPART_NAME;
    private String USER_PASS;
    private String REMARK;

    public UserDTO2() {
    }

    public String getUSER_LOGIN_ID() {
        return USER_LOGIN_ID;
    }

    public void setUSER_LOGIN_ID(String USER_LOGIN_ID) {
        this.USER_LOGIN_ID = USER_LOGIN_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getDEPART_NAME() {
        return DEPART_NAME;
    }

    public void setDEPART_NAME(String DEPART_NAME) {
        this.DEPART_NAME = DEPART_NAME;
    }

    public String getUSER_PASS() {
        return USER_PASS;
    }

    public void setUSER_PASS(String USER_PASS) {
        this.USER_PASS = USER_PASS;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
