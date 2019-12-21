package com.hc;

import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.StudentService;
import com.hc.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class SigninsystemApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;

    @Resource
    private LessonService lessonService;

    @Resource
    private TeacherService teacherService;

    @Test
    void contextLoads() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("a");
        Integer id = 1;
        studentService.insertStudent(studentEntity, 1);

    }
    @Test
    void t1() {
        teacherService.getClassList(1);

    }

    @Test
    void te(){


        lessonService.getClassLessonList(1);


    }

}
