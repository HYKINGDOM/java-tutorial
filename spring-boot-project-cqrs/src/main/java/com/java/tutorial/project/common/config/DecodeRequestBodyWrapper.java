package com.java.tutorial.project.common.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.java.tutorial.project.common.constant.HeaderConstant;
import com.java.tutorial.project.common.kits.AESKit;
import com.java.tutorial.project.common.kits.RSAKit;
import com.java.tutorial.project.common.kits.StrKit;
import org.springframework.util.StreamUtils;


import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 *
 * 使用AES解密Request Body
 *
 */
public final class DecodeRequestBodyWrapper extends HttpServletRequestWrapper {

	// 已经解密完成的InputStream在后续处理中使用
	private ServletInputStream decodeInputStream;

	/**
	 * 解密请求信息
	 */
	public DecodeRequestBodyWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	/**
	 * 1、拿到request body的 input stream
	 * 2、将输入流转成字符串（用AES加密的密文字符串）
	 * 3、拿到头信息里的AES秘钥（用RSA加密的AES秘钥）
	 * 4、用RSA解密AES秘钥，获得AES秘钥明文
	 * 5、用AES秘钥解密密文body
	 * 6、将明文转成ServletInputStream
	 */
	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ServletInputStream inputStream = super.getInputStream();
		if (Objects.isNull(inputStream)) {
			return null;
		}
		String ciphertextBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
		final String aesSign = getHeader(HeaderConstant.AES_SIGN);
		final String aseKey = RSAKit.decode(aesSign);
		final String plaintextBody = AESKit.decode(aseKey, ciphertextBody);
		if (StrKit.notBlank(plaintextBody)) {
			final ByteArrayInputStream stream = new ByteArrayInputStream(
					plaintextBody.getBytes(StandardCharsets.UTF_8));
			decodeInputStream = new ServletInputStream() {
				private boolean finished = false;

				@Override
				public boolean isFinished() {
					return finished;
				}

				@Override
				public int available() throws IOException {
					return stream.available();
				}

				@Override
				public void close() throws IOException {
					super.close();
					stream.close();
				}

				@Override
				public boolean isReady() {
					return true;
				}

				@Override
				public void setReadListener(ReadListener readListener) {
					throw new UnsupportedOperationException();
				}

				public int read() throws IOException {
					int data = stream.read();
					if (data == -1) {
						finished = true;
					}
					return data;
				}
			};
		}

		return decodeInputStream;
	}

}
