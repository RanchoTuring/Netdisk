package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encoder {


    public static String encodeBase64(String data) {
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] dataEncode= md.digest(data.getBytes());

        return Base64.getEncoder().encodeToString(dataEncode);
    }

}
