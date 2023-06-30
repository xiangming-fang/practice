package com.xm.jy.job_cx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.jy.job_cx.model.STeamDimensionScore;

public interface STeamDimensionScoreMapper extends BaseMapper<STeamDimensionScore> {
    int deleteByPrimaryKey(Long id);

    int insert(STeamDimensionScore record);

    int insertSelective(STeamDimensionScore record);

    STeamDimensionScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(STeamDimensionScore record);

    int updateByPrimaryKey(STeamDimensionScore record);
}