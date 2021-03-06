package com.hc.modules.business.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.business.service.BusinessService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * 查询课程列表
     */
    @RequestMapping(value = "/business/list",method = RequestMethod.GET)
    public ResponseUtil getCourseList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<BusinessEntity> businessList = businessService.getBusinessList(token);
        PageInfo<BusinessEntity> pageInfo = new PageInfo<>(businessList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 新增加盟商
     */
    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public ResponseUtil createCourse(BusinessEntity businessEntity){
        businessService.insertBusiness(businessEntity);
        return ResponseUtil.success();
    }

    /**
     * 删除加盟商
     */
    @RequestMapping(value = "/business/delete/{businessId}", method = RequestMethod.POST)
    public ResponseUtil deleteStudent(@PathVariable(value = "businessId") Integer businessId){
        businessService.deleteBusiness(businessId);
        return ResponseUtil.success();
    }

    /**
     * 增加购买短信量
     */
    @RequestMapping(value = "/business/add/msnNumber", method = RequestMethod.POST)
    public ResponseUtil addMsnNumber(Integer businessId, Integer msnNumber){
        businessService.addMsnNumber(businessId, msnNumber);
        return ResponseUtil.success();
    }

    /**
     * 查看用户信息
     */
    @RequestMapping(value = "/business/user/get",method = RequestMethod.GET)
    public ResponseUtil getBusiness(HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        BusinessEntity businessEntity = businessService.getBusiness(token);

        return ResponseUtil.success(businessEntity);
    }

}
