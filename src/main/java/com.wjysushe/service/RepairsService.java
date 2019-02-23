/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: RepairsService
 * Author:   yuan
 * Date:     2019/2/21 9:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.service;

import com.wjysushe.bean.Apartment;
import com.wjysushe.bean.Repairs;
import com.wjysushe.bean.WaterElectricity;
import com.wjysushe.dao.ApartmentDao;
import com.wjysushe.dao.RepairsDao;
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
public class RepairsService {

	public Repairs get(String id){
		String where = " and repairs.id="+id;
		List<Repairs> repairsList = new RepairsDao().list(where);
		if (repairsList.size()>0){
			return repairsList.get(0);
		}
		return null;
	}

	public List<Repairs> list(String apartmentId, Long dormitoryId){
		String where = "";
		if (notEmpty(apartmentId)){
			where += " and apartment.id=" + apartmentId + " ";
		}
		if (dormitoryId != null){
			where += " and dormitory.id=" + dormitoryId + " ";
		}
		return new RepairsDao().list(where);
	}


	public Boolean delete(String id){
		if (new RepairsDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(Long userId, String dormitoryId, String explanation){
		Repairs repairs = new Repairs();
		repairs.setUserId(userId);
		repairs.setDormitoryId(Long.valueOf(dormitoryId));
		repairs.setExplanation(explanation);
		repairs.setReply("");
		repairs.setStatus(1);
		if (new RepairsDao().save(repairs)>0){
			return true;
		}
		return false;
	}

	public Boolean update(String id, String userId, String dormitoryId, String explanation, String reply){
		Repairs repairs = new Repairs();
		repairs.setId(Long.valueOf(id));
		repairs.setUserId(Long.valueOf(userId));
		repairs.setDormitoryId(Long.valueOf(dormitoryId));
		repairs.setExplanation(explanation);
		repairs.setReply(reply);
		repairs.setStatus(2);
		if (new RepairsDao().update(repairs)>0){
			return true;
		}
		return false;
	}
}