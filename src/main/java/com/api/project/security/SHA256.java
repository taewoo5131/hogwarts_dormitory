package com.api.project.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * SHA-256 + Salt
 */
public class SHA256 {

    private String salt;

    /**
     * desc 암호화할때 사용한 salt를 리턴
     * @return
     */
    public String getSalt() {
        return this.salt;
    }


    /**
     * desc 암호화 비밀번호를 가져오는 메소드
     * @param param
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getEncrypt(String param) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(param.getBytes());
        return bytesToHex(md.digest());
    }

    /**
     * desc 회원가입시 SHA-256 + salt(random) 로 암호화 하는 메소드
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String joinEncrypt(String text) throws NoSuchAlgorithmException {
        // Salt 생성
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        this.salt = new String(Base64.getEncoder().encode(bytes));

        // SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(this.salt.getBytes());
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    /**
     * desc 로그인 시 SHA-256 + salt(param) 로 암호화 하는 메소드
     * @param salt
     * @param pw
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String loginEncrypt(String salt , String pw) throws NoSuchAlgorithmException {
        // SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        md.update(pw.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
