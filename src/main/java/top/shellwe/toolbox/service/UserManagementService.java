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

    public String deleteOneRole(String userId){
        return amcmUserRepository.deleteRoleById(userId);
    }

    public String addNewRole(String roleId, String roleName){
        return amcmUserRepository.insertRole(roleId, roleName);
    }

    public String alterRole(String roleId, String newRoleName){
        return amcmUserRepository.updateRoleNameById(roleId, newRoleName);
    }

    public UserDTO findAndUpdateUser(String loginId, String password){
        return amcmUserRepository.findAndUpdateUser(loginId, password);
    }

    public void updateOnlineState(int userId, int onlineState){
        amcmUserRepository.updateOnlineState(userId, onlineState);
    }
}
