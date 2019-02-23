/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: UserDao
 * Author:   yuan
 * Date:     2019/2/20 12:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.dao;

import com.wjysushe.bean.User;
import com.wjysushe.utils.DBHelper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/20
 * @since 1.0.0
 */
public class UserDao {

    public List<User> list(String where){
        String sql = "select * from user where 1=1 ";
        if (where != null){
            sql += where;
        }
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<User> userList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            User user = new User();
            user.setId((Long) item.get("id"));
            user.setRole((Integer) item.get("role"));
            if (user.getRole() == 1){
                user.setRoleName("系统管理员");
            }else if (user.getRole() == 2){
                user.setRoleName("公寓管理员");
            }else if (user.getRole() == 3){
                user.setRoleName("学生");
            }
            user.setSno((String) item.get("sno"));
            user.setName((String) item.get("name"));
            user.setPassword((String) item.get("password"));
            user.setCreateTime((Date) item.get("create_time"));
            user.setUpdateTime((Date) item.get("last_update_time"));
            userList.add(user);
        }
        return userList;
    }

    public Integer save(User user){
        String sql = "insert into user (role, sno, name, password) values (?, ?, ?, ?)";
        int ret = new DBHelper().save(sql, user.getRole(), user.getSno(), user.getName(), user.getPassword());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from user where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(User user){
        String sql = "update user set role=?, sno=?, name=? where id=?";
        int ret = new DBHelper().update(sql, user.getRole(), user.getSno(), user.getName(), user.getId());
        return ret;
    }

    public Integer resetPassword(String userId, String password){
        String sql = "update user set password=? where id=?";
        int ret = new DBHelper().update(sql, password, userId);
        return ret;
    }


    public List<User> listMember(String dormitoryId){
        String sql = "select user.* from user,dormitory_student where dormitory_student.student_id=user.id and dormitory_student.dormitory_id="+dormitoryId;
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<User> userList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            User user = new User();
            user.setId((Long) item.get("id"));
            user.setRole((Integer) item.get("role"));
            user.setSno((String) item.get("sno"));
            user.setName((String) item.get("name"));
            userList.add(user);
        }
        return userList;
    }

    public Integer saveDormitoryMember(Long dormitoryId, String studentId){
        String sql = "insert into dormitory_student (dormitory_id, student_id) values (?, ?)";
        int ret = new DBHelper().save(sql, dormitoryId, studentId);
        return ret;
    }

    public Integer deleteDormitoryMember(Long dormitoryId){
        String sql = "delete from dormitory_student where dormitory_id=" + dormitoryId;
        int ret = new DBHelper().delete(sql);
        return ret;
    }
}