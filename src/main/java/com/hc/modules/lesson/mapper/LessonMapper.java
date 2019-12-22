package com.hc.modules.lesson.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface LessonMapper extends BaseMapper<LessonEntity> {

    public List<LessonEntity> getClassLessonList(@Param(value = "cid") Integer cid);


    public Integer insertLesson(LessonEntity lessonEntity);

    public Integer insertLessonStudents(@Param(value = "coid")Integer coid,
                                        @Param(value = "list") List<Integer> list);


    public CoursePackageEntity findCoursePackage(@Param(value = "id") Integer id,
                                          @Param(value = "studentId")Integer studentId);

    public Integer updateCoursePackage(CoursePackageEntity coursePackageEntity);

    public LessonEntity getLesson(@Param(value = "lessonId") Integer lessonId);

    public List<StudentEntity> getLessonStudentList(@Param(value = "lessonId") Integer lessonId);

    public List<StudentEntity> lessonAbsentStudentList(@Param(value = "lessonId") Integer lessonId,
                                                       @Param(value = "classId") Integer classId);

    public Integer deleteLessonStudent(@Param(value = "lessonId") Integer lessonId);

    Integer removeLessonStudent(@Param(value = "studentId")Integer studentId,
                                @Param(value = "lessonId")Integer lessonId);

    public Integer lessonSign(@Param(value = "lessonId") Integer lessonId,
                              @Param(value = "classId") Integer classId);
}
