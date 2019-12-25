package com.hc.modules.student.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.course.mapper.CourseMapper;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.student.service.StudentService;
import com.hc.modules.teacher.entity.TeacherEntity;
import com.hc.modules.teacher.mapper.TeacherMapper;
import com.hc.modules.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public void createStudent(StudentEntity studentEntity, Integer cid) {
        Integer result = studentMapper.createStudent(studentEntity);
        if(result == null || result == 0){
            throw new JcException("新增学员失败");
        }
        Integer id = studentEntity.getId();
        studentMapper.linkClassStudent(id, cid);
    }

    @Override
    public StudentEntity getStudentById(Integer sid) {

        StudentEntity studentEntity = studentMapper.getStudentById(sid);

        return studentEntity;
    }

    @Override
    public void insertStudent(StudentEntity studentEntity, String token) {
        String username = jwtUtil.getUsername(token);
        TeacherEntity t = courseMapper.getT(username);
        if(t != null){
            studentEntity.setTeacherId(t.getId());
            Integer result = studentMapper.createStudent(studentEntity);
            if(result == null || result == 0){
                throw new JcException("新增学员失败");
            }
        }else {
            throw new JcException(999,"服务器端数据异常");
        }

    }

    @Override
    public List<StudentEntity> selectStudentList(String token) {

        String username = jwtUtil.getUsername(token);
        TeacherEntity t = courseMapper.getT(username);
        List<StudentEntity> studentEntityList;
        System.out.println("t = " + t);
        if(t != null){
            studentEntityList = studentMapper.selectStudentList(t.getId());
            System.out.println("studentEntityList = " + studentEntityList);
        }else {
            throw new JcException(999,"服务器端数据异常");
        }
        return studentEntityList;
    }


}
