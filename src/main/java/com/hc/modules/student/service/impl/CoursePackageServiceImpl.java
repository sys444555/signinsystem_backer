package com.hc.modules.student.service.impl;

import com.hc.common.exception.JcException;
import com.hc.modules.student.mapper.CoursePackageMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.service.CoursePackageService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoursePackageServiceImpl extends ServiceImpl<CoursePackageMapper, CoursePackageEntity> implements CoursePackageService {

    @Resource
    private CoursePackageMapper coursePackageMapper;





    public List<CoursePackageEntity> coursePackageList(Integer studentId){

           return  coursePackageMapper.coursePackageList(studentId);


    }

    @Override
    public void createCoursePackage(CoursePackageEntity coursePackageEntity) {

        Integer result = coursePackageMapper.createCoursePackage(coursePackageEntity);
        if(result == null || result == 0){
            throw new JcException("添加课时包失败");
        }


    }


}