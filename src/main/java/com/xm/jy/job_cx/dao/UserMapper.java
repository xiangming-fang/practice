package com.xm.jy.job_cx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.jy.job_cx.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: albert.fang
 * @date: 2020/4/24 16:17
 * @description: User类dao层接口
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByNameAndPassword(@Param(value = "userName") String userName,
                                  @Param(value = "password") String password);

    void userRegister(User user);

    User getUserByName(@Param(value = "userName") String userName);
}
