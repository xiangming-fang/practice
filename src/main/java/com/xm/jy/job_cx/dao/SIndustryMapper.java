package com.xm.jy.job_cx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.jy.job_cx.model.SIndustry;

public interface SIndustryMapper extends BaseMapper<SIndustry> {
    int deleteByPrimaryKey(Long id);

    int insert(SIndustry record);

    int insertSelective(SIndustry record);

    SIndustry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SIndustry record);

    int updateByPrimaryKey(SIndustry record);
}