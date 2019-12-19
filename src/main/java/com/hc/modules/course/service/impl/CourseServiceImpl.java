package com.hc.modules.course.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.modules.course.mapper.CourseMapper;
import com.hc.modules.course.entity.CourseEntity;

import com.hc.modules.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseEntity> implements CourseService {

    @Resource
    private CourseMapper courseMapper;


    @Override
    public List<CourseEntity> getClassCourseList(Integer cid) {
        List<CourseEntity> classCourseList = courseMapper.getClassCourseList(cid);
        return classCourseList;
    }
}
