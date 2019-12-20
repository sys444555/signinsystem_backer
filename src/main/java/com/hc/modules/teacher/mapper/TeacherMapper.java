package com.hc.modules.teacher.mapper;

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
public interface TeacherMapper extends BaseMapper<TeacherEntity> {

    public List<ClassEntity> getClassList();

    public List<TeacherEntity> getTeacherList();

    public Integer insertClass(ClassEntity classEntity);

    public Integer updateClassStatus(@Param(value = "id") Integer id,
                                     @Param(value = "status") Integer status);

    public List<StudentEntity> getClassStudentById(@Param(value = "id") Integer id);

    public List<CourseEntity> getCourseById(@Param(value = "id") Integer id);

}
