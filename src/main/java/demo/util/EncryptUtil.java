package demo.util;


import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import demo.constant.ErrorCode;
import demo.exception.BizException;


public class EncryptUtil {
    private static final String ENCRYPT_TYPE = "AES";

    private static final String AES_ENCRYPT_MODE = "AES/CFB/NoPadding";

    private static final String CODING_TYPE = "utf-8";

    private static final String KEY = "1234567891234567";

    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * SHA512算法
     *
     * @param text 输入字符串
     * @return 结果字符串
     */
    public static String sha512(String text) {
        String result;
        try {
            // 创建加密对象，传入加密类型
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            // 传入加密字符串
            messageDigest.update(text.getBytes());
            // 得到byte类型结果
            byte[] bytes = messageDigest.digest();
            //将byte转换为string
            StringBuilder stringBuffer = new StringBuilder();
            // 遍历
            for (byte abyte : bytes) {
                String hex = Integer.toHexString(0xff & abyte);
                if (hex.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hex);
            }
            result = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        return result;
    }

    /**
     * 生成salt
     *
     * @return salt
     */
    public static String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[24];
        secureRandom.nextBytes(bytes);
        return encodeBase64String(bytes);
    }

    /**
     * 加密密码
     *
     * @param password password
     * @param salt     salt
     * @return encryptedPassword
     */
    public static String encryptPassword(String password, String salt) {
        String formPassword = sha512(password);
        String slatPassword = formPassword + salt;
        return sha512(slatPassword);
    }

    /**
     * AESjiemie解密
     *
     * @param bytes 待解密数组
     * @return 解密结果
     */
    public static String aesDecrypt(byte[] bytes) {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(ENCRYPT_TYPE);
        } catch (NoSuchAlgorithmException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        secureRandom.setSeed(KEY.getBytes());
        keyGenerator.init(128, secureRandom);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(AES_ENCRYPT_MODE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        try {
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(keyGenerator.generateKey().getEncoded(), ENCRYPT_TYPE), ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        byte[] decryptBytes;
        try {
            decryptBytes = cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        return new String(decryptBytes);
    }

    /**
     * AES加密
     *
     * @param content 待加密内容
     * @return 加密后的byte[]
     */
    public static byte[] aesEncrypt(String content) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(ENCRYPT_TYPE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        secureRandom.setSeed(KEY.getBytes());
        keyGenerator.init(128, secureRandom);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(AES_ENCRYPT_MODE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        try {
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(keyGenerator.generateKey().getEncoded(), ENCRYPT_TYPE), ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        byte[] bytes;
        try {
            bytes = cipher.doFinal(content.getBytes(CODING_TYPE));
        } catch (IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
        return bytes;
    }

    /**
     * base64加密
     *
     * @param bytes 待加密字符数组
     * @return result
     */
    public static String base64Encoding(byte[] bytes) {
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    /**
     * base64解密
     *
     * @param coding 待解密字符串
     * @return 解密后byte数组
     */
    public static byte[] base64Decoding(String coding) {
        try {
            return Base64.getUrlDecoder().decode(coding);
        } catch (IllegalArgumentException e) {
            throw new BizException(ErrorCode.SERVICE_INTERNAL_ERROR);
        }
    }
}
