package com.module.common.utils;

import java.util.Random;

/**
 * @description:    随机数、随机字符串工具类
 * @author:         sanduo
 * @date:           2020/2/20 13:12
 * @version:        1.0
 */
public final class RandomUtils {
	
	/** 
	 * 获得特定长度的一串随机数字
	 * @param length 长度
	 * @return   返回值
	 */
	public static String getRandomNumberStr(int length){
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < length; i++){
			builder.append(random.nextInt(10));
		}
		return builder.toString();
	}
	
	
	/**
	 * 获得特定长度的一个随机字母数字混编字符串（所包含的字符包括0-9A-Z)
	 * @param length 长度
	 * @return 返回值
	 */
	public static String getRandomAlphamericStr(int length) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < length; i++) {
			int n = random.nextInt(36);
			if(n < 10) {
				builder.append(n);
			} else {
				builder.append((char) (n + 55));
			}
		}
		return builder.toString();
	}
	
	/**
	 * 获得特定长度的一个随机十六进制数字字母混编字符串(所包含的字符包括0-9A-F)
	 * @param length
	 * @return
	 */
	public static String getRandomHexStr(int length) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < length; i++) {
			int n = random.nextInt(16);
			if(n < 10) {
				builder.append(n);
			}else {
				builder.append((char) (n + 55));
			}
		}
		return builder.toString();
	}
	

}
