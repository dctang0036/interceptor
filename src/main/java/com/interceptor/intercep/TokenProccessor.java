package com.interceptor.intercep;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class TokenProccessor {
//    private static final Logger logger = LogManager.getLogger(TokenProccessor.class);

    private TokenProccessor() {
    }

    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    public String makeToken() {
        String token = String.valueOf(System.currentTimeMillis() + new Random().nextInt(999999999));
        try {
            return DigestUtils.md5Hex(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() + "";
    }
}
