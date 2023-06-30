package com.xm.jy.xhz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.jy.xhz.pojo.SingleTable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fxm
 * @since 2023-06-29
 */
public interface SingleTableMapper extends BaseMapper<SingleTable> {

    SingleTable selectByCusId(@Param("id") Long id);

}
