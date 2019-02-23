/**
 * Copyright (C), 2015-2019, 虹软
 * FileName: User
 * Author:   yuan
 * Date:     2019/2/20 12:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * yuan           修改时间           版本号              描述
 */
package com.wjysushe.bean;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ly6333
 * @create 2019/2/20
 * @since 1.0.0
 */
public class User {

    private Long id;

    private String sno;

    private String name;

    private String password;

    private Integer role;

    private String roleName;


    private Date createTime;

    private Date updateTime;

    private Long dormitoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.getId().equals(this.getId());
    }
}