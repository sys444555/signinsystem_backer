package com.hc.modules.teacher.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.utils.JWTUtil;
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
    public List<ClassEntity> getClassList(String token) {
        String username = jwtUtil.getUsername(token);
        List<ClassEntity> classList = teacherMapper.getClassList(username);
        return classList;
    }


    @Override
    public List<TeacherEntity> getTeacherList() {
        List<TeacherEntity> teacherList = teacherMapper.getTeacherList();
        return teacherList;
    }
}
