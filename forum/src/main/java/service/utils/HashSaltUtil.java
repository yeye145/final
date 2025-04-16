package service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashSaltUtil {

    /*--------------------------------------------   生成哈希密码   --------------------------------------------*/
    public static String creatHashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }



    /*--------------------------------------------   验证哈希密码   --------------------------------------------*/
    public static boolean verifyHashPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
