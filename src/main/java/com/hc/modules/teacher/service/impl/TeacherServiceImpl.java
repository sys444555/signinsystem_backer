package com.hc.modules.teacher.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import com.hc.modules.teacher.mapper.TeacherMapper;
import com.hc.modules.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherEntity> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Override
    public List<ClassEntity> getClassList(String token) {
        String username = jwtUtil.getUsername(token);
        Integer userId = studentMapper.getUserId(username);
        List<ClassEntity> classList = teacherMapper.getClassList(userId);
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

    @Override
    public void insertClassStudents(Integer classId, String[] studentList) {
        if(studentList != null){
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<studentList.length;i++){
                Integer integer = Integer.valueOf(studentList[i]);
                list.add(integer);
            }
            teacherMapper.insertClassStudents(classId, list);
        }
    }

    @Override
    public List<StudentEntity> getClassAbsentStudentList(Integer classId, String token) {
        String username = jwtUtil.getUsername(token);
        Integer userId = studentMapper.getUserId(username);
        List<StudentEntity> studentEntityList = teacherMapper.getClassAbsentStudentList(classId, userId);
        return studentEntityList;
    }

    @Override
    public void deleteClass(Integer classId) {
        Integer result = teacherMapper.deleteClass(classId);
        if(result == null || result == 0){
            throw new JcException(999, "删除班级失败");
        }
    }
}
