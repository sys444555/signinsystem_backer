package com.hc.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.member.entity.StudentEntity;
import com.hc.modules.member.mapper.StudentMapper;
import com.hc.modules.member.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Resource
    private StudentMapper memberMapper;

    @Resource
    private JWTUtil jwtUtil;


    @Override
    public List<StudentEntity> getStudentList(String token) {
        String username = jwtUtil.getUsername(token);
        List<StudentEntity> memberList = memberMapper.getStudentList(username);

        return memberList;
    }

    @Override
    public void insertStudent(StudentEntity memberEntity) {
        Integer result = memberMapper.insert(memberEntity);
        if(result == null || result ==0){
            throw new JcException("新增学员失败");
        }

    }


}
