package top.shellwe.toolbox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.shellwe.toolbox.service.UserManagementService;

import java.util.List;
import java.util.Objects;

@RestController
public class HttpRequestSet {
    @PostMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllInfo() {
        UserManagementService u = new UserManagementService();
        return ResponseEntity.ok(u.getAllAMCMUserDTOInfo());
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserDTO userDTO) {
        UserManagementService u = new UserManagementService();
//        System.out.println(userDTO.toString());
        if(userDTO.getUSER_LOGIN_ID() == null || userDTO.getUSER_NAME() == null|| userDTO.getUSER_PASS() == null || userDTO.getDEPART_NAME() == null){
            return "除了备注，都是必填";
        }
        return (u.createUser(
                userDTO.getUSER_LOGIN_ID(),
                userDTO.getUSER_NAME(),
                userDTO.getUSER_PASS(),
                userDTO.getDEPART_NAME(),
                userDTO.getREMARK()
                ));
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/alter")
    public String updateUserByUserID(@RequestBody UserDTO userDTO) {
        if(
                Objects.equals(userDTO.getUSER_NAME(), "") ||
                Objects.equals(userDTO.getUSER_LOGIN_ID(), "") ||
                Objects.equals(userDTO.getUSER_ID(), "") ||
                Objects.equals(userDTO.getUSER_PASS(), "")
        ){
            return "The error is that necessary entries are null.";
        }

        UserManagementService u = new UserManagementService();
//        userId, userName, loginId, password, department, remark
        return (u.updateUser(
                Integer.parseInt(userDTO.getUSER_ID()),
                userDTO.getUSER_NAME(),
                userDTO.getUSER_LOGIN_ID(),
                userDTO.getUSER_PASS(),
                userDTO.getDEPART_NAME(),
                userDTO.getREMARK()
        ));
    }

    @PostMapping("/alterState")
    public String updateState(@RequestBody UserDTO userDTO){
        if(
                Objects.equals(userDTO.getUSER_ID(), "") ||
                Objects.equals(userDTO.getUSER_STATE(), "")
        ){
            return "The error is that necessary entries are null.";
        }
        UserManagementService u = new UserManagementService();
        return (u.updateState(Integer.parseInt(userDTO.getUSER_ID()), Integer.parseInt(userDTO.getUSER_STATE())));
    }

    @PostMapping("/allRole")
    public ResponseEntity<List<UserDTO>> getAllRole() {
        UserManagementService u = new UserManagementService();
        return ResponseEntity.ok(u.getAllRole());
    }
}
