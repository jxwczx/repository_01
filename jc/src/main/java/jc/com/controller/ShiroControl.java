package jc.com.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jc.com.model.User;
import jc.com.service.ChatService;

@Controller
@RequestMapping("shiro")
public class ShiroControl {
	@Resource(name="chatService")
	private ChatService chatService;
	
	@RequestMapping("toUser")
	public String toClient01(HttpServletRequest request, HttpServletResponse response) {
		return "shiro/user";
	}
//	@RequestMapping("/regesiter")
//	public void regesiter(HttpServletRequest request, HttpServletResponse response,User user) {
//		user.setId(UUID.randomUUID().toString());
//		chatService.saveUser(user);
//		System.out.println(user.getId());
//	}
	
//	@RequestMapping("/login/{password}") // url
//	public String dologin(@PathVariable(value = "password") String password) {
//		User user = new User("jxw","jxw", password);
//		String info = loginUser(user);
//		if (!"SUCC".equals(info)) {
//			return "shiro/error";
//		} else {
//			return "shiro/success";// 返回的页面
//		}
//	}

//@RequestMapping("/logout.do")
//public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
//Subject subject = SecurityUtils.getSubject();
//if (subject != null) {
//try{
//subject.logout();
//}catch(Exception ex){
//}
//}
//response.sendRedirect("/index.jsp");
//}
//	private String loginUser(User user) {
//		if (isRelogin(user))
//			return "SUCC"; // 如果已经登陆，无需重新登录
//		return shiroLogin(user); // 调用shiro的登陆验证
//	}

//	private String shiroLogin(User user) {
//		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword().toCharArray(),
//				null);
//		token.setRememberMe(true);
//// shiro登陆验证
//		try {
//			SecurityUtils.getSubject().login(token);
//		} catch (UnknownAccountException ex) {
//			return "用户不存在或者密码错误！";
//		} catch (IncorrectCredentialsException ex) {
//			return "用户不存在或者密码错误！";
//		} catch (AuthenticationException ex) {
//			return ex.getMessage(); // 自定义报错信息
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return "内部错误，请重试！";
//		}
//		return "SUCC";
//	}
//
//	private boolean isRelogin(User user) {
//		Subject us = SecurityUtils.getSubject();
//		if (us.isAuthenticated()) {
//			return true; // 参数未改变，无需重新登录，默认为已经登录成功
//		}
//		return false; // 需要重新登陆
//	}

}
