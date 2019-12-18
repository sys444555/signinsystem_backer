package com.hc.modules.member.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.member.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper extends BaseMapper<StudentEntity> {

    public List<StudentEntity> getStudentList(@Param(value = "username") String username);

    public Integer insertStudent(StudentEntity studentEntity);
}
