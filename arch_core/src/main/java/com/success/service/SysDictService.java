package com.success.service;

import com.success.bean.SysDict;

import java.util.List;

public interface SysDictService {
    public List<SysDict> selectAll();

    public List<SysDict> selectByCategory(String category);

    public int saveSysDict(SysDict sysDict);

    public int updateSysDict(SysDict sysDict);

    public int deleteSysDict(Integer id);

    public SysDict selectById(Integer id);
}
