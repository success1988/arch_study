package com.success.mapper;

import com.success.bean.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysDictMapper {

    public List<SysDict> selectListByCondition(SysDict sysDict);


    public SysDict selectByPrimaryKey(@Param("id") Integer id);


    public int insert(SysDict sysDict);


    public int updateByPrimaryKeySelective(SysDict sysDict);


    public int deleteByPrimaryKey(@Param("id") Integer id);

}
