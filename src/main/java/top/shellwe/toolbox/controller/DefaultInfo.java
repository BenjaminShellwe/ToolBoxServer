package top.shellwe.toolbox.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.shellwe.toolbox.service.LoginService;

/**
 * @Auther: Benjamin Thomas Shellwe
 * @Date: 2023/9/4 - 09 - 04 - 11:37
 * @Description: top.shellwe.toolsbox.controller
 * @version: 1.0
 */

@RestController
public class DefaultInfo {

    @GetMapping("/DefaultInfo")
    public String hello() {
        return """
                此接口仅用于测试服务是否启动成功</br>
                如此返回本内容说明已成功启动
                """;
    }

    /**用布尔值返回登录是否成功
     * 一个判断，是否在数据库中有该数据
     * 目前返回全部数据，不再添加其他内容，不然之后肯定要改不少地方
     * */

    @PostMapping("/login")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {

        LoginService ls = new LoginService();

        if (ls.login(userDTO.getUsername(),userDTO.getPassword())){

            UserDTO validatedUserInfo = ls.getUserDTOInfo(ls.afterLogin(userDTO.getUsername(), userDTO.getPassword()));
            validatedUserInfo.setLogged(true);
            validatedUserInfo.setUsername(userDTO.getUsername());
            validatedUserInfo.setPassword(userDTO.getPassword());
            validatedUserInfo.setId(ls.afterLogin(userDTO.getUsername(), userDTO.getPassword()));
            return new ResponseEntity<>(validatedUserInfo, HttpStatus.OK);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.UNAUTHORIZED);
    }
}
