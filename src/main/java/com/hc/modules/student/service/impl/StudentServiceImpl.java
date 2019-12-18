package com.hc.modules.student.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private JWTUtil jwtUtil;


    @Override
    public List<StudentEntity> getStudentList(String token) {
        String username = jwtUtil.getUsername(token);
        List<StudentEntity> studentList = studentMapper.getStudentList(username);

        return studentList;
    }

    @Override
    public void insertStudent(StudentEntity studentEntity) {
        Integer result = studentMapper.insert(studentEntity);
        if(result == null || result ==0){
            throw new JcException("新增学员失败");
        }

    }


}
