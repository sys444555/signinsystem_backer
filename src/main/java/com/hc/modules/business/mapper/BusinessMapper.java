package com.hc.modules.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.course.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface BusinessMapper extends BaseMapper<BusinessEntity> {


    public List<BusinessEntity> getBusinessList(@Param(value = "username") String username);

    public Integer insertUser(BusinessEntity businessEntity);

    public Integer  insertBusiness(BusinessEntity businessEntity);

    public Integer deleteBusiness(@Param(value = "businessId")Integer businessId);

    public Integer addMsnNumber(@Param(value = "businessId")Integer businessId,
                                @Param(value = "msnNumber")Integer msnNumber);

    public BusinessEntity getBusiness(@Param(value = "username") String  username);
}
