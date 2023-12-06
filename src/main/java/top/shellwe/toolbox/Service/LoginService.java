package top.shellwe.toolbox.Service;

import top.shellwe.toolbox.Repository.UserRepository;

// 简单的登录服务就不添加接口声明方法了
public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public boolean login(String username, String password) {
        return userRepository.validateUser(username, password);
    }
}
