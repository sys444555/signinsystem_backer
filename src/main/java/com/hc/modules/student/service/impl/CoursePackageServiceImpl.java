package com.hc.modules.student.service.impl;

import com.hc.common.exception.JcException;
import com.hc.modules.student.mapper.CoursePackageMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.service.CoursePackageService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoursePackageServiceImpl extends ServiceImpl<CoursePackageMapper, CoursePackageEntity> implements CoursePackageService {

    @Resource
    private CoursePackageMapper coursePackageMapper;





    public Map<String, Object> coursePackageList(Integer studentId, Integer classId){
        Map<String, Object> map = new HashMap<>();
        map.put("coursePackageList", coursePackageMapper.coursePackageList(studentId));
        Integer cpid = coursePackageMapper.getCpid(studentId, classId);
        map.put("defaultUse", cpid);
        return  map;


    }

    @Override
    public void createCoursePackage(CoursePackageEntity coursePackageEntity) {

        Integer result = coursePackageMapper.createCoursePackage(coursePackageEntity);
        if(result == null || result == 0){
            throw new JcException("添加课时包失败");
        }


    }

    @Override
    public void setCoursePackage(Integer studentId, Integer classId, Integer cpid) {
        Integer cpid1 = coursePackageMapper.getCpid(studentId, classId);
        if(cpid1 != null){
            coursePackageMapper.updateCoursePackage(studentId, classId, cpid);
        }else{
            Integer result = coursePackageMapper.setCoursePackage(studentId, classId, cpid);
            if(result == null || result == 0){
                throw new JcException("设置默认课时包失败");
            }
        }

    }


}
