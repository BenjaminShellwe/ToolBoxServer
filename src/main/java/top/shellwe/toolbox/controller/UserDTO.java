package top.shellwe.toolbox.controller;

public class UserDTO {
//    USER相关表中数据
    private String USER_ID;
    private String USER_LOGIN_ID;
    private String USER_NAME;
    private String GROUP_ID;
    private String DEPART_NAME;
    private String USER_PASS;
    private String USERPWD_LAPSE;
    private String SETPWD_LAPSE;
    private String MODIFY_TIME;
    private String USER_STATE;
    private String ONLINE_STATE;
    private String REMARK;
    private String USER_TYPE;
    private String OLD_PASSWORDS;
    private String SYNC_FLAG;

//    Role相关数据
    private String ROLE_ID;
    private String ROLE_TYPE;
    private String ROLE_NAME;

    public UserDTO() {
    }

    public UserDTO(String ROLE_ID, String ROLE_TYPE, String ROLE_NAME) {
        this.ROLE_ID = ROLE_ID;
        this.ROLE_TYPE = ROLE_TYPE;
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
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

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
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

    public String getUSERPWD_LAPSE() {
        return USERPWD_LAPSE;
    }

    public void setUSERPWD_LAPSE(String USERPWD_LAPSE) {
        this.USERPWD_LAPSE = USERPWD_LAPSE;
    }

    public String getSETPWD_LAPSE() {
        return SETPWD_LAPSE;
    }

    public void setSETPWD_LAPSE(String SETPWD_LAPSE) {
        this.SETPWD_LAPSE = SETPWD_LAPSE;
    }

    public String getMODIFY_TIME() {
        return MODIFY_TIME;
    }

    public void setMODIFY_TIME(String MODIFY_TIME) {
        this.MODIFY_TIME = MODIFY_TIME;
    }

    public String getUSER_STATE() {
        return USER_STATE;
    }

    public void setUSER_STATE(String USER_STATE) {
        this.USER_STATE = USER_STATE;
    }

    public String getONLINE_STATE() {
        return ONLINE_STATE;
    }

    public void setONLINE_STATE(String ONLINE_STATE) {
        this.ONLINE_STATE = ONLINE_STATE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }

    public String getOLD_PASSWORDS() {
        return OLD_PASSWORDS;
    }

    public void setOLD_PASSWORDS(String OLD_PASSWORDS) {
        this.OLD_PASSWORDS = OLD_PASSWORDS;
    }

    public String getSYNC_FLAG() {
        return SYNC_FLAG;
    }

    public void setSYNC_FLAG(String SYNC_FLAG) {
        this.SYNC_FLAG = SYNC_FLAG;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getROLE_TYPE() {
        return ROLE_TYPE;
    }

    public void setROLE_TYPE(String ROLE_TYPE) {
        this.ROLE_TYPE = ROLE_TYPE;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "USER_ID='" + USER_ID + '\'' +
                ", USER_LOGIN_ID='" + USER_LOGIN_ID + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", GROUP_ID='" + GROUP_ID + '\'' +
                ", DEPART_NAME='" + DEPART_NAME + '\'' +
                ", USER_PASS='" + USER_PASS + '\'' +
                ", USERPWD_LAPSE='" + USERPWD_LAPSE + '\'' +
                ", SETPWD_LAPSE='" + SETPWD_LAPSE + '\'' +
                ", MODIFY_TIME='" + MODIFY_TIME + '\'' +
                ", USER_STATE='" + USER_STATE + '\'' +
                ", ONLINE_STATE='" + ONLINE_STATE + '\'' +
                ", REMARK='" + REMARK + '\'' +
                ", USER_TYPE='" + USER_TYPE + '\'' +
                ", OLD_PASSWORDS='" + OLD_PASSWORDS + '\'' +
                ", SYNC_FLAG='" + SYNC_FLAG + '\'' +
                ", ROLE_ID='" + ROLE_ID + '\'' +
                ", ROLE_TYPE='" + ROLE_TYPE + '\'' +
                ", ROLE_NAME='" + ROLE_NAME + '\'' +
                '}';
    }
}
