package com.akbar.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * BCrypt加密工具类
 */
public class BCryptUtil {

    public static String generateHashPwd(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashPwd) {
        return BCrypt.checkpw(password, hashPwd);
    }
}
