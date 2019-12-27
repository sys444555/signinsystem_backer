package com.hc.modules.student.service;

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
public interface StudentService extends IService<StudentEntity> {


    public void createStudent(StudentEntity studentEntity, Integer cid);

    public StudentEntity getStudentById(Integer sid);

    public void insertStudent(StudentEntity studentEntity, String token);

    public List<StudentEntity> selectStudentList(String token);

    public List<StudentEntity> getStudentCoursePackageList(Integer studentId);

    public void deleteStudent(Integer studentId);

    public void updateStudent(StudentEntity studentEntity);
}

