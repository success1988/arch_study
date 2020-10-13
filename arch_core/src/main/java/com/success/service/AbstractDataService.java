package com.success.service;


import com.success.mapper.BaseMapper;

import java.util.List;

public abstract class AbstractDataService<K, T> implements BaseDataService<K, T> {
    public AbstractDataService() {
    }

    protected abstract BaseMapper<K, T> getBaseMapper();

    public T selectByPrimaryKey(K k) {
        return this.getBaseMapper().selectByPrimaryKey(k);
    }

    public List<T> selectList(T t) {
        return this.getBaseMapper().selectList(t);
    }

    public int selectCount(T t) {
        return this.getBaseMapper().selectCount(t);
    }

    public int deleteByPrimaryKey(K k) {
        return this.getBaseMapper().deleteByPrimaryKey(k);
    }

    public int insert(T t) {
        return this.getBaseMapper().insert(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        return this.getBaseMapper().updateByPrimaryKeySelective(t);
    }
}
