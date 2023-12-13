package top.shellwe.toolbox.service;

import top.shellwe.toolbox.controller.UserDTO;
import top.shellwe.toolbox.repository.UserRepository;

import java.util.List;

// 简单的登录服务就不添加接口声明方法了，结果却变成了不是接口的接口
public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public boolean login(String username, String password) {
        return userRepository.validateUser(username, password);
    }

    public int afterLogin(String username, String password) {
        return userRepository.afterValidateUser(username, password);
    }

    public UserDTO getUserDTOInfo(int userId) {
        return userRepository.getUserDTO(userId);
    }

    public List<UserDTO> getAllUserDTOInfo() {
        return userRepository.getUsersWithoutAdmin();
    }

    public boolean updateUserInfo(int userId, String fullName, String gender, String phone, String role){
        return userRepository.updateUserInfo(userId, fullName, gender, phone, role);
    }

    public boolean insertUserAndUserInfo(String username, String password, String fullName, String gender, String phone, String role, int userId){
        return userRepository.insertUserAndUserInfo(username, password, fullName, gender, phone, role, userId);
    }

    public boolean deleteUserAndUserInfo(int userId){
        return userRepository.deleteUserAndUserInfoByUserId(userId);
    }

}
