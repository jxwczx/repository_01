package jc.com.realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import jc.com.model.User;
import jc.com.service.ChatService;
import jc.com.utils.ShiroUtil;

public class ShiroDbRealm extends AuthorizingRealm {
	private ChatService chatService;
	public ChatService getChatService() {
		return chatService;
	}
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
	public static final String SESSION_USER_KEY = "gray";

	/**
	 *   授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.addRole(user.getRole().trim());
		return info;
	}

	/**
	 *   认证回调函数，登录信息和用户验证信息验证 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		//把token转换成User对象
		User userLogin = tokenToUser((UsernamePasswordToken) authcToken);
		User user = chatService.getUser(userLogin.getLoginName());
		if(user!=null) {
			String ps = ShiroUtil.encrypt("MD5", userLogin.getPassword(), user.getSalt(), 3);
			if(!ps.equals(user.getPassword())) {
				return null;//登陆失败！
			}
		}else {
			return null;// 异常处理，找不到数据
		}
		// 设置session
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(ShiroDbRealm.SESSION_USER_KEY, user);
		// 当前 Realm 的 name
		String realmName = this.getName();
		// 登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
		//Object principal = ui.getUsername();
		Object principal = authcToken.getPrincipal();
		return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
	}

	private User tokenToUser(UsernamePasswordToken authcToken) {
		User user = new User();
		user.setLoginName(authcToken.getUsername());
		user.setPassword(String.valueOf(authcToken.getPassword()));
		return user;
	}
}
