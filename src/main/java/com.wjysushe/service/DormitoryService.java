/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: DormitoryService
 * Author:   yuan
 * Date:     2019/2/21 9:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.service;

import com.wjysushe.bean.Dormitory;
import com.wjysushe.dao.DormitoryDao;
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
public class DormitoryService {


	public Dormitory get(String id){
		String where = "and dormitory.id="+id;
		List<Dormitory> dormitoryList = new DormitoryDao().list(where);
		if (dormitoryList.size()>0){
			return dormitoryList.get(0);
		}
		return null;
	}

	public Long getDormitoryIdByUserId(Long userId){
		return new DormitoryDao().getDormitoryIdByUserId(userId);
	}

	public List<Dormitory> list(String apartmentId, String name){
		String where = "";
		if (notEmpty(apartmentId)){
			where += " and dormitory.apartment_id =" + apartmentId;
		}
		if (notEmpty(name)){
			where += " and dormitory.name like '%" + name + "%' ";
		}
		List<Dormitory> dormitoryList = new DormitoryDao().list(where);
		return dormitoryList;
	}

	public Boolean delete(String id){
		if (new DormitoryDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(String apartmentId, String name, String studentIds){
		Dormitory dormitory = new Dormitory();
		dormitory.setApartmentId(Long.valueOf(apartmentId));
		dormitory.setName(name);
		if (new DormitoryDao().save(dormitory)>0){
			dormitory = new DormitoryDao().list("and dormitory.apartment_id=" + apartmentId + " and dormitory.name="+name).get(0);
			UserDao userDao = new UserDao();
			String[] strings = studentIds.split(",");
			for (String item : strings) {
				userDao.saveDormitoryMember(dormitory.getId(), item);
			}
			return true;
		}
		return false;
	}

	public Boolean update(String id, String apartmentId, String name, String studentIds){
		Dormitory dormitory = new Dormitory();
		dormitory.setId(Long.valueOf(id));
		dormitory.setApartmentId(Long.valueOf(apartmentId));
		dormitory.setName(name);
		if (new DormitoryDao().update(dormitory)>0){
			UserDao userDao = new UserDao();
			userDao.deleteDormitoryMember(dormitory.getId());
			String[] strings = studentIds.split(",");
			for (String item : strings) {
				userDao.saveDormitoryMember(dormitory.getId(), item);
			}
			return true;
		}
		return false;
	}

}