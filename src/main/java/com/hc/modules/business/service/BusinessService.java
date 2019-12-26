package com.hc.modules.business.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.course.entity.CourseEntity;


import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface BusinessService extends IService<BusinessEntity> {


    public List<BusinessEntity> getBusinessList(String token);

    public void insertBusiness(BusinessEntity businessEntity);

    public void deleteBusiness(Integer businessId);

    public void addMsnNumber(Integer businessId, Integer msnNumber);
}

