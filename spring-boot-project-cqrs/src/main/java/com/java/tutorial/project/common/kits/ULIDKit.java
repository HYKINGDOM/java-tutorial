package com.java.tutorial.project.common.kits;

import com.github.f4b6a3.ulid.UlidCreator;
import org.hashids.Hashids;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 生成全局唯一ID
 *
 */
public final class ULIDKit {

	private static String alphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^*()|<>";

	public static String create() {
		return UlidCreator.getUlid().toString();
	}

	public static String createToken(Long userId) {
		String ulid = create();
		Hashids hashids = new Hashids(ulid, 20, alphabet);

		return hashids.encode(userId);
	}

	public static String encryptData(String data, String salt) {
		Hashids hashids = new Hashids(salt, 0, alphabet);
		long[] numbers = data.chars().asLongStream().toArray();
		int length = numbers.length;
		long number = 0L;
		List<String> list = null;

		StringBuilder str = new StringBuilder(length * 3 + 16);
		for (int i = 0; i < length; ++i) {
			number = numbers[i];
			if (number == -1) {
				continue;
			}
			list = new ArrayList<>();
			list.add(String.valueOf(number));
			list.add(String.valueOf(i));
			for (int j = i + 1; j < length; ++j) {
				if (number == numbers[j]) {
					numbers[j] = -1;
					list.add(String.valueOf(j));
				}
			}
			str.append(hashids.encode(list.stream().mapToLong(Long::parseLong).toArray())).append("-");
		}
		str.append(hashids.encode((long) length));

		return str.toString();
	}

	public static String decryptData(String data, String salt) {
		int codePoint = 0;
		Hashids hashids = new Hashids(salt, 0, alphabet);
		String[] array = data.split("-");
		int size = array.length - 1;
		long[] numbers = hashids.decode(array[size]);
		int[] codePoints = new int[(int) numbers[0]];
		for (int i = 0; i < size; ++i) {
			numbers = hashids.decode(array[i]);
			codePoint = (int) numbers[0];
			for (int j = 1, length = numbers.length; j < length; ++j) {
				codePoints[(int) numbers[j]] = codePoint;
			}
		}

		return new String(codePoints, 0, size);
	}

}
