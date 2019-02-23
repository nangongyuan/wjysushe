/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Examination
 * Author:   Administrator
 * Date:     2019/2/22 0022 23:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wjysushe.bean;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/22 0022
 * @since 1.0.0
 */
public class Examination {

	private Long id;

	private String apartmentName;

	private Long dormitoryId;

	private String dormitoryName;

	private String examinationDate;

	private Long peopleNumber;

	private Long realNumber;

	private String studentNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public Long getDormitoryId() {
		return dormitoryId;
	}

	public void setDormitoryId(Long dormitoryId) {
		this.dormitoryId = dormitoryId;
	}

	public String getDormitoryName() {
		return dormitoryName;
	}

	public void setDormitoryName(String dormitoryName) {
		this.dormitoryName = dormitoryName;
	}

	public String getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(String examinationDate) {
		this.examinationDate = examinationDate;
	}

	public Long getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(Long peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public Long getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(Long realNumber) {
		this.realNumber = realNumber;
	}

	public String getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(String studentNames) {
		this.studentNames = studentNames;
	}
}