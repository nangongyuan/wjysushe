/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WaterElectricity
 * Author:   Administrator
 * Date:     2019/2/22 0022 19:31
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
public class WaterElectricity {

	private Long id;

	private Long dormitoryId;

	private String dormitoryName;

	private String apartmentName;

	private String statDate;

	private Float waterNum;

	private Float waterCost;

	private Float electricityNum;

	private Float electricityCost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDormitoryId() {
		return dormitoryId;
	}

	public void setDormitoryId(Long dormitoryId) {
		this.dormitoryId = dormitoryId;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public Float getWaterNum() {
		return waterNum;
	}

	public void setWaterNum(Float waterNum) {
		this.waterNum = waterNum;
	}

	public Float getWaterCost() {
		return waterCost;
	}

	public void setWaterCost(Float waterCost) {
		this.waterCost = waterCost;
	}

	public Float getElectricityNum() {
		return electricityNum;
	}

	public void setElectricityNum(Float electricityNum) {
		this.electricityNum = electricityNum;
	}

	public Float getElectricityCost() {
		return electricityCost;
	}

	public void setElectricityCost(Float electricityCost) {
		this.electricityCost = electricityCost;
	}

	public String getDormitoryName() {
		return dormitoryName;
	}

	public void setDormitoryName(String dormitoryName) {
		this.dormitoryName = dormitoryName;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
}