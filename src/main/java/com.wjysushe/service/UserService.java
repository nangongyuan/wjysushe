/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: UserService
 * Author:   yuan
 * Date:     2019/2/21 9:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.service;

import com.wjysushe.bean.User;
import com.wjysushe.dao.UserDao;

import java.util.List;

import static com.wjysushe.utils.StringUtil.notEmpty;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/21
 * @since 1.0.0
 */
public class UserService {

	public static final String SESSION_USER = "sessionUser";

	public User login(String sno, String password){
		String where = "and sno = " + sno + " and password = " + password;
		List<User> userList = new UserDao().list(where);
		if (userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	public Integer resetPassword(String userId, String password, String newPassword){
		User user = get(userId);
		if (user.getPassword().equals(password)){
			int ret = new UserDao().resetPassword(userId, newPassword);
			if (ret > 0){
				return 0;
			}else{
				return 1;
			}
		}
		return 2;
	}

	public User get(String id){
		String where = "and id="+id;
		List<User> userList = new UserDao().list(where);
		if (userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	public List<User> list(String role, String name){
		String where = "";
		if (notEmpty(role)){
			where += " and role=" + role + " ";
		}
		if (notEmpty(name)){
			where += " and name like '%" + name + "%' ";
		}
		return new UserDao().list(where);
	}

	public List<User> listByRole(Long role){
		String where = " and role=" + role;
		return new UserDao().list(where);
	}

	public Boolean delete(String id){
		if (new UserDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(String role, String sno, String name){
		User user = new User();
		user.setRole(Integer.valueOf(role));
		user.setName(name);
		user.setSno(sno);
		user.setPassword("123456");
		if (new UserDao().save(user)>0){
			return true;
		}
		return false;
	}

	public Boolean update(String id, String role, String sno, String name){
		User user = new User();
		user.setId(Long.valueOf(id));
		user.setRole(Integer.valueOf(role));
		user.setName(name);
		user.setSno(sno);
		if (new UserDao().update(user)>0){
			return true;
		}
		return false;
	}

}