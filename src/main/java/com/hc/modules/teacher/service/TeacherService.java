package com.hc.modules.teacher.service;

import com.baomidou.mybatisplus.service.IService;
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
public interface TeacherService extends IService<TeacherEntity> {

    public List<ClassEntity> getClassList(String token);

    public List<TeacherEntity> getTeacherList();

    public void insertClass(ClassEntity classEntity);

    public void updateClassStatus(Integer status, Integer id);

    public List<StudentEntity> getClassStudentById(Integer id);

    public List<CourseEntity> getClassCourseById(Integer id);

    public void insertClassStudents(Integer classId, String[] studentList);

    public List<StudentEntity> getClassAbsentStudentList(Integer classId, String token);

    public void deleteClass(Integer classId);

    public ClassEntity getClassById(Integer classId);

    public void updateClass(ClassEntity classEntity);
}

