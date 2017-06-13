package com.example.administrator.module.aes;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 目前问题：NoPadding 是解密成功的，但是加密会失败，
 * javax.crypto.IllegalBlockSizeException: error:0607F08A:digital envelope routines:EVP_EncryptFinal_ex:data not multiple of block length
 * <p>
 * 改为 PKCS5Padding 加密是成功的，但是解密是失败的。
 * javax.crypto.BadPaddingException: error:06065064:digital envelope routines:EVP_DecryptFinal_ex:bad decrypt
 * <p>
 * 说明客户端和服务端的加解密存在不一致的地方。（并非是自己写的问题，而是两种语言里封装的不是完全一样）
 */
public class Aes {

    private static IvParameterSpec iv;
    private static SecretKeySpec skeySpec;
    private static Cipher cipher;

    public static void init(String key, String initVector) {
        try {
            iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String value) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return new String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        init(ConfigConstants.key, ConfigConstants.iv);
//        System.out.println(encrypt("sdfjajfsakjfklsaj"));
//    }
}
