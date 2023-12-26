package com.fatih.petking.infrastructure.commons.crypto;

import com.fatih.petking.infrastructure.exception.CryptoDecryptException;
import com.fatih.petking.infrastructure.exception.CryptoEncryptException;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@UtilityClass
public final class CryptoUtils {
    private static final String AES = "AES";
    private static final String SECRET_KEY = "v5PTuQd6jkTsotjBNMkJdQ=="; // TODO will get property and should will cipher
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String IV = "VF9iJvFKDb4x1eKD"; // TODO will get property and should will cipher
    private static final int KEY_LENGTH = 128;

    public static String oneWayHashSha512(String value) {
        return Optional.ofNullable(Base64.getEncoder().encodeToString(DigestUtils.sha512(value))).orElse(value);
    }

    public static String encrypt(String value) {
        return encrypt(value, SECRET_KEY);
    }

    public static String decrypt(String value) {
        return decrypt(value, SECRET_KEY);
    }

    public static String encrypt(String value, String secretKey) {
        if (value == null) {
            return null;
        }

        try {
            Cipher cipher = getCipher(secretKey, Cipher.ENCRYPT_MODE);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new CryptoEncryptException("Cannot encrypt string", e);
        }
    }

    public static String decrypt(String value, String secretKey) {
        if (value == null) {
            return null;
        }

        try {
            Cipher cipher = getCipher(secretKey, Cipher.DECRYPT_MODE);
            byte[] decode = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
            byte[] plaintext = cipher.doFinal(decode);
            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new CryptoDecryptException("Cannot decrypt string", e);
        }
    }

    private Cipher getCipher(String secretKey, int decryptMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        Key key = generateAESKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(decryptMode, key, gcmParameterSpec());
        return cipher;
    }

    private static GCMParameterSpec gcmParameterSpec(){
        return new GCMParameterSpec(KEY_LENGTH, IV.getBytes());
    }

    private static SecretKey generateAESKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, AES);
    }
}
