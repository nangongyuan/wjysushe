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
import com.wjysushe.bean.User;
import com.wjysushe.dao.ApartmentDao;
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
public class ApartmentService {

	public Apartment get(String id){
		String where = "and apartment.id="+id;
		List<Apartment> apartmentList = new ApartmentDao().list(where);
		if (apartmentList.size()>0){
			return apartmentList.get(0);
		}
		return null;
	}

	public List<Apartment> list(String name){
		String where = "";
		if (notEmpty(name)){
			where += " and apartment.name like '%" + name + "%' ";
		}
		return new ApartmentDao().list(where);
	}


	public Boolean delete(String id){
		if (new ApartmentDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(String managerId, String name){
		Apartment apartment = new Apartment();
		apartment.setManagerId(Long.valueOf(managerId));
		apartment.setName(name);
		if (new ApartmentDao().save(apartment)>0){
			return true;
		}
		return false;
	}

	public Boolean update(String id, String managerId, String name){
		Apartment apartment = new Apartment();
		apartment.setId(Long.valueOf(id));
		apartment.setManagerId(Long.valueOf(managerId));
		apartment.setName(name);
		if (new ApartmentDao().update(apartment)>0){
			return true;
		}
		return false;
	}

}