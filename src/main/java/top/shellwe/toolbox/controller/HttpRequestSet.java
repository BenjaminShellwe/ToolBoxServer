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

    @PostMapping("/deleteOneRole")
    public String deleteRoleOfOne(@RequestBody UserDTO userDTO) {
        if(
                Objects.equals(userDTO.getROLE_ID(), "")
        ){
            return "The error is that necessary entries are null.";
        }
        UserManagementService u = new UserManagementService();
        return u.deleteOneRole(userDTO.getROLE_ID());
    }

    @PostMapping("/insertRole")
    public String addNewRole(@RequestBody UserDTO userDTO){
        if(
                Objects.equals(userDTO.getROLE_ID(), "")||
                Objects.equals(userDTO.getROLE_NAME(), "")
        ){
            return "The error is that necessary entries are null.";
        }
        UserManagementService u = new UserManagementService();
        return u.addNewRole(userDTO.getROLE_ID(), userDTO.getROLE_NAME());
    }

    @PostMapping("/updateRole")
    public String alterRole(@RequestBody UserDTO userDTO){
        if(
                Objects.equals(userDTO.getROLE_ID(), "")||
                        Objects.equals(userDTO.getROLE_NAME(), "")
        ){
            return "The error is that necessary entries are null.";
        }
        UserManagementService u = new UserManagementService();
        return u.alterRole(userDTO.getROLE_ID(), userDTO.getROLE_NAME());
    }

    @PostMapping("/loginAction")
    public ResponseEntity<UserDTO> signinAction(@RequestBody UserDTO userDTO){
        if(
                Objects.equals(userDTO.getUSER_LOGIN_ID(), "")||
                        Objects.equals(userDTO.getUSER_PASS(), "")
        ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userDTO);
        }
        UserManagementService u = new UserManagementService();
        UserDTO uDTO2;
        uDTO2 = u.findAndUpdateUser(userDTO.getUSER_LOGIN_ID(),userDTO.getUSER_PASS());
        if(uDTO2 == null || Objects.equals(uDTO2.getUSER_ID(), "") || uDTO2.getUSER_ID() == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(uDTO2);
        }
        return ResponseEntity.status(HttpStatus.OK).body(uDTO2);
    }

    @PostMapping("/updateOnlineState")
    public String alterOnlineState(@RequestBody UserDTO userDTO){
        if(
                Objects.equals(userDTO.getUSER_ID(), "")||
                        Objects.equals(userDTO.getONLINE_STATE(), "")
        ){
            return "The error is that necessary entries are null.";
        }
        UserManagementService u = new UserManagementService();
        u.updateOnlineState(Integer.parseInt(userDTO.getUSER_ID()), Integer.parseInt(userDTO.getONLINE_STATE()));
        return "Success!";
    }
}

