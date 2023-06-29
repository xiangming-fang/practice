package com.xm.jy.xhz.service.impl;

import com.xm.jy.xhz.pojo.Test;
import com.xm.jy.xhz.mapper.TestMapper;
import com.xm.jy.xhz.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fxm
 * @since 2023-06-29
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
