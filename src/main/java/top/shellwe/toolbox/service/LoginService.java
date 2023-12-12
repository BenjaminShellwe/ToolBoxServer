package top.shellwe.toolbox.service;

import top.shellwe.toolbox.controller.UserDTO;
import top.shellwe.toolbox.repository.UserRepository;

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
}
