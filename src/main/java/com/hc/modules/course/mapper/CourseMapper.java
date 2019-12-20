package com.hc.modules.course.mapper;

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
public interface CourseMapper extends BaseMapper<CourseEntity> {

    public List<CourseEntity> getCourseList(@Param("username") String username);

    public Integer insertCourse(CourseEntity courseEntity
                                );

    public TeacherEntity getT(@Param("username") String username);
}
