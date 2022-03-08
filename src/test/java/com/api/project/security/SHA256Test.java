package com.api.project.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class SHA256Test {

    @Test
    void 암호화테스트() {
        SHA256 sha256 = new SHA256();
        try {
            String encrypt = sha256.encrypt("1234");
            System.out.println(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}