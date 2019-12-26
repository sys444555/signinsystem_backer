package com.hc.modules.lesson.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.entity.StudentLessonEntity;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface LessonService extends IService<LessonEntity> {


    public List<LessonEntity> getClassLessonList(Integer cid);

    public void insertLesson(LessonEntity lessonEntity, String dataRange,String timeRange, Integer period, Integer times) throws ParseException;

    public void insertLessonStudents(Integer coid, String[] studentList);

    public void lessonSign(Integer lessonId, Integer studentId, String token) throws ParseException;

    public LessonEntity getLesson( Integer lessonId);

    public List<StudentLessonEntity> getLessonStudentList(Integer lessonId);

    public void removeLessonStudent(Integer studentId, Integer lessonId);

    public List<StudentEntity> getLessonAbsentStudentList(Integer lessonId, Integer classId);

}

