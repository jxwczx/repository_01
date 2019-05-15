 package jc.com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jc.com.model.User;
import jc.com.utils.ResultUtil;

@Transactional
@Repository("userDao")
public class UserDao {
	@Resource(name="jdbcTempLate")
	private JdbcTemplate jdbcTempLate;
	
//	public JdbcTemplate getJdbcTempLate() {
//		return jdbcTempLate;
//	}
//
//	public void setJdbcTempLate(JdbcTemplate jdbcTempLate) {
//		this.jdbcTempLate = jdbcTempLate;
//	}
	
	public void test() {
        String sql = "select * from chat_user ";
        List<Map<String,Object>> users = jdbcTempLate.queryForList(sql);
        System.out.println(users.size());
    }
	
	/**
	 * 朋友列表
	 * 
	 * */
	public List<User> getUserList(String loginName){
		String sql = "select * from chat_user where loginName <> ?";
		List<Map<String,Object>> list = jdbcTempLate.queryForList(sql,new Object[]{loginName});
		User user = null;
		List<User> lists = new ArrayList<User>();
		for (Map<String, Object> map : list) {
			user = new User();
			user.setId(map.get("id").toString());
			user.setName(map.get("name").toString());
			user.setLoginName(map.get("LoginName").toString());
			user.setPassword(map.get("password").toString());
			user.setSalt(map.get("salt").toString());
			
			lists.add(user);
		}
		return lists;
	}
	public User getUser(String loginName){
		String sql = "select * from chat_user where loginName = ? ";
		List<Map<String,Object>> list = jdbcTempLate.queryForList(sql,new Object[]{loginName});
		if(list.size()>1) {
			throw new RuntimeException("账号存在异常，请联系管理员！");
		}
		User user = null;
		for (Map<String, Object> map : list) {
			user = new User();
			user.setId(map.get("id").toString());
			user.setName(map.get("name").toString());
			user.setLoginName(map.get("LoginName").toString());
			user.setPassword(map.get("password").toString());
			user.setSalt(map.get("salt").toString());
		}
		return user;
	}
	
	/**
	 * 保存user
	 * 
	 * */
	public ResultUtil saveUser(User user) {
		if(user==null) {
			return new ResultUtil(false,"对象不能为空！");
		}
		
		if(user.getLoginName()==null||user.getLoginName().equals("")) {
			return new ResultUtil(false,"用户名不能为空！");
		}
		
		User user_source = getUser(user.getLoginName());
		if(user_source!=null&&user_source.getLoginName()!=null&&user_source.getLoginName().equals(user.getLoginName())) {
			return new ResultUtil(false,"用户名已存在！");
		}
		
		String sql = "insert into chat_user (id,name,loginName,password,salt) values(?,?,?,?,?)";
		int num = this.jdbcTempLate.update(sql, new Object[] {user.getId(),user.getName(),user.getLoginName(),user.getPassword(),user.getSalt()});
		if(num>0) {
			return new ResultUtil(true,"注册成功！");
		}
		return new ResultUtil(true,"注册失败！");
	}
	
}
