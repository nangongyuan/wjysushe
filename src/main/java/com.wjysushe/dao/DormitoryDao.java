/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: DormitoryDao
 * Author:   yuan
 * Date:     2019/2/21 9:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.dao;

import com.wjysushe.bean.Dormitory;
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
 * @create 2019/2/21
 * @since 1.0.0
 */
public class DormitoryDao {

    public List<Dormitory> list(String where){
        String sql = "select dormitory.*, apartment.name as apartment_name from dormitory,apartment where dormitory.apartment_id=apartment.id ";
        if (where != null){
            sql += where;
        }
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<Dormitory> dormitoryList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            Dormitory dormitory = new Dormitory();
            dormitory.setId((Long) item.get("id"));
            dormitory.setApartmentId((Long) item.get("apartment_id"));
            dormitory.setApartmentName((String) item.get("apartment_name"));
            dormitory.setName((String) item.get("name"));
            dormitory.setCreateTime((Date) item.get("create_time"));
            dormitory.setLastUpdateTime((Date) item.get("last_update_time"));
            List<User> member = new UserDao().listMember(String.valueOf(dormitory.getId()));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<member.size(); i++){
                stringBuilder.append(member.get(i).getName());
                if (i != member.size()-1){
                    stringBuilder.append(",");
                }
            }
            dormitory.setMember(stringBuilder.toString());
            dormitory.setMemberUser(member);
            dormitoryList.add(dormitory);
        }
        return dormitoryList;
    }

    public Integer save(Dormitory dormitory){
        String sql = "insert into dormitory (apartment_id, name) values (?, ?)";
        int ret = new DBHelper().save(sql, dormitory.getApartmentId(), dormitory.getName());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from dormitory where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(Dormitory dormitory){
        String sql = "update dormitory set apartment_id=?, name=? where id=?";
        int ret = new DBHelper().update(sql, dormitory.getApartmentId(), dormitory.getName(), dormitory.getId());
        return ret;
    }

    public Long getDormitoryIdByUserId(Long userId){
        String sql = "select * from dormitory_student where dormitory_student.student_id="+userId;
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        if (mapList==null || mapList.size()<1){
            return 0L;
        }
        return (Long) mapList.get(0).get("dormitory_id");
    }
}