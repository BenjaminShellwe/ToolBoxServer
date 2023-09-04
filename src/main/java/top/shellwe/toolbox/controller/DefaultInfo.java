package top.shellwe.toolbox.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
