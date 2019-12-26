package com.hc.modules.business.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.business.mapper.BusinessMapper;
import com.hc.modules.business.service.BusinessService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.mapper.CourseMapper;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.student.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/12/26 18:17
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, BusinessEntity> implements BusinessService {

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private JWTUtil jwtUtil;
    @Override
    public List<BusinessEntity> getBusinessList(String token) {
        String username = jwtUtil.getUsername(token);
        List<BusinessEntity> businessEntityList = businessMapper.getBusinessList(username);

        return businessEntityList;
    }

    @Override
    public void insertBusiness(BusinessEntity businessEntity) {
        Integer result = businessMapper.insertUser(businessEntity);
        if(result == null || result == 0){
            throw new JcException("新增加盟商失败");
        }
        businessEntity.setUserId(businessEntity.getId());
        businessMapper.insertBusiness(businessEntity);
    }
}
