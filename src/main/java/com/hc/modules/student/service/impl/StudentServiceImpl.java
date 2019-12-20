package com.hc.modules.student.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Resource
    private StudentMapper studentMapper;


    @Override
    public void insertStudent(StudentEntity studentEntity, Integer cid) {
        Integer result = studentMapper.insertStudent(studentEntity);
        if(result == null || result == 0){
            throw new JcException("新增学员失败");
        }
        Integer id = studentEntity.getId();
        studentMapper.linkClassStudent(id, cid);
    }


}
