/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: Dormitory
 * Author:   yuan
 * Date:     2019/2/21 9:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.bean;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/21
 * @since 1.0.0
 */
public class Dormitory {

    private Long id;

    private Long apartmentId;

    private String apartmentName;

    private String name;

    private String member;

    private Date createTime;

    private Date lastUpdateTime;

    private List<User> memberUser;

    public List<User> getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(List<User> memberUser) {
        this.memberUser = memberUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}