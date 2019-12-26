package com.hc;

import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.business.service.BusinessService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.entity.StudentLessonEntity;
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

    @Resource
    private BusinessService businessService;

    @Test
    void contextLoads() throws ParseException {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setClassHour(new BigDecimal(2.0));
        lessonEntity.setClassId(1);
        lessonEntity.setName("hello");

    }
    @Test
    void t1() {
        CoursePackageEntity coursePackageEntity = new CoursePackageEntity();
        coursePackageEntity.setClassPackage("跆拳道包");



    }

    @Test
    void te() throws ParseException {
        businessService.deleteBusiness(8);

    }

}
