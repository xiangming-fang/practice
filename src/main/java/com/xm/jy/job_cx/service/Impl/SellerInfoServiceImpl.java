package com.xm.jy.job_cx.service.Impl;

import com.xm.jy.job_cx.model.Seller_Info;
import com.xm.jy.job_cx.repository.SellerInfoRepository;
import com.xm.jy.job_cx.service.SellerInfoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther 方翔鸣
 * @date 2020/3/8 17:01
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoSevice {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public Seller_Info getSellInfoById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Seller_Info getSellInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
