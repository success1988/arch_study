package com.success.service.impl;

import com.success.bean.SysDict;
import com.success.mapper.SysDictMapper;
import com.success.service.SysDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

    public List<SysDict> selectAll() {
        return sysDictMapper.selectListByCondition(new SysDict());
    }

    public List<SysDict> selectByCategory(String category) {
        SysDict sysDict = new SysDict();
        sysDict.setCategory(category);
        return sysDictMapper.selectListByCondition(sysDict);
    }

    public int saveSysDict(SysDict sysDict) {
        sysDict.setCreateTime(new Date());
        return sysDictMapper.insert(sysDict);
    }

    public int updateSysDict(SysDict sysDict) {
        return sysDictMapper.updateByPrimaryKeySelective(sysDict);
    }

    public int deleteSysDict(Integer id) {
        return sysDictMapper.deleteByPrimaryKey(id);
    }

    public SysDict selectById(Integer id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }
}
