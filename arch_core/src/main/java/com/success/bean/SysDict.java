package com.success.bean;

import java.util.Date;


public class SysDict {
    /**
     * 自动编号
     */
    private Integer id;

    /**
     * 分类
     */
    private String category;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 获取自动编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自动编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置分类
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * 获取字典名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取字典值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置字典值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
