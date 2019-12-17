package com.hc.modules.classStudent.service;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.classStudent.entity.ClassEntity;
import com.hc.modules.member.entity.MemberEntity;

import java.util.List;


/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface ClassService extends IService<ClassEntity> {

    public List<ClassEntity> getClassList(String token);
}

