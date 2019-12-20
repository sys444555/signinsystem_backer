package com.hc.modules.course.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.mapper.CourseMapper;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.student.service.StudentService;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseEntity> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Override
    public List<CourseEntity> getCourseList(String token) {
        String username = jwtUtil.getUsername(token);
        List<CourseEntity> courseList = courseMapper.getCourseList(username);
        return courseList;
    }


    @Override
    public void insertCourse(CourseEntity courseEntity, String token) {
        String username = jwtUtil.getUsername(token);
        TeacherEntity t = courseMapper.getT(username);
        courseEntity.setTId(t.getId());
        Integer integer = courseMapper.insertCourse(courseEntity);
        if(integer == null || integer == 0){
            throw new JcException("新增课程失败");
        }
    }
}
