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

    public String updateUser(int userId, String userName, String loginId, String password, String department, String remark){
        return amcmUserRepository.updateUser(userId, userName, loginId, password, department, remark);
    }

    public String updateState(int userId, int userState) {
        return amcmUserRepository.updateUserState(userId, userState);
    }

    public List<UserDTO> getAllRole() {
        return amcmUserRepository.getAllRoles();
    }
}
