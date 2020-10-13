package com.success.mapper;


import java.util.List;


public interface BaseMapper<K, T> {

    public T selectByPrimaryKey(K k);

    public List<T> selectList(T t);

    public int selectCount(T t);

    public int deleteByPrimaryKey(K k);

    public int insert(T t);

    public int updateByPrimaryKeySelective(T t);
}
