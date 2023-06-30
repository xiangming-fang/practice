package com.xm.jy.job_cx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.jy.job_cx.model.ProductCategory;
import com.xm.jy.job_cx.model.TypeOrId;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @auther 方翔鸣
 * @date 2020/3/8 17:37
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    ProductCategory selectByCategoryType(Integer categoryType);
    ProductCategory selectOne(@Param("categoryId") Integer categoryId, @Param("categoryType") Integer categoryType);
    ProductCategory selectOneByObject(TypeOrId typeOrId);

    ProductCategory selectOneByHashMap(HashMap<String, Integer> hashMap);

    List<ProductCategory> selectAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 得到总记录数
     * @return
     */
    Integer count();
}
