/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: ApartmentDao
 * Author:   yuan
 * Date:     2019/2/21 9:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.dao;

import com.wjysushe.bean.Apartment;
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
public class ApartmentDao {


    public List<Apartment> list(String where){
        String sql = "select * from apartment where 1=1 ";
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<Apartment> apartmentList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            Apartment apartment = new Apartment();
            apartment.setId((Long) item.get("id"));
            apartment.setManagerId((Long) item.get("manager_id"));
            apartment.setName((String) item.get("name"));
            apartment.setCreateTime((Date) item.get("create_time"));
            apartment.setLastUpdateTime((Date) item.get("last_update_time"));
            apartmentList.add(apartment);
        }
        return apartmentList;
    }

    public Integer save(Apartment apartment){
        String sql = "insert into apartment (manager_id, name) values (?, ?)";
        int ret = new DBHelper().save(sql, apartment.getManagerId(), apartment.getName());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from apartment where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(Apartment apartment){
        String sql = "update apartment set manager_id=?, name=? where id=?";
        int ret = new DBHelper().update(sql, apartment.getManagerId(), apartment.getName(), apartment.getId());
        return ret;
    }
}