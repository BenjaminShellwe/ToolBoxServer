package top.shellwe.toolbox.controller;


import java.time.LocalDateTime;

//Data Transfer Object 处理json信息
public class UserDTO {
    private String username;
    private String password;
    
    private boolean isLogged;
    private String ipAddress;
    private LocalDateTime loginTime;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String ipAddress, LocalDateTime loginTime) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
