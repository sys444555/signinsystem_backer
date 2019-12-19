package com.hc.modules.student.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.student.entity.StudentEntity;
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
public interface StudentService extends IService<StudentEntity> {


    public void insertStudent(StudentEntity studentEntity, Integer cid);

}

