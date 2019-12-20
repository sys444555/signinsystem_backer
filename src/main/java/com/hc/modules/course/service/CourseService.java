package com.hc.modules.course.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;

import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface CourseService extends IService<CourseEntity> {


    public List<CourseEntity> getCourseList(String token);

    public void insertCourse(CourseEntity courseEntity);

}

