package com.module.common.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
 * @description:    hash加密
 * @author:         sanduo
 * @date:           2020/2/20 13:04
 * @version:        1.0
 */
public class HashUtils {
  

  private static final HashFunction FUNCTION = Hashing.sha256();
  
  private static final HashFunction MURMUR_FUNC = Hashing.murmur3_128();
  
  private static final String  SALT = "sd.wlw.com";

  /**
   * 密码加密
   * @param password
   * @return
   */
  public static String encryptPassword(String password){
    HashCode code = FUNCTION.hashString(password+SALT, Charset.forName("UTF-8"));
    return code.toString();
  }

  /**
   *  加密成hashcode
   * @param input
   * @return
   */
  public static String hashString(String input){
    HashCode code = null;
      code = MURMUR_FUNC.hashBytes(input.getBytes(StandardCharsets.UTF_8));
      return code.toString();
  }
  
  //public static void main(String[] args) {
  //  System.out.println(encryPassword("session_secret"));
  //}
}
