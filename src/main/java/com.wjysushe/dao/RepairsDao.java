/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: RepairsDao
 * Author:   yuan
 * Date:     2019/2/21 9:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.dao;

import com.wjysushe.bean.Dormitory;
import com.wjysushe.bean.Repairs;
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
public class RepairsDao {

    public List<Repairs> list(String where){
        String sql = "select * from repairs where 1=1 ";
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<Repairs> repairsList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            Repairs repairs = new Repairs();
            repairs.setId((Long) item.get("id"));
            repairs.setUserId((Long) item.get("user_id"));
            repairs.setDormitoryId((Long) item.get("dormitory_id"));
            repairs.setExplanation((String) item.get("explanation"));
            repairs.setReply((String) item.get("reply"));
            repairs.setStatus((Integer) item.get("status"));
            repairs.setCreateTime((Date) item.get("create_time"));
            repairs.setLastUpdateTime((Date) item.get("last_update_time"));
            repairsList.add(repairs);
        }
        return repairsList;
    }

    public Integer save(Repairs repairs){
        String sql = "insert into repairs (user_id, dormitory_id, explanation, reply, status) values (?, ?, ?, ?, ?)";
        int ret = new DBHelper().save(sql, repairs.getUserId(), repairs.getDormitoryId(), repairs.getExplanation(), repairs.getReply(), repairs.getStatus());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from repairs where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(Repairs repairs){
        String sql = "update repairs set user_id=?, dormitory_id=? , explanation=?, reply=?, status=? where id=?";
        int ret = new DBHelper().update(sql, repairs.getUserId(), repairs.getDormitoryId(), repairs.getExplanation(), repairs.getReply(), repairs.getStatus());
        return ret;
    }

}