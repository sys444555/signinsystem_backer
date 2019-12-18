package com.hc.modules.teacher.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface TeacherMapper extends BaseMapper<TeacherEntity> {

    public List<ClassEntity> getClassList(@Param(value = "username") String username);

    public List<TeacherEntity> getTeacherList();

}