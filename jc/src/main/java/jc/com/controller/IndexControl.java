package jc.com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import jc.com.model.User;
import jc.com.service.ChatService;
import jc.com.utils.ResultUtil;
import jc.com.utils.ShiroUtil;

@Controller
@RequestMapping("index")
public class IndexControl extends BaseControl{
	@Resource(name="chatService")
	private ChatService chatService;
	
	/**
	 * 首页 登陆
	 * @throws IOException 
	 * */
	@RequestMapping("/login")
	public void regesiter(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String loginName = (String) request.getSession().getAttribute("loginName");
		String userId = null;
		if(loginName!=null&&!loginName.equals("")) {
			User user = chatService.getUser(loginName);
			if(ShiroUtil.isRelogin(user)) {//检查用户是否登陆
				userId = user.getId();
			}
		}
		writeString(response, initHtml(userId));
	}
	
	
	
	/**
	 * 用户 注册 
	 * @throws IOException 
	 * */
	@RequestMapping("/regesiter")
	public void regesiter(HttpServletRequest request, HttpServletResponse response,User user) throws IOException {
		user.setId(UUID.randomUUID().toString());
		String salt = RandomStringUtils.randomAlphabetic(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtil.encrypt("MD5", user.getPassword(), salt, 3));
		ResultUtil ru = chatService.saveUser(user);
		this.writeString(response,ru);
	}
	
	/**
	 * 用户登陆
	 * 
	 * */
	@RequestMapping("/userLogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response,String loginName,String password) throws IOException {
		User user = chatService.getUser(loginName);
		ResultUtil ru = null;
		if(user==null) {
			ru = new ResultUtil(false,"用户名不存在！");
		}else {
//			if(ShiroUtil.isRelogin(user)) {
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("userId", user.getId());
//				map.put("loginName", user.getLoginName());
//				map.put("notice","用户已登陆！");
//				ru = new ResultUtil(true,JSON.toJSONString(map));
//				
//			}else {
				UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), password.toCharArray());
				token.setRememberMe(true);
				//shiro登陆验证
				try {
					SecurityUtils.getSubject().login(token);
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", user.getId());
					map.put("loginName", user.getLoginName());
					map.put("notice","登陆成功！");
					String loginName_Session = (String)request.getSession().getAttribute("loginName");
					if(loginName_Session==null) {
						request.getSession().setAttribute("loginName", user.getLoginName());
					}
					
					//传输朋友列表数据
					List<User> users = chatService.getUserList(loginName);
					map.put("users", users);
					
					ru = new ResultUtil(true,JSON.toJSONString(map));
				} catch (UnknownAccountException ex) {
					ru = new ResultUtil(false,"用户不存在或者密码错误！");// 自定义报错信息
				} catch (IncorrectCredentialsException ex) {
					ru = new ResultUtil(false,"用户不存在或者密码错误！");
				} catch (AuthenticationException ex) {
					ru = new ResultUtil(false,ex.getMessage());// 自定义报错信息
				} catch (Exception ex) {
					ex.printStackTrace();
					ru = new ResultUtil(false,"内部错误，请重试！");
				}
			}
//		}
		this.writeString(response,ru);
	}
	
	/**
	 * 退出登陆
	 * 
	 * */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		ResultUtil ru = null;
		if (subject != null) {
			try{
				subject.logout();
				request.getSession().removeAttribute("loginName");
				ru = new ResultUtil(true,"已退出登陆！");
			}catch(Exception ex){
				ex.printStackTrace();
				ru = new ResultUtil(false,"内部错误，请重试！");
			}
		}else {
			ru = new ResultUtil(false,"");
		}
		this.writeString(response,ru);
	}
	
	
	
}
