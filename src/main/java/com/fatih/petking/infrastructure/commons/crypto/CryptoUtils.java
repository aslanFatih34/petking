package com.fatih.petking.infrastructure.commons.crypto;

import com.fatih.petking.infrastructure.exception.CryptoDecryptException;
import com.fatih.petking.infrastructure.exception.CryptoEncryptException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoUtils {

    private static final String SALT = "Xj(5G_Bm&47}FR+m"; //TODO will get from properties with cipher form

    public static String encrypt(String value, String salt) {
        if (value == null) {
            return null;
        } else {
            try {
                Key key = new SecretKeySpec(salt.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(1, key);
                return Base64.encodeBase64String(cipher.doFinal(value.getBytes()));
            } catch (Exception var4) {
                throw new CryptoEncryptException("Cannot encrypt string", var4);
            }
        }
    }

    public static String encrypt(String value) {
        return encrypt(value, SALT);
    }

    public static String decrypt(String value, String salt) {
        if (value == null) {
            return null;
        } else {
            try {
                Key key = new SecretKeySpec(salt.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(2, key);
                return new String(cipher.doFinal(Base64.decodeBase64(value)));
            } catch (Exception var4) {
                throw new CryptoDecryptException("Cannot decrypt string", var4);
            }
        }
    }

    public static String decrypt(String value) {
        return decrypt(value, SALT);
    }
}
