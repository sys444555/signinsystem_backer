package com.hc.modules.student.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.student.entity.StudentEntity;
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
public interface StudentMapper extends BaseMapper<StudentEntity> {



    public Integer createStudent(StudentEntity studentEntity);

    public Integer linkClassStudent(@Param(value = "sid") Integer sid,
                                    @Param(value = "cid") Integer cid);

    public StudentEntity getStudentById(@Param(value = "sid") Integer sid);

}
