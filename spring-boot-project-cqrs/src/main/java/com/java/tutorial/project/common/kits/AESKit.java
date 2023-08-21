package com.java.tutorial.project.common.kits;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * AES加密
 *
 */
public final class AESKit {

	/**
	 * 采用AES加密算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	/**
	 * 字符编码(要注意new String()默认使用UTF-8编码 getBytes()默认使用ISO8859-1编码)
	 */
	private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/*
	 * AES加密
	 * 1.构造密钥生成器
	 * 2.根据ecnodeRules规则初始化密钥生成器
	 * 3.产生密钥
	 * 4.创建和初始化密码器
	 * 5.内容加密
	 * 6.返回字符串
	 */
	public static String encode(String key, String data) {
		try {
			// 创建AES秘钥
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET_UTF8), KEY_ALGORITHM);
			// 创建密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 初始化加密器
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encryptByte = cipher.doFinal(data.getBytes(CHARSET_UTF8));

			// 将加密以后的数据进行 Base64 编码
			return new String(Base64.getEncoder().encode(encryptByte));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * AES解密 解密过程：
	 * 1.同加密1-4步
	 * 2.将加密后的字符串反纺成byte[]数组
	 * 3.将加密内容解密
	 */
	public static String decode(String key, String content) {
		try {
			byte[] data = Base64.getDecoder().decode(content);
			// 创建AES秘钥
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET_UTF8), KEY_ALGORITHM);
			// 创建密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 初始化解密器
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			// 执行解密操作
			byte[] result = cipher.doFinal(data);

			return new String(result, CHARSET_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
