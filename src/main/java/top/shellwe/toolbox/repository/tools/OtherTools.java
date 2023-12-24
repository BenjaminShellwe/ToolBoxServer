package top.shellwe.toolbox.repository.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherTools {
    public static boolean checkString(String input) {
        // 正则表达式匹配包含大小写字母、数字和符号的字符串
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,45}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
//    public static void main(String[] args) {
//
//        String testPassword1 = "Abcdef123@";
//        String testPassword2 = "WeakPass";
//
//        System.out.println(checkString(testPassword1)); // Output: true
//        System.out.println(checkString(testPassword2)); // Output: false
//    }
}
