package top.shellwe.toolbox.service;

import top.shellwe.toolbox.controller.UserDTO;
import top.shellwe.toolbox.repository.AMCMUserRepository;

import java.util.List;

public class UserManagementService {
    public final AMCMUserRepository amcmUserRepository;


    public UserManagementService() {
        this.amcmUserRepository = new AMCMUserRepository();
    }

    public List<UserDTO> getAllAMCMUserDTOInfo() {
        return amcmUserRepository.getAllUserInfoWithRoles();
    }

    public String createUser(String userLoginID, String userName, String userPass, String departName, String remark) {
        return amcmUserRepository.createUser(userLoginID, userName, userPass, departName, remark);
    }
}
