package jc.com.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

import jc.com.model.User;

public class ShiroUtil {
	/**
	 *  利用shiro的加盐加密
	 * @param encrytType 加密方式 MD5 。。。等等
	 * @param source 明文密码
	 * @param salt 盐
	 * @param encrytNum 加密次数
	 * 
	 * */
	public static String encrypt(String encrytType, String source, String salt,int encrytNum){
		SimpleHash sh = new SimpleHash(encrytType, source, salt, 3);
		return sh.toString();
	}
	
	/**
	 * 判断用户是否已经登陆
	 * 
	 * */
	public static boolean isRelogin(User user) {
		Subject us = SecurityUtils.getSubject();
		if (us.isAuthenticated()) {
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}
}
