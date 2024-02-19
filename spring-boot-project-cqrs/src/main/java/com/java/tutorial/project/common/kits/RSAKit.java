package com.java.tutorial.project.common.kits;

import com.java.tutorial.project.common.exception.SystemException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * RSA加密
 */
public final class RSAKit {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    private static final String privateKeyStr =
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCUdxvZueQmFQBwUcSw89JHWqaxI2Strss9W73RCwJXGhFZNbrjOaRXfiZfe6B025W6EGg8p4sIHSbwN2DwYhV9Dh3CbyfEMMwNlK5FNib4zdzbaemEZBek69ZITfy/lNErcWCNT1yrZqwjhDsPbQmFrv1q2eDxYU+RCLTo6frOLfbUl8rxZ+iFxYMZ0BK1Q0QHudbhBgnRM6tq8tiyfLlC4tjSLF2I7uSSUuA9CEYUeXyB1V2k/EB1szNAQdmxSItPe4S5nmrUs8KH47OO26EoKHAMB7HU3BLcLM4yydFUkWAPivNgwsuaR7weRiYjvDtzzNpOZHzqiPiAHDTSjTvxAgMBAAECggEAEy7gtl1CkXkdOWYXWBXpEbcw2qqME7UGJcN4303g24hOvYwnvbxw860lIClFLlRBIG2GSnALktzRbzB4ohbKWAI+7iSWqs7zTr3rVp4MoxFWfDNPLQAR3glrAqSxB8Ae1tIre5bJjowRqfUn/HJGTLOzot7MJeRA96h8V6wD8Cfl0FvJzB5r3D6A+LHQzEP6ytT8tmc6g+FXsyrZPqoFSlA//xGufgg4UvLWPF3SDnFqwyaUuY1h4MCRohbA8ytsbx7jbYVp99oUfp6pKbf6oJc0zkP2PwB0cmcNZ1B8QdDoS8WJhfbIyeBhxm965IrsIOIE0fd9Mi1T9aPwKSbowQKBgQDMleWybFm2ZnCVMJ8tJgrVw/VXPVcSnD5FtkPgWUlHsNUp+fsTA3iglYl/Ki6/2hKZVAWzjc8wUWaui755H/P20NJjXpN7w+evOdh9mHiY6/q6Jpr0VzxO+RJlBdRliqnUqZQjVlEnSCSW2OBYD3ZwLjyon9vzfKIfcQwg9G2tFQKBgQC5xrBWb8QRojkUY7m9WWKKmOfxkei9jVwTdfeDXg9ou5r5t9rILAQKVLmfftWzzygXKO7867Mv4XlPS+aQwktH3y4SWTQQZyMCt4RDnZe+0Nyl47VmsoIIuny6uqBsUuDUar7n4NYMTA48Z1cDC0TtYPLRbNx8E7xWnG2eqFbibQKBgFAglWogTbLGxGlqNa21LKE0sq6Mc8HyN+GN1V/VGIJfm91o7xEvVttuNf/8QXsaqhYrdmAwtmkyOIk8akl+XkrY1s3ciN78h411lnj6cqFSK00lOaNhV1mG69FtSxbKDGIiWMbOU9KSyuHZ52QWCkk9krxof/kJY2X1HM77bMlRAoGAchCDjt7a5PTU1ScSESeDsJOHvjQXUeMsiKpiyZ8EGQsvcWoog8U6ydkRi9OFhPyJoga5DkPt5lUrdbulmnYhYMSwdgWdSQD6Zx2ksRte44M8JwL0C1VJL/DIyqHyA/CzKNlN0/OhmTVNfFr6+tx9er6O+HU9v1Uiy48KY2L6zdUCgYEAmgJ4Fnzis0+v07uwK+i0ipWJBmx9hVIlUSsh4FEw2SbPXV7pCJDQbWKj0uq9rV0GaR1m2iHc/d3nFlPLOgKNY2AmNTy+iPMpIgU2beB54ZtYbKAy8RZnxfqy+u41yy6T/RLYoLa+hzktRa+nDLdyI86OxYMx+onJUv4ru0Xmd3k=";

    public static String decode(String data) {
        try {
            final RSAPrivateKey privateKey = getPrivateKey(privateKeyStr);

            return privateDecrypt(data, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        throw new SystemException("解码异常");
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));

        return (RSAPublicKey)keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串
     */
    public static RSAPrivateKey getPrivateKey(String privateKey)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));

        return (RSAPrivateKey)keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 公钥加密
     *
     * @param data      待加密的数据
     * @param publicKey 公钥
     * @return 经过Base64编码过密文的字符串
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return new String(Base64.getEncoder().encode(cipher.doFinal(data.getBytes(CHARSET))));
        } catch (Exception e) {
            throw new SystemException("加密字符串时遇到异常");
        }
    }

    /**
     * 私钥解密
     *
     * @param data       待解密的数据
     * @param privateKey 私钥
     * @return 明文字符串
     */
    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))));
        } catch (Exception e) {
            throw new SystemException("解密字符串时遇到异常");
        }
    }

}
