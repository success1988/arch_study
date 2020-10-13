package com.success.service;


import java.util.List;

/**
 * @Title：基础操作接口
 */
public interface BaseDataService<K, T> {

    /**
     * 通过主键查询Entity
     */
    T selectByPrimaryKey(K k);

    /**
     * 返回查询列表
     */
    List<T> selectList(T t);

    /**
     * 返回查询记录数
     */
    int selectCount(T t);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(K k);

    /**
     * 新增记录
     */
    int insert(T t);

    /**
     * 更新记录
     */
    int updateByPrimaryKeySelective(T t);
}
