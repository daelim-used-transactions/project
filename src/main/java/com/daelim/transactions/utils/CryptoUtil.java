package com.daelim.transactions.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CryptoUtil {

    public String sha256(String msg)  throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return byteToHexString(md.digest());
    }

    /**
     * 바이트 배열을 HEX 문자열로 변환한다.
     * @param data
     * @return
     */
    public  String byteToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for(byte b : data) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            //16진수의 값을 스트링으로 바꾸고 자름
        }
        return sb.toString();
    }

}
