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

import com.wjysushe.bean.Examination;
import com.wjysushe.bean.Repairs;
import com.wjysushe.dao.ExaminationDao;
import com.wjysushe.dao.RepairsDao;

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
public class ExaminationService {

	public Examination get(String id){
		String where = " and repairs.id="+id;
		List<Examination> examinationList = new ExaminationDao().list(where);
		if (examinationList.size()>0){
			return examinationList.get(0);
		}
		return null;
	}

	public List<Examination> list(String apartmentId, Long dormitoryId){
		String where = "";
		if (notEmpty(apartmentId)){
			where += " and apartment.id=" + apartmentId + " ";
		}
		if (dormitoryId != null){
			where += " and dormitory.id=" + dormitoryId + " ";
		}
		return new ExaminationDao().list(where);
	}


	public Boolean delete(String id){
		if (new ExaminationDao().delete("and id = " + id)>0){
			return true;
		}
		return false;
	}

	public Boolean add(String examinationDate, String dormitoryId, String peopleNumber, String realNumber, String studentNames){
		Examination examination = new Examination();
		examination.setExaminationDate(examinationDate);
		examination.setDormitoryId(Long.valueOf(dormitoryId));
		examination.setPeopleNumber(Long.valueOf(peopleNumber));
		examination.setRealNumber(Long.valueOf(realNumber));
		examination.setStudentNames(studentNames);
		if (new ExaminationDao().save(examination)>0){
			return true;
		}
		return false;
	}

	public Boolean update(String id, String examinationDate, String dormitoryId, String peopleNumber, String realNumber, String studentNames){
		Examination examination = new Examination();
		examination.setId(Long.valueOf(id));
		examination.setExaminationDate(examinationDate);
		examination.setDormitoryId(Long.valueOf(dormitoryId));
		examination.setPeopleNumber(Long.valueOf(peopleNumber));
		examination.setRealNumber(Long.valueOf(realNumber));
		examination.setStudentNames(studentNames);
		if (new ExaminationDao().update(examination)>0){
			return true;
		}
		return false;
	}
}