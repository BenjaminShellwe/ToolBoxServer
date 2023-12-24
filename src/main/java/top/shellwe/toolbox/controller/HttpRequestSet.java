package top.shellwe.toolbox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.shellwe.toolbox.service.UserManagementService;

import java.util.List;
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
        System.out.println(userDTO.toString());
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

}
