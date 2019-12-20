package com.hc.modules.teacher.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import com.hc.modules.teacher.mapper.TeacherMapper;
import com.hc.modules.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherEntity> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    @Resource
    private JWTUtil jwtUtil;

    @Override
    public List<ClassEntity> getClassList(Integer cId) {
        List<ClassEntity> classList = teacherMapper.getClassList(cId);
        return classList;
    }


    @Override
    public List<TeacherEntity> getTeacherList() {
        List<TeacherEntity> teacherList = teacherMapper.getTeacherList();
        return teacherList;
    }

    @Override
    public void insertClass(ClassEntity classEntity) {
        Integer integer = teacherMapper.insertClass(classEntity);
        if(integer == null || integer == 0){
            throw new JcException("新增班级失败");
        }

    }

    @Override
    public void updateClassStatus(Integer status, Integer id) {
        Integer integer = teacherMapper.updateClassStatus(status, id);
        if(integer == null || integer == 0){
            throw new JcException("修改班级状态失败");
        }
    }

    @Override
    public List<StudentEntity> getClassStudentById(Integer id) {
        List<StudentEntity> studentList = teacherMapper.getClassStudentById(id);
        return studentList;
    }

    @Override
    public List<CourseEntity> getClassCourseById(Integer id) {
        List<CourseEntity> courseList = teacherMapper.getCourseById(id);
        return courseList;
    }
}
