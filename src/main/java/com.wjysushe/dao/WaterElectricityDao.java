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
import com.wjysushe.bean.WaterElectricity;
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
public class WaterElectricityDao {

    public List<WaterElectricity> list(String where){
        String sql = "select water_electricity.*,apartment.name as apartmentName,dormitory.name as dormitoryName from " +
                "water_electricity,dormitory,apartment where water_electricity.dormitory_id=dormitory.id and dormitory.apartment_id=apartment.id";
        if (where != null){
            sql += where;
        }
        List<Map<String, Object>> mapList = new DBHelper().query(sql,null);
        List<WaterElectricity> waterElectricityList = new LinkedList<>();
        for (Map<String, Object> item : mapList) {
            WaterElectricity waterElectricity = new WaterElectricity();
            waterElectricity.setId((Long) item.get("id"));
            waterElectricity.setApartmentName((String) item.get("apartmentName"));
            waterElectricity.setDormitoryId((Long) item.get("dormitory_id"));
            waterElectricity.setDormitoryName((String) item.get("dormitoryName"));
            waterElectricity.setElectricityCost((Float) item.get("electricity_cost"));
            waterElectricity.setElectricityNum((Float) item.get("electricity_num"));
            waterElectricity.setWaterCost((Float) item.get("water_cost"));
            waterElectricity.setWaterNum((Float) item.get("water_num"));
            waterElectricity.setStatDate((String) item.get("stat_date"));
            waterElectricityList.add(waterElectricity);
        }
        return waterElectricityList;
    }

    public Integer save(WaterElectricity waterElectricity){
        String sql = "insert into water_electricity (dormitory_id, stat_date, water_num, water_cost, electricity_num, electricity_cost) values (?, ?, ?, ?, ?, ?)";
        int ret = new DBHelper().save(sql, waterElectricity.getDormitoryId(), waterElectricity.getStatDate(), waterElectricity.getWaterNum(), waterElectricity.getWaterCost(),
                waterElectricity.getElectricityNum(), waterElectricity.getElectricityCost());
        return ret;
    }

    public Integer delete(String where){
        String sql = "delete from water_electricity where 1=1 " + where;
        int ret = new DBHelper().delete(sql);
        return ret;
    }

    public Integer update(WaterElectricity waterElectricity){
        String sql = "update water_electricity set dormitory_id=?, stat_date=?, water_num=?, water_cost=?, electricity_num=?, electricity_cost=? where id=?";
        int ret = new DBHelper().update(sql, waterElectricity.getDormitoryId(), waterElectricity.getStatDate(), waterElectricity.getWaterNum(), waterElectricity.getWaterCost(),
                waterElectricity.getElectricityNum(), waterElectricity.getElectricityCost(),waterElectricity.getId());
        return ret;
    }

}