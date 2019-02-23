/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: ApartmentService
 * Author:   yuan
 * Date:     2019/2/21 9:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.service;

import com.wjysushe.bean.Apartment;
import com.wjysushe.bean.WaterElectricity;
import com.wjysushe.dao.ApartmentDao;
import com.wjysushe.dao.WaterElectricityDao;

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
public class WaterElectricityService {

	public WaterElectricity get(String id){
		String where = " and water_electricity.id="+id;
		List<WaterElectricity> waterElectricityList = new WaterElectricityDao().list(where);
		if (waterElectricityList.size()>0){
			return waterElectricityList.get(0);
		}
		return null;
	}

	public List<WaterElectricity> list(String apartmentId, String dormitoryName, Long dormitoryId){
		String where = "";
		if (notEmpty(apartmentId)){
			where += " and apartment.id=" + apartmentId + " ";
		}
		if (notEmpty(dormitoryName)){
			where += " and dormitory.name like '%" + dormitoryName + "%' ";
		}
		if (dormitoryId != null){
			where += " and dormitory.id=" + dormitoryId;
		}
		return new WaterElectricityDao().list(where);
	}


	public Boolean delete(String id){
		if (new WaterElectricityDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(String dormitoryId,String statDate,String waterNum,String waterCost,String electricityNum,String electricityCost){
		WaterElectricity waterElectricity = new WaterElectricity();
		waterElectricity.setDormitoryId(Long.valueOf(dormitoryId));
		waterElectricity.setStatDate(statDate);
		waterElectricity.setWaterNum(Float.valueOf(waterNum));
		waterElectricity.setWaterCost(Float.valueOf(waterCost));
		waterElectricity.setElectricityNum(Float.valueOf(electricityNum));
		waterElectricity.setElectricityCost(Float.valueOf(electricityCost));
		if (new WaterElectricityDao().save(waterElectricity)>0){
			return true;
		}
		return false;
	}

	public Boolean update(String id, String dormitoryId,String statDate,String waterNum,String waterCost,String electricityNum,String electricityCost){
		WaterElectricity waterElectricity = new WaterElectricity();
		waterElectricity.setId(Long.valueOf(id));
		waterElectricity.setDormitoryId(Long.valueOf(dormitoryId));
		waterElectricity.setStatDate(statDate);
		waterElectricity.setWaterNum(Float.valueOf(waterNum));
		waterElectricity.setWaterCost(Float.valueOf(waterCost));
		waterElectricity.setElectricityNum(Float.valueOf(electricityNum));
		waterElectricity.setElectricityCost(Float.valueOf(electricityCost));
		if (new WaterElectricityDao().update(waterElectricity)>0){
			return true;
		}
		return false;
	}

}