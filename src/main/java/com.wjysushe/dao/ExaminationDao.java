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

import com.wjysushe.bean.Examination;
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
public class ExaminationDao {

    public List<Examination> list(String where){
        String sql = "select dormitory_examination.*,apartment.name as apartmentName,dormitory.name as dormitoryName  from dormitory_examination,dormitory,apartment " +
				"where dormitory_examination.dormitory_id=dormitory.id and dormitory.apartment_id=apartment.id ";
        if (where != null){
            sql += where;
        }
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<Examination> examinationList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
			Examination examination = new Examination();
			examination.setId((Long) item.get("id"));
			examination.setExaminationDate((String) item.get("examination_date"));
			examination.setDormitoryId((Long) item.get("dormitory_id"));
			examination.setPeopleNumber((Long) item.get("people_number"));
			examination.setRealNumber((Long) item.get("real_number"));
			examination.setStudentNames((String) item.get("student_names"));
			examination.setDormitoryName((String) item.get("dormitoryName"));
			examination.setApartmentName((String) item.get("apartmentName"));
			examinationList.add(examination);
        }
        return examinationList;
    }

    public Integer save(Examination examination){
        String sql = "insert into dormitory_examination (examination_date, dormitory_id, people_number, real_number, student_names) values (?, ?, ?, ?, ?)";
        int ret = new DBHelper().save(sql, examination.getExaminationDate(), examination.getDormitoryId(), examination.getPeopleNumber(), examination.getRealNumber(),
				examination.getStudentNames());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from dormitory_examination where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(Examination examination){
        String sql = "update dormitory_examination set examination_date=?, dormitory_id=?, people_number=?, real_number=?, student_names=? where id=?";
        int ret = new DBHelper().update(sql, examination.getExaminationDate(), examination.getDormitoryId(), examination.getPeopleNumber(), examination.getRealNumber(),
				examination.getStudentNames(), examination.getId());
        return ret;
    }

}