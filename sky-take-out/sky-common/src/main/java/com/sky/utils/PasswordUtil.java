package com.sky.utils;

import com.sky.constant.PasswordConstant;
// 加密算法库
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {

    public static String encryptPassword(String password) {
        return DigestUtils.sha256Hex(password + PasswordConstant.SALT);
    }
}
