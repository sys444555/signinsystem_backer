package com.hc;

import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.CoursePackageService;
import com.hc.modules.student.service.StudentService;
import com.hc.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SigninsystemApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;

    @Resource
    private LessonService lessonService;

    @Resource
    private CoursePackageService coursePackageService;

    @Resource
    private TeacherService teacherService;

    @Test
    void contextLoads() throws ParseException {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setClassHour(new BigDecimal(2.0));
        lessonEntity.setClassId(1);
        lessonEntity.setName("hello");
       lessonService.insertLesson(lessonEntity,"2019-12-13 01:01:01 - 2019-12-13 02:02:06",1,2 );

    }
    @Test
    void t1() {
        CoursePackageEntity coursePackageEntity = new CoursePackageEntity();
        coursePackageEntity.setClassPackage("跆拳道包");

        coursePackageService.createCoursePackage(coursePackageEntity);

    }

    @Test
    void te() throws ParseException {

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(1);
        lessonEntity.setClassHour(new BigDecimal(2.0));

        LessonEntity lesson = lessonService.getLesson(1);
        System.out.println("lesson = " + lesson);
        List<StudentEntity> classLessonList = lessonService.getLessonStudentList(1);
        System.out.println("classLessonList = " + classLessonList);

    }

}
