package com.hc.modules.classStudent.service.impl;

import com.hc.common.utils.JWTUtil;
import com.hc.modules.classStudent.entity.ClassListVO;
import com.hc.modules.classStudent.mapper.ClassMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.modules.classStudent.entity.ClassEntity;
import com.hc.modules.classStudent.service.ClassService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, ClassEntity> implements ClassService {

    @Resource
    private ClassMapper classMapper;

    @Resource
    private JWTUtil jwtUtil;


    @Override
    public List<ClassListVO> getClassList(String token) {
        String username = jwtUtil.getUsername(token);
        List<ClassListVO> classList = classMapper.getClassList(username);
        return classList;
    }
}
